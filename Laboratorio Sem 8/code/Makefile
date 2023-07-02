#!/usr/bin/bash

# Variables
KC=kotlinc
KFLAGS=-include-runtime

# Compilation
all: HashTableEntry.kt CircularList.kt HashTableChaining.kt Main.kt
	$(KC) $(KFLAGS) HashTableEntry.kt CircularList.kt HashTableChaining.kt Main.kt $*
	
clean:
	rm -rf *.jar *.class ./META-INF/