fun main(args: Array<String>) {
    
    // Unimos la entrada en un solo string
    var mensaje = ""
    for (letra in args) {
        mensaje += letra + " "
    }
    
    // Init CodigoMorse
    val morse = CodigoMorse()
    
    // Imprime el mensaje dado
    println(morse.decodificarMensaje(mensaje))

}