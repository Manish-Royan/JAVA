# 🧩 Static Variable in Java

## 📚 Overview

* Static variables are a core feature of Java, enabling class-level data sharing across all instances of class.

* Static variables in Java are one of the foundational pillars in class design, providing a way to share data and behavior among all instances of a class without each instance carrying its own copy.

* They are declared using the `static` keyword and are **initialized only once when the class is loaded into memory**. 

### 💭 Remember: 💡 "Static means shared — not individual!"

## # Syntax of Static Variable in Java
```Java
static datatype variableName = Value;
```
[SM1]

## 🧾 Declaration

➡️ Declared with the `static` keyword inside a class (but outside methods/constructors).
```Java
public class Counter {
    static int count = 0; // Static variable
}
```


## 📖 Definitation:

→ A static variable (also known as a class variable) is declared with the `static` keyword. Unlike **instance variables**, which are created anew for every object, **static variables** belong entirely to the class. This means:

- **Single Memory Allocation**: **Only one copy of a static variable exists**, irrespective of how many objects are instantiated.

- **Shared State**: Any change made to a static variable is immediately visible to all objects that reference it, making it ideal for properties that should be common across all instances (e.g., *configuration settings*, *counters*, *caches*).


## Q. What does it mean that static variable are class level variable❓
→ When we say that static variables are class-level variables, it means that they belong to the class itself rather than any specific instance of the class. This has several important implications:

1. **🌟 Shared Among All Instances**
    - Unlike **instance variables, which are unique to each object**, **static variables are common for all object** which exist only once in memory.
    - Any change made to a static variable is reflected across all objects of the class.
    ```Java
    public class DEMO {

      static int number;

      public static void main(String args[]) {
        DEMO ob1 = new DEMO();
        ob1.number++; // Increment the value of static variable number

        // Printing the static variable througb ob1
        System.out.println(DEMO.number); 

        DEMO ob2 = new DEMO();
        ob2.number++; // Increment the value of static variable number

        // Printing the static variable througb ob2
        System.out.println(DEMO.number); 
      }
    }
    ```

    #### The code above outputs the following ⬇️:
    ```
    1
    2
    ```

    ### 💡 Explaination of above code:
    * In the above example, a single copy of the static variable in java, i.e, `number` is shared among all the two instances of the class.
    * First instance named as `ob1` increments the value of `number` variable by `1` and the second instance named as `ob2` again increments the value of a `static` variable `number` by `1` finally making it `2`.

2. **💾 Memory Efficiency**
    - Static variables are allocated only once, when the class is loaded.
    - **They reside in a special memory area rather than being duplicated for every object**.

3. **✅ Accessed via Class Name**
    - Since static variables belong to the class, they can be accessed without creating an object:
    ```Java
    class Example {
        static int count = 0; // Class-level variable
    }

    public class Main {
        public static void main(String[] args) {
            System.out.println(Example.count); // Accessed using class name
        }
    }
    ```

4. **⏳ Exists Throughout the Program**
    - The static variable remains in memory as long as the class is loaded.
    - Instance variables, by contrast, disappear when objects are garbage collected.


## Q. What is the Use of static Keyword in Java❓
→ The main use of the static keyword in Java is memory management. Whenever we place a `static` keyword before initialising a particular class’s methods or variables, these static methods and variables belong to the class instead of their instances or objects.


## 🔭 Scope

* Accessible throughout the **class** and **other classes** (depending on access modifiers).
    ```Java
    class Example {
        // Static variable with public access modifier
        public static int publicStaticVar = 10;

        // Static variable with private access modifier (Accessible only within this class)
        private static int privateStaticVar = 20;

        // Static method to access private static variable within this class
        public static int getPrivateStaticVar() {
            return privateStaticVar;
        }
    }

    public class StaticVariableDemo {
        public static void main(String[] args) {
            // Accessing public static variable directly using class name
            System.out.println("Public Static Variable: " + Example.publicStaticVar);

            // Accessing private static variable using a public static method
            System.out.println("Private Static Variable: " + Example.getPrivateStaticVar());
        }
    }
    ```
    ### 💡 Explaination of above code:
    - `publicStaticVar` can be accessed from anywhere using `Example.publicStaticVar` because it has `public` access.
    - `privateStaticVar` is restricted to `Example` class, but can be accessed through the `getPrivateStaticVar()` method.
    - Static variables belong to the class rather than instances, meaning they remain shared across all objects of the class.

* Exists independently of objects.


### Q. Why Can't Static Variables Be Declared Inside Methods❓
➡️ We cannot declare static variables in the `main()` method or any kind of method of the class. The `static` variables must be declared as a class member in the class, because during compilation time, JVM binds `static` variables to the class level, which means they have to be declared as class members.
#### 📌 Example:
```Java
class ADEMO {

  static int insideClass; // Valid declaration

  void hello() {
    static int insideMethod; // Invalid declaration
  }

  public static void main(String[] args) {
    static String insideMainmethod; // Illegal modifier
  }
}   
```
👉 Static variables must be declared as class members (inside the class but outside any method, constructor, or block). The main reason is that the JVM binds static variables to the class level during compilation. Here’s what happens:

