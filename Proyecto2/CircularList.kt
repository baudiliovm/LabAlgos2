class CircularList {

    var cabeza: HashTableEntry? = null

    fun agregarLista(clave: HashTableEntry) {
        clave.siguiente = cabeza
        cabeza?.anterior = clave
        cabeza = clave  
    }

    fun eliminarLista(clave: HashTableEntry) {
        if (clave.anterior == null) cabeza = clave.siguiente
            
        clave.anterior?.siguiente = clave.siguiente
        clave.siguiente?.anterior = clave.anterior
    }

    fun buscarLista(clave: String): HashTableEntry? {
        var c: HashTableEntry? = cabeza
        while (c != null && c.clave != clave) c = c.siguiente
        return c
    }
}


