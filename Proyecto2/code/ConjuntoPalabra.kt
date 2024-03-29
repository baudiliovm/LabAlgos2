import kotlin.system.exitProcess
import kotlin.math.absoluteValue

/**
 * Implementacion de tabla de hash dinamica con encadenamiento con listas circulares
 * doblemente enlazadas para simular un conjunto de palabras.
 * 
 * Soporta las operaciones de agregar, eliminar, buscar y existencia.
 */
class ConjuntoPalabra {
    private var palabras: Array<CircularList?> = arrayOfNulls(3)
    private var tamano: Int = 0
        get() = palabras.size
    private var factorDeCarga: Double = 0.0
        get() = elementos / tamano.toDouble()
    private var elementos: Int = 0
    

    /**
     * Funcion hash para obtener el indice de la tabla, usando el metodo de 
     * la division
     * 
     * @param llave la palabra a agregar
     */
    private fun hash(llave: String): Int {
        return (llave.hashCode() % tamano).absoluteValue
    }
    

    /**
     * Funcion de rehash para aumentar el tamaño de la tabla dos veces su tamaño
     * cuando el factor de carga es mayor o igual a 0,7
     */
    private fun rehash() {
        val nuevoTamano = 2 * tamano
        val tablaCopia = palabras
        elementos = 0

        palabras = arrayOfNulls(nuevoTamano)

        for (i in tablaCopia) {
            if (i != null) {
                var x: Palabra? = i.cabeza
                while (x != null) {
                    agregar(x.valor)
                    x = x.siguiente
                }
            }
        }
    }

    /**
     * Funcion para agregar una palabra al ConjuntoPalabra
     * 
     * @param palabra la palabra a agregar
     */
    fun agregar(palabra: String) {

        if (!Palabra(palabra).esPalabraValida()) {
            println("La palabra $palabra no es valida")
            return
        }
        
        if (existe(palabra)) {
            return
        }
        
        val hashValor = hash(palabra)

        if (palabras[hashValor] == null) palabras[hashValor] = CircularList()

        palabras[hashValor]?.agregarLista(Palabra(palabra))

        elementos++
        
        if (factorDeCarga >= 0.7) rehash()
    }

    /**
     * Busca una palabra en el conjuntoPalabra
     * 
     * @param palabra la palabra a buscar
     */
    fun buscar(palabra: String): String? {
        
        val hashValor = hash(palabra)
        var actual = palabras[hashValor]?.buscarLista(palabra)
        
        return actual?.valor
    }

    /**
     * Elimina una palabra del conjuntoPalabra
     * 
     * @param palabra la palabra a eliminar
     */
    fun eliminar(palabra: String) {

        val hashValor = hash(palabra)
        var borra = palabras[hashValor]?.buscarLista(palabra)

        if (borra != null) {
            palabras[hashValor]?.eliminarLista(borra)
            println("Palabra eliminada.")
        } else {
            println("La palabra $palabra no existe en el diccionario")
            return
        }
        
        if (palabras[hashValor]?.cabeza == null) palabras[hashValor] = null
        elementos--
    }

    /**
     * Verifica si una palabra existe en el conjuntoPalabra
     * 
     * @param palabra la palabra a verificar
     * @return true si la palabra existe, false en caso contrario
     */
    fun existe(palabra: String): Boolean {

        val hash = hash(palabra)
        var valor = palabras[hash]?.buscarLista(palabra)

        return valor != null
    }
    
    /**
     * Devuelve el numero de palabras en el ConjuntoPalabra
     * 
     * @return el numero de palabras en el ConjuntoPalabra
     */
    fun numElementos(): Int {
        return elementos
    }

    /**
     * Ordena las palabras en el ConjuntoPalabra en orden alfabetico
     * 
     * @return la lista ordenada
     */
    fun ordenar() {
        val list = mutableListOf<Palabra>()
        for (i in 0 until tamano) {
            if (palabras[i] != null) {
                var x: Palabra? = palabras[i]?.cabeza
                while (x != null) {
                    list.add(x)
                    x = x.siguiente
                }
            }
        }
        val sorted = list.sortedBy { it.valor }
        println(sorted)
    }

    /**
     * Devuelve un array con todas las palabras en el ConjuntoPalabra
     * 
     * @return un array con todas las palabras en el ConjuntoPalabra
     */
    fun obtenerArray(): Array<String> {
        val array = Array(elementos) { "" }
        var i = 0
        for (j in palabras) {
            if (j != null) {
                var x: Palabra? = j.cabeza
                while (x != null) {
                    array[i] = x.valor
                    i++
                    x = x.siguiente
                }
            }
        }
        return array
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
                var x: Palabra? = i.cabeza
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