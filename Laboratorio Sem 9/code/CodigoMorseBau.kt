pubblic class CodigoMorse() {


    fun decodificarLetra(secuencia: String): String? {
        
        for (caracter in secuencia) {

        }
        
        return null
    }

    fun decodificarMensaje(frase: Array<String>): String {
        var mensaje = ""
        for (palabra in frase) {
            val letras = palabra.split(" ").toTypedArray()
            for (i in letras) {
                val letra = decodificarLetra(i)
                mensaje += letra
            }
        }
        return mensaje
    }
}