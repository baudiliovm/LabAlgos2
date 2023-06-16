
fun distanciaGanada(dOLD1: Int, dOLD2: Int, dNEW1: Int, dNEW2: Int): Int {
    return (dNEW1 + dNEW2) - (dOLD1 + dOLD2)
}

fun distancia(a: Int, b: Int): Int {
    return (a + b)
}

fun min(a: Int, b: Int): Int {
    if (a < b) {
        return a
    } else {
        return b
    }
}

fun combinarCiclos(A: Array<Array<Int>>, B: Array<Array<Int>>): Array<Array<Int>> {

    if (A.size == 0) {
        return B
    }
    
    if (B.size == 0) {
        return A
    }

    var minG = 1  // INfinito REVISAR

    var ladosAgregarC1 = arrayOf<Array<Int>>()
    var ladosAgregarC2 = arrayOf<Array<Int>>()
    var ladosEliminarC1 = arrayOf<Array<Int>>()
    var ladosEliminarC2 = arrayOf<Array<Int>>()

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
                        ladosAgregarC1 += arrayOf<Int>(lado[0], ladoB[0])
                        ladosAgregarC2 += arrayOf<Int>(lado[1], ladoB[1])
                    } else {
                        ladosAgregarC1 += arrayOf<Int>(lado[0], ladoB[1])
                        ladosAgregarC2 += arrayOf<Int>(lado[1], ladoB[0])
                    }
                    ladosEliminarC1 += arrayOf<Int>(lado[0], lado[1])
                    ladosEliminarC2 += arrayOf<Int>(ladoB[0], ladoB[1])
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