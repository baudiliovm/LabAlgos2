/**
 * Implementación de una tabla hash cuckoo
 * 
 * Soporta las operaciones de agregar, buscar y eliminar
 */
class CuckooHashTable() {

    private var valorI = 7
    private var tabla1 = Array(valorI) { CuckooHashTableEntry(-1, "") }
    private var tabla2 = Array(valorI) { CuckooHashTableEntry(-1, "") }
    private var elementos = 0
        
    /** 
     * Agrega un elemento a la tabla
     * 
     * @param clave la clave del elemento
     * @param valor el valor del elemento
     */
    fun agregar(clave: Int, valor: String): Boolean {
        var entrada = CuckooHashTableEntry(clave, valor)
        val index1 = hash1(clave)
        val index2 = hash2(clave)
        if ((1.0 * elementos) / valorI >= 0.7) rehash()
        
        if (buscar(clave) != null) {
            return true
        }
        elementos++
        val temp1 = tabla1[index1]
        tabla1[index1] = entrada
        entrada = temp1
        
        if (entrada.clave == -1) {
            elementos++
            return true
        }
        
        val temp2 = tabla2[index2]
        tabla2[index2] = entrada
        entrada = temp2

        if (entrada.clave == -1) {
            elementos++
            return true
        }
        if (entrada.clave != -1) {
            rehash()
            agregar(entrada.clave, entrada.valor)
        }
        return true
    }

    /** 
     * Busca un elemento en la tabla
     * 
     * @param clave la clave del elemento
     * @return el valor del elemento
     */
    fun buscar(clave: Int): String? {
        val index1 = hash1(clave)
        if (tabla1[index1].clave == clave) {
            return tabla1[index1].valor
        }

        val index2 = hash2(clave)
        if (tabla2[index2].clave == clave) {
            return tabla2[index2].valor
        }
        
        throw Exception("La clave $clave no existe")
    }

    /**
     * Elimina un elemento de la tabla
     * 
     * @param clave la clave del elemento
     */
    fun eliminar(clave: Int): Boolean {
        val index1 = hash1(clave)
        if (tabla1[index1].clave == clave) {
            tabla1[index1] = CuckooHashTableEntry(-1, "")
            elementos--
            return true
        }
        val index2 = hash2(clave)
        if (tabla2[index2].clave == clave) {
            tabla2[index2] = CuckooHashTableEntry(-1, "")
            elementos--
            return true
        }

        throw Exception("La clave $clave no existe")
    }

    /**
     * Regresa el número de elementos en la tabla
     * 
     * @return el número de elementos en la tabla
     */
    fun numElementos(): Int{
        return elementos
    }

    /** 
     * Metodo de la division para obtener el indice
     * 
     * @param key la clave del elemento
     * @return el indice del elemento
     */
    private fun hash1(key: Int): Int {
        return key % valorI
    }

    /** 
     * Metodo de la multiplicacion para obtener el indice
     * 
     * @param key la clave del elemento
     * @return el indice del elemento
     */
    private fun hash2(key: Int): Int {
        return (key * 0.6180339887).toInt() % valorI
    }

    /**
     * Rehashing de la tabla
     * 
     * Se crea una nueva tabla con un tamaño 3 veces el tamaño anterior + 16
     * dividido entre 2, y se reasignan los elementos de la tabla anterior 
     * a la nueva tabla.
     */
    private fun rehash() {
        val newtabla1 = Array(valorI * 2) { CuckooHashTableEntry(-1, "") }
        val newtabla2 = Array(valorI * 2) { CuckooHashTableEntry(-1, "") }

        for (i in 0 until valorI) {
            val entradaActual1 = tabla1[i]
            if (entradaActual1.clave != -1) {
                val index1 = hash1(entradaActual1.clave)
                newtabla1[index1] = entradaActual1
            }

            val entradaActual2 = tabla2[i]
            if (entradaActual2.clave != -1) {
                val index2 = hash2(entradaActual2.clave)
                newtabla2[index2] = entradaActual2
            }
        }

        tabla1 = newtabla1
        tabla2 = newtabla2
        valorI = valorI * 2
    }

    /** 
     * Representación en String de la tabla
     * 
     * @return la representación en String de la tabla
     */
    override fun toString(): String {
        val builder = StringBuilder()
        builder.append("{")
        for (i in 0 until valorI) {
            var entrada = tabla1[i]
            if (entrada.clave != -1) {
                builder.append("${entrada.toString()} ")
            }
            entrada = tabla2[i]
            if (entrada.clave != -1) {
                builder.append("${entrada.toString()} ")
            }
        }
        builder.append("}")
        return builder.toString()
    }
}

