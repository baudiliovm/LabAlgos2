fun multiplicacionSimpleDeMatrices(a: Array<Array<Int>>, b: Array<Array<Int>>, n: Int): Array<Array<Int>> {
    val product = Array(n) { Array(n) { 0 } }
    for (i in 0 until n) {
        for (j in 0 until n) {
            for (k in 0 until n) {
                product[i][j] += a[i][k] * b[k][j]
            }
        }
    }
    return product
}

fun multiplicacionStrassen(a: Array<Array<Int>>, b: Array<Array<Int>>, n: Int): Array<Array<Int>> {
    // Base case.
    if (n <= 1) {
        return a * b
    }

    // Divide the matrices into submatrices.
    val a11 = 
    val a12 = 
    val a21 = 
    val a22 = 
    val b11 = 
    val b12 = 
    val b21 = 
    val b22 = 

    // Calculate the seven recursive matrix products.
    val p1 = multiplicacionStrassen(a11, b12 - b22, n / 2)
    val p2 = multiplicacionStrassen(a11 + a12, b22, n / 2)
    val p3 = multiplicacionStrassen(a21 + a22, b11, n / 2)
    val p4 = multiplicacionStrassen(a22, b21 - b11, n / 2)
    val p5 = multiplicacionStrassen(a11 + a22, b11 + b22, n / 2)
    val p6 = multiplicacionStrassen(a12 - a22, b21 + b22, n / 2)
    val p7 = multiplicacionStrassen(a11 - a21, b11 + b12 , n / 2)

    // Combine the seven recursive results to form the final result.
    val c11 = p5 + p4 - p2 + p6
    val c12 = p1 + p2
    val c21 = p3 + p4
    val c22 = p5 + p1 - p3 - p7
    return arrayOf(c11, c12, c21, c22)
}
