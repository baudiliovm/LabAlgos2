import java.io.File

class AyudanteOrtografico {
    private val MAX = 27
    private val dicc = Array(MAX) { i -> PMLI('a' + i) }

    fun cargarDiccionario(fname: String) {
        val file = File(fname)
        file.forEachLine { line ->
            if (esPalabraValida(line)) {
                val index = line[0] - 'a'
                dicc[index].agregarPalabra(line)
            }
        }
    }

    fun borrarPalabra(p: String) {
        if (esPalabraValida(p)) {
            val index = p[0] - 'a'
            dicc[index].eliminarPalabra(p)
        }
    }

    fun corregirTexto(finput: String, foutput: String) {
        ///
    }

    fun imprimirDiccionario() {
        for (pmli in dicc) {
            pmli.mostrarPalabras()
        }
    }
}