import kotlin.system.exitProcess

class Cola(val MAX: Int) {
    
    init {
        if (MAX < 1) {
            println("El valor maximo de la cola debe ser mayor o igual a 0")
            exitProcess(1)
        }
        println("--> Una nueva cola de maximo $MAX elementos fue creada")
        
    }
    
    private val contenido = ListaCircular()
    private var size = 0

    fun encolar(e: Int) {
        if (size < MAX) {
            contenido.agregarAlFinal(e)
            size++
        } else {
            println("La cola esta llena")
        }
    }

    fun desencolar() {
        if (size > 0) {
            contenido.eliminar(contenido.head())
            size--
        } else {
            println("La cola esta vacia")
        }
    }

    fun primero(): Int? {
        return if (size > 0) contenido.head()?.value as Int else null
    }

    fun estaVacia(): Boolean {
        return size == 0
    }

    override fun toString(): String {
        var str = "["
        var current = contenido.head()
        for (i in 0 until size) {
            str += "${current?.value}"
            if (i < size - 1) str += ", "
            current = current?.next
        }
        str += "]"
        return str
    }
}