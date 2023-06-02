#!/usr/bin/bash

# Variables
KC=kotlinc
KFLAGS=-include-runtime -d

# Compilation
all: Sortlib.kt Main.kt
	$(KC) $(KFLAGS) TestSort.jar Sortlib.kt Main.kt

clean:
	rm -rf *.jar *.class ./META-INF/