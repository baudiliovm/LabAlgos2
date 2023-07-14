import java.io.File

class AyudanteOrtografico {
    private val max = arrayOf("a", "b", "c", "d", "e", "f", "g","h", "i", "j", "k", "l", "m", "n", "ñ", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z")
    private val dicc = Array(max.size) { i -> PMLI(max[i]) }

    fun cargarDiccionario(fname: String) {
        val file = File(fname)
        file.forEachLine { line ->
            if (Palabra(line).esPalabraValida()) {
                if (line[0] == 'ñ') {
                    dicc[14].agregarPalabra(line)
                } else {
                    val index = line[0] - 'a'
                    dicc[index].agregarPalabra(line)
                }
            }
        }
    }
    

    fun borrarPalabra(palabra: String) {
        if (Palabra(palabra).esPalabraValida()) {
            val index = palabra[0] - 'a'
            dicc[index].eliminarPalabra(palabra)
        }
    }

    
    fun corregirTexto(finput: String, foutput: String) {
        val input = File(finput)
        val output = File(foutput)
        input.forEachLine { linea ->
            val textoLinea = linea.split(Regex("\\s+"))
            for (palabra in textoLinea) {
                if (Palabra(palabra).esPalabraValida()) {
                    println("Palabra: $palabra")
                    val letra = palabra.get(0)
                    var index = letra - 'a'
                    if (letra == 'ñ') {
                        index = 14
                    } 
                    println("Index: $index")
                    if (!dicc[index].buscarPalabra(palabra)) {
                        val arrayPmli = dicc[index].arrayPalabras()
                        val arrayPair = Array<Pair<String, Int>>(arrayPmli.size) {Pair("",0)}
                        for (i in 0 until arrayPmli.size){
                            val distance = distancia(palabra,arrayPmli[i])
                            arrayPair[i] = Pair(arrayPmli[i], distance)
                            println(distance)
                        }
                        val sugerencia = arrayPair.sortedBy { it.second }.take(4).joinToString(", ") { it.first }
                        println("Sugerencia: $sugerencia")
                        output.appendText("$palabra $sugerencia\n")
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