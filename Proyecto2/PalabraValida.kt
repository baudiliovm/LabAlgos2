fun esPalabraValida(palabra: String): Boolean {
    val regex = Regex("[a-zA-Z]+")
    return regex.matches(palabra)
}