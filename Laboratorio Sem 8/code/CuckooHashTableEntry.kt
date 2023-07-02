class CuckooHashTableEntry(val key: Int, val value: String){
    override fun toString(): String {
        return "[$key, $value]"
    }
}
