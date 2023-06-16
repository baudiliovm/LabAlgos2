import kotlin.math.sqrt

fun distanciaGanada(
    dOLD1: Double, 
    dOLD2: Double, 
    dNEW1: Double, 
    dNEW2: Double
): Double {
    return (dNEW1 + dNEW2) - (dOLD1 + dOLD2)
}

fun distancia(a: Pair<Double,Double>, b: Pair<Double, Double>): Double {
    val x = a.first - b.first
    val y = a.second - b.second
    return sqrt(x*x + y*y)
}

fun min(a: Double, b: Double): Double {
    return if (a < b) a else b
}

fun combinarCiclos(
    A: Array<Array<Pair<Double, Double>>>, 
    B: Array<Array<Pair<Double, Double>>>
): Array<Array<Pair<Double, Double>>> {

    if (A.size == 0) {
        return B
    }
    
    if (B.size == 0) {
        return A
    }

    var minG = 1.0  // INfinito REVISAR

    var ladosAgregarC1 = arrayOf<Array<Pair<Double, Double>>>()
    var ladosAgregarC2 = arrayOf<Array<Pair<Double, Double>>>()
    var ladosEliminarC1 = arrayOf<Array<Pair<Double, Double>>>()
    var ladosEliminarC2 = arrayOf<Array<Pair<Double, Double>>>()

    for (lado in A) {
        do {
            val dOLD1 = distancia(lado[0], lado[1])

            for (ladoB in B) {

                val dOLD2 = distancia(ladoB[0], ladoB[1])
                val dNEW1 = distancia(lado[0], ladoB[0])
                val dNEW2 = distancia(lado[1], ladoB[1])
                val dNEW3 = distancia(lado[0], ladoB[1])
                val dNEW4 = distancia(lado[1], ladoB[0])
                val g1 = distanciaGanada(dOLD1, dOLD2, dNEW1, dNEW2)
                val g2 = distanciaGanada(dOLD1, dOLD2, dNEW3, dNEW4)
                var ganancia = min(g1, g2)

                if (ganancia < minG) {

                    minG = ganancia

                    if (g1 < g2) {
                        ladosAgregarC1 += arrayOf<Pair<Double, Double>>(lado[0], ladoB[0])
                        ladosAgregarC2 += arrayOf<Pair<Double, Double>>(lado[1], ladoB[1])
                    } else {
                        ladosAgregarC1 += arrayOf<Pair<Double, Double>>(lado[0], ladoB[1])
                        ladosAgregarC2 += arrayOf<Pair<Double, Double>>(lado[1], ladoB[0])
                    }
                    ladosEliminarC1 += arrayOf<Pair<Double, Double>>(lado[0], lado[1])
                    ladosEliminarC2 += arrayOf<Pair<Double, Double>>(ladoB[0], ladoB[1])
                }
            }
        }

    }
    for (lados in ladosEliminarC1) {
        A.remove(lados) // No sé si esto funciona
    }
    
    for (lados in ladosEliminarC2) {
        B.remove(lados)
    }
    
    var C = A + ladosAgregarC1 + ladosAgregarC2 + B
    
    return C
    
}


fun divideAndConquerAndLocalSearchTSP(P: Array<Pair<Double, Double>>) {
    val c1 = divideAndConquerTSP(P)
    return busquedaLocalCon20PT(c1) // Por hacer está en wikipedia link en pdf
}