class CodigoMorse() {

    fun decodificarLetra(secuencia: String): String? {
        
        for (caracter in secuencia) {

        }

        return null
    }

    fun decodificarMensaje(frase: Array<String>): String {
        
        var mensaje = ""

        for (letras in frase) {
            if (letras == "/") {
                mensaje += " "
            } else {
                val letra = decodificarLetra(letras)
                mensaje += letra
            }
        }
    
        return mensaje
    }


}