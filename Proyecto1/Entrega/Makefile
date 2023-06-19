#!/usr/bin/bash

# Variables
KC=kotlinc
KFLAGS=-include-runtime

# Compilation
all: DCLSTSP.kt VerificadorTSP.kt
	$(KC) $(KFLAGS) DCLSTSP.kt VerificadorTSP.kt

VerificadorTSP: VerificadorTSP.kt
	$(KC) $(KFLAGS) VerificadorTSP.kt

DCLSTSP: DCLSTSP.kt
	$(KC) $(KFLAGS) DCLSTSP.kt

clean:
	rm -rf *.jar *.class ./META-INF/