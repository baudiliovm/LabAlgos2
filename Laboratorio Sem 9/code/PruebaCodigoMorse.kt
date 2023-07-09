/** 
 * Main para probar la clase CodigoMorse
 * 
 * @param args Mensaje en codigo morse
 */
fun main(args: Array<String>) {
    // Unimos la entrada en un solo string
    var mensaje = ""
    for (letra in args) {
        mensaje += letra + " "
    }
    
    // Init CodigoMorse
    println("Creamos un arbol de Codigo Morse...\n")
    val morse = CodigoMorse()
    val decodificacion = morse.decodificarMensaje(mensaje)
    // Imprime el mensaje dado
    if (decodificacion != null){
        println("Mensaje decodificado: ${decodificacion}")
    }
    else {
        println("Error: Codigo Morse invalido")
    }

}