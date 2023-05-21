
/**
 * ------------------------------ MultiplicacionMatrices.kt ------------------------------------
 * Aqui se encuentra dos formas de multiplicar dos matrices de tamano NxN, basadas en los libros
 * Brassard, G., and Bratley, P. Fundamentals of Algorithmics. Prentice Hall, 1996 y  
 * T. Cormen, C. Leirserson, R. R., and Stein, C. Introduction to Algorithms. McGraw Hill, 2022.
 * 
 *
 * Autores: Baudilio Velasquez, Arthur Ortega
 * Fecha: mayo 2023
 * Universidad Simon Bolivar
 */


/**
 * This function multiply two matrices.
 *
 * @param a the first matrix of size Nxn.
 * @param b the second matrix of size Nxn.
 * @param n The size of matrix.
 * @return the matrix resulting from the multiplication.
 */
fun multiplicacionSimpleDeMatrices(a: Array<IntArray>, b: Array<IntArray>, n: Int): Array<IntArray> {
  val product = Array(n) { IntArray(n) }
  for (i in 0 until n) {
    for (j in 0 until n) {
      for (k in 0 until n) {
        product[i][j] += a[i][k] * b[k][j]
      }
    }
  }
  return product
}

/**
 * This function adds two matrices.
 *
 * @param a the first matrix of size Nxn.
 * @param b the second matrix of size Nxn.
 * @return the matrix resulting from the sum.
 */
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

/**
 * This function subtracts two matrices.
 *
 * @param a the first matrix of size Nxn.
 * @param b the second matrix of size Nxn.
 * @return the matrix resulting from the subtraction.
 */
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

/**
 * This function multiply two matrices with strassen algorithm.
 *
 * @param a the first matrix of size Nxn.
 * @param b the second matrix of size Nxn.
 * @param n The size of matrix.
 * @return the matrix resulting from the multiplication.
 */
fun multiplicacionStrassen(a: Array<IntArray>, b: Array<IntArray>, n: Int): Array<IntArray>{
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
    val c = Array(n) { IntArray(n) }
    for (i in 0 until n) {
        for (j in 0 until n) {
            if (i < n / 2) {
                if (j < n / 2) {
                    c[i][j] = c11[i][j]
                } else {
                    c[i][j] = c12[i][j - n / 2]
                }
            } else {
                if (j < n / 2) {
                    c[i][j] = c21[i - n / 2][j]
                } else {
                    c[i][j] = c22[i - n / 2][j - n / 2]
                }
            }
        }
    } 
    return c
}


