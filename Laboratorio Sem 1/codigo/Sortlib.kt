/**
 * Intercambia dos elementos de un arreglo de enteros
 * 
 * @param A Arreglo de enteros 
 * @param i Índice del primer elemento a intercambiar
 * @param j Índice del segundo elemento a intercambiar
 */
fun swap(A: Array<Int>, i: Int, j: Int) {
    var temp = A[i]
    A[i] = A[j]
    A[j] = temp
}

/**
 * Ordena un arreglo de enteros usando el algoritmo de Bubble Sort
 * 
 * @param A Arreglo de enteros a ordenar
 * @return Arreglo de enteros ordenado
 */
fun bubbleSort(A: Array<Int>) {
    var n = A.size
    for (i in 0 until n) {
        for (j in (n-1) downTo (i+1)) {
            if (A[j-1] > A[j]) {
                // Swap A[j-1] and A[j]
                swap(A, j-1, j)
            }
        }
    }
}

fun insertionSort(A: Array<Int>) {
    val n = A.size
    for (i in 1 until n) {
        var j = i
        while (j>0 && A[j] < A[j-1]) {
            swap(A, j, j-1)
            j = j-1
        }
    }
}

fun selectionSort(A: Array<Int>) {
    var n = A.size
    for (i in 0 until n) {
        var lowIndex = i
        var lowKey = A[i]
        for (j in i+1 until n) {
            if (A[j] < lowKey) {
                lowKey = A[j]
                lowIndex = j
            }
        swap(A, i, lowIndex)
        }
    }
}

fun shellSort(A: Array<Int>) {
    val n = A.size
    var incr = n.div(2)
    while (incr > 0) {
        for (i in (incr) until n) {
            var j = (i - incr)
            while (j > -1) {
                if (A[j] > A[j+incr]) {
                    // Make a swap A[j] and A[j+incr]
                    swap(A, j, j+incr)
                    j = (j - incr)
                } else {
                    j = -1
                }
            }
        }
    incr = incr.div(2)
    }
}
