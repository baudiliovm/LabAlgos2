import kotlin.system.exitProcess
import kotlin.io.println

class PMLI(val letra: String) {

    init {
        if (!Palabra.esLetraValida(letra)) {
            println("La letra $letra no es valida")
            exitProcess(1)
        }
    }

    var palabras = ConjuntoPalabra()
    
    fun agregarPalabra(palabra: String) {
        if (palabra.first().toString() == letra) {
            palabras.agregar(palabra)
        }
    }

    fun eliminarPalabra(palabra: String) {
        if (palabra.first().toString() == letra) {
            palabras.eliminar(palabra)
        }
    }

    fun arrayPalabras(): Array<String>{
        val array = palabras.obtenerArray()
        return array
    }

    fun mostrarPalabras() {
        println("Palabras con la letra $letra:")
        palabras.ordenar()
    }

    fun buscarPalabra(palabra: String): Boolean {
        if (palabra.first().toString() == letra) {
            return (palabras.buscar(palabra) != null)
        }
        return false
    }

}