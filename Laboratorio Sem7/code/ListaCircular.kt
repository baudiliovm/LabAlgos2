class ListaCircular {
    private var head: Nodo? = null

    fun agregarAlFrente(value: Int) {
        val newNode = Nodo(value)
        if (head == null) {
            newNode.next = newNode
            newNode.prev = newNode
            head = newNode
        } else {
            val tail = head!!.prev
            newNode.next = head
            newNode.prev = tail
            head!!.prev = newNode
            tail!!.next = newNode
            head = newNode
        }
    }

    fun agregarAFinal(value: Int) {
        val newNode = Nodo(value)
        if (head == null) {
            newNode.next = newNode
            newNode.prev = newNode
            head = newNode
        } else {
            val tail = head!!.prev
            newNode.next = head
            newNode.prev = tail
            head!!.prev = newNode
            tail!!.next = newNode
        }
    }

    fun buscar(value: Int): Nodo? {
        if (head == null) return null

        var current = head!!
        while (current.value != value) {
            current = current.next!!
            if (current == head) return null
        }
        return current
    }

    fun eliminar(nodo: Nodo?) {
        if (nodo == null || head == null) return

        if (nodo == head && nodo.next == head) {
            head = null
        } else {
            nodo.prev!!.next = nodo.next!!
            nodo.next!!.prev = nodo.prev!!
            if (nodo == head) head = nodo.next!!
        }
    }
}