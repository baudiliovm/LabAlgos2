import java.io.File

class AyudanteOrtografico {
    private val max = arrayOf("a", "b", "c", "d", "e", "f", "g","h", "i", "j", "k", "l", "m", "n", "ñ", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z")
    private val dicc = Array(max.size) { i -> PMLI(max[i]) }

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
        //
    }
    

    fun imprimirDiccionario() {
        for (pmli in dicc) {
            pmli.mostrarPalabras()
        }
    }

    fun esLetraValida(letra: String): Boolean {
            if (letra.length != 1) {
                return false
            }
            val regex = Regex("[a-zñ]")
            return regex.matches(letra)
    }
    

    fun esPalabraValida(s: String): Boolean {
        for (i in 0 until s.length) {
            if (s[i] !in 'a'..'z') {
                return false
            }
        }
        return true
    }
}