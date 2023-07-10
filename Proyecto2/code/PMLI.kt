import kotlin.system.exitProcess

class PMLI(val letra: String) {

    init {
        if (Palabra.esLetraValida(letra) == false) {
            println("La letra $letra no es valida")
            exitProcess(1)
        }
    }

    var palabras = ConjuntoPalabra()
    
    fun agregarPalabra(p: String) {
        if (p.first().toString() == letra) {
            palabras.agregar(p)
        }
    }

    fun eliminarPalabra(p: String) {
        if (p.first().toString() == letra) {
            palabras.eliminar(p)
        }
    }

    fun mostrarPalabras() {
        val sortedPalabras = palabras.ordenar()
        println(sortedPalabras.toString())/* 
        for (p in sortedPalabras) {
            println(p)
        } */
    }
    

    fun buscarPalabra(p: String): Boolean {
        if (p.first().toString() == letra) {
            return (palabras.buscar(p) != null)
        }
        return false
    }

}