/**
 * Test for mergeSort with insertion sort for arrays that are smaller than a 
 * x value.
 * 
 * @param T The array to be sorted.
 * @param x The value that determines when to use insertion sort.
 */
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

/**
 * Print the time of the test of mergesort, with insertion sort for different 
 * sizes of arrays.
 */
fun mergesortTest() {
    var A = randArray(1000000, 0, 900000)
    val t = 5
    println("Test for $t tries")
    for (i in 10..100 step 10) {
        println("")
        println("Array size: $i")
        timeSortIns(A, i, t)
    }
}
