class HashTableEntry(val clave: Int,val valor: String) {
    var siguiente: HashTableEntry? = null
    var anterior: HashTableEntry? = null

    override fun toString(): String {
        return "[$clave, $valor]"
    }
}