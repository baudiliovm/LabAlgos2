import kotlin.random.Random
import kotlin.math.abs
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


fun main(args: Array<String>) {
    val n = args[0].toInt()
   
    val a = randArray(n, 0, n)
    println("Sequence: dual pivot")
    println(a.joinToString(", "))
    quicksortDualPivot(a, 0, n-1)
    println("\nOrdenado: ")
    println(a.joinToString(", "))
}