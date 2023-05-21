
fun mergesortInsertionPrueba(T: Array<Int>, x: Int) {
    var n = T.size
    if (n < x) {
        insertionSort(T)
    } else {
        val floor = n/2
        var U = T.copyOfRange(0, floor)
        var V = T.copyOfRange(floor, n)
        mergesortInsertionPrueba(U, x)
        mergesortInsertionPrueba(V, x)
        merge(U, V, T)
    }
}

fun InsertionPrueba() {
    var A = randArray(1000000, 0, 900000)
    val t = 1
    for (i in 10..100 step 10) {
        println("tamaño de arreglo: $i")
        timeSortIns(A, i, t)
        println("")
    }
}
