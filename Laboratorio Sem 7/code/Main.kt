fun main(args: Array<String>) {

    /*var lista = ListaCircular()
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


    /*var cola = Cola(4)
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
    println(cola.primero())*/


   var pila = Pila(4)
    println(pila.toString())
    println(pila.estaVacia())
    pila.empilar(1)
    println(pila.toString())
    pila.empilar(2)
    println(pila.toString())
    pila.empilar(3)
    println(pila.toString())
    pila.empilar(4)
    println(pila.toString())

    println(pila.tope())
    println(pila.estaVacia())
    pila.desempilar()
    println(pila.toString())
    
}