/**
 * Implementacion de tabla de hash dinamica con encadenamiento con listas circulares
 * doblemente enlazadas
 * 
 * Soporta las operaciones de agregar, eliminar, buscar y existencia.
 */
class Palabras() {

    private var palabras: Array<CircularList?> = arrayOfNulls(7)
    private var tamano: Int = 0
        get() = palabras.size
    private var factorDeCarga: Double = 0.0
        get() = elementos / tamano.toDouble()
    private var elementos: Int = 0
    
    
    private fun length(palabra: String) = palabra.length
    
    fun esLetraValida(letra: String): Boolean {
        val regex = Regex("[a-zñ]")
        return regex.matches(letra)
    }

    fun esPalabraValida(palabra: String): Boolean {
        if (length(palabra) < 0) {
            return false
        }
        for (letra in 0 until length(palabra)) {
            if (!esLetraValida(palabra[letra].toString())) {
                return false
            }
        }
        return true
    }

    /**
     * Metodo de la division para obtener el indice de la tabla
     * 
     * @param key la clave del elemento
     * @return el indice de la tabla
     */
    private fun hash(key: String) = key.hashCode()
    
    /**
     * Rehashing de la tabla
     * 
     * Se crea una nueva tabla con un tamaño 3 veces el tamaño anterior + 16
     * dividido entre 2, y se reasignan los elementos de la tabla anterior 
     * a la nueva tabla.
     */
    private fun rehash() {
        val nuevoTamano = (3 * (tamano + 16)) / 2
        val tablaCopia = palabras
        elementos = 0

        palabras = arrayOfNulls(nuevoTamano)

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
    fun agregar(clave: String, valor: Int) {

        if (existe(clave)) throw Exception("La clave $clave ya existe")

        val hashValor = hash(clave)

        if (palabras[hashValor] == null) palabras[hashValor] = CircularList()

        palabras[hashValor]?.agregarLista(HashTableEntry(clave, valor))

        elementos++
        
        if (factorDeCarga >= 0.7) rehash()
    }

    /**
     * Busca un elemento en la tabla
     * 
     * @param clave la clave del elemento
     * @return el valor del elemento
     */
    fun buscar(clave: String): Int? {

        val hashValor = hash(clave)
        var actual = palabras[hashValor]?.buscarLista(clave)

        return actual?.valor
    }

    /**
     * Elimina un elemento de la tabla
     * 
     * @param clave la clave del elemento
     */
    fun eliminar(clave: String) {

        val hashValor = hash(clave)
        var borra = palabras[hashValor]?.buscarLista(clave)

        if (borra != null) {
            palabras[hashValor]?.eliminarLista(borra)
        } else {
            throw Exception("La clave $clave no existe")
        }
        
        if (palabras[hashValor]?.cabeza == null) palabras[hashValor] = null
        elementos--
    }

    /**
     * Verifica si un elemento existe en la tabla
     * 
     * @param clave la clave del elemento
     * @return true si el elemento existe, false en otro caso
     */
    fun existe(clave: String): Boolean {

        val hash = hash(clave)
        var valor = palabras[hash]?.buscarLista(clave)

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
        for (i in palabras) {
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