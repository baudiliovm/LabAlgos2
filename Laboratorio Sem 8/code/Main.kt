fun main(args: Array<String>) {

    val n = args[0].toInt()

    val inter = n / 3

    val arregloClaves = Array<Int>(n, {(0..inter).random()})


    val pares = Array<Pair<Int, String>>(n, {Pair(arregloClaves[it], arregloClaves[it].toString())})

    println("Cantidad pares: $n")
    println("Intervalo: [0, $inter]")


    var sumaTiempo = 0.0
    var sumaTiempo1 = 0.0

    for (i in 0..4) {
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
        sumaTiempo += time
    
        var cuckoo = CuckooHashTable(7)
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
        sumaTiempo1 += time1
    }

    val promedio = sumaTiempo / 5
    val promedio1 = sumaTiempo1 / 5

   
    println("Tiempo promedio de ejecucion Chaining: $promedio segundos")
    println("Tiempo promedio de ejecucion Cuckoo: $promedio1 segundos")
}