/**
 * ------------------------------ Sortlib.kt --------------------------------- 
 * Aquí encontraremos los distintos algoritmos de ordenamiento basados en el 
 * libro Data Structures and Algorithms de A. Aho, J. Hopcroft, J. Ullman; 
 * usados para los labs del curso CI2692.
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
        for (j in (n - 1) downTo (i + 1)) {
            if (A[j - 1] > A[j]) {
                // Swap A[j-1] and A[j]
                swap(A, j - 1, j)
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
        while (j > 0 && A[j] < A[j - 1]) {
            swap(A, j, j - 1)
            j = j - 1
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
    for (i in 0 until n - 1) {
        var lowIndex = i
        var lowKey = A[i]
        for (j in i + 1 until n) {
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
                if (A[j] > A[j + incr]) {
                    // Make a swap A[j] and A[j+incr]
                    swap(A, j, j + incr)
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
        val floor = T.size / 2
        var U = T.copyOfRange(0, floor)
        var V = T.copyOfRange(floor, T.size)
        mergesortInsertion(U)
        mergesortInsertion(V)
        merge(U, V, T)
    }
}

/**
 * To heapify a subtree rooted.
 *
 * @param A The array to sort.
 * @param i The index into the array.
 * @param n The size of heap.
 */
fun maxHeapify(A: Array<Int>, i: Int, n: Int) {
    var largest = i
    val left = (2 * i) + 1
    val right = (2 * i) + 2

    if (left <= n - 1 && A[left] > A[largest]) {
        largest = left
    } 

    if (right <= n - 1 && A[right] > A[largest]) {
        largest = right
    }

    if (largest != i) {
        swap(A, i, largest)
        maxHeapify(A, largest, n)
    }
}

/**
 * Converts an array into a max-heap
 *
 * @param A The array to sort.
 * @param n The size array to sort.
 */
fun buildMaxHeap(A: Array<Int>, n: Int) {
    for (i in ((n - 1  / 2)) downTo 0) {
        maxHeapify(A, i, n)
    }
}

/**
 * Sorts an array of integers using the heap sort algorithm.
 *
 * @param A The array to sort.
 * @param n The size array to sort.
 */
fun heapSort(A: Array<Int>) {
    val n = A.size
    buildMaxHeap(A, n)
    for (i in n - 1 downTo 0) {
        swap(A, 0, i)
        maxHeapify(A, 0, i)
    }
}

/**
 * up function to be used in smoothsort
 * 
 * @param arg The array of args.
 */
fun up(arg: Array<Int>) {
    // var b = arg[3]
    // var c = arg[4]
    var b = arg[3]
    var c = arg[4]
    arg[3] = b + c + 1
    arg[4] = b
}

/**
 * down function to be used in smoothsort
 * 
 * @param arg The array of args.
 */
fun down(arg: Array<Int>) {
    // var b = arg[3]
    // var c = arg[4]
    var b = arg[3]
    var c = arg[4]
    arg[3] = c
    arg[4] = b - c - 1
}

/**
 * Checks if a number is even.
 * 
 * @param x The number to check.
 */
fun even(x: Int): Boolean { return ((x % 2) == 0) }


/**
 * sift function to be used in smoothsort
 * 
 * @param m The array to sort.
 * @param arg1 The array of args with index 1 changed.
 */
fun sift(m: Array<Int>, arg1: Array<Int>) {
/*
    Guide for args:
    var q1 = arg1[0]
    var r1 = arg1[1]
    var p1 = arg1[2]
    var b1 = arg1[3]
    var c1 = arg1[4] */
    while (arg1[3] >= 3) {
        var r2 = arg1[1] - arg1[3] + arg1[4]
        if (m[r2] <= m[arg1[1] - 1]) {
            r2 = arg1[1] - 1
            down(arg1)
        }
        if (m[arg1[1]] >= m[r2]) {
            arg1[3] = 1
        } else {
            swap(m, arg1[1], r2)
            arg1[1] = r2
            down(arg1)
        }
    }
}

/**
 * trinkle function to be used in smoothsort
 * 
 * @param m The array to sort.
 * @param arg The array of args.
 * @param arg1 The array of args with index 1 changed.
 */
fun trinkle(m: Array<Int>, arg: Array<Int>, arg1: Array<Int>) {
/* 
    Guide for args:
    var q = arg[0]
    var r = arg[1]
    var p = arg[2]
    var b = arg[3]
    var c = arg[4]

    var q1 = arg1[0]
    var r1 = arg1[1]
    var p1 = arg1[2]
    var b1 = arg1[3]
    var c1 = arg1[4] */
    arg1[2] = arg[2]
    arg1[3] = arg[3]
    arg1[4] = arg[4]


    while (arg1[2] > 0) {
        while (even(arg1[2]) == true) {
            arg1[2] = arg1[2] / 2
            up(arg1)
        }

        var r3 = arg1[1] - arg1[3]

        if ((arg1[2] == 1) || m[r3] <= m[arg1[1]]) {
            arg1[2] = 0
        } else if (arg1[2] > 1 && m[r3] > m[arg1[1]]) {
            arg1[2] -= 1

            if (arg1[3] == 1) {
                swap(m, arg1[1], r3)
                arg1[1] = r3

            } else if (arg1[3] >= 3) {

                var r2 = arg1[1] - arg1[3] + arg1[4]

                if (m[r2] <= m[arg1[1] - 1]) {
                    r2 = arg1[1] - 1
                    down(arg1)
                    arg1[2] = 2 * arg1[2]
                }
                if (m[r3] >= m[r2]) {
                    swap(m, arg1[1], r3)
                    arg1[1] = r3
                } else if (m[r3] <= m[r2]) {
                    swap(m, arg1[1], r2)
                    arg1[1] = r2
                    down(arg1)
                    arg1[2] = 0
                }
            }
        }
    }
    sift(m, arg1)
}

