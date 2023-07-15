import java.io.File
/**
 * Inicializa el ayudante ortográfico con las opciones del menú
 */
fun main() {
    val ayudante = AyudanteOrtografico()
    var option = 0
    while (option != 6) {
        println("Menú:")
        println("1. Crear un nuevo ayudante ortográfico.")
        println("2. Cargar un diccionario.")
        println("3. Eliminar palabra.")
        println("4. Corregir texto.")
        println("5. Mostrar diccionario.")
        println("6. Salir de la aplicación.")
        print("Seleccione una opción: ")
        option = readLine()?.toIntOrNull() ?: 0
        when (option) {
            1 -> {
                println("Ayudante ortográfico creado.")
            }
            2 -> {
                print("Ingrese el nombre del archivo del diccionario: ")
                val fname = readLine() ?: ""
                if (File(fname).exists()) {
                    ayudante.cargarDiccionario(fname)
                    println("Diccionario cargado.")
                } else {
                    println("El archivo no existe.")
                }
            }
            3 -> {
                print("Ingrese la palabra a eliminar: ")
                val palabra = readLine() ?: ""
                if (Palabra(palabra).esPalabraValida()) {
                    ayudante.borrarPalabra(palabra)
                    println("Palabra eliminada.")
                } else {
                    println("La palabra no es válida.")
                }
            }
            4 -> {
                print("Ingrese el nombre del archivo de entrada: ")
                val finput = readLine() ?: ""
                if (File(finput).exists()) {
                    print("Ingrese el nombre del archivo de salida: ")
                    val foutput = readLine() ?: ""
                    ayudante.corregirTexto(finput, foutput)
                    println("Texto corregido.")
                } else {
                    println("El archivo de entrada no existe.")
                }
            }
            5 -> {
                ayudante.imprimirDiccionario()
            }
            6 -> {
                // Salir del programa
            }
            else -> {
                println("Opción inválida.")
            }
        }
        println()
    }
}