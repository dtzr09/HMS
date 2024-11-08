#!/bin/bash
rm -rf bin data


# Create the bin directory if it doesn't exist
mkdir -p bin

# Compile Java files into the bin directory
javac -d bin Main.java

# Run the Main class from the bin directory
java -cp bin Main
