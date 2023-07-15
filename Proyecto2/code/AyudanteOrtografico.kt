import java.io.File

/**
 * Clase que utiliza PMLI para almacenar las palabras del diccionario y corregir
 * un texto
 */
class AyudanteOrtografico {
    private val max = arrayOf("a", "b", "c", "d", "e", "f", "g","h", "i", "j", 
    "k", "l", "m", "n", "ñ", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", 
    "y", "z")
    private val dicc = Array(max.size) { i -> PMLI(max[i]) }

    /**
     * Lee el archivo de texto y carga las palabras en el diccionario
     */
    fun cargarDiccionario(fname: String) {
        val file = File(fname)
        file.forEachLine { line ->
            if (Palabra(line).esPalabraValida()) {
                var index = max.indexOf(line.get(0).toString())
                dicc[index].agregarPalabra(line)
            }
        }
    }
    
    /**
     * Recibe una palabra de entrada y la borra del diccionario
     */
    fun borrarPalabra(palabra: String) {
        if (Palabra(palabra).esPalabraValida()) {
            var index = max.indexOf(palabra.get(0).toString())
            dicc[index].eliminarPalabra(palabra)
        }
    }

    /**
     * Retorna un array de palabras de todo el diccionario
     */
    fun arrayTodoDiccionario(): Array<String> {
        var array = dicc[0].arrayPalabras()
        for (i in 1 until 27){
            array = array + dicc[i].arrayPalabras()
        }
        return array
    }
    
    /**
     * Procesa un archivo de texto y genera un archivo de salida con las palabras
     * mal escritas y sus sugerencias utilizando la distancia de Levenshtein
     */
    fun corregirTexto(finput: String, foutput: String) {
        val input = File(finput)
        val output = File(foutput)
        val arrayPmli = arrayTodoDiccionario()
        input.forEachLine { line ->
            var palabras = line.split(Regex("\\s+|[\n\r\t,.—;:¡!¿?()]")).filter { it.isNotEmpty() }
            for (palabra in palabras) {
                if (Palabra(palabra).esPalabraValida()) {
                    var index = max.indexOf(palabra.get(0).toString())
                    if (!dicc[index].buscarPalabra(palabra)) {
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
    
    /**
     * Imprime el diccionario las palabras del PMLI en orden léxicografico
     */
    fun imprimirDiccionario() {
        for (pmli in dicc) {
            pmli.mostrarPalabras()
        }
    }
    
}