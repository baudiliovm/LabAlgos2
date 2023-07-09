class PMLI(l: Char) {
    private var letra: Char = l
    private var palabras = Palabra()

    fun agregarPalabra(p: String) {
        if (p[0] == letra) {
            palabras.agregar(p)
        }
    }

    fun eliminarPalabra(p: String) {
        if (p[0] == letra) {
            palabras.eliminar(p)
        }
    }

    fun mostrarPalabras() {
        val sortedPalabras = palabras.ordenar()
        for (p in sortedPalabras) {
            println(p)
        }
    }

    fun buscarPalabra(p: String): Boolean {
        return p[0] == letra && p in palabras
    }
}