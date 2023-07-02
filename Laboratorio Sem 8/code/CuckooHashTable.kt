class CuckooHashTable() {
    
    
    private var valorI = 7
    private var tabla1 = Array(valorI) { CuckooHashTableEntry(-1, "") }
    private var tabla2 = Array(valorI) { CuckooHashTableEntry(-1, "") }
    private var elementos = 0
    

    
    
    fun agregar(key: Int, value: String): Boolean {
        if((1.0*elementos)/valorI >= 0.7){
            rehash()
        }
        var entrada = CuckooHashTableEntry(key, value)
        val index1 = hash1(key)
        val index2 = hash2(key)
        
        if(buscar(key) != null){
            return true
        }
        elementos++
        val temp1 = tabla1[index1]
        tabla1[index1] = entrada
        entrada = temp1
        
        if (entrada.key == -1) {
            return true
        }
        
        val temp2 = tabla2[index2]
        tabla2[index2] = entrada
        entrada = temp2

        if (entrada.key == -1) {
            return true
        }
        if (entrada.key != -1) {
            elementos--
            rehash()
            agregar(entrada.key,entrada.value)
        }
        return true
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
            elementos--
            return true
        }
        val index2 = hash2(key)
        if (tabla2[index2].key == key) {
            tabla2[index2] = CuckooHashTableEntry(-1, "")
            elementos--
            return true
        }

        return false
    }

    fun numElementos(): Int{
        return elementos
    }

    private fun hash1(key: Int): Int {
        return key % valorI
    }

    private fun hash2(key: Int): Int {
        return (key * 0.6180339887).toInt() % valorI
    }

    private fun rehash() {
        val newtabla1 = Array(valorI * 2) { CuckooHashTableEntry(-1, "") }
        val newtabla2 = Array(valorI * 2) { CuckooHashTableEntry(-1, "") }

        for (i in 0 until valorI) {
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
        valorI = valorI * 2
    }

    override fun toString(): String {
        val builder = StringBuilder()
        builder.append("{")
        for (i in 0 until valorI) {
            var entrada = tabla1[i]
            if (entrada.key != -1) {
                builder.append("${entrada.toString()} ")
            }
            entrada = tabla2[i]
            if (entrada.key != -1) {
                builder.append("${entrada.toString()} ")
            }
        }
        builder.append("}")
        return builder.toString()
    }
}

