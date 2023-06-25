#!/usr/bin/bash

# Variables
KC=kotlinc
KFLAGS=-include-runtime

# Compilation
all: Cola.kt ListaCircular.kt Nodo.kt Pila.kt Main.kt
	$(KC) $(KFLAGS) Cola.kt ListaCircular.kt Nodo.kt Pila.kt Main.kt $*
	
clean:
	rm -rf *.jar *.class ./META-INF/