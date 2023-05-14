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
fun randArray(n: Int, a: Int, b: Int): IntArray {
    var array = IntArray(n) { 
        abs(Random.nextInt()) % (b - a + 1) + a
    }

    return array
}

fun main(args: Array<String>) {
    val chus = arrayOf(12, 33, 1, 3, -6, 0)
    insertionSort(chus)
    if (isSorted(chus)) { println("Sorted") } else { println("Not sorted") }

    // Get n, a, b from args
    val n = args[0].toInt()
    val a = args[1].toInt()
    val b = args[2].toInt()

    println(randArray(n, a, b).joinToString(", "))
    println(chus.joinToString(", "))
}