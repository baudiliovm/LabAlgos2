class HashTableChaining() {

    private var tabla: Array<CircularList?> = arrayOfNulls(7)
    private var tamano: Int = tabla.size
    private var factorDeCarga: Double = 0.0
    private var elementos: Int = 0
    
    // Hash metodo de division
    private fun hash(key: Int) = key % tamano
    
    factorDeCarga = elementos / tamano.toDouble()
    
    private fun rehash() {
        val nuevoTamano = (3 * (tamano + 16)) / 2
        val tablaCopia = tabla
        elementos = 0

        tabla = arrayOfNulls(nuevoTamano)

        for (i in tablaCopia) {
            if (i != null) {
                var x: HashTableEntry? = i.cabeza
                while (x != null) {
                    agregar(x.clave, x.valor)
                    x = x.siguiente
                }
            }
        }
    }

    fun agregar(key: Int, valor: String) {

        val hashValor = hash(key)

        if (tabla[hashValor] == null) tabla[hashValor] = CircularList()

        tabla[hashValor]?.agregarLista(HashTableEntry(key, valor))

        elementos++
        factorDeCarga = elementos / tamano.toDouble()

        if (factorDeCarga >= 0.7) rehash()
    }


    fun buscar(clave: Int): String? {

        val hashValor = hash(clave)
        var actual = tabla[hashValor]?.buscarLista(clave)

        return actual?.valor
    }

    fun eliminar(key: Int) {

        val hashValor = hash(key)
        var borra = tabla[hashValor]?.buscarLista(key)

        if (borra != null) {
            tabla[hashValor]?.eliminarLista(borra)
        }
        
        if (tabla[hashValor]?.cabeza == null) tabla[hashValor] = null
        elementos--
    }

    fun existe(key: Int): Boolean {

        val hash = hash(key)
        var valor = tabla[hash]?.buscarLista(key)

        return valor != null
    }
    
    fun numElementos(): Int {
        return elementos
    }
    
    override fun toString(): String {
        var cadena = "["
        for (i in tabla) {
            if (i != null) {
                var x: HashTableEntry? = i.cabeza
                while (x != null) {
                    cadena += x.toString() + " "
                    x = x.siguiente
                }
            }
        }
        cadena += "]"
        return cadena
    }
}