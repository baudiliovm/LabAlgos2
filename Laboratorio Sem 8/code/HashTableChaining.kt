import kotlin.system.exitProcess

/**
 * Implementacion de tabla de hash dinamica con encadenamiento con listas circulares
 * doblemente enlazadas
 * 
 * Soporta las operaciones de agregar, eliminar, buscar y existencia.
 */
class HashTableChaining() {

    private var tabla: Array<CircularList?> = arrayOfNulls(7)
    private var tamano: Int = 0
        get() = tabla.size
    private var factorDeCarga: Double = 0.0
        get() = elementos / tamano.toDouble()
    private var elementos: Int = 0
    
    /**
     * Metodo de la division para obtener el indice de la tabla
     * 
     * @param key la clave del elemento
     * @return el indice de la tabla
     */
    private fun hash(key: Int) = key % tamano
    
    /**
     * Rehashing de la tabla
     * 
     * Se crea una nueva tabla con un tamaño 3 veces el tamaño anterior + 16
     * dividido entre 2, y se reasignan los elementos de la tabla anterior 
     * a la nueva tabla.
     */
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

    /**
     * Agrega un elemento a la tabla
     * 
     * @param key la clave del elemento
     * @param valor el valor del elemento
     */
    fun agregar(clave: Int, valor: String) {

        if (existe(clave)) throw Exception("La clave $clave ya existe")

        val hashValor = hash(clave)

        if (tabla[hashValor] == null) tabla[hashValor] = CircularList()

        tabla[hashValor]?.agregarLista(HashTableEntry(clave, valor))

        elementos++
        
        if (factorDeCarga >= 0.7) rehash()
    }

    /**
     * Busca un elemento en la tabla
     * 
     * @param clave la clave del elemento
     * @return el valor del elemento
     */
    fun buscar(clave: Int): String? {

        val hashValor = hash(clave)
        var actual = tabla[hashValor]?.buscarLista(clave)

        return actual?.valor
    }

    /**
     * Elimina un elemento de la tabla
     * 
     * @param clave la clave del elemento
     */
    fun eliminar(clave: Int) {

        val hashValor = hash(clave)
        var borra = tabla[hashValor]?.buscarLista(clave)

        if (borra != null) {
            tabla[hashValor]?.eliminarLista(borra)
        } else {
            throw Exception("La clave $clave no existe")
        }
        
        if (tabla[hashValor]?.cabeza == null) tabla[hashValor] = null
        elementos--
    }

    /**
     * Verifica si un elemento existe en la tabla
     * 
     * @param clave la clave del elemento
     * @return true si el elemento existe, false en otro caso
     */
    fun existe(clave: Int): Boolean {

        val hash = hash(clave)
        var valor = tabla[hash]?.buscarLista(clave)

        return valor != null
    }
    
    /**
     * Devuelve el numero de elementos en la tabla
     * 
     * @return el numero de elementos en la tabla
     */
    fun numElementos(): Int {
        return elementos
    }
    
    /**
     * Imprime la tabla
     * 
     * @return la tabla en forma de cadena String
     */
    override fun toString(): String {
        var cadena = "{ "
        for (i in tabla) {
            if (i != null) {
                var x: HashTableEntry? = i.cabeza
                while (x != null) {
                    cadena += x.toString() + " "
                    x = x.siguiente
                }
            }
        }
        cadena += "}"
        return cadena
    }
}