#!/usr/bin/bash

# Variables
KC=kotlinc
KFLAGS=-include-runtime

# Compilacion
all: Palabra.kt CircularList.kt ConjuntoPalabra.kt PMLI.kt Distancia.kt AyudanteOrtografico.kt PruebaOrtografica.kt
	$(KC) $(KFLAGS) Palabra.kt CircularList.kt ConjuntoPalabra.kt PMLI.kt Distancia.kt AyudanteOrtografico.kt PruebaOrtografica.kt $*
	
clean:
	rm -rf *.jar *.class ./META-INF/
