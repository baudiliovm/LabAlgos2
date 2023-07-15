import kotlin.math.min

fun distancia(palabra1: String, palabra2: String): Int {
    
    // Crear la matriz
    val matriz = Array(palabra1.length + 1) { IntArray(palabra2.length + 1) }

    // Inicializar la matriz
    for (i in 0..palabra1.length) {
        matriz[i][0] = i
    }
    for (j in 0..palabra2.length) {
        matriz[0][j] = j
    }

    for (i in 1..palabra1.length) {
        for (j in 1..palabra2.length) {
            if (palabra1[i - 1] == palabra2[j - 1]) {
                matriz[i][j] = matriz[i - 1][j - 1]
            } else {
                matriz[i][j] = 1 + min(min(matriz[i - 1][j - 1], matriz[i - 1][j]), matriz[i][j - 1])
            }
        }
    }
    
    // Devolver el valor de la distancia
    return matriz[palabra1.length][palabra2.length]
}