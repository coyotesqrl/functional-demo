# Java 8 Functional Demo
This demo will go over a few of the more common aspects of Java 8 lambdas, streaming, and functional
programming. For many of the demonstration methods, an arbitrarily complex and poorly structured
object model has been defined. This object model should never be emulated; its twisting nature was 
specified to add complexity to some of the traditional, procedural/iterative solutions to mimic more
complex object hierarchies that sometimes do have difficult relationships.

To build the demo, simply run `mvn clean install`.

The provided Spock tests pass against the provided procedural/iterative solutions; it is the point
 of the demo to rewrite the underlying code in the `com.connexta.functional` package to replace
 with functional solutions.
 
There is a README file located in that package with solutions provided should you get stuck.
  
Remember that there are many ways to solve a problem and the current procedural implementation is not
 wrong in any way. Likewise, the provided functional code samples are not the only way to solve the 
 problems.
