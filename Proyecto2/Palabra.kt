class Palabra {
    var palabra: String = ""

    private fun length(): Int {
        return palabra.length
    }
    
    fun esLetraValida(letra: String): Boolean {
        val regex = Regex("[a-zñ]")
        return regex.matches(letra)
    }

    fun esPalabraValida(palabra: String): Boolean {
        if (length() < 0) {
            return false
        }
        for (letra in 0 until length()) {
            if (!esLetraValida(palabra[letra].toString())) {
                return false
            }
        }
        return true
    }
}