#!/bin/bash
rm -f output 2>&1 >/dev/null
javac *.java
java Test > output
javac Testing.java
java Testing 26 output.reference output