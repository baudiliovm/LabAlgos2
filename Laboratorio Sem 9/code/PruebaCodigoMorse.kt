fun main(args: String) {
    
    // Separamos la entrada por palabras
    var delim = "/"
    val mensaje = args.split(delim).toTypedArray()

    // Retorna el mensaje dado
    CodigoMorse().decodificarMensaje(mensaje)
}