### 💡 Explaination of above code:
* In Java, static variables can be declared like class members, like static int insideClass, which is a valid declaration of the static variable. However, **static variables cannot be declared inside any method scope**.
* The compiler will show a syntax error if we try to declare static variables inside any method.
* In the above example, if we try to declare a static variable in the `main()` method, the compiler will show an '***illegal modifier***' syntax error.

1. **🗃️ Class-Level Storage**
    - Static variables are allocated **when the class is loaded into memory**, not when methods are called.
    - Methods only execute when explicitly invoked, meaning any variables inside them exist **only when the method runs**.
    - A static variable **needs to persist** throughout the class lifecycle, but local variables inside a method **are temporary** and get deleted once the method finishes execution.
 
2. **📥 Memory Location Differences**
    - Static variables are stored in the Class Area (Metaspace in Java 8+), meaning they belong to the class rather than an instance or a method.
    - Local variables inside methods are stored in the stack, which is temporary and method-specific.

    ### 📌 Example: Invalid Static Declaration in a Method
    ```Java
    //This code will cause a compilation error:
    class Demo {
        public static void main(String[] args) {
            static int x = 100; // ❌ ERROR: Cannot declare static inside a method
        }
    }
    ```
    **💡 The compiler rejects this because static variables are class-level properties, not method-local variables.**

3. **✅ Correct Usage**: Static Variable as a Class Member
    - Static variables should be **declared inside the class but outside any method**, like this:
    ### 📌 Example: Invalid Static Declaration in a Method
    ```Java
    class Demo {
        static int x = 100; // ✅ Correct: Static variable is a class member

        public static void main(String[] args) {
            System.out.println(x); // Accessing static variable
        }
    }
    ```
    **👉 Since `x` is a class member, it’s bound to the class when the JVM loads Demo, and it remains available throughout the program execution.**

🔑 **Key Takeaways**
✔ Static variables belong to the class, not methods.

✔ JVM binds static variables during class loading, before any method runs.

✔ Static variables persist, while method variables disappear when execution ends.

✔ Always declare static variables inside the class but outside methods, constructors, or blocks.


## 🟢 Initialization & ⚙️ Default Values

→ Static variables in Java are unique in their behavior compared to instance variables. Their initialization and default value assignment follow specific rules tied to **class loading**, m**emory allocation**, and **JVM phases** . Below is a comprehensive breakdown:

### 1. **🚀 Initialization Phases of Static Variables**
➡️ Static variables undergo two distinct phases during class loading:

* **📋 Phase 1**: Preparation (Default Initialization)
    - **When**: During class loading, before the class is actively used.
    - **What Happens**: 
        - The JVM allocates memory for static variables.
        - Assigns default values (as shown below).
        ```Java
        public class DefaultValueExample {
            static int x;          // Default: 0
            static String name;   // Default: null
            static boolean flag;  // Default: false

            public static void main(String[] args) {
                System.out.println(x);     // Output: 0
                System.out.println(name);  // Output: null
                System.out.println(flag);  // Output: false
            }
        }
        ```
    - **Key Point**: This phase does not execute any code (e.g., no static blocks or constructors run here).

* **📋 Phase 2**: Initialization (Explicit Initialization)
    - **When**: After the preparation phase, triggered by active use of the class (e.g., creating an instance, invoking a static method, accessing a static field).
    - **What Happens**:
        - Static variables are assigned their explicit values (e.g., `static int x = 10;`).
        - Static initializer blocks (`static { ... }`) execute in the order of declaration.
        ```Java
        public class InitializationPhases {
            static int a = 10; // Direct initialization
        
            static {
                b = a + 5; // Static block uses 'a' (already initialized)
            }
        
            static int b; // Declared after static block
        
            public static void main(String[] args) {
                System.out.println(a); // Output: 10
                System.out.println(b); // Output: 15
            }
        }
        ```
### 2. **Static Variable Initialization Order (Top-Down)**
➡️ In Java, static variables and static initialization blocks are executed in strict top-down order within a class. This means the JVM processes them sequentially, in the exact order they appear in the source code.

### Q. How it works❓
→ When a class is loaded:
* Static variable declarations are processed first (memory allocated + default values assigned).
* Static initialization blocks (static { ... }) run next.
* Order matters: The JVM executes them line by line, from top to bottom.

#### 📌 Example:

