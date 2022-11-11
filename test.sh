#!/bin/bash
rm classes/*.class
javac --release 11 classes/DriverControlador.java
java classes.DriverControlador