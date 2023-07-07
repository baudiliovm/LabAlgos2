fun main(args: Array<String>) {
    
    // Unimos la entrada en un solo string
    var mensaje = ""
    for (letra in args) {
        mensaje += letra + " "
    }
    
    var CodigoMorse = CodigoMorse()
    
    // Imprime el mensaje dado
    println(CodigoMorse.decodificarMensaje(mensaje))

}