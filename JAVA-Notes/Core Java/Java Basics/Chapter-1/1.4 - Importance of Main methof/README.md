# Importance of Main method in Java

## ▸ Let’s look more closely at one of the simplest Java programs you can have—one that simply prints a message to console:

```JAVA
 public class FirstSample
 {
   public static void main(String[] args)
   {
      System.out.println("We will not use 'Hello, World!'");
   }
 }
```

It is worth spending all the time we need to become comfortable with the framework of this sample; the pieces will recur in all applications. First and foremost, **Java is case sensitive**. If you made any mistakes in capitalization (such as typing ***Main*** instead of ***main***), **the program will not run**.

Now let’s look at this source code line by line. The keyword ***public* is called an **access modifier; these modifiers control the level of access other parts of a program have to this code**. The keyword ***class*** reminds you that **everything in a Java program lives inside a *class***, think of a ***class* as a container for the program logic that defines the behavior of an application**, **classes are the building blocks with which all Java applications and applets are built**. ***Everything in a Java program must be inside a class***.


The standard naming convention (which we follow in the name *FirstSample*) is that **class names are nouns that start with an uppercase letter**. If a name consists of multiple words, use an initial uppercase letter in each of the words. (This use of uppercase letters in the middle of a word is sometimes called “**camel case**” or, self-referentially, “**CamelCase**”.)

We need to make the file name for the source code the same as the name of the *public class*, with the extension **.java** appended. Thus, we must store this code in a file called FirstSample.java. (*Again, case is important—don’t use firstsample.java*.)

If we have named the file correctly and not made any typos in the source code, then when you compile this source code, you end up with a file containing the **bytecodes** for this class. The Java compiler automatically names the bytecode file *FirstSample.class* and stores it in the same directory as the source file. Finally, launch the program by issuing the following command:

```bash
 java FirstSample
```

(*Remember to leave off the .class extension.*) When the program executes, it simply displays the string We will not use 'Hello, World!' on the console. When you use

```bash
 java ClassName
```

to run a compiled program, the Java virtual machine **(JVM) always starts execution with the code in the ***main*** method in the class you indicate**. (***The term “method” is Javaspeak for a function.***) Thus, we must have a main method in the source file for our class for our code to execute. We can, of course, add our own methods to a class and call them from the main method.


## Q. How JRE call `main` method❓

When we execute a Java program, the JVM (Java Virtual Machine) does the following:

1. **Loads the Class**: The JVM loads the class (in this case, MainMethod).

2. **Finds the main Method**: The JVM searches for a public static void main(String[] args) method inside the loaded class. This is why the main method must have this exact signature.

3. **Invokes the main Method**: The JVM internally calls main(String[] args) similar to this:

```JAVA
MainMethod.main(new String[]{}); //This is not recursion at the JVM level because the JVM only calls main once.
```
⇛ This code resembles how the Java Runtime Environment (JRE) calls the main method internally.

## Q. How Does the JVM Internally Call `main`❓
⇛ Internally, the JVM uses JNI (Java Native Interface) and calls the main method in native C++ code:

```JAVA
CallStaticVoidMethod(class_MainMethod, method_main, args); //This is handled at a lower level, not through recursion.
```

### Q. Can We Simulate JVM Calling `main`❓
⇛ If we want to simulate how the JVM calls main, we can do this:

```JAVA
class MainMethod {
  public static void myMain(String[] args) { // Custom main-like method
    System.out.println("JVM-like main method call simulation");
  }

  public static void main(String[] args) {
    myMain(args); // Simulating how JVM calls main
  }
}
```

```
Output> JVM-like main method call simulation  
```
### 👉 Here, the main method calls another method (myMain), just like the JVM calls main.


## Q. How JRE/JVM calls `main` method by passing args❓

```JAVA
class MainMethod {
  public static void main(String[] args)
  {
    MainMethod.main(args);
  }
}
```
⇛ This code resembles how the Java Runtime Environment (JRE) calls the main method internally, but it does not exactly demonstrate the internal mechanism and it will eventually cause a `StackOverflowError` this means that main keeps calling itself over and over without a base condition to stop.

### Q. What happens when we run this?
⇛ When you execute this program:

  ➡️ The Java Virtual Machine (JVM) calls `main(String[] args)`.

  ➡️ The `main method` calls itself again.

  ➡️ This cycle never stops because there is no termination condition.

  ➡️ Eventually, the call stack (which keeps track of function calls) fills up, leading to a `StackOverflowError`.

### Expected Output:
```bash
Exception in thread "main" java.lang.StackOverflowError
```
### 👉 The error occurs because Java has a limited memory stack, and calling main infinitely fills up the stack space.

### How to Fix It❓
» If you want to prevent infinite recursion, you should have a stopping condition:

```JAVA
class MainMethod {
  public static void main(String[] args) {
    if (args.length > 0) {  // A condition to prevent infinite recursion
      System.out.println("Base condition met. Stopping recursion.");
    } else {
      System.out.println("Calling main again...");
      MainMethod.main(new String[]{"stop"});  // Providing an argument to break recursion
    }
  }
}
```

### Q. When is this concept used❓
⇛ Even though this program causes an error, recursive calls to the main method can be used in certain cases:

1. Restarting a Java Program:
    * You can use main recursion with a base case to restart a program without exiting.
2. Simulating Recursion for Learning Purposes:
    * Some programmers use this to understand recursion behavior.
### 👉 However, it is not a good practice to use main for recursion because main is meant to start the program, not act as a recursive function.

## ▸ Anatomy of JAVA

![1_8ls-fv5l7V61wXLQwWrHEQ](https://github.com/user-attachments/assets/c0010386-9df7-4504-845d-39d66d48a585)

## Q. Why main method is ***public*** in Java❓
⇛ "**public**" is an access modiier in Java. The main method is "**public**" so JVM can call or access main method form outside of public class. The JVM (which execute code) call main method form outside of class, if the main method is not "public" then JVM cna't call or access the main method nad our program cannot be execute and since main method cannot be executed then whole program cannot be executed.


## Q. Why main method is ***static*** in Java❓
⇛ The main method is "**static**" so we can access the main method without creating any object. It won't neccessary to create a **new object** to run method since it is "**static**". If it was not "**static**" then we have to call it by creating another object of class so, that's why Java Developer created main method "**static**". Since main method is the beginning of program, **when JVM calls the main method it won't have to depend upon another class object to be execute and it will execute by itself without object creation**.


## Q. Why main method is ***void*** in Java❓
⇛ "**void**" is a data-type, whenever write "**void**" as return type it means we don't expect any value in return from main method.


## Q. What is (Stirng[] args)❓
⇛ It is array of **String**, ***args*** is an array of **String** (Stirng[] args) which store command-line arguments passed to the program. It stores Java command-line arguments and is an array of type java.lang.String class. 


## ▸ Importance of Main Method

i. **Entry Point**: It's the entry point of a Java program, where the execution starts. Without the main method the JVM doesn't know where to begin running the code.

ii. **Public and Static**: The main method must be *public* ensuring it's accessible to the JVM and *static* so that JVM access it without instanting the class.

iii. **Fixed Signature**: The main method has a fixed signature `public static void main(String[] args)`. Deviating from this signature means the JVM won't recogonize it as the starting point.
