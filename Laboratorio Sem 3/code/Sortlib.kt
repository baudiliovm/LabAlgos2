/**
 * ------------------------------ Sortlib.kt ---------------------------------
 * Aquí encontraremos los distintos algoritmos de ordenamiento basados en el 
 * libro Data Structures and Algorithms de A. Aho, J. Hopcroft, J. Ullman;
 * usados para los labs del curso CI2692.
 * 
 * 
 * Autores: Baudilio Velasquez, Arthur Ortega
 * Fecha: mayo 2023
 * Universidad Simon Bolivar
 */

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
 * Sorts an array of integers using the merge sort algorithm.
 * 
 * @param T The array to sort.
 */
fun mergesortInsertion(T: Array<Int>) {
    if (T.size < 20) {
        insertionSort(T)
    } else {
        val floor = T.size/2
        var U = T.copyOfRange(0, floor)
        var V = T.copyOfRange(floor, T.size)
        mergesortInsertion(U)
        mergesortInsertion(V)
        merge(U, V, T)
    }
}

fun maxHeapify(A: Array<Int>, i: Int, n: Int) {
  var largest = i
  val left = (2 * i) + 1
  val right = (2 * i) + 2

  if (left <= n-1 && A[left] > A[largest]) {
    largest = left
  }

  if (right <= n-1 && A[right] > A[largest]) {
    largest = right
  }

  if (largest != i) {
    swap(A, i, largest)
    maxHeapify(A, largest, n)
  }
}

fun buildMaxHeap(A: Array<Int>, n: Int) {
  for (i in ((n / 2) - 1) downTo 0) {
    maxHeapify(A, i, n)
  }
}

fun heapSort(A: Array<Int>, n: Int) {
  buildMaxHeap(A, n)
  for (i in n-1 downTo 1) {
    swap(A, 0, i)
    maxHeapify(A, 0, i-1)
  }
}


fun up1(b: Int, c: Int) {
    var b1 = b
    var c1 = c

    b1 = b1 + c1 + 1
    c1 = b1
}

fun down1(b: Int, c: Int) {
    var b1 = b
    var c1 = c
    
    b1 = c1
    c1 = b1 - c1 - 1
}

fun even(x: Int): Boolean {if ((x % 2) == 0) {return true} else {return false}}

fun sift(m: Array<Int>, b: Int, r: Int, c: Int) {
    var b1 = b
    var c1 = c
    var r1 = r
    while (b1 >= 3) {
        var r2 = r1 - b1 + c1
        if (m[r2] >= m[r1 - 1]) {
            continue
        } else if (m[r2] <= m[r1 - 1]) {
            r2 = r1 - 1
            down1(b1, c1) // para pensar
        }
        if (m[r1] <= m[r2]) {
            b1 = 1
        } else if (m[r1] < m[r2]) {
            swap(m, r1, r2)
            r1 = r2
            down1(b1, c1) // para pensar
        }
    }
}

fun trinkle(m: Array<Int>, p: Int, b: Int, c: Int, r: Int) {
    var p1 = p
    var b1 = b
    var c1 = c
    var r1 = r

    while (p1 > 0) {

        while (even(p1)) {
            p1 = p1 / 2
            up1(b1, c1)
        }
        var r3 = r1 - b1

        if (p1 == 1 || (m[r3] <= m[r1])) {
            p1 = 0
        } else if (p1 > 1 && (m[r3] > m[r1])) {
            p1 -= 1
        }

        if (b1 == 1) {
            swap(m, r1, r3)
            r1 = r3
        } else if (b1 >= 3) {
            var r2 = r1 - b1 + c1
            if (m[r2] >= m[r1 - 1]) {
                continue
            } else if (m[r2] <= m[r1 - 1]) {
                r2 = r1 - 1
                down1(b1, c1)
                p1 *= 2
            }
            if (m[r3] >= m[r2]) {
                swap(m, r1, r3)
                r1 = r3
            } else if (m[r3] <= m[r2]) {
                swap(m, r1, r2)
                r1 = r2
                down1(b1, c1)
                p1 = 0
            }
        }
    }
    sift(m, b, r, c)
}

fun semitrinkle(m: Array<Int>, r: Int, c: Int, p: Int, b: Int) {
    var r1 = r - c
    if (m[r1] <= m[r]) {
        swap(m, r, r1)
        trinkle(m, p, b, c, r)
    }
}

fun smoothSort(m: Array<Int>) {
    var q = 1
    var r = 0
    var p = 1
    var b = 1
    var c = 1 
    var n = m.size

    while (q != n) {
        var r1 = r
        if (p % 8 == 3) {
            var b1 = b
            var c1 = c
            sift(m, b, r, c)
            p = (p + 1) / 4
            up1(b1, c1)
            up1(b1, c1)
        } else if (p % 4 == 1) {
            if ((q + c) < n) {
                var b1 = b
                var c1 = c
                sift(m, b, r, c)
            } else {
                trinkle(m, p, b, c, r)
            }
            down1(b1, c1)
            p *= 2
            while (b != 1) {
                down1(b1, c1)
                p *= 2
            }
            p += 1
        }
        q += 1
        r += 1
    }

    var r1 = r
    trinkle(m, p, b, c, r)
    
    while (q != 1) {
        q -= 1
        if (b == 1) {
            r -= 1
            p -= 1
            while (even(p)) {
                p = p / 2
                up1(b1, c1)
            }
        } else if (b >= 3) {
            p -= 1
            r -= b + c

            if (p == 0) {
                continue
            } else if (p > 0) {
                semitrinkle(m, r, c, p, b)
            }
            down1(b1, c1)
            p = p * 2 + 1 ; r += c ; semitrinkle(m, r, c, p, b)
            down1(b1, c1) ; p = p * 2 + 1
        }
    }
}


