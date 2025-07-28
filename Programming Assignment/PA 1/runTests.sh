#!/bin/bash
rm -f output 2>&1 >/dev/null
javac CalendarManager.java
java CalendarManager > output
javac Testing.java
java Testing 13 output.reference output