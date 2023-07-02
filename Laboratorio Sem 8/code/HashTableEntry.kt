class HashTable(tamano: Int) {

    private var tabla: Array<HashTableEntry?> = arrayOfNulls(7)
    private var tamano: Int = tamano
    private var factorDeCarga: Double = 0.0
    factorDeCarga = elementos / tamano

    private var elementos: Int = 0
    
    private fun hash(key: Int) = key % tamano
   
    private fun rehash() {

        val nuevoTamano = (3 * (tamano + 16)) / 2
        val tablaCopia = tabla

        tabla = arrayOfNulls(nuevoTamano)

        for (i in tablaCopia) {
            if (i != null) {
                val hash = hash(i.clave)
                tabla[hash] = i
            }
        }
    }

    fun agregar(key: Int, value: String) {

        val hash = hash(key)
        val nuevoElemento = HashTableEntry(key, value)

        if (tabla[hash] == null) {
            tabla[hash] = nuevoElemento
        } else {
            var actual = tabla[hash]
            while (actual!!.siguiente != null) {
                actual = actual.siguiente
            }
            actual.siguiente = nuevoElemento
            nuevoElemento.anterior = actual
        }
        elementos++
        factorDeCarga = elementos.toDouble() / tamano.toDouble()

        if (factorDeCarga >= 0.7) rehash()
    }

    fun eliminar(key: Int) {

        val hash = hash(key)
        var actual = tabla[hash]

        if (actual != null) {
            if (actual.clave == key) {
                tabla[hash] = actual.siguiente
                actual.siguiente?.anterior = null
            } else {
                while (actual!!.siguiente != null && actual.siguiente!!.clave != key) {
                    actual = actual.siguiente
                }
                if (actual.siguiente != null) {
                    actual.siguiente = actual.siguiente!!.siguiente
                    actual.siguiente?.anterior = actual
                }
            }
            elementos--
            factorDeCarga = elementos.toDouble() / tamano.toDouble()
        }
    }

    fun buscar(key: Int): String? {

        val hash = hash(key)
        var actual = tabla[hash]

        if (actual != null) {
            if (actual.clave == key) {
                return actual.valor
            } else {
                while (actual!!.siguiente != null && actual.siguiente!!.clave != key) {
                    actual = actual.siguiente
                }
                if (actual.siguiente != null) {
                    return actual.siguiente!!.valor
                }
            }
        }
        return null
    }

    fun existe(key: Int): Boolean {

        val hash = hash(key)
        var actual = tabla[hash]

        if (actual != null) {
            if (actual.clave == key) {
                return true
            } else {
                while (actual!!.siguiente != null && actual.siguiente!!.clave != key) {
                    actual = actual.siguiente
                }
                if (actual.siguiente != null) {
                    return true
                }
            }
        }
        return false
    }
    
    fun numElementos(): Int {
        return elementos
    }
    
    override fun toString(): String {
        var cadena = ""
        for (i in tabla) {
            if (i != null) {
                var actual = i
                while (actual != null) {
                    cadena += actual.toString() + " "
                    actual = actual.siguiente
                }
            }
        }
        return cadena
    }

}



class HashTableEntry(var clave: Int, var valor: String) {
    var siguiente: HashTableEntry? = null
    var anterior: HashTableEntry? = null

    override fun toString(): String {
        return "[$clave, $valor]"
    }
}

