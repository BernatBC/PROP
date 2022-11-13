#!/bin/bash
cd ../../FONT
rm classes/*.class
rm test/classes/DriverControlador.class
javac --release 11 test/classes/DriverControlador.java
java test.classes.DriverControlador
