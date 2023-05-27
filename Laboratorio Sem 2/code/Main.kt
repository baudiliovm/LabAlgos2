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
 * Computes the time it takes to sort an array of integers using the mergesortInsetionPrueba algorithm.
 * 
 * @param A The array to sort.
 * @param size The size of the array.
 * @param t The number of times to run the algorithm.
 */
fun timeSortIns(
    A: Array<Int>,
    size: Int,
    t: Int
) {
    var averageTime = 0.0
    val arrayTimes = Array<Double>(t) { 0.0 }

    repeat(t) { it ->
        val aClone = A.clone()
        val begin = System.nanoTime()
        mergesortInsertionPrueba(aClone, size)
        val end = System.nanoTime()

        // Check if the array is sorted
        checkIsSorted(aClone)

        val timeInSeg = (end - begin) / 1e9

        arrayTimes[it] = timeInSeg
        averageTime += timeInSeg
    }

    averageTime /= t

    if (t == 1) {
        println("  Execution time: ${averageTime} sec")
    } else {
        val stDev = standardDeviation(averageTime, arrayTimes)
        println("  Standard deviation: ${stDev} sec\n  Average time: ${averageTime} sec")
    }
}

/**
 * Main program that executes the insertion test.
 */

 fun main(args: Array<String>) {
    val n = args[0].toInt()
    val a = createRandomMatrix(n)
    val b = createRandomMatrix(n)
    timeMatrices(a, b, "simple", n, 1)
    timeMatrices(a, b, "strassen", n, 1)
  }
/* fun main() {
    mergesortTest()
}
 */