import kotlin.system.exitProcess
import kotlin.math.absoluteValue

/**
 * Implementacion de tabla de hash dinamica con encadenamiento con listas circulares
 * doblemente enlazadas
 * 
 * Soporta las operaciones de agregar, eliminar, buscar y existencia.
 */
class ConjuntoPalabra() {
    private var palabras: Array<CircularList?> = arrayOfNulls(3)
    private var tamano: Int = 0
        get() = palabras.size
    private var factorDeCarga: Double = 0.0
        get() = elementos / tamano.toDouble()
    private var elementos: Int = 0
    
    private fun hash(key: String): Int {
        return (key.hashCode() % tamano).absoluteValue
    }

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

    fun agregar(palabra: String) {

        if (!Palabra(palabra).esPalabraValida()) {
            println("La palabra $palabra no es valida")
            return
        }
        
        if (existe(palabra)) {
            println("La palabra $palabra ya existe en el conjunto")
            return
        }
        
        val hashValor = hash(palabra)

        if (palabras[hashValor] == null) palabras[hashValor] = CircularList()

        palabras[hashValor]?.agregarLista(Palabra(palabra))

        elementos++
        
        if (factorDeCarga >= 0.7) rehash()
    }

    
    fun buscar(palabra: String): String? {
        
        val hashValor = hash(palabra)
        var actual = palabras[hashValor]?.buscarLista(palabra)
        
        return actual?.valor
    }


    fun eliminar(palabra: String) {

        val hashValor = hash(palabra)
        var borra = palabras[hashValor]?.buscarLista(palabra)

        if (borra != null) {
            palabras[hashValor]?.eliminarLista(borra)
        } else {
            println("La palabra $palabra no existe en el conjunto")
            return
        }
        
        if (palabras[hashValor]?.cabeza == null) palabras[hashValor] = null
        elementos--
    }

    fun existe(palabra: String): Boolean {

        val hash = hash(palabra)
        var valor = palabras[hash]?.buscarLista(palabra)

        return valor != null
    }
    
    /**
     * Devuelve el numero de palabras en la tabla
     * 
     * @return el numero de palabras en la tabla
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