/**
 * semi-trinkle function to be used in smoothsort
 * 
 * @param m The array to sort.
 * @param arg The array of args.
 * @param arg1 The array of args with index 1 changed.
 */
fun semitrinkle(m: Array<Int>, arg: Array<Int>, arg1: Array<Int>) {
/* 
    Guide for args:
    var q = arg[0]
    var r = arg[1]
    var p = arg[2]
    var b = arg[3]
    var c = arg[4]

    var q1 = arg1[0]
    var r1 = arg1[1]
    var p1 = arg1[2]
    var b1 = arg1[3]
    var c1 = arg1[4] */
    arg1[1] = arg[1] - arg[4]
   
    if (m[arg1[1]] > m[arg[1]]) {
        swap(m, arg[1], arg1[1])
        trinkle(m, arg, arg1)
    }
}

/**
 * This function sorts an array of integers using the smooth sort algorithm.
 *
 * @param m The array to sort.
 */
fun smoothSort(m: Array<Int>) {
    /* 
    Guide for args:
    var q = arg[0]
    var r = arg[1]
    var p = arg[2]
    var b = arg[3]
    var c = arg[4]

    var q1 = arg1[0]
    var r1 = arg1[1]
    var p1 = arg1[2]
    var b1 = arg1[3]
    var c1 = arg1[4] */

    val n = m.size
    var arg = arrayOf(1, 0, 1, 1, 1)
    var arg1 = arrayOf(1, 0, 1, 1, 1)
    
    while (arg[0] != n) {
        arg1[1] = arg[1]
        if (arg[2] % 8 == 3) {
            arg1[3] = arg[3]
            arg1[4] = arg[4]
            sift(m, arg1)
            arg[2] = (arg[2] + 1) / 4
            up(arg)
            up(arg)
        } else if (arg[2] % 4 == 1) {
            if ((arg[0] + arg[4]) < n) {
                arg1[3] = arg[3]
                arg1[4] = arg[4]
                sift(m, arg1)
            } else {
                trinkle(m, arg, arg1)
            }
            down(arg)
            arg[2] *= 2

            while (arg[3] != 1) {
                down(arg)
                arg[2] *= 2
            }
            arg[2] += 1
        }
        arg[0] += 1
        arg[1] += 1
    }

    arg1[1] = arg[1]
    trinkle(m, arg, arg1)

    while (arg[0] != 1) {
        arg[0] -= 1
        if (arg[3] == 1) {
            arg[1] -= 1
            arg[2] -= 1
            while (even(arg[2])) {
                arg[2] = arg[2] / 2
                up(arg)
            }
        } else if (arg[3] >= 3) {
            arg[2] -= 1
            arg[1] = arg[1] - arg[3] + arg[4]
            if (arg[2] > 0) {
                semitrinkle(m, arg, arg1)
            }
            down(arg)
            arg[2] = arg[2] * 2 + 1
            arg[1] = arg[1] + arg[4]
            semitrinkle(m, arg, arg1)
            down(arg)
            arg[2] = arg[2] * 2 + 1
        }
    }
}



// QuickSort

/**
 * Partition algorithm for quick sort.
 * 
 * @param A The array to sort.
 * @param p The first index of the array.
 * @param r The last index of the array.
 */
fun partition(A: Array<Int>, p: Int, r: Int): Int {
    val x = A[r]
    var i = p - 1
    for (j in p until r) {
        if (A[j] <= x) {
            i++
            swap(A, i, j)
        }
    }
    swap(A, i + 1, r)
    return (i + 1)
}

/**
 * This function sorts an array of integers using the quick sort algorithm.
 * 
 * @param A The array to sort.
 * @param p The first index of the array.
 * @param r The last index of the array.
 */
fun quicksortClasicox(A: Array<Int>, p: Int, r: Int) {
    if (p < r) {
        var q = partition(A, p, r)
        quicksortClasicox(A, p, q - 1)
        quicksortClasicox(A, q + 1, r)
    }
}

/**
 * Calls the quicksortClasicox function with the correct parameters.
 * 
 * @param A The array to sort.
 */
fun quicksortClasico(A: Array<Int>) = quicksortClasicox(A, 0, A.size - 1)

