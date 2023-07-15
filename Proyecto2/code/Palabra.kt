
/**
 * Clase de tipo Palabra que contiene el valor de la palabra y los enlaces
 * a la siguiente y anterior palabra
 * 
 * @param valor el valor de la palabra
 * @constructor crea un nodo de tipo Palabra
 */
class Palabra(val valor: String) {

    var siguiente: Palabra? = null
    var anterior: Palabra? = null
    
    companion object {
        /**
         * Funcion para obtener la longitud de una palabra
         * 
         * @param palabra la palabra a obtener su longitud
         * @return la longitud de la palabra
         */
        private fun length(palabra: String) = palabra.length
        
        /**
         * Funcion para verificar si una letra es valida
         * 
         * @param letra la letra a verificar
         * @return true si la letra es valida, false en caso contrario
         */
        fun esLetraValida(letra: String): Boolean {
            if (length(letra) != 1) {
                return false
            }
            val regex = Regex("[a-zñ]")
            return regex.matches(letra)
        }
    }
    
    /**
     * Funcion para verificar si una palabra es valida
     * 
     * @return true si la palabra es valida, false en caso contrario
     */
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