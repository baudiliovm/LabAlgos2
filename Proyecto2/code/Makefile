#!/usr/bin/bash

# Variables
KC=kotlinc
KFLAGS=-include-runtime

# Compilacion
all: Palabra.kt CircularList.kt ConjuntoPalabra.kt PMLI.kt Distancia.kt AyudanteOrtografico.kt PruebaOrtografica.kt
	$(KC) $(KFLAGS) Palabra.kt CircularList.kt ConjuntoPalabra.kt PMLI.kt Distancia.kt AyudanteOrtografico.kt PruebaOrtografica.kt $*

PMLI: Palabra.kt CircularList.kt ConjuntoPalabra.kt PMLI.kt Distancia.kt AyudanteOrtografico.kt PruebaPMLI.kt
	$(KC) $(KFLAGS) Palabra.kt CircularList.kt ConjuntoPalabra.kt PMLI.kt Distancia.kt AyudanteOrtografico.kt PruebaPMLI.kt $*

ConjuntoPalabra: Palabra.kt CircularList.kt ConjuntoPalabra.kt PMLI.kt Distancia.kt AyudanteOrtografico.kt PruebaConjuntoPalabra.kt
	$(KC) $(KFLAGS) Palabra.kt CircularList.kt ConjuntoPalabra.kt PMLI.kt Distancia.kt AyudanteOrtografico.kt PruebaConjuntoPalabra.kt $*
	
clean:
	rm -rf *.jar *.class ./META-INF/
