# Methods in JAVA

## What Is a Method in Java❓

↳ A method is a block of code that performs a specific task. It’s like a reusable tool you define once and use many times.

↳ Methods are a fundamental building block in Java programming, encapsulating reusable blocks of code that perform specific tasks. They promote **modularity**, **readability**, and **maintainability** by allowing us to break down complex programs into smaller, manageable pieces. In object-oriented programming (OOP), **methods define the behavior of objects**, **enabling encapsulation and abstraction**.

## 📖 Definition and Purpose
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

## 🧾 Method Structure 

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


## 🧩 Components Explained:
| Component        | Description                                                                 |
|------------------|-----------------------------------------------------------------------------|
| `accessModifier` | Controls visibility (`public`, `private`, `protected`, default)             |
| `returnType`     | Type of value returned (`int`, `String`, `void`, etc.)                      |
| `methodName`     | Follows camelCase naming convention                                         |
| `parameters`     | Optional inputs (can be zero or more)                                       |
| `method body`    | Code that runs when the method is called                                    |
| `return`         | Sends back a value (if return type is not `void`)                           |

## ⛓️‍💥 Breakdown of Components
### ◻ Access Modifier (optional, defaults to package-private):
* **public**: Accessible from any class.
    #### 📌 Example:
    ```java
    // Accessible from any other class or package
    public class PublicExample {
        public void show() {
            System.out.println("This is public");
        }
    }
    ```
* **protected**: Accessible within the same package or subclasses.
    #### 📌 Example:
    ```java
    // Accessible within same package or subclasses
    public class ProtectedExample {
        protected void display() {
            System.out.println("This is protected");
        }
    }
    ```
* **private**: Accessible only within the same class.
    #### 📌 Example:
    ```java
    // Accessible only within this class
    public class PrivateExample {
        private void secret() {
            System.out.println("This is private");
        }

        public void reveal() {
            secret(); // OK: calling private method from within the same class
        }
    }
    ```

* **(No modifier)**: Package-private (**default**), accessible within the same package.
    #### 📌 Example:
    ```java
    // Accessible only within the same package
    class PackagePrivateExample {
        void execute() {
            System.out.println("This is package-private");
        }
    }
    ```

### ◻ Other Modifiers (optional):
* **static**: Belongs to the class, not instances (covered later).
    #### 📌 Example:
    ```java
    public class Counter {
        private static int count = 0;

        public static void increment() {
            count++;
        }

        public static int getCount() {
            return count;
        }
    }
    ```

* **final**: Cannot be overridden (in subclasses).
    #### 📌 Example:  
    ```java
    class Vehicle {
        public final void startEngine() {    // cannot be overridden
            System.out.println("Engine started");
        }
    }

    class Car extends Vehicle {
        // Compilation error if you try to override startEngine():
        // public void startEngine() { … }
    }
    ```


* **abstract**: Declaration only; no body (must be in abstract class/interface).
    #### 📌 Example:  
    ```java
    public abstract class Animal {          // abstract class
        public abstract void makeSound();   // no method body

        public void sleep() {                // concrete method
            System.out.println("Zzz");
        }
    }

    class Dog extends Animal {
        @Override
        public void makeSound() {            // must provide implementation
            System.out.println("Woof");
        }
    }
    ```

* **synchronized**: Thread-safe; acquires lock on object/class (covered later).
    #### 📌 Example:  
    ```java
    public class BankAccount {
        private int balance = 1000;

        public synchronized void withdraw(int amount) {  // thread-safe
            if (balance >= amount) {
                balance -= amount;
            }
        }
    }
    ```

* **native**: Implemented in native code (e.g., C/C++ via JNI).
    #### 📌 Example:  
    ```java
    public class NativeDemo {
        // implemented in C/C++ via JNI
        public native int compute(int x, int y);

        static {
            System.loadLibrary("NativeLib");  // loads libNativeLib.so or .dll
        }
    }
    ```

* **strictfp**: Ensures floating-point consistency across platforms.
    #### 📌 Example:  
    ```java
    public strictfp class Calculator {      // enforces consistent Floating-Point behavior
        public double divide(double a, double b) {
            return a / b;
        }
    }
    ```
