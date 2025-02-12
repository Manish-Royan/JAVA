# [Java Buzzwords](https://www.javaguides.net/2018/10/java-buzzwords-or-features-of-java.html)

**The primary objective of Java programming language creation was to make it *portable*, *simple* and *secure* programming language**. Apart from this, there are also some excellent features which play an important role in the popularity of this language. The features of Java are also known as Java buzzwords.

![java-features](https://github.com/user-attachments/assets/19bf79d6-c88b-47e8-8b1f-260bf422f753)

## » A list of the most important features/buzzwords of the Java language is given below:

i. **Simple**: 

* Java is very easy to learn, and its syntax is simple, clean and easy to understand. 
* Java has removed many complicated and rarely-used features, such as explicit pointers and operator overloading
* There is no need to remove unreferenced objects because there is an Automatic Garbage Collection in Java.

ii. **Object-oriented**:Java is an object-oriented programming language. Everything in Java is an object. Object-oriented means we organize our software as a combination of different types of objects that incorporate both data and behavior.

* Everything in Java revolves around *objects* and *classes*.
* Java allows you to model real-world entities (like a car or a bank account) as objects in your program, making it easier to manage and build complex applications.
    * Key Object-Oriented Programming (OOP) concepts include:
        * **Object**: An instance of a class.
        * **Class**: A blueprint for creating objects.
        * **Inheritance**: Allows one class to inherit the properties of another.
        * **Polymorphism**: The ability of objects to take on multiple forms.
        * **Abstraction**: Hides the complex details and shows only the essentials.
        * **Encapsulation**: Keeps the data safe by restricting access to it.

iii. **Platform Independent**: Java is platform independent because it is different from other languages like C, C++, etc. which are compiled into platform specific machines while Java is a write once, run anywhere language. A platform is the hardware or software environment in which a program runs.

![platform-independent-java](https://github.com/user-attachments/assets/3c5296e1-894d-402b-b33c-fc6b1bfd81bf)

There are two types of platforms software-based and hardware-based. Java provides a software-based platform.

The Java platform differs from most other platforms in the sense that it is a software-based platform that runs on top of other hardware-based platforms. It has two components:

1. **Runtime Environment**
2. **API(*Application Programming Interface*)**

Java code can be executed on multiple platforms, for example, Windows, Linux, Sun Solaris, Mac/OS, etc. J**ava code is compiled by the compiler and converted into *bytecode***. This bytecode is a platform-independent code because it can be run on multiple platforms, *i.e., Write Once and Run Anywhere (WORA)*.

* Java combines both compiled and interpreted approaches, making it a **two-stage system**.
* **Compiled**: Java compiles programs into an intermediate representation called ***Java Bytecode***.
* **Interpreted**: **Bytecode** is then interpreted, generating machine code that can be directly executed by the machine that provides a **JVM**.

iv. **Secured**: Java is best known for its security. With Java, we can develop virus-free systems. Java is secured because:

* No explicit pointer.Java does not use pointers, which helps prevent unauthorized memory access. 
* The JVM verifies Java bytecode before execution, ensuring that it adheres to Java’s security constraints. 
* Java applications run in a restricted environment (sandbox) that limits their access to system resources and user data, enhancing security
* Java Programs run inside a virtual machine sandbox:

  ![java-security](https://github.com/user-attachments/assets/4a1eb95d-6c5d-4df3-a576-ff4512267c3b)

   * **Classloader**: Classloader in Java is a part of the **Java Runtime Environment (*JRE*)** which is **used to load Java classes into the Java Virtual Machine dynamically**. It adds security by separating the package for the classes of the local file system from those that are imported from network sources.
    * **Bytecode Verifier**: It checks the code fragments for illegal code that can violate access rights to objects.
    * **Security Manager**: It determines what resources a class can access such as reading and writing to the local disk.

Java language provides these securities by default. Some security can also be provided by an application developer explicitly through **SSL**, **JAAS**, **Cryptography**, etc.

v. **Robust**:The English meaning of Robust is strong. Java is robust because:

* It uses strong memory management.
* Java provides many features that make programs execute reliably in a variety of environments.
* Java is a strictly typed language that checks code at compile time and runtime.
* There is a lack of pointers that avoids security problems.
* Java provides automatic garbage collection which runs on the Java Virtual Machine to get rid of objects which are not being used by a Java application anymore.
* There are exception handling and the type checking mechanism in Java. 

vi. **Architecture-neutral**: Java is architecture neutral because there are no implementation dependent features, for example, the size of primitive types is fixed.

In C programming, **int data type occupies 2 bytes of memory for 32-bit architecture and 4 bytes of memory for 64-bit architecture**. **However, it occupies 4 bytes of memory for both 32 and 64-bit architectures in Java**.

* Java language and JVM help achieve the goal of “***write once; run anywhere, any time, forever***.”
* Changes and upgrades in operating systems, processors, and system resources do not force any changes in Java programs.

vi. **Portable**: Java is portable because it facilitates you to carry the Java **bytecode** to any platform. It doesn't require any implementation.

* Java provides a way to download programs dynamically to various types of platforms connected to the Internet.
* Java is portable because of the **JVM, which provides a consistent runtime environment across different platforms**.

vii.**Distributed**: Java is distributed because it facilitates users to create distributed applications in Java. **RMI** and **EJB** are used for creating distributed applications. This feature of Java makes us able to access files by calling the methods from any machine on the internet.

* Java is designed to create distributed applications on networks.
* Java applications can access remote objects on the Internet as easily as they can do in the local system.
* Java enables multiple programmers at multiple remote locations to collaborate and work together on a single project.

viii. **Multi-threaded**: 
A** thread is like a separate program, executing concurrently**. We can write Java programs that deal with many tasks at once by defining multiple threads. **The main advantage of multi-threading is that it doesn't occupy memory for each thread**. It shares a common memory area. Threads are important for multi-media, Web applications, etc.

* Multithreaded programs **handle multiple tasks simultaneously**, which is helpful in creating interactive, networked programs.
* **Java run-time** system supports *multiprocess synchronization* for constructing interactive systems.

ix. **Dynamic**: Java is a dynamic language. **It supports the dynamic loading of classes**. It means **classes are loaded on demand**. It also supports functions from its native languages, *i.e., C and C++*.

Java supports dynamic compilation and automatic memory management (garbage collection).

* Java can link in new class libraries, methods, and objects dynamically.
* Java programs carry substantial amounts of run-time type information, enabling dynamic linking in a safe and expedient manner.

x. **Interpreted**: Java combines high performance with interpretability, as its bytecode is interpreted by the Java Virtual Machine (JVM) which employys JUST-IN-TIME (JIT) compliation for efficient and fast execution. In Java, it is byte code to machine code.

xi. **High-performance**: J**ava is faster than other traditional interpreted programming languages because Java bytecode is "close" to native code**. It is still a little bit slower than a compiled language (*e.g., C++*). Java is an interpreted language that is why it is slower than compiled languages, *e.g., C, C++, etc*.

## 🔖 Conclusion
Understanding the buzzwords of Java provides insights into the language's key concepts and principles. Java's simplicity, platform independence, object-oriented nature, robustness, security, and portability make it a versatile choice for a wide range of applications. With its focus on performance, multithreading, and a thriving community, Java continues to evolve and remain a prominent force in the world of software development.

Whether you are a beginner or an experienced developer, grasping these Java buzzwords enables a deeper understanding of the language. It empowers you to leverage its strengths to build powerful and reliable applications.

Java is commonly used to develop a wide range of applications, including desktop, mobile, game, web, and enterprise-level applications. The language is known for its simplicity, reliability, security, and object-oriented programming.
