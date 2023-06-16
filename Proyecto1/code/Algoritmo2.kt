fun obtenerCoordenadasRectangulo(P: Array<Pair<Int, Int>>): Pair<Pair<Int, Int>, Pair<Int, Int>> {
    val xCoords = P.map { it.first }
    val yCoords = P.map { it.second }
    val xMin = xCoords.minOrNull() ?: 0
    val xMax = xCoords.maxOrNull() ?: 0
    val yMin = yCoords.minOrNull() ?: 0
    val yMax = yCoords.maxOrNull() ?: 0
    return Pair(Pair(xMin, yMin), Pair(xMax, yMax))
}


fun obtenerPuntoDeCorte(P: Array<Pair<Int, Int>>, ejeDeCorte: String): Pair<Int, Int> {
    val n = P.size
    val pos = (n / 2) - 1
    if (ejeDeCorte == "X") {
        P.sortWith(compareBy({ it.first }, { it.second }))
    } else {
        P.sortWith(compareBy({ it.second }, { it.first }))
    }
    return P[pos]
}

fun aplicarCorte(ejeDeCorte: String, corte: Pair<Int, Int>, rectangulo: Pair<Pair<Int, Int>, Pair<Int, Int>>): Pair<Pair<Pair<Int, Int>, Pair<Int, Int>>, Pair<Pair<Int, Int>, Pair<Double, Double>>> {
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


