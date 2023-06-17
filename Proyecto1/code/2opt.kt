fun distanciaTotal(P: Array<Array<Pair<Double, Double>>>): Double {
    var distanciaTotal = 0.0
    for (i in 0 until P.size - 1) {
        distanciaTotal += distancia(P[i][0], P[i + 1][0])
    }
    return distanciaTotal
}

fun optswap(P: Array<Array<Pair<Double, Double>>>, i: Int, j: Int): Array<Array<Pair<Double, Double>>> {
    var nuevaRuta = P.clone()

    for (k in 0 until i) {
        nuevaRuta[k] = P[k]
    }
    for (k in i..j) {
        nuevaRuta[k] = P[j - k + i]  // Reversa
    }
    for (k in j+1 until P.size) {
        nuevaRuta[k] = P[k]
    }
    return nuevaRuta
}

fun busquedaLocalCon20PT(
    P: Array<Array<Pair<Double, Double>>>
): Array<Array<Pair<Double, Double>>> {

    var huboMejora = true
    var n = P.size
    var rutaExistente = P.clone()

    while (huboMejora) {
        huboMejora = false
        var mejorDistancia = distanciaTotal(P)
        for (i in 0 until n-1) {
            for (j in i+1 until n) {
                var nuevaRuta = optswap(P, i, j)
                var nuevaDistancia = distanciaTotal(nuevaRuta)
                if (nuevaDistancia < mejorDistancia) {
                    rutaExistente = nuevaRuta
                    mejorDistancia = nuevaDistancia
                    huboMejora = true
                }
            }
        }
    }
}



fun divideAndConquerAndLocalSearchTSP(P: Array<Pair<Double, Double>>) {
    val c1 = divideAndConquerTSP(P)
    return busquedaLocalCon20PT(c1)
}

