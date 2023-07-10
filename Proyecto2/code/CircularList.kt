class CircularList {

    var cabeza: Palabra? = null

    fun agregarLista(clave: Palabra) {
        clave.siguiente = cabeza
        cabeza?.anterior = clave
        cabeza = clave
    }

    fun eliminarLista(clave: Palabra) {
        if (clave.anterior == null) cabeza = clave.siguiente
        clave.anterior?.siguiente = clave.siguiente
        clave.siguiente?.anterior = clave.anterior
    }

    fun buscarLista(clave: String): Palabra? {
        var c: Palabra? = cabeza
        while (c != null && c.valor != clave) c = c.siguiente
        return c
    }

    fun ordenar() {
        var actual = cabeza
        var nodoAnterior: Palabra? = null
        while (actual != null) {
            var siguiente = actual.siguiente
            if (nodoAnterior != null && actual.valor < nodoAnterior.valor) {
                nodoAnterior.siguiente = siguiente
                actual.siguiente = cabeza
                cabeza = actual
            }
            nodoAnterior = actual
            actual = siguiente
        }
    }

}




