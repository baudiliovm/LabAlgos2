class CuckooHashTable(var valorInicial: Int) {

    private var tabla1 = Array(valorInicial) { CuckooHashTableEntry(-1, "") }
    private var tabla2 = Array(valorInicial) { CuckooHashTableEntry(-1, "") }
    public var size = 0

    fun agregar(key: Int, value: String) {
        if (size / valorInicial >= 0.7) {
            rehash()
        }

        val index1 = hash1(key)
        val index2 = hash2(key)

        if (tabla1[index1].key == -1) {
            tabla1[index1] = CuckooHashTableEntry(key, value)
            size++
            return
        }

        if (tabla2[index2].key == -1) {
            tabla2[index2] = CuckooHashTableEntry(key, value)
            size++
            return
        }

        
        var tablaActual = tabla1
        var tablaProxima = tabla2

        while (true) {
            val entradaV = tablaActual[index1]

            
            tablaProxima[hash2(entradaV.key)] = entradaV

            
            val currentIndex = hash1(key)
            if (tablaActual[currentIndex].key == -1) {
                tablaActual[currentIndex] = CuckooHashTableEntry(key, value)
                size++
                return
            }

            
            tablaActual = tablaProxima
            tablaProxima = tablaActual
        }
    }

    fun buscar(key: Int): String? {
        val index1 = hash1(key)
        if (tabla1[index1].key == key) {
            return tabla1[index1].value
        }

        val index2 = hash2(key)
        if (tabla2[index2].key == key) {
            return tabla2[index2].value
        }

        return null
    }

    fun eliminar(key: Int): Boolean {
        val index1 = hash1(key)
        if (tabla1[index1].key == key) {
            tabla1[index1] = CuckooHashTableEntry(-1, "")
            return true
        }
        val index2 = hash2(key)
        if (tabla2[index2].key == key) {
            tabla2[index2] = CuckooHashTableEntry(-1, "")
            return true
        }

        return false
    }

    private fun hash1(key: Int): Int {
        return key % valorInicial
    }

    private fun hash2(key: Int): Int {
        return (key * 0.6180339887).toInt() % valorInicial
    }

    private fun rehash() {
        val newSize = valorInicial * 2
        val newtabla1 = Array(newSize) { CuckooHashTableEntry(-1, "") }
        val newtabla2 = Array(newSize) { CuckooHashTableEntry(-1, "") }

        for (i in 0 until valorInicial) {
            val entradaActual1 = tabla1[i]
            if (entradaActual1.key != -1) {
                val index1 = hash1(entradaActual1.key)
                newtabla1[index1] = entradaActual1
            }

            val entradaActual2 = tabla2[i]
            if (entradaActual2.key != -1) {
                val index2 = hash2(entradaActual2.key)
                newtabla2[index2] = entradaActual2
            }
        }

        tabla1 = newtabla1
        tabla2 = newtabla2
        valorInicial = newSize
    }

    override fun toString(): String {
        val builder = StringBuilder()
        builder.append("CuckooHashTable {\n")
        for (i in 0 until valorInicial) {
            var entrada = tabla1[i]
            if (entrada.key != -1) {
                builder.append("${entrada.toString()}\n")
            }

            entrada = tabla2[i]
            if (entrada.key != -1) {
                builder.append("  tabla2[${i}] = ${entrada.toString()}\n")
            }
        }
        builder.append("}")
        return builder.toString()
    }
}

