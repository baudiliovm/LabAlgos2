import kotlin.random.Random
import kotlin.math.pow
import kotlin.math.sqrt
import kotlin.math.abs
import kotlin.system.exitProcess

/**
 * Checks if an array of integers is sorted.
 *
 * @param A The array to check.
 */
fun checkIsSorted(A: Array<Int>) {
    for (i in 0 until (A.size - 1)) {
        if (A[i] > A[i+1]) {
            println("\nNot sorted! Returning...\n")
            exitProcess(1)
        }
    }
}

/**
 * Generates an array of integers with random values between a
 * and b, inclusive.
 *
 * @param n The number of elements in the array.
 * @param a The minimum value of the random integers.
 * @param b The maximum value of the random integers.
 * @return An array of integers with random values between a and b, inclusive.
 */
fun randArray(n: Int, a: Int, b: Int): Array<Int> {
    var array = Array<Int>(n) {
        abs(Random.nextInt()) % (b - a + 1) + a
    }
    return array
}

/**
 * Creates an array of integers that are sorted in ascending order.
 *
 * @param n The number of elements in the array.
 * @return An array of integers that are sorted in ascending order.
 */
fun sortedArray(n: Int): Array<Int> = Array<Int>(n) { it + 1 }

/**
 * Creates an array of integers that are sorted in
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
    return am + amInv
}

/**
 * Computes the standard deviation of an array of doubles.
 *
 * @param a The average of the array.
 * @param b The array of doubles.
 */
fun standardDeviation(a: Double, b: Array<Double>): Double{
    var deviations = 0.0
    for (i in 0 until (b.size - 1)){
        deviations += Math.pow((b[i] - a).toDouble(), 2.0)
    }
    return Math.sqrt(deviations / b.size)
}


/**
 * Prints the execution time of a sorting algorithm.
 *
 * @param A The array to sort.
 * @param sortFunction The sorting algorithm to use.
 * @param sortFunctionName The name of the sorting algorithm.
 * @param t The number of times to run the sorting algorithm.
 */
fun timeSort(
    A: Array<Int>,
    sortFunction: (Array<Int>) -> Unit,
    sortFunctionName: String,
    t: Int
) {
    var averageTime = 0.0
    val arrayTimes = Array<Double>(t) { 0.0 }

    repeat(t) { it ->
        val aClone = A.clone()
        val begin = System.nanoTime()
        sortFunction(aClone)
        val end = System.nanoTime()

        // Check if the array is sorted
        checkIsSorted(aClone)

        val timeInSeg = (end-begin) / 1e9

        arrayTimes[it] = timeInSeg
        averageTime += timeInSeg
    }

    averageTime /= t

    println("${sortFunctionName}Sort:")

    if (t == 1) {
        println("  Execution time: ${averageTime} sec")
    } else {
        val stDev = standardDeviation(averageTime, arrayTimes)
        println("  Standard deviation: ${stDev} sec\n  Average time: ${averageTime} sec")
    }
}

// Error handling hasta verify()
/**
 * Prints an error message and exits the program.
 *
 * @param message The error message to print.
 */
fun errorMessage(message: String) {
    println(message)
    println("Error\nUse syntax -> ./runSortlib.sh [-t num] [-s <secuencia>] [-n num]")
    exitProcess(1)
}


fun verify(a: Array<String>): {
    // tengo [-t #num] intentos [-s <secuencia>] secuencia [-n #num] tamaño de array
    
    
    /*  Hacer funcion o tomar de args 
     */

    var t = a[0].toInt()
    var s = a[1].toString()
    var n = a[2].toInt()

    val validFlags = arrayOf("-t", "-s", "-n")
    val validSecuences = arrayOf("random", "zu", "sorted", "inv", "media")

    var flag = 1


    when {
        flag == t ->  {
            // Verificar si -t es un Int>-1
        }
        flag == s -> {
            for (i in 0 until validSecuences.size) {
                if (s != validSecuences[i]) {
                    errorMessage("mal")
                }
            }
        }
        flag == n -> {
            // Verficar que -n sea Int>0
        }
    }
    return Triple(t, s, n)
}

fun verificar(a: Array<String>): {
    val validSequences = arrayOf("random", "zu", "sorted", "inv", "media")
    var s: String? = null
    var t: Int? = null
    var n: Int? = null

    for (i in args.indices) {
        when (args[i]) {
            "-s" -> s = args[i + 1]
            "-t" -> t = args[i + 1].toInt()
            "-n" -> n = args[i + 1].toInt()
        }
    }

    if (s == null || t == null || n == null) {
        println("Error: missing argument")
        exitProcess(1)
    }

    if (t <= 0 || n <= 0) {
        println("Error: integer arguments must be positive")
        exitProcess(1)
    }

    if (!validSequences.contains(s)) {
        println("Error: invalid sequence")
        exitProcess(1)
    }
}




fun runAllSorts(args: Array<String>) {
        // Get n, t, s from args
        val n = args[0].toInt()
        val t = args[1].toInt()

        val sortFunctions = arrayOf(
        ::bubbleSort,
        ::insertionSort,
        ::selectionSort,
        ::shellSort
        )

        val sortFunctionNames = arrayOf(
        "Bubble",
        "Insertion",
        "Selection",
        "Shell"
        )

        // Test arrays
        val sortedA = sortedArray(n)
        val invA = sortedA.reversedArray()

        val testCases = arrayOf(
            randArray(n, 1, n), // "random"
            randArray(n, 0, 1), // "zu"
            sortedA,            // "sorted"
            invA,               // "inv"
            midArray(n)         // "media"
        )

        val testCasesNames = arrayOf("random", "zu", "sorted", "inv", "media")

        for (i in 0 until sortFunctions.size) {
            for (j in 0 until testCases.size) {
                print("${testCasesNames[j]}: ")
                timeSort(testCases[j], sortFunctions[i], sortFunctionNames[i], t)
            }
            println()
        }
}

fun main(args: Array<String>) {
    // verify(args)
    runAllSorts(args)
}