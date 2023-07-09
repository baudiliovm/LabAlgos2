fun main(args: Array<String>) {

    var conjunto = ConjuntoPalabra()
    for (i in 0 until args.size) {
        conjunto.agregar(Palabra(args[i]).valor)
        println(conjunto.toString())
    }


    println(conjunto.numElementos())
    println(conjunto.toString())
    println(conjunto.buscar("hola"))
    println(conjunto.buscar("bau"))
    println(conjunto.buscar("bueno"))
    println(conjunto.buscar("si"))
    
    
    for (i in 0 until args.size) {
        conjunto.eliminar(Palabra(args[i]).valor)
        println(conjunto.numElementos())
        println(conjunto.toString())
    }
}