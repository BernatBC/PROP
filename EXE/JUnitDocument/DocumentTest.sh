#!/bin/bash
cd ../../FONT
rm test/classes/*.class classes/*.class
rm -r out/

mkdir out
javac -d out classes/*.java
javac -d out -cp out:lib/junit-platform-console-standalone-1.9.1.jar test/classes/DocumentTest.java
java -jar lib/junit-platform-console-standalone-1.9.1.jar --class-path out --scan-class-path
