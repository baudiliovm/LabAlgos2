

fun divideAndConquerTSP(P: Array<Pair<Double, Double>>): Array<Array<Pair<Pair<Double, Double>, Pair<Double, Double>>>> {
    val n = P.size
    if (n == 0) {
        val tour = arrayOf(arrayOf(Pair(Pair(0.0,0.0),Pair(0.0,0.0))))
        return tour  
    }
    else if (n == 1) {
        val tour = arrayOf(arrayOf(Pair(P[0],P[0]),Pair(P[0],P[0])))
        return tour
    }
    else if (n == 2){
        val tour = arrayOf(arrayOf(Pair(P[0],P[1]),Pair(P[0],P[1])))
        return tour
    }
    else if (n == 3){
        val tour = arrayOf(arrayOf(Pair(P[0],P[1]),Pair(P[0],P[3]),Pair(P[1],P[3])))
        return tour
    }
    else {
        val (P1, P2) = obtenerParticiones(P) 
        val c1 = divideAndConquerTSP(P1)
        val c2 = divideAndConquerTSP(P2)
        return combinarCiclos(c1, c2)
    }
}