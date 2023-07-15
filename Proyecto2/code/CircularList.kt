/**
 * Clase que implementa una lista circular doblemente enlazada
 */
class CircularList {

    var cabeza: Palabra? = null

    /**
     * Agrega un elemento tipo Palabra a la lista
     * 
     * @param clave la palabra a agregar
     */
    fun agregarLista(clave: Palabra) {
        clave.siguiente = cabeza
        cabeza?.anterior = clave
        cabeza = clave
    }

    /**
     * Elimina un elemento tipo Palabra de la lista
     * 
     * @param clave la palabra a eliminar
     */
    fun eliminarLista(clave: Palabra) {
        if (clave.anterior == null) cabeza = clave.siguiente
        clave.anterior?.siguiente = clave.siguiente
        clave.siguiente?.anterior = clave.anterior
    }

    /**
     * Busca un elemento tipo Palabra en la lista
     * 
     * @param clave la palabra a buscar
     * @return el elemento tipo Palabra si se encuentra, null en caso contrario
     */
    fun buscarLista(clave: String): Palabra? {
        var c: Palabra? = cabeza
        while (c != null && c.valor != clave) c = c.siguiente
        return c
    }
}




