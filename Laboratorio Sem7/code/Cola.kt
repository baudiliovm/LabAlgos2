class Cola(val MAX: Int) {
    private val contenido = ListaCircular()
    private var size = 0

    fun crearCola(): Cola{
        assert(MAX > 0)
        return Cola(MAX)
    }

    fun encolar(e: Int) {
        if (size < MAX) {
            contenido.agregarAFinal(e)
            size++
        }
    }

    fun desencolar() {
        if (size > 0) {
            contenido.eliminar(contenido.buscar(0))
            size--
        }
    }

    fun primero(): Int? {
        return if (size > 0) contenido.buscar(0)?.value as Int else null
    }

    fun estaVacia(): Boolean {
        return size == 0
    }

    override fun toString(): String {
        var str = "["
        var current = contenido.buscar(0)
        for (i in 0 until size) {
            str += "${current?.value}"
            if (i < size - 1) str += ", "
            current = current?.next
        }
        str += "]"
        return str
    }
}