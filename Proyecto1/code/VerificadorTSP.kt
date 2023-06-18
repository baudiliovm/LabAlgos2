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


fun verificarTamano(ciudades: Array<Pair<Double, Double>>, tour: Array<Array<Pair<Double, Double>>>) {
    if (ciudades.size != tour.size/2) {
        errorMessage("Hay duplicados o hay recorridos repetidos en el tour")
    }
}


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

fun abrirSolucion(archivo: String): Array<Int> {
    val lineas:Array<String> = File(archivo).readLines().toTypedArray()
    var ciudades = Array<Int>(0) {0}

    for (linea in lineas) {
        val lineSplit = linea.split("\\s".toRegex()).toTypedArray()
        try {
            val num = lineSplit[0].toInt()        // Verifica si es un número
            ciudades += num
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

    println(ciudades.size)
    println(ciudades.joinToString(", "))
    println(tour.size)
    println(tour.joinToString(", "))


    // verificarCiudades(ciudades, tour)
    // verificarTamano(ciudades, tour)

    println("todo correcto")
}