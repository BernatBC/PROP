#!/bin/bash
cd ../../FONT
rm ./test/classes/*.class
javac -cp ./lib/junit-platform-console-standalone-1.9.1.jar:. ./test/classes/ConsultaSemblantTest.java
java -jar ./lib/junit-platform-console-standalone-1.9.1.jar -cp ./test/classes/ --scan-classpath
