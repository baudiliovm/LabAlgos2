import kotlin.math.abs
import kotlin.math.pow
import kotlin.math.sqrt
import kotlin.random.Random
import kotlin.system.exitProcess

/**
 * Checks if an array of integers is sorted.
 *
 * @param A The array to check.
 */
fun checkIsSorted(A: Array<Int>) {
    for (i in 0 until (A.size - 1)) {
        if (A[i] > A[i + 1]) {
            println("\nNot sorted! Returning...\n")
            exitProcess(1)
        }
    }
}

/**
 * Generates an array of integers with random values between a and b, inclusive.
 *
 * @param n The number of elements in the array.
 * @param a The minimum value of the random integers.
 * @param b The maximum value of the random integers.
 * @return An array of integers with random values between a and b, inclusive.
 */
fun randArray(n: Int, a: Int, b: Int): Array<Int> {
    var array = Array<Int>(n) { abs(Random.nextInt()) % (b - a + 1) + a }
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
 * Creates an array of integers that are sorted in ascending orden until n/2, then reversed
 *
 * @param n The number of elements in the array.
 * @return An array of integers that are sorted in ascending orden until n/2, then reversed.
 */
fun midArray(n: Int): Array<Int> {
    var am = sortedArray(abs(n / 2))
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
fun standardDeviation(a: Double, b: Array<Double>): Double {
    var deviations = 0.0
    for (i in 0 until (b.size - 1)) {
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
fun timeSort(A: Array<Int>, sortFunction: (Array<Int>) -> Unit, sortFunctionName: String, t: Int) {
    var averageTime = 0.0
    val arrayTimes = Array<Double>(t) { 0.0 }

    repeat(t) { it ->
        val aClone = A.clone()
        val begin = System.nanoTime()
        sortFunction(aClone)
        val end = System.nanoTime()

        // Check if the array is sorted
        checkIsSorted(aClone)

        val timeInSeg = (end - begin) / 1e9

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
    println("Error: $message")
    println("Use syntax -> ./runSortlib.sh [-t int] [-s <secuence>] [-n int]")
    exitProcess(1)
}

fun verify(a: Array<String>): Array<String> {
    val validSequences = arrayOf("random", "zu", "sorted", "inv", "media")
    var s: String? = null
    var t: Int? = null
    var n: Int? = null

    for (i in 0 until a.size) {
        when (a[i]) {
            "-s" -> s = a[i + 1].toString()
            "-t" -> {
                try {
                    t = a[i + 1].toInt()
                } catch (ex: NumberFormatException) {
                    errorMessage("t must be a number.")
                    exitProcess(1)
                }
            }
            "-n" -> {
                try {
                    n = a[i + 1].toInt()
                } catch (ex: NumberFormatException) {
                    errorMessage("n must be a number.")
                    exitProcess(1)
                }
            }
        }
    }

    if (s == null || t == null || n == null) {
        errorMessage("missing argument.")
        exitProcess(1)
    }

    if (t <= 0 || n < 0) {
        errorMessage("integer arguments must be positive.")
        exitProcess(1)
    }

    if (!validSequences.contains(s)) {
        errorMessage("invalid sequence \nMust be the follow options: 'random', 'zu', 'sorted', 'inv', 'media'")
        exitProcess(1)
    }

    var x = t.toString()
    var y = n.toString()

    return arrayOf(s, x, y)
}

fun runAllSorts(args: Array<String>) {
    // Get n, t, s from args
    var s = args[0]
    val t = args[1].toInt()
    val n = args[2].toInt()

    val sortFunctions = arrayOf(::bubbleSort, ::insertionSort, ::selectionSort, ::shellSort)

    val sortFunctionNames = arrayOf("Bubble", "Insertion", "Selection", "Shell")

    // Test arrays
    val sortedA = sortedArray(n)
    val invA = sortedA.reversedArray()

    /*
            val testCases = arrayOf(
                randArray(n, 1, n), // "random"
                randArray(n, 0, 1), // "zu"
                sortedA,            // "sorted"
                invA,               // "inv"
                midArray(n)         // "media"
                )
            val testCasesNames = arrayOf("random", "zu", "sorted", "inv", "media")
    */
    var w = invA

    when (s) {
        "random" -> w = randArray(n, 1, n)
        "zu" -> w = randArray(n, 0, 1)
        "sorted" -> w = sortedA
        "inv" -> w = invA
        "media" -> w = midArray(n)
    }

    println("Sequence: ${s}")
    for (i in 0 until sortFunctions.size) {
        print("\n")
        timeSort(w, sortFunctions[i], sortFunctionNames[i], t)
    }
}

fun main(args: Array<String>) {
    var data = verify(args)
    runAllSorts(data)
}
