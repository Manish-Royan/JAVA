# Methods in JAVA

## What Is a Method in Java❓

↳ A method is a block of code that performs a specific task. It’s like a reusable tool you define once and use many times.

↳ Methods are a fundamental building block in Java programming, encapsulating reusable blocks of code that perform specific tasks. They promote **modularity**, **readability**, and **maintainability** by allowing us to break down complex programs into smaller, manageable pieces. In object-oriented programming (OOP), **methods define the behavior of objects**, **enabling encapsulation and abstraction**.

## Definition and Purpose
↳ A method is a named block of code that can be invoked (called) to execute a sequence of statements. It can:
* Perform computations.
* Manipulate data (e.g., ***modify object fields***).
* Interact with other methods or external resources (e.g., ***I/O operations***).
* Return a value or perform side effects (e.g., ***printing output***).

### ▹ Key Purposes:
* **Reusability**: Write once, call multiple times.
* **Abstraction**: Hide implementation details; expose only the interface.
* **Modularity**: Organize code logically within classes.
* **Debugging**: Isolate logic for easier testing and error handling.

## Method Structure

### i. The general syntax for declaring a method (without return type) is:
```
accessModifier returnType methodName() {
    // method body   
}
```
🔖 Example:
```JAVA
public void methodName()
{
    // method body
}
```

### ii. The general syntax for declaring a method (with return type) is:
```
accessModifier returnType methodName() {
    // method body   
}
```
🔖 Example:
```JAVA
public int SumTwoNumber()
{ 
    return 5 + 5;
}
```

### iii. The general syntax for declaring a parameterized method is:
```java
accessModifier returnType methodName(dataType1 param1, dataType2 param2, …) {
    // method body
}
```
🔖 Example (void return type):  
```java
public void greet(String name) {
    System.out.println("Hello, " + name);
}
```
🔖 Example (with return type):  
```java
public int add(int a, int b) {
    return a + b;
}
```

👉 This kind of method is called a **parameterized method**, because it accepts one or more parameters in its signature.

### iv. The general syntax for declaring a method with modifiers and an exception-throwing clause is:
```java
[access_modifier] [other_modifiers] return_type method_name([parameter_list]) [throws exception1, exception2, …] {
    // Method body: statements
}
```
🔖 Example:
```java
public static synchronized int compute(int a, int b) throws IOException, IllegalArgumentException {
    if (a < 0 || b < 0) {
        throw new IllegalArgumentException("Negative numbers not allowed");
    }
    // imagine some I/O operation here
    return a + b;
}
```
