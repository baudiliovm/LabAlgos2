fun merge(U: Array<Pair<Double, Double>>, V: Array<Pair<Double, Double>>, T: Array<Pair<Double, Double>>, ejeDeCorte: String) {
    val m = U.size
    val n = V.size
    var i = 0
    var j = 0
    if (ejeDeCorte == "X"){
        for (k in 0 until T.size) {
            if ((i < m) && (j >= n || U[i].first < V[j].first)) {
                T[k] = U[i]
                i += 1
            } else {
                T[k] = V[j]
                j += 1
            }
        }
    }
    else{
        for (k in 0 until T.size) {
            if ((i < m) && (j >= n || U[i].second < V[j].second)) {
                T[k] = U[i]
                i += 1
            } else {
                T[k] = V[j]
                j += 1
            }
        }
    }
}

fun swap(A: Array<Pair<Double, Double>>, i: Int, j: Int) {
    var temp = A[i]
    A[i] = A[j]
    A[j] = temp
}

fun insertionSort(A: Array<Pair<Double, Double>>, ejeDeCorte: String) {
    val n = A.size
    if (ejeDeCorte == "X"){
        for (i in 1 until n) {
            var j = i
            while (j > 0 && A[j].first < A[j - 1].first) {
                swap(A, j, j - 1)
                j = j - 1
            }
        }
    }
    else {
        for (i in 1 until n) {
            var j = i
            while (j > 0 && A[j].second < A[j - 1].second) {
                swap(A, j, j - 1)
                j = j - 1
            }
        }
    } 
}

fun mergesortInsertion(T: Array<Pair<Double, Double>>, ejeDeCorte: String) {
    if (T.size < 20) {
        insertionSort(T,ejeDeCorte)
    } else {
        val floor = T.size / 2
        var U = T.copyOfRange(0, floor)
        var V = T.copyOfRange(floor, T.size)
        mergesortInsertion(U,ejeDeCorte)
        mergesortInsertion(V,ejeDeCorte)
        merge(U, V, T,ejeDeCorte)
    }
}

fun obtenerCoordenadasRectangulo(P: Array<Pair<Double, Double>>): Pair<Pair<Double, Double>, Pair<Double, Double>> {
    val xCoords = P.map { it.first }
    val yCoords = P.map { it.second }
    val xMin = xCoords.minOrNull() ?: 0.0
    val xMax = xCoords.maxOrNull() ?: 0.0
    val yMin = yCoords.minOrNull() ?: 0.0
    val yMax = yCoords.maxOrNull() ?: 0.0
    return Pair(Pair(xMin, yMin), Pair(xMax, yMax))
}


fun obtenerPuntoDeCorte(P: Array<Pair<Double, Double>>, ejeDeCorte: String): Pair<Double, Double> {
    val n = P.size
    val pos = (n / 2) - 1
    if (ejeDeCorte == "X") {
        mergesortInsertion(P,ejeDeCorte)
    } else {
        mergesortInsertion(P,ejeDeCorte)
    }
    return P[pos]
}

fun aplicarCorte(ejeDeCorte: String, corte: Pair<Double, Double>, rectangulo: Pair<Pair<Double, Double>, Pair<Double, Double>>): Pair<Pair<Pair<Double, Double>, Pair<Double, Double>>, Pair<Pair<Double, Double>, Pair<Double, Double>>> {
    val (xc, yc) = corte
    val (rectanguloIzq, rectanguloDer) = if (ejeDeCorte == "X") {
        val rectanguloIzq = Pair(rectangulo.first, Pair(xc, rectangulo.second.second))
        val rectanguloDer = Pair(Pair(xc, rectangulo.first.second), rectangulo.second)
        Pair(rectanguloIzq, rectanguloDer)
    } else {
        val rectanguloIzq = Pair(rectangulo.first, Pair(rectangulo.second.first, yc))
        val rectanguloDer = Pair(Pair(rectangulo.first.first, yc), rectangulo.second)
        Pair(rectanguloIzq, rectanguloDer)
    }
    return Pair(rectanguloIzq, rectanguloDer)
}

fun obtenerPuntoDeCorteMitad(rectangulo: Pair<Pair<Double, Double>, Pair<Double, Double>>, eje: String): Pair<Double, Double> {
    val (xMin, yMin) = rectangulo.first
    val (xMax, yMax) = rectangulo.second

    val puntoDeCorte = if (eje == "X") {
        val xDim = xMax - xMin
        Pair(xMin + xDim / 2, yMin)
    } else {
        val yDim = yMax - yMin
        Pair(xMin, yMin + yDim / 2)
    }
    return puntoDeCorte
}

fun obtenerPuntosRectangulo(P: Array<Pair<Double, Double>>, rectangulo: Pair<Pair<Double, Double>, Pair<Double, Double>>): Array<Pair<Double, Double>> {
    val (xMin, yMin) = rectangulo.first
    val (xMax, yMax) = rectangulo.second
    val particion = P.filter { (x, y) -> x >= xMin && x <= xMax && y >= yMin && y <= yMax }.toTypedArray()
    return particion
}

fun obtenerDimensiones(rectangulo: Pair<Pair<Double, Double>, Pair<Double, Double>>): Pair<Double,Double>{
    val (xMin, yMin) = rectangulo.first
    val (xMax, yMax) = rectangulo.second
    val xDim = xMax - xMin
    val yDim = yMax - yMin
    return Pair(xDim,yDim) 
}

fun obtenerParticiones(P: Array<Pair<Double, Double>>): Pair<Array<Pair<Double, Double>>, Array<Pair<Double, Double>>> {
    val rectangulo = obtenerCoordenadasRectangulo(P)
    val (Xdim, Ydim) = obtenerDimensiones(rectangulo)
    val ejeDeCorte = if (Xdim > Ydim) "X" else "Y"
    val (xc, yc) = obtenerPuntoDeCorte(P, ejeDeCorte)
    var (rectanguloIzq, rectanguloDer) = aplicarCorte(ejeDeCorte, Pair(xc, yc), rectangulo)
    var particionIzq = obtenerPuntosRectangulo(P, rectanguloIzq)
    var particionDer = obtenerPuntosRectangulo(P, rectanguloDer)

    if ((particionIzq.size == 0 && particionDer.size > 3) || (particionIzq.size > 3 && particionDer.size == 0)) {
        val nuevoEjeDeCorte = if (ejeDeCorte == "X") "Y" else "X"
        val (nuevoXc, nuevoYc) = obtenerPuntoDeCorte(P, nuevoEjeDeCorte)
        val (nuevoRectanguloIzq, nuevoRectanguloDer) = aplicarCorte(nuevoEjeDeCorte, Pair(nuevoXc, nuevoYc), rectangulo)
        particionIzq = obtenerPuntosRectangulo(P, nuevoRectanguloIzq)
        particionDer = obtenerPuntosRectangulo(P, nuevoRectanguloDer)

        if ((particionIzq.size == 0 && particionDer.size > 3) || (particionIzq.size > 3 && particionDer.size == 0)) {
            val (nuevoXc2, nuevoYc2) = obtenerPuntoDeCorteMitad(rectangulo, nuevoEjeDeCorte)
            val (nuevoRectanguloIzq2, nuevoRectanguloDer2) = aplicarCorte(nuevoEjeDeCorte, Pair(nuevoXc2, nuevoYc2), rectangulo)
            particionIzq = obtenerPuntosRectangulo(P, nuevoRectanguloIzq2)
            particionDer = obtenerPuntosRectangulo(P, nuevoRectanguloDer2)
        }
    }
    return Pair(particionIzq, particionDer)
}


