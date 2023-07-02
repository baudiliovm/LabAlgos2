fun main(args: Array<String>) {

    val n = args[0].toInt()

    val inter = n / 3

    val arregloClaves = Array<Int>(n, {(0..inter).random()})


    val pares = Array<Pair<Int, String>>(n, {Pair(arregloClaves[it], arregloClaves[it].toString())})

    println("Cantidad pares: $n")
    println("Intervalo: [0, $inter]")

    var createHash = HashTableChaining()
    val begin = System.nanoTime()
    for ((i,j) in pares) {
        if (createHash.buscar(i) == null) {
            createHash.agregar(i, j)
        } else {
            createHash.eliminar(i)
        }
    }
    val end = System.nanoTime()
    val time = end - begin / 1e9


    /* var createCuckoo = CuckooHashTable()

    for ((i,j) in pares) {
        if (createCuckoo.buscar(i) == null) {
            createCuckoo.agregar(i, j)
        } else {
            createCuckoo.eliminar(i)
        }
    }
    */
    println("Tiempo de ejecucion: $time segundos")
}