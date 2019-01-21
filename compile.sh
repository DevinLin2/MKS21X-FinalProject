#!/usr/bin/env bash
resize -s 30 80
javac -cp lanterna.jar:. Field.java
chmod +x run.sh
./run.sh
