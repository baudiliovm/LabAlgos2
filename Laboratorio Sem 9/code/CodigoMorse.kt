/**
 * Clase que representa un arbol de Codigo Morse y permite decodificar mensajes
 * dados en codigo morse
 */
public class CodigoMorse {
    private val arbolMorse: NodoArbol
    /**
     * Inicializa el arbol de Codigo Morse
     */
    init {
        println("Creamos un arbol de Codigo Morse...\n")
        arbolMorse = NodoArbol()
        val letras = arrayOf("E", "T", "I", "A", "N", "M", "S", "U", "R", "W", "D", "K", "G", "O", "H", "V", "F", "", "L", "", "P", "J", "B", "X", "C", "Y", "Z", "Q")
        val codigos = arrayOf(".", "-", "..", ".-", "-.", "--", "...", "..-", ".-.", ".--", "-..", "-.-", "--.", "---",
            "....", "...-", "..-.", "", ".-..", "", ".--.", ".---",
            "-...", "-..-", "-.-.", "-.--",
            "--..","--.-")
        for (i in letras.indices) {
            insertar(arbolMorse, codigos[i], letras[i])
        }
    }

    /** 
     * Inserta un codigo en el arbol
     * 
     * @param nodo Nodo actual
     * @param codigo Codigo a insertar
     * @param letra Letra a insertar
     */
    private fun insertar(nodo: NodoArbol, codigo: String, letra: String) {
        if (codigo.isEmpty()) {
            nodo.letra = letra
            return
        }
        if (codigo[0] == '.') {
            if (nodo.izquierda == null) {
                nodo.izquierda = NodoArbol()
            }
            insertar(nodo.izquierda!!, codigo.substring(1), letra)
        } else {
            if (nodo.derecha == null) {
                nodo.derecha = NodoArbol()
            }
            insertar(nodo.derecha!!, codigo.substring(1), letra)
        }
    }

    /**
     * De una secuencia de codigo morse, decodifica la letra correspondiente
     * 
     * @param secuencia Secuencia de codigo morse
     * @return Letra decodificada
     */
    fun decodificarLetra(secuencia: String): String? {
        var nodoActual = arbolMorse
        for (simbolo in secuencia) {
            if (simbolo == '.') {
                if (nodoActual.izquierda != null) {
                    nodoActual = nodoActual.izquierda!!
                } else {
                    return null
                }
            } else if (simbolo == '-') {
                if (nodoActual.derecha != null) {
                    nodoActual = nodoActual.derecha!!
                } else {
                    return null
                }
            } else {
                return null
            }
        }
        return nodoActual.letra
    }


    /**
     * Decodifica un mensaje en codigo morse
     * 
     * @param frase Frase en codigo morse
     * @return Frase decodificada
     */
    fun decodificarMensaje(frase: String): String? {
        val palabras = frase.split("/")
        val resultado = StringBuilder()
        for (palabra in palabras) {
            val letras = palabra.split(" ")
            for (letra in letras) {
                val decodificacion = decodificarLetra(letra)
                if (decodificacion != null) {
                    resultado.append(decodificacion)
                } else {
                    return "Error: Codigo Morse invalido"
                }
            }
            resultado.append(" ")
        }
        return "Mensaje decodificado: ${resultado.toString().trim()}"
    }
}