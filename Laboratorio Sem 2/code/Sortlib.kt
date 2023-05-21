/**
 * ------------------------------ Sortlib.kt ---------------------------------
 * Aquí encontraremos los distintos algoritmos de ordenamiento basados en el 
 * libro Data Structures and Algorithms de A. Aho, J. Hopcroft, J. Ullman;
 * usados para el programa encontrado en Main.kt 
 * 
 * 
 * Autores: Baudilio Velasquez, Arthur Ortega
 * Fecha: mayo 2023
 * Universidad Simon Bolivar
 */

import kotlin.math.ceil
import kotlin.math.floor

/**
 * This function swaps the values at two given indexes in an array.
 *
 * @param A The array to swap the values in.
 * @param i The index of the first value to swap.
 * @param j The index of the second value to swap.
 */
fun swap(A: Array<Int>, i: Int, j: Int) {
    var temp = A[i]
    A[i] = A[j]
    A[j] = temp
}

/**
 * This function sorts an array of integers using the bubble sort algorithm.
 *
 * @param A The array to sort.
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

/**
 * This function sorts an array of integers using the insertion sort algorithm.
 *
 * @param A The array to sort.
 */
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

/**
 * This function sorts an array of integers using the selection sort algorithm.
 *
 * @param A The array to sort.
 */
fun selectionSort(A: Array<Int>) {
    var n = A.size
    for (i in 0 until n-1) {
        var lowIndex = i
        var lowKey = A[i]
        for (j in i+1 until n) {
            if (A[j] < lowKey) {
                lowKey = A[j]
                lowIndex = j
            }
        }
        swap(A, i, lowIndex)
    }
}

/**
 * This function sorts an array of integers using the shell sort algorithm.
 *
 * @param A The array to sort.
 */
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

/**
 * Merge algorithm for merge sort.
 * 
 * @param U The first array to merge.
 * @param V The second array to merge.
 * @param T The array to merge into.
 */
fun merge(U: Array<Int>, V: Array<Int>, T: Array<Int>) {
    val m = U.size
    val n = V.size
    var i = 0
    var j = 0
    
    for (k in 0 until T.size) {
        if (i < m && (j >= n || U[i] < V[j])) {
            T[k] = U[i]
            i += 1
        } else {
            T[k] = V[j]
            j += 1
        }
    }
}

/**
 * This function sorts an array of integers using the merge sort algorithm.
 * 
 * @param T The array to sort.
 */
fun mergesortInsertion(T: Array<Int>) {
    var n = T.size
    if (n < 10) {
        insertionSort(T)
    } else {
        val floor = n/2
        var U = T.copyOfRange(0, floor)
        var V = T.copyOfRange(floor, n)
        mergesortInsertion(U)
        mergesortInsertion(V)
        merge(U, V, T)
    }
}