/**
 * This function sorts an array of integers using the quick sort algorithm with 
 * 3-way partitioning.
 * 
 * @param A The array to sort.
 * @param p The first index of the array.
 * @param r The last index of the array.
 */
fun quicksortThreeWayx(A: Array<Int>, l: Int, r: Int) {
    if (r <= l) {
        return
    }

    var i = l-1
    var j = r
    var p = l - 1
    var q = r
    val v = A[r]
    
    while (true) {

        while (A[++i] < v) {

        }

        while (v < A[--j]) {
            if (j == l) {
                break
            }
        }

        if (i >= j) {
            break
        }

        swap(A, i, j)

        if (A[i] == v) {
            p++
            swap(A, p, i)
        }

        if (v == A[j]) {
            q--
            swap(A, j, q)
        }
    }

    swap(A, i, r)
    j = i - 1
    i = i + 1

    for (k in l until p) {
        swap(A, k, j)
        j--
    }

    for (k in r-1 downTo q + 1) {
        swap(A, i, k)
        i++
    }

    quicksortThreeWayx(A, l, j)
    quicksortThreeWayx(A, i, r)
}

/**
 * Calls the quicksortThreeWayx function with the correct parameters.
 * 
 * @param A The array to sort.
 */
fun quicksortThreeWay(A: Array<Int>) = quicksortThreeWayx(A, 0, A.size - 1)

/**
 * This function sorts an array of integers using the quick sort algorithm with 
 * dual-pivot using the Yaroslavskiy's partitioning scheme.
 * 
 * @param A The array to sort.
 * @param left The first index of the array.
 * @param right The last index of the array.
 */
fun quicksortDualPivotx(A: Array<Int>, left: Int, right: Int) {
    
    if ((right - left) >= 1) {
        var p = A[left]
        var q = A[right]

        if (p > q) {
            swap(A, left, right)
            var temp = p
            p = q
            q = temp
        }

        var l = (left + 1)
        var k = l
        var g = (right - 1)

        while (k <= g) {
        
            if (A[k] < p) {
                swap(A, k, l)
                l = l + 1

            } else {
                if (A[k] > q) {
                    while (A[g] > q && k < g) {
                        g = g - 1
                    }
                    swap(A, k, g)
                    g = g - 1
                    if (A[k] < p) {
                        swap(A, k, l)
                        l = l + 1
                    }
                }    
            }
            k = k + 1
        }
        
        l = l - 1
        g = g + 1

        swap(A, left, l)
        swap(A, right, g)
        quicksortDualPivotx(A, left, l - 1)
        quicksortDualPivotx(A, l + 1, g - 1)
        quicksortDualPivotx(A, g + 1, right)

    }
}

/**
 * Calls the quicksortDualPivotx function with the correct parameters.
 * 
 * @param A The array to sort.
 */
fun quicksortDualPivot(A: Array<Int>) = quicksortDualPivotx(A, 0, A.size - 1)


/* 
fun Countingsort(A, B, k) {


} */

/**
 * Sorts an array of integers using the counting sort algorithm.
 * 
 * @param A The array to sort.
 * @param n The size of the array.
 * @param k The maximum value of the array.
 */
fun countingSortx(A: Array<Int>, n: Int, k: Int) {

    // Create two new arrays
    val array = A.clone()
    val C = Array<Int>(k+1) {it * 0}

    // Count the occurrences of each element in the input array
    for (j in 0 until n) {
        C[array[j]]++
    }

    // Cumulatively sum the count array
    for (i in 1 until k+1) {
        C[i] += C[i - 1]
    }

    // Iterate over the input array from the end
    for (j in n - 1 downTo 0) {
        val index = C[array[j]] - 1
        A[index] = array[j]
        C[array[j]]--
    }

}

/**
 * Calls the countingSortx function with the correct parameters.
 * 
 * @param A The array to sort.
 */
fun countingSort(A: Array<Int>){
    countingSortx(A, A.size, A.max())
}

/**
 * Modified version of the counting sort algorithm for the radix sort algorithm.
 * 
 * @param A The array to sort.
 * @param n The size of the array.
 * @param k The maximum value of the array.
 * @param index The index of the digit to sort.
 */
fun countingSortRedix(A: Array<Int>, n: Int, k: Int, index: Int) {
        val array = A.clone()
        val C = Array<Int>(k+1) {it * 0}

        // Count the occurrences of each element in the input array
        for (i in 0 until n) {
            val digit = (array[i] / index) % 10
            C[digit]++
        }

        // Cumulatively sum the count array
        for (i in 1 until k+1) {
            C[i] += C[i - 1]
        }

        // Iterate over the input array from the end
        for (i in n - 1 downTo 0) {
            val digit = (array[i] / index) % 10
            A[C[digit] - 1] = array[i]
            C[digit]--
        }

/**
 * Sorts an array of integers using the radix sort algorithm.
 * 
 * @param A The array to sort.
 */
}
fun radixSort(A: Array<Int>){
    val max = A.max()
    var posicion = 1
    while((max/posicion) > 0){
        countingSortRedix(A,A.size,max,posicion)
        posicion *= 10
    }    
}

