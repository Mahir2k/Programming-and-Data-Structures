#!/bin/bash
rm -f output.test 2>&1 >/dev/null
rm -r test_tree
cp -r tree test_tree

javac Recursion.java
java Recursion > output.test
