import kotlin.random.Random
import kotlin.math.abs

/**
 * Verifica si un array está ordenado
 * 
 * @param A Array<Int> Array a verificar
 * @return Boolean True si está ordenado, False si no
 */
fun isSorted(A: Array<Int>): Boolean {
    for (i in 0 until (A.size - 1)) {
        if (A[i] > A[i+1]) {
            return false
        }
    }
    return true
}

/**
 * Genera un array de n elementos aleatorios entre a y b
 * 
 * @param n Cantidad de elementos
 * @param a Valor mínimo
 * @param b Valor máximo
 * @return Array<Int> Array de n elementos aleatorios entre a y b
 */
fun randArray(n: Int, a: Int, b: Int): Array<Int> {
    var array = Array<Int>(n) { 
        abs(Random.nextInt()) % (b - a + 1) + a
    }

    return array
}

/**
 * sortedArray()
 *
 * This function returns an array of integers that are sorted in ascending order.
 *
 * @param n The number of elements in the array.
 * @return An array of integers that are sorted in ascending order.
 */
fun sortedArray(n: Int): Array<Int> {
    val array = Array<Int>(n) { it + 1 }
    return array
}

/**
 * concatenate()
 *
 * This function concatenates two arrays of integers.
 *
 * @param a The first array of integers.
 * @param b The second array of integers.
 * @return A new array that contains the elements of both a and b.
 */
fun concatenate(a: Array<Int>, b: Array<Int>): Array<Int> {
    return a + b
}


/**
 * midArray()
 * 
 * This function return an array of integers that are sorted in 
 * ascending orden until n/2, then reversed
 * 
 * @param n The number of elements in the array.
 * @return An array of integers that are sorted in 
 * ascending orden until n/2, then reversed.
 */
fun midArray(n: Int): Array<Int> {
    var am = sortedArray(abs(n/2))
    var amInv = am.clone()
    amInv.sortDescending()
    val concat = concatenate(am, amInv)
    return concat
}

fun main(args: Array<String>) {
    
    // Get n, a, b from args
    val n = args[0].toInt()

    // Arrays
    val arrayAleatorio = randArray(n,1,n)
    val arrayZu = randArray(n,0,1)
    val arraySorted = sortedArray(n)
    val arrayInv = sortedArray(n)
    arrayInv.sortDescending()
    val arrayMedia = midArray(n)

    
    val begin = System.nanoTime()
    insertionSort(arrayAleatorio)
    val end = System.nanoTime()
   
    println("Execution time: ${(end-begin)/(1000000000.0)
    } seconds, function: insertionSort")
    if (isSorted(arrayAleatorio)) { println("Sorted") } else { println("Not sorted") }
}