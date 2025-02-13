## The Terminologies in Java

JVM (Java Virtual Machine): JVM is the specification that facilitates the runtime environment in which the execution of the Java bytecode takes place. Whenever one uses the command java, an instance of the JVM is created. JVM facilitates the definition of the memory area, register set, class file format, and fatal error reporting. Note that the JVM is platform dependent.

Byte Code: It has already been discussed in the introductory part that the Java compiler compiles the Java code to generate the .class file or the byte code. One has to use the javac command to invoke the Java compiler.

Java Development Kit (JDK): It is the complete Java Development Kit that encompasses everything, including JRE(Java Runtime Environment), compiler, java docs, debuggers, etc. JDK must be installed on the computer for the creation, compilation, and execution of a Java program.

Java Runtime Environment (JRE): JRE is part of the JDK. If a system has only JRE installed, then the user can only run the program. In other words, only the java command works. The compilation of a Java program will not be possible (the javac command will not work).

Garbage Collector: Programmers are not able to delete objects in Java. In order to do so, JVM has a program known as Garbage Collector. Garbage Collectors recollect or delete unreferenced objects. Garbage Collector makes the life of a developer/ programmer easy as they do not have to worry about memory management.

Classpath: As the name suggests, classpath is the path where the Java compiler and the Java runtime search the .class file to load. Many inbuilt libraries are provided by the JDK. However, if someone wants to use the external libraries, it should be added to the classpath.