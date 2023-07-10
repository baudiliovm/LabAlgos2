class Palabra(val valor: String) {

    var siguiente: Palabra? = null
    var anterior: Palabra? = null
    
    companion object {
        private fun length(palabra: String) = palabra.length
        fun esLetraValida(letra: String): Boolean {
            if (length(letra) != 1) {
                return false
            }
            val regex = Regex("[a-zñ]")
            return regex.matches(letra)
        }
    }

    public fun esPalabraValida(): Boolean {
        if (length(valor) < 0) {
            return false
        }
        for (letra in 0 until length(valor)) {
            if (!esLetraValida(valor[letra].toString())) {
                return false
            }
        }
        return true
    }
    
    /**
     * Representación en String de la entrada de clave y valor
     */
    override fun toString(): String {
        return valor
    }
}