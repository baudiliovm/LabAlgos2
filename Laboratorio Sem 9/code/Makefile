#!/usr/bin/bash

# Variables
KC=kotlinc
KFLAGS=-include-runtime

# Compilacion
all: CodigoMorse.kt Nodo.kt PruebaCodigoMorse.kt
	$(KC) $(KFLAGS) CodigoMorse.kt Nodo.kt PruebaCodigoMorse.kt
	
clean:
	rm -rf *.jar *.class ./META-INF/