class CodigoMorse {
    private val arbolMorse: Nodo

    init {
        arbolMorse = Nodo()
        val letras = arrayOf("E", "T", "I", "A", "N", "M", "S", "U", "R", "W", "D", "K", "G", "O", "H", "V", "F", "", "L", "", "P", "J", "B", "X", "C", "Y", "Z", "Q")
        val codigos = arrayOf(".", "-", "..", ".-", "-.", "--", "...", "..-", ".-.", ".--", "-..", "-.-", "--.", "---",
            "....", "...-", "..-.", "", ".-..", "", ".--.", ".---",
            "-...", "-..-", "-.-.", "-.--",
            "--..","--.-")
        for (i in letras.indices) {
            insertar(arbolMorse, codigos[i], letras[i])
        }
    }

    private fun insertar(nodo: Nodo, codigo: String, letra: String) {
        if (codigo.isEmpty()) {
            nodo.letra = letra
            return
        }
        if (codigo[0] == '.') {
            if (nodo.izquierda == null) {
                nodo.izquierda = Nodo()
            }
            insertar(nodo.izquierda!!, codigo.substring(1), letra)
        } else {
            if (nodo.derecha == null) {
                nodo.derecha = Nodo()
            }
            insertar(nodo.derecha!!, codigo.substring(1), letra)
        }
    }
}