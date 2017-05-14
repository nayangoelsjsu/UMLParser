# UMLParser

This is a UML parser. It takes javacode as input and outputs a UML Class Diagram.

Following are the tools used for the developement of this project:

JavaParser: It parses the source code of a given java file and creates tokens that match the java grammar using Abstract Syntax Tree (AST). The AST records the source code structure. You can analyze source code for any purpose. This is the link to the library: https://github.com/javaparser/javaparser.
I used javaparser-core-2.1.0.jar for parsing the source files and create tokens.

yUML: The Javaparser generates a grammar from which a URL is generated and which can be used to create class diagrams with the provided grammar by yUML. This is the lik to the yUML website : http://yuml.me/diagram/scruffy/class/draw.

The Following are the instructions to run the jar file and generate the class diagram for a given source code.

-> Download the jar file.

-> Open terminal and change the directory to the one in which the jar file was saved. 

-> Type the following command to execute the code:

java -jar <name of jar file> "<path to test case folder>" <name of output diagram> generate_classDiagram

for eg:

java -jar classDiagram_nayan_final.jar "/Users/NayanGoel/Downloads/test4" test4Class generate_classDiagram

here: the classDiagram_nayan_final.jar is the name of the jar. 
      
      /Users/NayanGoel/Downloads/test4 is the path to test case folder
      
      test4Class is the output file name. It will reate test4Class.png file.
      
      generate_classDiagram is the keyword to generate Class Diagram. It was added beacuse I was going to add Sequence Diagram       
      as well but due to time constraint that was not possible.
      
      