```Java
public class Example {
    // Static variable declaration (initialized first)
    static int x = 5;

    // Static initialization block (runs after x is declared)
    static {
        System.out.println("Static block 1: x=" + x); // x is 5
        x = 10;
    }

    // Another static variable (processed after the block above)
    static int y = x * 2; // y = 10 * 2 = 20

    // Second static block (runs after y is declared)
    static {
        System.out.println("Static block 2: x=" + x + ", y=" + y); // x=10, y=20
    }

    public static void main(String[] args) {
        System.out.println("Final: x=" + x + ", y=" + y);
    }
}
```

#### 👉 Step-by-Step Execution:
* First, `static int x = 5;` is initialized.
* Then, the first `static` block runs, modifying `x` to `10`.
* Next, `static int y = x * 2;` calculates `y` using the updated `x` (now `10` → `y=20`).
* Finally, the second `static` block prints the latest values (`x=10`, `y=20`).

#### The code above outputs the following ⬇️:
```
Static block 1: x=5
Static block 2: x=10, y=20
Final: x=10, y=20
```

### 🔑Key Rules: 
1. **Declaration Order Matters**:
- If we swap the positions of `x` and `y`, the results change.
- Example: If `y` is declared before the first static block, it will use `x`’s default value (`0`).

2. **Default Values First**:
* All static variables start with default values (`0`, `null`, `false`) before explicit initialization.
    #### 📌 Example:
    ```Java
    static int a;
    static { System.out.println(a); } // Prints 0 (default) before assignment.
    ```
3. **Circular Dependencies Cause Errors**:
- If `static int A = B + 1;` and `static int B = A + 1;` are inter-dependent, the JVM throws an error.

4. **Practical Implications**
- **Use Case**: Initialize static resources (e.g., database connections, caches) in a specific order.
- **Pitfall**: Accessing a static variable before its declaration leads to its default value.
    ```Java
    static {
        System.out.println(z); // Compiles, but prints 0 (default for int).
    }
    static int z = 100;
    ```

### 3. **🔒 Final Static Variables (Constants)**
➡️ `static final` variables (constants) have special behavior:

* **Compile-Time Constants**: If a `static final` variable is initialized with a **compile-time constant expression**, the compiler **inlines** its value directly into the bytecode of dependent classes.
    #### 📌 Example:
    ```Java
    public class Constants {
        public static final int MAX = 100; // Compile-time constant
    }

    public class User {
        public static void main(String[] args) {
            System.out.println(Constants.MAX); // Inlined as 100 in bytecode
        }
    }
    ```

* **Non-Compile-Time Constants**: If initialized with a runtime value (e.g., method call), the value is not inlined.
    #### 📌 Example:
    ```Java
    public class DynamicConstants {
        public static final int RANDOM = new Random().nextInt(100);
        // Not a compile-time constant
    }
    ```
### 4. ⚠️ **Exception Handling in Static Initialization**
➡️ If an exception occurs during static initialization, the JVM throws `ExceptionInInitializerError`.
#### 📌 Example:
```Java
public class BadStaticInit {
    static int x = Integer.parseInt("invalid");

    public static void main(String[] args) {
        // This will never run
    }
}
```
#### The code above outputs the following ⬇️:
```
Exception in thread "main" java.lang.ExceptionInInitializerError
Caused by: java.lang.NumberFormatException: For input string: "invalid"
```

### 5. **Static Initializer Blocks**
➡️ Used to initialize complex static variables or perform logic during class loading.
```Java
public class StaticBlock {
    static String message;
    static {
        message = "Hello, Static World!";
        // Can also include complex logic
    }
}
```

