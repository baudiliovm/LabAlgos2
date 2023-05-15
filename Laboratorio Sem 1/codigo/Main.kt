import kotlin.random.Random
import kotlin.math.*
import kotlin.system.exitProcess


/**
 * This function checks if an array of integers is sorted.
 *
 * @param A The array to check.
 * @return true if the array is sorted, false otherwise.
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
 * This function generates an array of integers with random values between a 
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

fun standardDeviation(a: Double, b: Array<Double>): Double{
    var deviations = 0.0
    for (i in 0 until (b.size - 1)){
        deviations += Math.pow((b[i] - a).toDouble(), 2.0)
    }
    deviations = Math.sqrt(deviations / b.size)
    return deviations
}

fun runBubbleSort(a: Array<Int>, t: Int): Array<Int>{
    if (t == 1){
        val begin = System.nanoTime()
        bubbleSort(a)
        val end = System.nanoTime()
         println("Execution time: ${(end-begin)/(1000000000.0)} seconds, function: bubbleSort")
        if (isSorted(a)) { 
            println("Sorted") 
        } 
        else { 
            println("Not sorted")
            exitProcess(0)
        }
        return a 
    }
    else {
        var averageTime: Double = 0.0
        var arrayTimes = Array<Double>(t) { it*0.0 }
        for (i in 0 until t){
            val aClone = a.clone()
            val begin = System.nanoTime()
            bubbleSort(aClone)
            val end = System.nanoTime()
            arrayTimes[i] = (end-begin)/(1000000000.0)
            averageTime += ((end-begin)/1000000000.0)
            println("Execution time: ${(end-begin)/(1000000000.0)}, function: bubbleSort")
            if (isSorted(aClone)) { 
            println("Sorted") 
            } 
            else { 
                println("Not sorted")
                exitProcess(0)
            }
        }
        val sD = standardDeviation(averageTime, arrayTimes)
        println("Average time: ${averageTime} seconds, function: bubbleSort, standard deviation: ${sD}")
        return a
    }
}

fun runInsertionSort(a: Array<Int>, t: Int): Array<Int>{
    if (t == 1){
        val begin = System.nanoTime()
        insertionSort(a)
        val end = System.nanoTime()
        println("Execution time: ${(end-begin)/(1000000000.0)} seconds, function: insertionSort")
        if (isSorted(a)) { 
            println("Sorted") 
        } 
        else { 
            println("Not sorted")
            exitProcess(0)
        }
        return a 
    }
    else {
        var averageTime: Double = 0.0
        var arrayTimes = Array<Double>(t) { it*0.0 }
        for (i in 0 until t){
            val aClone = a.clone()
            val begin = System.nanoTime()
            insertionSort(aClone)
            val end = System.nanoTime()
            arrayTimes[i] = (end-begin)/(1000000000.0)
            averageTime += ((end-begin)/1000000000.0)
            println("Execution time: ${(end-begin)/(1000000000.0)}, function: insertionSort")
            if (isSorted(aClone)) { 
            println("Sorted") 
            } 
            else { 
                println("Not sorted")
                exitProcess(0)
            }
        }
        val sD = standardDeviation(averageTime, arrayTimes)
        println("Average time: ${averageTime} seconds, function: insertionSort, standard deviation: ${sD}")
        return a
    }
}


fun runSelectionSort(a: Array<Int>, t: Int): Array<Int>{
    if (t == 1){
        val begin = System.nanoTime()
        selectionSort(a)
        val end = System.nanoTime()
        println("Execution time: ${(end-begin)/(1000000000.0)} seconds, function: selectionSort")
        if (isSorted(a)) { 
            println("Sorted") 
        } 
        else { 
            println("Not sorted")
            exitProcess(0)
        }
        return a 
    }
    else {
        var averageTime: Double = 0.0
        var arrayTimes = Array<Double>(t) { it*0.0 }
        for (i in 0 until t){
            val aClone = a.clone()
            val begin = System.nanoTime()
            selectionSort(aClone)
            val end = System.nanoTime()
            arrayTimes[i] = (end-begin)/(1000000000.0)
            averageTime += ((end-begin)/1000000000.0)
            println("Execution time: ${(end-begin)/(1000000000.0)}, function: selectionSort")
            if (isSorted(aClone)) { 
            println("Sorted") 
            } 
            else { 
                println("Not sorted")
                exitProcess(0)
            }
        }
        val sD = standardDeviation(averageTime, arrayTimes)
        println("Average time: ${averageTime} seconds, function: selectionSort, standard deviation: ${sD}")
        return a
    }
}

fun runShellSort(a: Array<Int>, t: Int): Array<Int>{
    if (t == 1){
        val begin = System.nanoTime()
        shellSort(a)
        val end = System.nanoTime()
        println("Execution time: ${(end-begin)/(1000000000.0)} seconds, function: shellSort")
        if (isSorted(a)) { 
            println("Sorted") 
        } 
        else { 
            println("Not sorted")
            exitProcess(0)
        }
        return a 
    }
    else {
        var averageTime: Double = 0.0
        var arrayTimes = Array<Double>(t) { it*0.0 }
        for (i in 0 until t){
            val aClone = a.clone()
            val begin = System.nanoTime()
            shellSort(aClone)
            val end = System.nanoTime()
            arrayTimes[i] = (end-begin)/(1000000000.0)
            averageTime += ((end-begin)/1000000000.0)
            println("Execution time: ${(end-begin)/(1000000000.0)}, function: shellSort")
            if (isSorted(aClone)) { 
            println("Sorted") 
            } 
            else { 
                println("Not sorted")
                exitProcess(0)
            }
        }
        val sD = standardDeviation(averageTime, arrayTimes)
        println("Average time: ${averageTime} seconds, function: shellSort, standard deviation: ${sD}")
        return a
    }
}

fun runAllSorts(a: Array<Int>, b: Array<Int>, c: Array<Int>, d: Array<Int>, e: Array<Int>, t: Int): Unit{
    println("random array")
    runBubbleSort(a,t)
    runInsertionSort(a,t)
    runSelectionSort(a,t)
    runShellSort(a,t)

    println("sorted array")
    runBubbleSort(b,t)
    runInsertionSort(b,t)
    runSelectionSort(b,t)
    runShellSort(b,t)

    println("inv array")
    runBubbleSort(c,t)
    runInsertionSort(c,t)
    runSelectionSort(c,t)
    runShellSort(c,t)

    println("zu array")
    runBubbleSort(d,t)
    runInsertionSort(d,t)
    runSelectionSort(d,t)
    runShellSort(d,t)

    println("media array")
    runBubbleSort(e,t)
    runInsertionSort(e,t)
    runSelectionSort(e,t)
    runShellSort(e,t)
}



fun main(args: Array<String>) {
    
    // Get n, t from args
    val n = args[0].toInt()
    val t = args[1].toInt()

    // Arrays
    val arrayAleatorio = randArray(n,1,n)
    val arrayZu = randArray(n,0,1)
    val arraySorted = randArray(n,1,n)
    val arrayInv = randArray(n,1,n)
    arrayInv.sortDescending()
    val arrayMedia = midArray(n)

    runAllSorts(arrayAleatorio,arraySorted,arrayInv,arrayZu,arrayMedia,t) 
}
   
 