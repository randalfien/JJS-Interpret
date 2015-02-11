## JavaJavaScript Interpreter 

*Semestral project for MI-RUN course at FIT CTU in Prague*

### Author
Ondrej Paska (paskaond)

### Compilation
Compile with Apache Ant (build.xml)
* ant - builds project to bin folder
* ant Main - runs the interpreter  
* ant test - runs all the tests
* ant create_run_jar  - creates a jar file to bin-jar folder

### Interpreter
If there are no arguments, reads from standart input, otherwise takes the first argument as a filename to load as a JS program. Additional argumemts are passed to the program.

###Example program
Sat3 Solver ( java -jar javajavascript.jar sat_solver.js sat3_1.dimacs )

####Input
dimacs CNF  (example in sat3_1.dimacs)
####Output
list of variable (those that are false in solution are preceded with '-') or unsatisfiable.

###About solution:
JavaCC (JJTree) parses input to AST according to a grammer, then it is interpreted.
I used and modified the JavaCC EcmaScript Grammer from Dojo Foundation:
Licensed under the Academic Free License version 2.1 or above OR the modified BSD license.
Accessed from: http://svn.dojotoolkit.org/src/trunk/tools/jslinker/src/org/dojo/jsl/parser/EcmaScript.jjt

###Features: 
* Basic Flow Control: IF-THEN-ELSE, WHILE, FOR, BREAK
* Operators: +,-,*, &&, ||, +=, -=, >, <, =, ==, !=
* Literals: String, Number, Long, Boolean, undefined, null
* JavaScript Objects: Literals ( var a = {attr1:1, attr2:2} ), properties accessed by a.attr1 or a["attr2"]
* Functions and first class function objects
* Closures
* Array class with builtin methods join, pop, push
* String class with builtin methods charAt and split
* Strings native, only cast to objects when needed
* Simple IO - readFile, writeFile functions - only string

### What is different from JavaScript
	* trace() function to println to standart out
	* io functions
	* all properties can be overwritten, including operators +,-,==, etc
	* Missing: continue, new, exceptions, for in, switch, with, prefix expressions, operators...


**More details can be found in test classes**

	






