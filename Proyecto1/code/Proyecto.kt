import kotlin.math.sqrt
import java.io.File
import kotlin.collections.forEach
import java.lang.*
import java.util.*
import kotlin.math.roundToInt
import distanciaTotal

/**
 * Funcion que calcula la distancia ganada al unir dos lados
 * 
 * @param dOLD1 Distancia del lado 1
 * @param dOLD2 Distancia del lado 2
 * @param dNEW1 Distancia del lado 1 al unirlo con el lado 2
 * @param dNEW2 Distancia del lado 2 al unirlo con el lado 1
 */
fun distanciaGanada(
    dOLD1: Int, 
    dOLD2: Int, 
    dNEW1: Int ,
    dNEW2: Int
): Int {
    return (dNEW1 + dNEW2) - (dOLD1 + dOLD2)
}

/** 
 * Funcion que calcula la distancia entre dos lados
 * 
 * @param a Arreglo de pares de coordenadas
 * @return Distancia entre los dos lados
 */
fun distancia(a: Array<Pair<Double, Double>>): Int {
    val x = a[1].first - a[0].first
    val y = a[1].second - a[0].second
    return sqrt(x*x + y*y).roundToInt()
}


/**
 * Funcion que calcula la distancia entre dos puntos
 * 
 * @param a Par de coordenadas del primer punto
 * @param b Par de coordenadas del segundo punto
 * @return Distancia entre los dos puntos
 */
fun distanciaDNew(a: Pair<Double, Double>, b: Pair<Double, Double>): Int{
    val x = b.first - a.first
    val y = b.second - a.second
    return sqrt(x*x  + y*y).roundToInt()
}

/**
 * Funcion que calcula la distancia total de un ciclo
 * 
 * @param P Arreglo de arreglos de pares de coordenadas
 * @return Distancia total del ciclo
 */
fun distanciaTotal(P: Array<Array<Pair<Double, Double>>>): Int {
    var distanciaTotal = 0.0
    for (i in 0 until P.size) {
        distanciaTotal += distancia(P[i])
    }
    return distanciaTotal.roundToInt()
}

/**
 * Funcion que calcula el minimo entre dos enteros
 * 
 * @param a Primer entero
 * @param b Segundo entero
 * @return Minimo entre los dos enteros
 */
fun min(a: Int, b: Int): Int {
    return if (a < b) a else b
}


