#!/usr/bin/bash

# Variables
KC=kotlinc
KFLAGS=-include-runtime -d

# Compilation
all: Sortlib.kt Main.kt InsertionPrueba.kt MultiplicacionMatrices.kt
	$(KC) $(KFLAGS) TestSort.jar Sortlib.kt Main.kt InsertionPrueba.kt MultiplicacionMatrices.kt

clean:
	rm -rf *.jar *.class ./META-INF/