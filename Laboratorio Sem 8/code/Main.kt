/**
 * Computes the standard deviation of an array of times.
 *
 * @param a The average time
 * @param b The array of times
 */
fun standardDeviation(a: Double, b: Array<Double>): Double {
    var deviations = 0.0
    for (i in 0 until (b.size - 1)) {
        deviations += Math.pow((b[i] - a).toDouble(), 2.0)
    }
    return Math.sqrt(deviations / b.size)
}

fun main(args: Array<String>) {

    val n = args[0].toInt()

    val inter = n / 3

    val arregloClaves = Array<Int>(n, {(0..inter).random()})


    val pares = Array<Pair<Int, String>>(n, {Pair(arregloClaves[it], arregloClaves[it].toString())})

    println("Cantidad pares: $n")
    println("Intervalo: [0, $inter]")
    println()

    var sumaTiempo = 0.0
    var sumaTiempo1 = 0.0
    val arregloTiempos = Array<Double>(5) { 0.0 }
    val arregloTiempos1 = Array<Double>(5) { 0.0 }

    println("Para HashTableChaining: ")
    repeat (5) { it ->
        var chaining = HashTableChaining()
        val begin = System.nanoTime()
        for ((clave, valor) in pares) {
            if (chaining.buscar(clave) == null) {
                chaining.agregar(clave, valor)
            } else {
                chaining.eliminar(clave)
            }
        }
        val end = System.nanoTime()
        val time = (end - begin) / 1e9
        arregloTiempos[it] = time
        sumaTiempo += time
    }

    val promedio = sumaTiempo / 5
    println("   Tiempo promedio de ejecucion Chaining: $promedio segundos")
    println("   Desviacion estandar: ${standardDeviation(promedio, arregloTiempos)} segundos")

    println("Para CuckooHashTable: ")
    repeat(5) { it ->
        var cuckoo = CuckooHashTable()
        val begin1 = System.nanoTime()
        for ((clave, valor) in pares) {
            if (cuckoo.buscar(clave) == null) {
                cuckoo.agregar(clave, valor)
            } else {
                cuckoo.eliminar(clave)
            }
        }
        val end1 = System.nanoTime()
        val time1 = (end1 - begin1) / 1e9
        arregloTiempos1[it] = time1
        sumaTiempo1 += time1
    }

    val promedio1 = sumaTiempo1 / 5
    println("   Tiempo promedio de ejecucion Cuckoo: $promedio1 segundos")
    println("   Desviacion estandar: ${standardDeviation(promedio1, arregloTiempos1)} segundos")
}