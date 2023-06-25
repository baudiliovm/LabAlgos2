#!/usr/bin/bash

# Variables
KC=kotlinc
KFLAGS=-include-runtime

# Compilation
all: Cola.kt ListaCircular.kt Nodo.kt Pila.kt Main.kt
	$(KC) $(KFLAGS) Cola.kt ListaCircular.kt Nodo.kt Pila.kt Main.kt $*

Cola: Cola.kt Main.kt
	$(KC) $(KFLAGS) Cola.kt Main.kt $*

Lista: ListaCircular.kt Main.kt
	$(KC) $(KFLAGS) ListaCircular.kt Main.kt $*

Nodo: Nodo.kt Main.kt
	$(KC) $(KFLAGS) Nodo.kt Main.kt $*

Pila: Pila.kt Main.kt
	$(KC) $(KFLAGS) Pila.kt Main.kt $*

clean:
	rm -rf *.jar *.class ./META-INF/