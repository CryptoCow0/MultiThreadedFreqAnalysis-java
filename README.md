# MultiThreadedFreqAnalysis-java
Takes an input file of all lowercase characters and outputs how often each character appears.

## Overview
The input file will consist of lowercase characters, all on one line. For example:
| Example 1 |
|----------|
| aabgeaf  |

A correct running would then output:
| Letter | Count |
|--------|-------|
|   a    |   3   |
|   b    |   1   |
|   e    |   1   |
|   f    |   1   |
|   g    |   1   |

## Multithreading (project.java)
This code uses Atomic Integers to ensure Thread safety, avoid Race Conditions, and for simplicity. It uses SERIAL_RANDOM_THINGS.py to generate a random string of a specified size, then you can run the project.java with this to perform frequency analysis

Analysis was done in a separate paper and 8 cores should be sufficient for files larger than 1 billion so that was hardcoded in. 

## Serial Implementation (serial.java)
Before trying to run this code in parallel, I designed the serial implementation, which is also available. It runs decently quickly even at larger levels. However, past testing on files with 100,000,000 characters suggests using Multi-threaded implementation.


## Dependencies and Setup

Ensure that `SERIAL_RANDOM_THINGS.py` is available and executable. <br />
JDK is required to compile and run the Java code. Ensure that you have JDK 8 or higher installed.  <br />


## USAGE

| Command                                       | Description                          |
|-----------------------------------------------|--------------------------------------|
| `python3 input_generator.py 10 > input_ten`  | Generate input file with 10 lines    |
| `javac project.java ` or `javac serial.java` |  Compile the java code               |
| `java project.java input_file > output_file` | Execute the file (replace `project` with `serial` if using the serial version) |






