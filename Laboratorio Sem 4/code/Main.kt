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
 *
 */
fun timeSort(sortFunction: (Array<Int>) -> Unit, n: Int) {
    var averageTime = 0.0
    val arrayTimes = Array<Double>(10) { 0.0 }

    var i = 1
    println("")
    repeat(10) { it ->
        val a = randArray(n, 0, n)
        print("Time for Array ${i++}: ")
        val begin = System.nanoTime()
        sortFunction(a)
        val end = System.nanoTime()
        
        val timeInSeg = (end - begin) / 1e9
        print("${timeInSeg} sec\n")
        
        // Check if the array is sorted
        checkIsSorted(a)
        arrayTimes[it] = timeInSeg
        averageTime += timeInSeg
    }

    averageTime /= 10
    
    val stDev = standardDeviation(averageTime, arrayTimes)
    println("\n  Standard deviation: ${stDev} sec\n  Average time: ${averageTime} sec")
}

fun runAllQuick(argument: Array<String>) {
    var n = argument[0].toInt()

    val sortFunctions = arrayOf(
        ::quicksortClasico,
        ::quicksortThreeWay,
        ::quicksortDualPivot
        )
        
        println("For size: $n")
    for (i in 0 until sortFunctions.size) {
        print("\n")
        println("Function $i -> ${sortFunctions[i].toString()}")
        timeSort(sortFunctions[i], n)
    }
}

fun main(args: Array<String>) {
    runAllQuick(args)
}