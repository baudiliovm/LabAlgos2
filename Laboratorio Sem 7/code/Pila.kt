import kotlin.system.exitProcess

class Pila(val MAX: Int) {
    private val contenido = ListaCircular()
    private var size = 0

    fun crearPila(): Pila{
        assert(MAX > 0)
        return Pila(MAX)
    }

    fun empilar(e: Int) {
        if (size < MAX) {
            contenido.agregarAlFinal(e)
            size++
        } else {
            println("La pila esta llena")
        }
    }

    fun desempilar() {
        if (size > 0) {
            contenido.eliminar(contenido.tail())
            size--
        } else {
            println("La pila esta vacia")
        }
    }


    fun tope(): Int? {
        return if (size > 0) contenido.tail()?.value as Int else null
    }

    fun estaVacia(): Boolean {
        return size == 0
    }

    override fun toString(): String {
        var str = "["
        var current = contenido.tail()
        for (i in 0 until size) {
            str += "${current?.value}"
            if (i < size - 1) str += ", "
            current = current?.next
        }
        str += "]"
        return str
    }
}