/**
 * Clase que representa una entrada de la tabla hash con clave y valor
 * 
 * Sirve de Nodo para la lista enlazada CircularList
 * 
 * @param clave la clave del elemento
 * @param valor el valor del elemento
 */
class HashTableEntry(val clave: Int,val valor: String) {
    var siguiente: HashTableEntry? = null
    var anterior: HashTableEntry? = null

    /**
     * Representación en String de la entrada de clave y valor
     */
    override fun toString(): String {
        return "[$clave, $valor]"
    }
}