import kotlin.system.exitProcess
import kotlin.io.println

/**
 * Clase que implementa un PMLI (Palabras que empiezan con la letra inicial)
 * 
 * @param letra la letra inicial del PMLI
 */
class PMLI(val letra: String) {

    /**
     * Verifica que la letra sea valida
     */
    init {
        if (!Palabra.esLetraValida(letra)) {
            println("La letra $letra no es valida")
            exitProcess(1)
        }
    }

    var palabras = ConjuntoPalabra()
    
    /**
     * Funcion para agregar una palabra al PMLI
     * 
     * @param palabra la palabra a agregar
     */
    fun agregarPalabra(palabra: String) {
        if (palabra.first().toString() == letra) {
            palabras.agregar(palabra)
        }
    }

    /**
     * Funcion para eliminar una palabra del PMLI
     * 
     * @param palabra la palabra a eliminar
     */
    fun eliminarPalabra(palabra: String) {
        if (palabra.first().toString() == letra) {
            palabras.eliminar(palabra)
        }
    }

    /**
     * Retorna un array de palabras del PMLI
     */
    fun arrayPalabras(): Array<String>{
        val array = palabras.obtenerArray()
        return array
    }

    /**
     * Imprime las palabras del PMLI en orden léxicografico
     */
    fun mostrarPalabras() {
        println("Palabras con la letra $letra:")
        palabras.ordenar()
    }

    /**
     * Busca una palabra en el PMLI
     * 
     * @param palabra la palabra a buscar
     * @return true si la palabra se encuentra, false en caso contrario
     */
    fun buscarPalabra(palabra: String): Boolean {
        if (palabra.first().toString() == letra) {
            return (palabras.buscar(palabra) != null)
        }
        return false
    }

}