### 6. ⏳ Lifetime of Static Variables
* **Start**: When the class is loaded into memory (via **the JVM's ClassLoader**).
* **End** : When the class is unloaded from the JVM, which happens only when:
    1. The **ClassLoader** that loaded the class is garbage-collected.
    2. No references to the class or its instances exist.
* **Implications**:
    * Static variables remain in memory for the entire application lifecycle unless manually unloaded.
    * Potential memory leaks if static variables hold references to large objects or resources.

#### 📌Example of Static Variable Lifetime :
```Java
public class Counter {
    static int count = 0; // Shared across all instances

    public Counter() {
        count++; // Increments the shared variable
    }

    public static void main(String[] args) {
        Counter c1 = new Counter();
        Counter c2 = new Counter();
        System.out.println(Counter.count); // Output: 2
    }
}
```

#### 📌Example of Static Variable Lifecycle:
```Java
public class StaticLifecycle {
    static String name = "Initial"; // Direct initialization
    static {
        System.out.println("Static block executed"); // Runs after 'name' is initialized
    }

    public static void main(String[] args) {
        System.out.println(StaticLifecycle.name); // Triggers class loading
        StaticLifecycle.name = "Updated"; // Modify static variable
        StaticLifecycle obj = new StaticLifecycle();
        obj.name = "Modified via instance"; // Not recommended (confusing)
        System.out.println(StaticLifecycle.name); // Output: Modified via instance
    }
}
```

## 🧐 What happens in memory🖥️❓
➡️ Behind the scenes:
* The `static` **variable is stored in a special area of memory called the Method Area**.
* It is **loaded once** when the class is loaded into memory by the JVM.
* It is **not stored in the heap** like object instance variables.


## 🔑 Key Characteristics of Static Variables
1. **🔗 Class-Level Scope**: Static variables belong to the class rather than any specific instance.
2. **📦 Single Copy in Memory**: Only one copy of a static variable exists, regardless of how many objects are created.
3. **🌐 Shared Among Instances**: All objects of the class share the same copy of static variables. If one object modifies a static variable, the change is visible to all other objects of that class.
4. **✅ Accessed Using Class Name**: Static variables can be accessed directly using the class name without creating an object.
5. **⏳ Lifetime**: Static variables exist from the time the class is loaded until the program terminates or the class is unloaded. They persist throughout the entire program execution.
6. **⚡Memory Efficiency**: Since only one copy exists, static variables help conserve memory.


## 🔐 Different Access Modifiers

```Java
class Example {
    // 🌐 Public static variable (accessible everywhere)
    public static int publicStaticVar = 10;

    // 🔒 Private static variable (accessible only within this class)
    private static int privateStaticVar = 20;

    // 🔍 Static method to access private static variable
    public static int getPrivateStaticVar() {
        return privateStaticVar;
    }
}

public class StaticVariableDemo {
    public static void main(String[] args) {
        // ✅ Accessing public static variable directly
        System.out.println("🌐 Public Static Variable: " + Example.publicStaticVar);

        // ✅ Accessing private static variable through method
        System.out.println("🔒 Private Static Variable: " + Example.getPrivateStaticVar());
    }
}
```
### 💡 Explaination of above code:
* 🌐 `publicStaticVar` → Accessible from anywhere using `Example.publicStaticVar`
* 🔒 `privateStaticVar` → Restricted to `Example` class only
* 📦 Static variables remain shared across all objects of the class


## 🔓 Syntax for Accessing: 
```Java
// Method 1

// Print the static variable VariableName from class ClassName.
System.out.println(ClassName.VariableName);

// Method 2

// We can access static variables using objects as well
// ob is an object of ClassName class

// This throws a warning that can be ignored
System.out.println(ob.VariableName);
```
* **Classname**: Name of the class containing the static variable.
* **(.) dot operator**: Operator used to access static variables.
* **Variablename**: Name of static variable that needs to be called.
[SM2]


#### 📌 Example:
```Java
public class Main {

  static int num = 10;

  public static void main(String[] args) {
    // Method 1
    System.out.println(Main.num);

    // Method 2
    Main ob = new Main();
    System.out.print(ob.num);
  }
}
```

### 💡 Explaination of above code:
* In the above example, the static variable named `num` is accessed in two ways. One way is without creating an instance of the class using the class reference only and the second way is using the instance of the class.
* While accessing the static variable in java using the instance of the class, the compiler will show a warning: **The static field Main.ans should be accessed in a static way**.
* This is because there is no need to create an instance of accessing the static members of the class.

#### The code above outputs the following ⬇️:
```
10
10
```

## 💽 Static Variables in Static and Non-Static Methods
- **Static methods can access static variables directly** because both are at the class level.
- **Non-static methods can also access static variables**, since static variables exist independent of instance creation.
```Java
class Test {
    static int count = 5;  // Static variable

    static void show() {  // Static method
        System.out.println("Count: " + count); // ✅ Works: Static method accessing static variable
    }

    void display() {  // Non-static method
        System.out.println("Count in non-static method: " + count); // ✅ Works
    }
}
```
### 👉 Here, both `show()` and `display()` can access count, since it's a c**lass-level** variable.

## Q. Why Can't Static Methods Access Non-Static (Instance) Variables❓
A static method cannot directly access instance variables because:
1. **Static methods belong to the class**, not an instance.
2. **Instance variables belong to objects**, meaning they are created only when an object is instantiated.
3. **If no instance exists, there is no object to provide instance variables**, leading to a reference error.

#### 📌 Example:
```Java
class Example {
    int number = 10; // Non-static (instance) variable

    static void show() {
        System.out.println(number); // ❌ ERROR: Cannot access instance variable inside a static method
    }
}
```
### Q. Why does this fail⚠️❓
- The method `show()` is static, meaning it belongs to the class.
- `number` is non-static, meaning it belongs to an object.
- Since no instance is created, there is no object to reference `number`, causing an error.


## ▸ The Concept of Object Reference in Static Context
- **If a static method contains a reference to a non-static variable, which object should that variable belong to?**
- Since static methods do **not belong to any instance**, the JVM **doesn’t know which object's data to refer to**, leading to a reference error.

✅ To fix this, we need an object inside a static method before accessing non-static variables:
```Java
class Example {
    int number = 10; // Non-static variable

    static void show() {
        Example obj = new Example(); // ✅ Creating an instance
        System.out.println(obj.number); // ✅ Works now
    }
}
```



## 🌐 [Accessibility of the Static Variable in Java](https://www.scaler.com/topics/java/static-variable-in-java/)
→ Static variables can be accessed by calling the class name of the class. There is no need to create an instance of the class for accessing the static variables because static variables are the class variables and are shared among all the class instances.
#### 📌 Example:
```Java
class demo {
    static String msg = "Hello";

    public static void main(String[] args)
    {
        System.out.print(demo.msg); //directly accessing through class name 🔓

        demo ref = new demo();
         System.out.print(ref.msg); //still valid but not reccomended ⚠️
    }
}
```

* The **static variables can be accessed in all types of methods**: static or non-static.
    #### 📌 Example:
    ```Java
    class Example {
        // Static variable (accessible in both static and non-static methods)
        static int staticVar = 10;

        // Non-static variable (accessible only in non-static methods)
        int nonStaticVar = 20;

        // Static method
        static void staticMethod() {
            System.out.println("Static variable: " + staticVar); // Accessible

            // Trying to access non-static variable directly - will cause error
            // System.out.println("Non-static variable: " + nonStaticVar); // ❌ Reference error

            // Correct way to access non-static variable in static method
            Example obj = new Example();
            System.out.println("Non-static variable (via object): " + obj.nonStaticVar);
        }

        // Non-static method
        void nonStaticMethod() {
            System.out.println("Static variable: " + staticVar); // Accessible
            System.out.println("Non-static variable: " + nonStaticVar); // Accessible
        }

        public static void main(String[] args) {
            // Calling static method without creating an object
            staticMethod();

            // Calling non-static method requires an object
            Example obj = new Example();
            obj.nonStaticMethod();
        }
    }
    ```
    
* **We cannot access non-static variables in static methods** because **non-static variables can only be accessed by creating an instance of the class**.
    #### 📌 Example:
    ```Java
    class Example {
        // Non-static variable
        int nonStaticVar = 10;

        // Static method
        static void staticMethod() {
            // Trying to access a non-static variable directly (this will cause an error)
            // System.out.println(nonStaticVar); // Uncommenting this line will cause a compilation     error

            // Correct way: Access non-static variables by creating an instance
            Example obj = new Example();
            System.out.println("Accessing non-static variable through an instance: " + obj. nonStaticVar);
        }

        public static void main(String[] args) {
            staticMethod();
        }
    }
    ```
* However, **static methods can be called without creating an instance of the class**.
    #### 📌 Example:
    ```Java
    class Example {
        // Static method
        static void staticMethod() {
            System.out.println("Static method called without creating an instance!");
        }

        public static void main(String[] args) {
            // Calling static method directly using class name
            Example.staticMethod();
        }
    }
    ```
* This leads to reference error for the non-static variable because non-static variables can be accessed only through an instance of the class that’s why static methods cannot access non-static variables.
    #### 📌 Example:
    ```Java
    class Example {
        // Non-static variable
        int nonStaticVar = 10;

        // Static method attempting to access non-static variable
        static void staticMethod() {
            // Trying to access non-static variable directly (this causes a compilation error)
            // System.out.println(nonStaticVar); // ❌ Error: Cannot access non-static variable from    a static context

            // Correct way: Access non-static variable through an instance
            Example obj = new Example();
            System.out.println("Accessing non-static variable via object: " + obj.nonStaticVar);
        }

        public static void main(String[] args) {
            staticMethod(); // Calling static method
        }
    }
    ```

* Another way of understanding this: If we have not created any instance of a class and call the static method which contains a non-static reference, which object should the non-static member point to?
    ```Java
    public class DEMO {

      int number;

      static void check() {
        // Error as non-static number is accessed in a static method
        System.out.println(number);
      }

      void check1() {
        System.out.println(number); // Valid accessing
      }
    }
    ```
### 💡 Explaination of above code:
* In the above example, a non-static variable named `number` is called in two methods: one is a static method `check()` and the other is a non-static method i.e `check1()`.
* When the non-static variable is called inside `check()`, Java compiler will show a **reference error** because:
    * We can only access the non-static variable only after creating an instance of the class.
    * But `static` methods can be called without creating an instance of the class,
    * That’s why during accessing the non-static variable in the static method compiler will show **reference error**.



## 🤔 Common Confusions

| Confusion                                         | Clarification                                                                               |
| ------------------------------------------------- | ------------------------------------------------------------------------------------------- |
| Can a static variable be accessed from an object? | Yes, but **not recommended**. Prefer `ClassName.variable`                                   |
| Can we use `this` with static variables?          | ❌ No, because `this` refers to an instance, and `static` doesn’t belong to any one instance |
| Is static variable same as global variable?       | Not exactly, but behaves similarly in scope within a class                                  |


## 🧵 Thread Safety:
- ### **Concurrency Control**:
    Because static variables represent global state, they are susceptible to race conditions in multi-threaded applications. If a static variable is mutable, you must ensure thread-safe access using synchronization mechanisms, the volatile keyword, or thread-safe data structures (e.g., classes from `java.util.concurrent`).
- ### **Design Patterns and Common Uses**:
    - **Singleton Pattern**:
    The Singleton pattern often leverages a private static variable to hold its single instance, ensuring that only one instance exists throughout the lifecycle of the application.
    - **Counters and Caches**:
    Static variables are commonly used to track counts (like the number of object instances created) or to cache values that are expensive to compute.
    - **Configuration Parameters**:
    For application-wide configuration or constant values (like database connection strings or application names), static final variables provide a centralized, immutable reference.

## 📌 Example:  Static Behavior
``` Java
//the following code snippet that demonstrates various aspects of static variables:
public class StaticDemo {

    // A mutable static variable shared among all instances.
    public static int counter = 0;
    
    // A static final variable, representing an immutable constant.
    public static final String APP_NAME = "MyJavaApp";
    
    // Static block: executed once when the class is loaded.
    static {
        System.out.println("Static block executed. APP_NAME = " + APP_NAME);
    }
    
    public StaticDemo() {
        // Incrementing the shared counter upon each instantiation.
        counter++;
        System.out.println("Constructor invoked. Counter: " + counter);
    }
    
    // Static method: can access static variables directly.
    public static void displayAppName() {
        System.out.println("Application Name: " + APP_NAME);
    }
    
    public static void main(String[] args) {
        // Accessing static method and variable via class name.
        StaticDemo.displayAppName();
        System.out.println("Initial Counter: " + StaticDemo.counter);
        
        // Creating multiple instances to see the shared nature of 'counter'.
        new StaticDemo();
        new StaticDemo();
        new StaticDemo();
        
        System.out.println("Final Counter value: " + StaticDemo.counter);
    }
}
```

### 💡 Explaination of above code:
- **Static Block**:
    Runs only once when the class is loaded; it initializes or logs static data before any object is created.
- **Static vs. Instance**:
    The `counter` variable, being static, is incremented with each instance creation. All objects observe the same counter value. In contrast, if `counter` were an instance variable, each object would carry its own separate counter.
- **Access Pattern**:
    The static method `displayAppName()` directly references the static final variable `APP_NAME`, reinforcing that constants are part of the overall class context.

## 🍂 Pitfalls and Advanced Considerations
1. **Global State Management**:
    While static variables simplify certain designs, they introduce global state, which can lead to code that is hard to debug, test, and maintain if misused. Excessive reliance on static state can hide dependencies and complicate concurrency management.
    ```Java
    /*Example: If multiple threads modify a static variable, it can lead to unpredictable behavior*/
    class GlobalState {
    //Problem: Static variables introduce global state, making debugging and testing difficult.
        static int counter = 0; // Shared global state

        static void increment() {
            counter++; // Not thread-safe
        }

        public static void main(String[] args) {
            Thread t1 = new Thread(() -> {
                for (int i = 0; i < 1000; i++) {
                    increment();
                }
            });

            Thread t2 = new Thread(() -> {
                for (int i = 0; i < 1000; i++) {
                    increment();
                }
            });

            t1.start();
            t2.start();

            try {
                t1.join();
                t2.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println("Final Counter Value: " + counter); // Unpredictable output due to   race condition
        }
    }
    ```
    ### 👉 Solution: Use `synchronized` or `AtomicInteger` to ensure thread safety.

2. **Memory Leaks**:
    Static variables that hold references to large objects or resources, especially in contexts like web applications or applications running on custom class loaders, may inadvertently prevent those objects from being garbage-collected, leading to memory leaks.
    ```Java
    /*Example: A static cache holding large objects indefinitely*/

    import java.util.HashMap;
    import java.util.Map;

    class MemoryLeakExample {
        //Problem: Static variables holding large objects prevent garbage collection, leading to memory leaks.
        static Map<Integer, byte[]> cache = new HashMap<>(); // Large object reference

        static void loadCache() {
            for (int i = 0; i < 1000; i++) {
                cache.put(i, new byte[1024 * 1024]); // 1MB per entry
            }
        }

        public static void main(String[] args) {
            loadCache();
            System.out.println("Cache loaded with large objects.");

            // Even after setting cache to null, objects may not be garbage collected
            cache = null;

            System.gc(); // Attempt to trigger garbage collection
            System.out.println("Garbage collection requested.");
        }
    }
    ```
    ### 👉 Solution: Use `WeakReference` or `SoftReference` to allow garbage collection when memory is low.


3. **Lazy Initialization**:
    Sometimes, it's beneficial to delay the initialization of a static variable until it's actually needed. One common technique is to use a nested static helper class (often seen in the **Bill Pugh Singleton pattern**), which ensures thread safety without an explicit synchronization block.
    ```Java
    /*Solution: Use lazy initialization to delay creation until needed.*/
    class LazySingleton {
        //Problem: Static variables are initialized at class loading, even if they are never used.
        private LazySingleton() {
            System.out.println("Singleton instance created.");
        }

        private static class Holder {
            static final LazySingleton INSTANCE = new LazySingleton();
        }

        public static LazySingleton getInstance() {
            return Holder.INSTANCE; // Created only when accessed
        }

        public static void main(String[] args) {
            System.out.println("Before accessing Singleton...");
            LazySingleton instance = LazySingleton.getInstance(); // Instance created here
        }
    }
    /* Benefits:
    - Thread-safe without synchronization
    - Lazy initialization ensures the instance is created only when needed
    */
    ```
4. **Testing Challenges**:
    Global state via static variables can create difficulties for unit testing. Tests might inadvertently affect one another if they share mutable static state, unless carefully reinitialized or isolated.
    ```Java
    /*Example: A static variable affecting multiple test cases*/
    class TestHelper {
        static int sharedState = 0; // Shared across tests
    
        static void reset() {
            sharedState = 0; // Manual reset required
        }
    }
    
    public class StaticTestExample {
        //Problem: Static variables persist across tests, causing unintended side effects.
        public static void main(String[] args) {
            // Test 1
            TestHelper.sharedState = 5;
            System.out.println("Test 1: " + TestHelper.sharedState); // Expected: 5
    
            // Test 2 (unexpected behavior)
            System.out.println("Test 2: " + TestHelper.sharedState); // Expected: 0, but gets 5
    
            // Fix: Reset static state before each test
            TestHelper.reset();
            System.out.println("Test 2 after reset: " + TestHelper.sharedState); // Expected: 0
        }
    }
    /*Solution:
        ✔ Use dependency injection instead of static variables
        ✔ Reset static variables before each test
    */
    ```

5. **Class Loaders and Static Variables**:
    If two different ClassLoader instances load the same class, each class has its own copy of static variables.
    ```Java
    MyClassLoader loader1 = new MyClassLoader();
    MyClassLoader loader2 = new MyClassLoader();
    Class<?> class1 = loader1.loadClass("MyClass");
    Class<?> class2 = loader2.loadClass("MyClass");
    // class1 and class2 have separate static variables
    ```  
    ### 💡 Explaination of above code:
    * This statement refers to how **different instances of ClassLoader** can load the same class **separately**, leading to multiple versions of the class, each with its own copy of static variables.

    ### 🔍 How This Works   
    * Normally, when a class is loaded by the JVM, its **static variables are shared** across all instances of that class. However, if two different **ClassLoader** instances load the same class separately, each loaded version **does not share static data—instead**, each version gets its own independent copy.

    #### 📌 Code Demonstration of example:
    ```Java
    import java.lang.reflect.Method;

    class MyClass {
        // Static variable
        static int staticVar = 10;

        // Static method to print the static variable
        static void printStaticVar() {
            System.out.println("Static Variable: " + staticVar);
        }
    }

    class MyClassLoader extends ClassLoader {
        public Class<?> loadMyClass(String className) throws ClassNotFoundException {
            return super.loadClass(className);
        }
    }

    public class ClassLoaderDemo {
        public static void main(String[] args) throws Exception {
            // Creating two different ClassLoader instances
            MyClassLoader loader1 = new MyClassLoader();
            MyClassLoader loader2 = new MyClassLoader();

            // Loading the same class using different ClassLoaders
            Class<?> class1 = loader1.loadMyClass("MyClass");
            Class<?> class2 = loader2.loadMyClass("MyClass");

            // Checking if the classes are equal (they should not be)
            System.out.println("class1 == class2 ? " + (class1 == class2)); // ❌ False - Different versions

            // Accessing the static variable separately
            Method method1 = class1.getMethod("printStaticVar");
            Method method2 = class2.getMethod("printStaticVar");

            // Invoking static method using reflection
            method1.invoke(null); // Prints staticVar from class1
            method2.invoke(null); // Prints staticVar from class2

            // Modifying staticVar in one version
            class1.getField("staticVar").set(null, 50);

            // Checking if the change reflects across both versions
            method1.invoke(null); // ✅ Should print 50
            method2.invoke(null); // ✅ Should still print 10 (unchanged!)
        }
    }
    ```

    ### 🧐 Key Observations
    - **Two separate ClassLoader instances** (`loader1`, `loader2`) load `MyClass`.
    - This creates t**wo independent versions** of `MyClass` in memory (`class1` and `class2`).
    - These versions **do not share static variables**:
        - Modifying `staticVar` in one **does not** affect the other.
        - Each version maintains its **own independent copy** of static members.

    ### Q. Why This Happens❓
    Since the same class is loaded **twice separately** using **different ClassLoader instances**, the JVM treats them as **completely different classes**, preventing **static variable sharing** between them.
    This concept is commonly used in **plugin systems, isolated application loading, and JVM sandboxing**.

6. **Garbage Collection**:
    * Static variables are **not eligible for GC** until their class is unloaded.
    * Avoid holding references to temporary objects in static variables.


## 💼 Common Use Cases
* **Constants**: `public static final double PI = 3.14;`
* **Counters**: Track object instances (e.g., static int objectCount;).
    #### 📌 Example: 
    ```Java
    class Example {
        // Static counter variable (shared across all instances)
        static int objectCount = 0;

        // Constructor increments the counter each time a new object is created
        Example() {
            objectCount++;
        }

        // Static method to display the current object count
        static void showObjectCount() {
            System.out.println("Total objects created: " + objectCount);
        }

        public static void main(String[] args) {
            // Creating instances
            Example obj1 = new Example();
            Example obj2 = new Example();
            Example obj3 = new Example();

            // Displaying object count
            Example.showObjectCount(); // Outputs: Total objects created: 3
        }
    }
    ```
* **Singleton Pattern**: Maintain a single instance via a static variable.
    #### 📌 Example: 
    ```Java
    class Singleton {
        // Static variable to hold the single instance
        private static Singleton singleInstance;

        // Private constructor to prevent instantiation from outside
        private Singleton() {
            System.out.println("Singleton instance created!");
        }

        // Static method to return the single instance
        public static Singleton getInstance() {
            if (singleInstance == null) {
                singleInstance = new Singleton(); // Create instance if it doesn't exist
            }
            return singleInstance;
        }

        // Example method
        public void showMessage() {
            System.out.println("Hello from Singleton!");
        }
    }

    public class SingletonDemo {
        public static void main(String[] args) {
            // Retrieving the same instance multiple times
            Singleton obj1 = Singleton.getInstance();
            Singleton obj2 = Singleton.getInstance();

            // Both references point to the same object
            System.out.println("Are obj1 and obj2 same? " + (obj1 == obj2)); // ✅ True

            obj1.showMessage(); // Calling a method from Singleton instance
        }
    }

    /*Singleton in Real Life
        - Database Connection Managers
        - Logging Services
        - Configuration Manager 
    */
    ```
    ### 💡 Explaination of above code:
    - **Static Variable** (`singleInstance`) stores the **single instance** of the class.
    - **Private Constructor** ensures no other object can be created directly using `new`.
    - **Static Method** (`getInstance()`) returns the **single instance**, creating it only once.
    - Calling `getInstance()` multiple times **returns the same object**.

* **Shared Resources**: Caches, thread pools, database connections.
    #### 📌 Example: Cache using a static variable
    ```Java
    import java.util.HashMap;
    import java.util.Map;

    class CacheManager {
        // Static cache shared across all instances
        private static final Map<String, String> cache = new HashMap<>();
        /* Why use static?
        - The cache is shared across all parts of the program, avoiding redundant storage.
        */

        // Method to add data to cache
        static void addToCache(String key, String value) {
            cache.put(key, value);
        }

        // Method to retrieve data from cache
        static String getFromCache(String key) {
            return cache.getOrDefault(key, "Not found");
        }
    }

    public class CacheExample {
        public static void main(String[] args) {
            // Adding data to the cache
            CacheManager.addToCache("user1", "Alice");
            CacheManager.addToCache("user2", "Bob");

            // Retrieving cached data
            System.out.println(CacheManager.getFromCache("user1")); // Outputs: Alice
            System.out.println(CacheManager.getFromCache("user3")); // Outputs: Not found
        }
    }
    ```

    #### 📌 Example: Database Connection Using Static Singleton
    ```Java
    import java.sql.Connection;
    import java.sql.DriverManager;
    import java.sql.SQLException;

    //A database connection should be reused instead of creating multiple connections.

    class DatabaseConnection {
        // Static instance shared across the application
        private static Connection connection;

        // Private constructor to prevent instantiation
        private DatabaseConnection() {}

        // Static method to get the connection instance
        public static Connection getConnection() throws SQLException //Using Static to Prevents creating multiple database connections, reducing overhead.
        {
            if (connection == null) {
                connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb", "user", "password");
            }
            return connection;
        }
    }

    public class DatabaseExample {
        public static void main(String[] args) throws SQLException {
            // Getting a shared database connection
            Connection conn1 = DatabaseConnection.getConnection();
            Connection conn2 = DatabaseConnection.getConnection();

            System.out.println("Are connections same? " + (conn1 == conn2)); // ✅ True - Same  connection reused
        }
    }
    ```


## 🏆 Best Practices
* **Avoid Mutable Statics**: Prefer immutability (e.g., `static final`).
* **Synchronize Access**: Use locks or `atomic` utilities for mutable statics.
* **Prevent Leaks**: Nullify static references when no longer needed.
* **Use Sparingly**: Static state complicates testing and scalability.