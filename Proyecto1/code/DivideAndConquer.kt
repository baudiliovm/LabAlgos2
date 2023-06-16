

fun divideAndConquerTSP(P: Array<Pair<Double, Double>>): Array<Array<Pair<Double, Double>>> {
    val n = P.size
    if (n == 0) return

    else if (n == 1) return 

    else if (n == 2) return

    else if (n == 3) return

    else {
        val (P1, P2) = obtenerParticiones(P) 
        val c1 = divideAndConquerTSP(P1)
        val c2 = divideAndConquerTSP(P2)
        return combinarCiclos(c1, c2)
    }
}