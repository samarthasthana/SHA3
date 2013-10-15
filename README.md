SHA3
====

Implementation of SHA 3 (Keccak)

Includes the following deliverables.

sha3 project folder - (Which has the source code and can be build)

sha3.jar- An executable file that can be used to execute the implementation as will be explained later.

input.txt - which contains the input message to be hashed.

Readme.txt - You are here :P

Example.txt - Contains the demo runs which show the avalanche property of the implementation
-------------------------------------------------------------

Steps to execute.

1. Unzip the SHA3_sasthan3 folder 
2. cd SHA3_sasthan3
3. gedit input.txt (to change the message to be hashed) save after making the changes.
4. java -jar sha3.jar input.txt
5. The hash created is printed to the console and also written to 'result.txt'

Note= In step 4 , you can replace the 'input.txt' with any input file of your choice, the ouput will be displayed and written to 'result.txt' in the same folder.
----------------------------------------------------------

Steps to rebuild.

1. Unzip the SHA3_sasthan3 folder 
2. cd SHA3_sasthan3/sha3/
3. ant clean
4. ant build 
5. Copy the sha3.jar created to a location to execute as above