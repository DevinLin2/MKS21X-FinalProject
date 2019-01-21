#!/usr/bin/env bash
resize -s 30 80
javac -cp lanterna.jar:. Field.java
java -cp lanterna.jar:. Field
