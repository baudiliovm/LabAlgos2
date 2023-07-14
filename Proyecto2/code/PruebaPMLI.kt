fun main(args: Array<String>) {

    var conjunto = PMLI("a")
    for (i in 0 until args.size) {
        conjunto.agregarPalabra(args[i])
    }

    println(conjunto.buscarPalabra("avion"))
    conjunto.eliminarPalabra("avion")
    println(conjunto.buscarPalabra("avion"))
    conjunto.agregarPalabra("bau")
    conjunto.mostrarPalabras()
   
}