fun main(args: Array<String>) {

    val n = args[0].toInt()

    val inter = n / 3

    val arregloClaves = Array<Int>(n, {(0..inter).random()})


    val pares = Array<Pair<Int, String>>(n, {Pair(arregloClaves[it], arregloClaves[it].toString())})

    println("Cantidad pares: $n")
    println("Intervalo: [0, $inter]")
    println()

    println("Para HashTableChaining: ")
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
    println("   Tiempo de ejecucion Chaining: $time segundos")

    println("Para CuckooHashTable: ")
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
    println("   Tiempo promedio de ejecucion Cuckoo: $time1 segundos")
}