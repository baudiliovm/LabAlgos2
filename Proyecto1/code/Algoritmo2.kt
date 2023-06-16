fun obtenerRectangulo(array: Array<Array<Int>>): Array<Int> {
  val minX = array.map { it[0] }.min()!!
  val minY = array.map { it[1] }.min()!!
  val maxX = array.map { it[0] }.max()!!
  val maxY = array.map { it[1] }.max()!!

  val rectangulo = arrayOf(minX, minY, maxX - minX, maxY - minY)
  return rectangulo
}


fun obtenerPuntoDeCorte(array: Array<Array<Int>>, ejeDeCorte: String): Array<Int> {
    val n = array.size
    val pos = (n/2) - 1
    if (ejeDeCorte == "X"){
        for (i in 0 until n-1){
            if (array[i][0] > array[i+1][0]){
                val temp = array[i]
                array[i] = array[i+1]
                array[i+1] = temp
            }
            if (array[i][0] == array[i+1][0]){
                if (array[i][1] > array[i+1][1]){
                    val temp = array[i]
                    array[i] = array[i+1]
                    array[i+1] = temp
                }
            }
        }
    }
    else {
        for (i in 0 until n-1){
            if (array[i][1] > array[i+1][1]){
                val temp = array[i]
                array[i] = array[i+1]
                array[i+1] = temp
            }
            if (array[i][0] == array[i+1][0]){
                if (array[i][0] > array[i+1][0]){
                    val temp = array[i]
                    array[i] = array[i+1]
                    array[i+1] = temp
                }
            }
        }
    }
    return array[pos]
}

/*fun aplicarCorte(P: Array<Array<Int>>, ejeDeCorte: String, puntoDeCorte: Array<Int>, rectangle: Array<Int>): Pair<Array<Int, Array<Int> {
  
}
 */

fun obtenerPuntosRectangulo(P: Array<Array<Int>>, rectangulo: Array<Int>): Array<Array<Int>>{
    val minX = xmin = rectangulo[0]
    val maxX = rectangulo[0] + rectangulo[2]
     val points = mutableListOf<Array<Int>>()
    for (x in minX..maxX) {
        for (y in P.indices) {
            if (P[y][0] <= x && x < P[y][1]) {
            points.add(arrayOf(x, y))
            }
        }
    } 
  return points.toTypedArray()
}

fun obtenerPuntoDeCorteMitad(rectangulo: Array<Int>, eje: String): Array<Int> {
  val xmin = rectangulo[0]
  val ymin = rectangulo[1]

  val ancho = rectangulo[2]
  val altura = rectangulo[3]

  val puntoDeCorte = when (eje) {
    "X" -> arrayOf(xmin + ancho / 2, ymin)
    "Y" -> arrayOf(xmin, ymin + altura / 2)
    else -> throw IllegalArgumentException("Invalid axis")
  }
  return puntoDeCorte
}

fun obtenerParticiones(P: Array<Array<Int>>): Pair<Array<Int>, Array<Int>> {
    val rectangulo = obtenerRectangulo(p)
    val (Xdim, Ydim) = rectangulo.dimensions
    val ejeDeCorte = if (Xdim > Ydim) "X" else "Y"
    val puntoDeCorte = obtenerPuntoDeCorte(P, ejeDeCorte)
    val (rectanguloIzq, rectanguloDer ) = aplicarCorte(P,ejeDeCorte,puntoDeCorte,rectangulo)
    val particionIzq = obtenerPuntosRectangulo(P , rectanguloIzq) 
    val particionDer = obtenerPuntosRectangulo(P , rectanguloDer) 
    
    if ((particionIzq.size == 0 && particionDer.size > 3) || (particionIzq.size > 3 && particionDer.size == 0)) {
        val nuevoEjeDeCorte = if (ejeDeCorte == "X") "Y" else "X"
        val nuevoPuntoDeCorte = obtenerPuntoDeCorte(P, nuevoEjeDeCorte)
        val (nuevoRectanguloIzq, nuevoRectanguloDer) = aplicarCorte(P, nuevoEjeDeCorte, nuevoPuntoDeCorte, rectangulo)
        particionIzq = obtenerPuntosRectangulo(P, nuevoRectanguloIzq)
        particionDer = obtenerPuntosRectangulo(P, nuevoRectanguloDer)
        
        if ((particionIzq.size == 0 && particionDer.size > 3) || (particionIzq.size > 3 && particionDer.size == 0)) {
            val puntoCorteMitad = obtenerPuntoDeCorteMitad(rectangulo, nuevoEjeDeCorte)
            val (nuevoRectanguloIzq2, nuevoRectanguloDer2) = aplicarCorte(P, nuevoEjeDeCorte, puntoCorteMitad, rectangulo)
            particionIzq = obtenerPuntosRectangulo(P,nuevoRectanguloIzq2)
            particionDer = obtenerPuntosRectangulo(P,nuevoRectanguloDer2)
        }
    }
    return particionIzq to particionDer
}



