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

fun sum(a: Array<IntArray>, b: Array<IntArray>): Array<IntArray>{
    val n = a.size
    val c = Array(n) { IntArray(n) }
    for (i in 0 until n) {
        for (j in 0 until n) {
            c[i][j] = a[i][j] + b[i][j]
        }
    }
    return c
}

fun sub(a: Array<IntArray>, b:Array<IntArray>): Array<IntArray> {
    val n = a.size
    val c = Array(n) { IntArray(n) }
    for (i in 0 until n) {
        for (j in 0 until n) {
            c[i][j] = a[i][j] - b[i][j]
        }
    }
    return c
}

fun multiplicacionStrassen(a: Array<IntArray>, b: Array<IntArray>, n: Int): Array<IntArray>{
    
     // If the input matrix is 1x1
    if (n == 1) {
        val result = Array(n) { IntArray(n) }
        for (i in 0 until n) {
            for (j in 0 until n) {
                result[i][j] = a[i][j] * b[i][j]
            }
        }
        return result
    }

    // Divide the input matrices into four submatrices
    // Divide the input matrices into four submatrices
    val a11 = Array(n / 2) { IntArray(n / 2) }
    val a12 = Array(n / 2) { IntArray(n / 2) }
    val a21 = Array(n / 2) { IntArray(n / 2) }
    val a22 = Array(n / 2) { IntArray(n / 2) }
    for (i in 0 until n / 2) {
        for (j in 0 until n / 2) {
            a11[i][j] = a[i][j]
            a12[i][j] = a[i][j + n / 2]
            a21[i][j] = a[i + n / 2][j]
            a22[i][j] = a[i + n / 2][j + n / 2]
        }
    }

    val b11 = Array(n / 2) { IntArray(n / 2) }
    val b12 = Array(n / 2) { IntArray(n / 2) }
    val b21 = Array(n / 2) { IntArray(n / 2) }
    val b22 = Array(n / 2) { IntArray(n / 2) }
    for (i in 0 until n / 2) {
        for (j in 0 until n / 2) {
            b11[i][j] = b[i][j]
            b12[i][j] = b[i][j + n / 2]
            b21[i][j] = b[i + n / 2][j]
            b22[i][j] = b[i + n / 2][j + n / 2]
        }
    }


    // Calculate the seven recursive matrix products.
    val p1 = multiplicacionStrassen(a11, sub(b12,b22), n / 2)
    val p2 = multiplicacionStrassen(sum(a11,a12), b22, n / 2)
    val p3 = multiplicacionStrassen(sum(a21,a22), b11, n / 2)
    val p4 = multiplicacionStrassen(a22, sub(b21,b11), n / 2)
    val p5 = multiplicacionStrassen(sum(a11,a22), sum(b11,b22), n / 2)
    val p6 = multiplicacionStrassen(sub(a12,a22), sum(b21,b22), n / 2)
    val p7 = multiplicacionStrassen(sub(a11,a21), sum(b11,b12), n / 2)

    // Combine the seven recursive results to form the final result.
    val c11 = sum(sub(sum(p5, p4), p2), p6)
    val c12 = sum(p1, p2)
    val c21 = sum(p3, p4)
    val c22 = sub(sub(sum(p5, p1), p3), p7)
    return arrayOf(c11, c12, c21, c22)
}
