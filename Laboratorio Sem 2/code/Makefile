#!/usr/bin/bash

# Variables
KC=kotlinc
KFLAGS=-include-runtime -d

# Compilation
all: Sortlib.kt Main.kt PruebaMergesort.kt MultiplicacionMatrices.kt
	$(KC) $(KFLAGS) TestSort.jar Sortlib.kt Main.kt PruebaMergesort.kt MultiplicacionMatrices.kt

matrices: PruebaMergesort.kt MultiplicacionMatrices.kt
	$(KC) $(KFLAGS) TestSort.jar Main.kt  MultiplicacionMatrices.kt

clean:
	rm -rf *.jar *.class ./META-INF/