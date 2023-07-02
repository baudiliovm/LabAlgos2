/**
 * Representa la entrada de la tabla hash con clave y valor
 * 
 * @param clave la clave del elemento
 * @param valor el valor del elemento
 */
class CuckooHashTableEntry(val clave: Int, val valor: String){

    /**
     * Representación en String de la entrada de clave y valor
     */
    override fun toString(): String {
        return "[$clave, $valor]"
    }
}
