import java.io.File
import kotlin.system.exitProcess


/**
 * Prints an error message and exits the program.
 *
 * @param message The error message to print.
 */
fun errorMessage(message: String) {
    println("Error: $message")
    exitProcess(1)
}

/**
 * Verifica que todas las ciudades estén en el tour.
 * 
 * @param ciudades Arreglo de ciudades.
 * @param tour Arreglo de tours.
 */
fun verificarCiudades(ciudades: Array<Pair<Double, Double>>, tour: Array<Array<Pair<Double, Double>>>) {
    for (i in 0 until ciudades.size) {
        val elemento = ciudades[i]
        for (j in 0 until tour.size) {
            val contiene = elemento in tour[i]
            if (!contiene) {
                errorMessage("Falta una ciudad en el arreglo")
            }
        }
    }
}


/**
 * Verifica que el tamaño del tour sea el correcto.
 * 
 * @param ciudades Arreglo de ciudades.
 * @param tour Arreglo de tours.

 */
fun verificarTamano(ciudades: Array<Pair<Double, Double>>, tour: Array<Array<Pair<Double, Double>>>) {
    if (ciudades.size != tour.size/2) {
        errorMessage("Hay duplicados o hay recorridos repetidos en el tour")
    }
}

/**
 * Abre un archivo de texto y lo convierte en un arreglo de ciudades.
 * 
 * @param archivo Nombre del archivo.
 * @return Arreglo de ciudades.
 */
fun abrirCiudades(archivo: String): Array<Pair<Double, Double>> {
    val lineas:Array<String> = File(archivo).readLines().toTypedArray()
    var ciudades = Array<Pair<Double, Double>>(0) { Pair(0.0, 0.0) }

    for (linea in lineas) {
        val lineSplit = linea.split("\\s".toRegex()).toTypedArray()
        try {
            lineSplit[0].toInt()        // Verifica si es un número
        } catch (e: NumberFormatException) {
            continue
        }
        val x = lineSplit[1].toDouble()
        val y = lineSplit[2].toDouble()
        ciudades += Pair(x, y)          // Agrega la ciudad a la lista
    }
    return ciudades
}

/** 
 * Abre un archivo de texto solucion y lo convierte en un arreglo de tours.
 * 
 * @param archivo Nombre del archivo.
 * @return Arreglo de tours.
 */
fun abrirSolucion(archivo: String): Array<Array<Pair<Double, Double>>> {
    val lineas:Array<String> = File(archivo).readLines().toTypedArray()
    var ciudades = Array<Array<Pair<Double, Double>>>(0) { Array<Pair<Double, Double>>(0) { Pair(0.0, 0.0) } }

    for (linea in lineas) {
        val lineSplit = linea.split("\\s".toRegex()).toTypedArray()
        try {
            val x = lineSplit[0].toDouble()
            val y = lineSplit[1].toDouble()
        } catch (e: NumberFormatException) {
            continue
        }
        
        
    }
    return ciudades
}

fun main(args: Array<String>) {
    val instancia = args[0]
    val solucion = args[1]


    val ciudades = abrirCiudades(instancia)
    val tour = abrirSolucion(solucion)

    verificarCiudades(ciudades, tour)
    verificarTamano(ciudades, tour)

    println("todo correcto")
}