/**
 * Funcion que combina dos ciclos
 * 
 * @param A Arreglo de arreglos de pares de coordenadas
 * @param B Arreglo de arreglos de pares de coordenadas
 * @return Arreglo de arreglos de pares de coordenadas
 */
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

    var minG = Int.MAX_VALUE  // INfinito REVISAR

    var ladosAgregarC1 = arrayOf<Array<Pair<Double, Double>>>()
    var ladosAgregarC2 = arrayOf<Array<Pair<Double, Double>>>()
    var ladosEliminarC1 = arrayOf<Array<Pair<Double, Double>>>()
    var ladosEliminarC2 = arrayOf<Array<Pair<Double, Double>>>()
    
    for (lado in A) {
        
        val dOLD1 = distancia(lado)

        for (ladoB in B) {

            val dOLD2 = distancia(ladoB)
            val dNEW1 = distanciaDNew(lado[0], ladoB[0])
            val dNEW2 = distanciaDNew(lado[1], ladoB[1])
            val dNEW3 = distanciaDNew(lado[0], ladoB[1])
            val dNEW4 = distanciaDNew(lado[1], ladoB[0])
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

    var nuevoA = arrayOf<Array<Pair<Double, Double>>>()
    var nuevoB = arrayOf<Array<Pair<Double, Double>>>()
    var contadorA = 0
    var contadorB = 0   
    for (lado in A) {
        var agregar = true
        for (ladoEliminar in ladosEliminarC1) {
            if (contadorA == ladosEliminarC1.size){
                break
            }
            if (lado[0] == ladoEliminar[0] && lado[1] == ladoEliminar[1]) {
                contadorA++
                agregar = false
            }
        }
        if (agregar == true) {
            nuevoA += arrayOf<Pair<Double, Double>>(lado[0], lado[1])
            }
        
    }
    
     for (lado in B) {
        var agregar = true
        for (ladoEliminar in ladosEliminarC2) {
            if (contadorB == ladosEliminarC2.size){
                break
            }
            if (lado[0] == ladoEliminar[0] && lado[1] == ladoEliminar[1]) {
                contadorB++
                agregar = false
            }
        }
        if (agregar == true) {
            nuevoB += arrayOf<Pair<Double, Double>>(lado[0], lado[1])
            }
        
    }

    val c = nuevoA + ladosAgregarC1 + ladosAgregarC2 + nuevoB

    return c
    
}


/**
 * Combina 
 */
fun merge(
    U: Array<Pair<Double, Double>>, 
    V: Array<Pair<Double, Double>>, 
    T: Array<Pair<Double, Double>>, 
    ejeDeCorte: String
) {
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

/**
 * Intercambia dos elementos de un arreglo de pares de coordenadas
 * 
 * @param A Arreglo de pares de coordenadas
 * @param i Indice del primer elemento a intercambiar
 * @param j Indice del segundo elemento a intercambiar
 */
fun swap(A: Array<Pair<Double, Double>>, i: Int, j: Int) {
    var temp = A[i]
    A[i] = A[j]
    A[j] = temp
}

/**
 * Insertion sort para ordenar un arreglo de pares de coordenadas
 * 
 * @param A Arreglo de pares de coordenadas
 * @param ejeDeCorte Eje de corte para ordenar el arreglo
 */
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

/** 
 * Ordena un arreglo de pares de coordenadas usando mergesort con insertion sort
 * modificado
 * 
 * @param T Arreglo de pares de coordenadas
 * @param ejeDeCorte Eje de corte para ordenar el arreglo
 */
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

/**
 * Obtiene las coordenadas del rectangulo que contiene a todos los puntos
 * 
 * @param P Arreglo de pares de coordenadas
 * @return Par de pares de coordenadas que representan las coordenadas del rectangulo
 */
fun obtenerCoordenadasRectangulo(
    P: Array<Pair<Double, Double>>
): Pair<Pair<Double, Double>, Pair<Double, Double>> {
    val xCoords = P.map { it.first }
    val yCoords = P.map { it.second }
    val xMin = xCoords.minOrNull() ?: 0.0
    val xMax = xCoords.maxOrNull() ?: 0.0
    val yMin = yCoords.minOrNull() ?: 0.0
    val yMax = yCoords.maxOrNull() ?: 0.0
    return Pair(Pair(xMin, yMin), Pair(xMax, yMax))
}

/**
 * Obtiene el punto de corte de un arreglo de pares de coordenadas
 * 
 * @param P Arreglo de pares de coordenadas
 * @param ejeDeCorte Eje de corte para obtener el punto
 * @return Par de coordenadas que representan el punto de corte
 */
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

/**
 * Aplica el corte a un rectangulo
 * 
 * @param ejeDeCorte Eje de corte para aplicar el corte
 * @param corte Par de coordenadas que representan el punto de corte
 * @param rectangulo Par de pares de coordenadas que representan las coordenadas del rectangulo
 * @return Par de pares de coordenadas que representan los rectangulos resultantes del corte
 */
fun aplicarCorte(
    ejeDeCorte: String, 
    corte: Pair<Double, Double>, 
    rectangulo: Pair<Pair<Double, Double>, Pair<Double, Double>>
): Pair<Pair<Pair<Double, Double>, Pair<Double, Double>>, Pair<Pair<Double, Double>, Pair<Double, Double>>> {
    val (xc, yc) = corte
    val (rectanguloIzq, rectanguloDer) = if (ejeDeCorte == "X") {
        val rectanguloIzq = Pair(rectangulo.first, Pair(xc, rectangulo.second.second))
        val rectanguloDer = Pair(Pair(xc+1, rectangulo.first.second), rectangulo.second)
        Pair(rectanguloIzq, rectanguloDer)
    } else {
        val rectanguloIzq = Pair(rectangulo.first, Pair(rectangulo.second.first, yc))
        val rectanguloDer = Pair(Pair(rectangulo.first.first, yc+1), rectangulo.second)
        Pair(rectanguloIzq, rectanguloDer)
    }
    return Pair(rectanguloIzq, rectanguloDer)
}

/**
 * Obtiene el punto de corte de un rectangulo en la mitad
 * 
 * @param rectangulo Par de pares de coordenadas que representan las coordenadas del rectangulo
 * @param eje Eje de corte para obtener el punto
 * @return Par de coordenadas que representan el punto de corte
 */
fun obtenerPuntoDeCorteMitad(
    rectangulo: Pair<Pair<Double, Double>, Pair<Double, Double>>,
    eje: String
): Pair<Double, Double> {
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

/**
 * Obtiene los puntos de un rectangulo
 * 
 * @param P Arreglo de pares de coordenadas
 * @param rectangulo Par de pares de coordenadas que representan las coordenadas del rectangulo
 * @return Arreglo de pares de coordenadas que representan los puntos del rectangulo
 */
fun obtenerPuntosRectangulo(
    P: Array<Pair<Double, Double>>, 
    rectangulo: Pair<Pair<Double, Double>, Pair<Double, Double>>
): Array<Pair<Double, Double>> {
    val (xMin, yMin) = rectangulo.first
    val (xMax, yMax) = rectangulo.second
    val particion = P.filter { (x, y) -> x >= xMin && x <= xMax && y >= yMin && y <= yMax }.toTypedArray()
    return particion
}

/**
 * Obtiene las dimensiones de un rectangulo
 * 
 * @param rectangulo Par de pares de coordenadas que representan las coordenadas del rectangulo
 * @return Par de coordenadas que representan las dimensiones del rectangulo
 */
fun obtenerDimensiones(
    rectangulo: Pair<Pair<Double, Double>, Pair<Double, Double>>
): Pair<Double,Double>{
    val (xMin, yMin) = rectangulo.first
    val (xMax, yMax) = rectangulo.second
    val xDim = xMax - xMin
    val yDim = yMax - yMin
    return Pair(xDim,yDim) 
}

/**
 * Obtiene las particiones de un arreglo de pares de coordenadas
 * 
 * @param P Arreglo de pares de coordenadas
 * @return Par de arreglos de pares de coordenadas que representan las particiones
 */
fun obtenerParticiones(
    P: Array<Pair<Double, Double>>
): Pair<Array<Pair<Double, Double>>, Array<Pair<Double, Double>>> {
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

/**
 * Dado un arreglo de pares de coordenadas, obtiene el tour de puntos mas cercanos
 * 
 * @param P Arreglo de pares de coordenadas
 * @return Arreglo de arreglos de pares de coordenadas que representan el tour de puntos mas cercanos
 */
fun divideAndConquerTSP(P: Array<Pair<Double, Double>>): Array<Array<Pair<Double, Double>>> {
    val n = P.size
    if (n == 0) {
        val tour = arrayOf(arrayOf(Pair(0.0,0.0),Pair(0.0,0.0)))
        return tour  
    }
    else if (n == 1) {
        val tour = arrayOf(arrayOf(P[0],P[0]))
        return tour
    }
    else if (n == 2){
        val tour = arrayOf(arrayOf(P[0],P[1]),arrayOf(P[1],P[0]))
        return tour
    }
    else if (n == 3){
        val tour =  arrayOf(arrayOf(P[0],P[1]), arrayOf(P[1],P[2]), arrayOf(P[2],P[1]))
        return tour
    }
    else {
        val (P1, P2) = obtenerParticiones(P)
        val c1 = divideAndConquerTSP(P1)
        val c2 = divideAndConquerTSP(P2)
        return combinarCiclos(c1, c2)

    }
}

/**
 * Retorna una ruta alternativa a la ruta dada en reversa
 * 
 * @param P Arreglo de arreglos de pares de coordenadas que representan la ruta
 * @param i Indice de inicio de la ruta alternativa
 * @param j Indice de fin de la ruta alternativa
 * @return Arreglo de arreglos de pares de coordenadas que representan la ruta alternativa
 */
fun optswap(
    P: Array<Array<Pair<Double, Double>>>, 
    i: Int, 
    j: Int
): Array<Array<Pair<Double, Double>>> {
    var nuevaRuta = P.clone()
    for (k in i..j) {
        nuevaRuta[k] = P[j - k + i]  // Reversa
    }
    return nuevaRuta
}

/**
 * Retorna una mejora del tour dado buscanco una distancia menor
 * 
 * @param P Arreglo de arreglos de pares de coordenadas que representan la ruta
 * @return Arreglo de arreglos de pares de coordenadas que representan la ruta mejorada
 */
fun busquedaLocalCon20PT(
    P: Array<Array<Pair<Double, Double>>>
): Array<Array<Pair<Double, Double>>> {

    var huboMejora = true
    var n = P.size
    var rutaExistente = P.clone()

    while (!huboMejora) {
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
    return rutaExistente
}


/**
 * Dado un arreglo de pares de coordenadas, obtiene el tour de puntos mas cercanos 
 * con busqueda local y el algoritmo 2opt
 * 
 * @param P Arreglo de pares de coordenadas
 * @return Arreglo de arreglos de pares de coordenadas que representan el tour de puntos mas cercanos
 */
fun divideAndConquerAndLocalSearchTSP(
    P: Array<Pair<Double, Double>>
): Array<Array<Pair<Double, Double>>> {
    val c1 = divideAndConquerTSP(P)
    println(distanciaTotal(c1))
    return busquedaLocalCon20PT(c1)
    
}

/**
 * Retorna un arreglo con la posicion de las ciudades visitadas en el tour
 * 
 * @param ciudades Arreglo de pares de coordenadas que representan las ciudades
 * @param tour Arreglo de arreglos de pares de coordenadas que representan el tour
 * @return Arreglo de enteros que representan la posicion de las ciudades visitadas en el tour
 */
fun ciudadesVisitadas(ciudades: Array<Pair<Double, Double>>, tour: Array<Array<Pair<Double, Double>>>): Array<Int>{
    val a = Array<Int>(tour.size+1) {1}
    a[0] = ciudades.indexOf(tour[0][0])+1
    a[1] = ciudades.indexOf(tour[0][1])+1
    for (i in 2 until tour.size+1){
        if(tour[i-2].indexOf(tour[i-1][0]) == -1){
            a[i] = ciudades.indexOf(tour[i-1][0])+1
        }
        if(tour[i-2].indexOf(tour[i-1][1]) == -1){
            a[i] = ciudades.indexOf(tour[i-1][1])+1
        }
    }
    return a
}

/**
 * Retorna un arreglo con la posicion de las ciudades dado un archivo
 * 
 * @param archivo String que representa el nombre del archivo
 * @return Arreglo de enteros que representan la posicion de las ciudades visitadas en el tour
 */
fun archivoCiudades(archivo: String): Array<Pair<Double, Double>> {
    val lineas:Array<String> = File(archivo).readLines().toTypedArray()
    var ciudades = Array<Pair<Double, Double>>(0) { Pair(0.0, 0.0) }
    
    for (linea in lineas) {
        val lineSplit = linea.split("\\s".toRegex()).toTypedArray()
        try {
            lineSplit[0].toInt()        // Verifica si es un número
        } catch (e: NumberFormatException) {
            continue
        }
        val x = lineSplit[1].toDouble()
        val y = lineSplit[2].toDouble()
        ciudades += Pair(x, y)          // Agrega la ciudad a la lista
    }
    return ciudades
}

/** 
 * Dada unas ciudades y un archivo, escribe el archivo de salida
 * 
 * @param ciudades Arreglo de arreglos de pares de coordenadas que representan el tour
 * @param archivo String que representa el nombre del archivo
 * @param archivoInicial String que representa el nombre del archivo inicial
 */
fun ciudadesArchivo(ciudades: Array<Array<Pair<Double, Double>>>, archivo: String, archivoInicial: String) {
    val writer = File("${archivo}.txt").bufferedWriter()

    writer.write("NAME: $archivoInicial\nCOMMENT : Length ${distanciaTotal(ciudades)}\nTYPE : TOUR\nDIMENSION : ${ciudades.size}\nTOUR_SECTION\n")
    for (i in 0 until ciudades.size) {
        writer.write("(${ciudades[i][0].first.toInt()},${ciudades[i][0].second.toInt()}) (${ciudades[i][1].first.toInt()},${ciudades[i][1].second.toInt()})\n")
    }
    writer.write("-1\nEOF")
    writer.close()
    println(distanciaTotal(ciudades))
}


fun main(args: Array<String>) {
    val nombreArchivo = args[0]
    val nombreArchivoFinal = args[1]
    println(nombreArchivo)
    
    val ciudades = archivoCiudades(nombreArchivo)   
    val tour = divideAndConquerAndLocalSearchTSP(ciudades)
    ciudadesArchivo(tour,nombreArchivoFinal,nombreArchivo)


}