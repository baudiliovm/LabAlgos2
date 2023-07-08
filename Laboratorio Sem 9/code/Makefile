#!/usr/bin/bash

# Variables
KC=kotlinc
KFLAGS=-include-runtime

# Compilacion
all: CodigoMorse.kt NodoArbol.kt PruebaCodigoMorse.kt
	$(KC) $(KFLAGS) CodigoMorse.kt NodoArbol.kt PruebaCodigoMorse.kt $*
	
clean:
	rm -rf *.jar *.class ./META-INF/