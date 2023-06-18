#!/usr/bin/bash

# Variables
KC=kotlinc
KFLAGS=-include-runtime

# Compilation
all: DCLSTSP.kt VerificadorTSP.kt Proyecto.kt
	$(KC) $(KFLAGS) DCLSTSP.kt VerificadorTSP.kt Proyecto.kt

VerificadorTSP: VerificadorTSP.kt
	$(KC) $(KFLAGS) VerificadorTSP.kt

DCLSTSP: DCLSTSP.kt
	$(KC) $(KFLAGS) DCLSTSP.kt

Proyecto: Proyecto.kt
	$(KC) $(KFLAGS) Proyecto.kt


clean:
	rm -rf *.jar *.class ./META-INF/