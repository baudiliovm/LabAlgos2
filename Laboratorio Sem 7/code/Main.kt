fun main(args: Array<String>) {

    /* var lista = ListaCircular()
    println(lista.toString())
    lista.agregarAlFrente(1)
    println(lista.toString())

    lista.eliminar(lista.buscar(1))
    println(lista.toString())
    lista.eliminar(lista.buscar(1))
    println(lista.toString())

    lista.agregarAlFrente(2)
    println(lista.toString())
    lista.agregarAlFinal(3)
    println(lista.toString())
    lista.agregarAlFrente(4)
    println(lista.toString())
    lista.agregarAlFinal(4)
    println(lista.toString())


    lista.eliminar(lista.buscar(4))
    println(lista.toString())
    

    lista.eliminar(lista.buscar(0))
    println(lista.toString())
    
    println(lista.head())
    println(lista.tail()) */


    

    var cola = Cola(4)
    println(cola.toString())
    cola.encolar(1)
    println(cola.toString())
    cola.encolar(2)
    println(cola.toString())
    cola.encolar(3)
    println(cola.toString())
    println(cola.primero())
    cola.encolar(4)
    println(cola.toString())
    cola.encolar(5)
    println(cola.toString())
    cola.desencolar()
    println(cola.toString())
    cola.desencolar()
    println(cola.toString())
    println(cola.primero())


    /* 
    val Cola = Cola(4)
    Cola.encolar(1)
    Cola.encolar(2)
    Cola.encolar(3)
    Cola.encolar(4)
    println(Cola)
    println(Cola.primero())
    println(Cola) */

}