import java.io.File

class AyudanteOrtografico {
    private val max = arrayOf("a", "b", "c", "d", "e", "f", "g","h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z", "ñ")
    private val dicc = Array(max.size) { i -> PMLI(max[i]) }

    fun cargarDiccionario(fname: String) {
        val file = File(fname)
        file.forEachLine { line ->
            if (Palabra(line).esPalabraValida()) {
                var index = line.get(0) - 'a'
                if (line.get(0) == 'ñ'){
                    index = 26
                }
                dicc[index].agregarPalabra(line)
            }
        }
    }
    

    fun borrarPalabra(palabra: String) {
        if (Palabra(palabra).esPalabraValida()) {
            var index = palabra[0] - 'a'
            if (palabra.get(0) == 'ñ'){
                index = 26
            }
            dicc[index].eliminarPalabra(palabra)
        }
    }

    
    fun corregirTexto(finput: String, foutput: String) {
        val input = File(finput)
        val output = File(foutput)
        input.forEachLine { line ->
            var palabras = line.split(Regex("\\s+|[\n\r\t,.—;:¡!¿?()]")).filter { it.isNotEmpty() }
            for (palabra in palabras) {
                if (Palabra(palabra).esPalabraValida()) {
                    var index = palabra.get(0) - 'a'
                    if (palabra.get(0) == 'ñ'){
                        index = 26
                    }
                    if (!dicc[index].buscarPalabra(palabra)) {
                        val arrayPmli = dicc[index].arrayPalabras()
                        val arrayPair = Array<Pair<String, Int>>(arrayPmli.size) {Pair("",0)}
                        for (i in 0 until arrayPmli.size){
                            arrayPair[i] = Pair(arrayPmli[i], distancia(palabra,arrayPmli[i]))
                        }
                        val sugerencia = arrayPair.sortedBy { it.second }.take(4).joinToString(",") { it.first }
                        output.appendText("$palabra,$sugerencia\n")
                    }
                }
            }
        }
    }
    

    fun imprimirDiccionario() {
        for (pmli in dicc) {
            pmli.mostrarPalabras()
        }
    }
    
}