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
| `accessModifier` | Controls visibility (`public`, `private`, `protected` default)             |
| `returnType`     | Type of value returned (`int`, `String`, `void`, etc.)                      |
| `methodName`     | Follows ***camelCase*** naming convention                                         |
| `parameters`     | Optional inputs (can be zero or more)                                       |
| `Throws clause`  | Optional, declares exceptions the method might throw |
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

### ◻ Return Type:
* **void**: No return value.
    #### 📌 Example:  
    ```java
    public class Logger {
        public void log(String message) {
            System.out.println(message);
        }
    }
    ```
* **Primitive types**: `int`, `double`, `boolean`, etc.
    #### 📌 Example:  
    ```java
    public class MathUtils {
        public int add(int a, int b) {
            return a + b;
        }

        public double average(double x, double y) {
            return (x + y) / 2;
        }

        public boolean isEven(int number) {
            return number % 2 == 0;
        }
    }
    ```

* **Reference types**: `String`, **custom classes**, `arrays`, `generics` (e.g., `List<String>`).
    #### 📌 Example:  
    ```java
    import java.util.List;
    import java.util.Arrays;

    public class ReferenceExamples {
        // Returns a String
        public String getGreeting() {
            return "Hello, world!";
        }

        // Returns a custom reference type Person
        public Person createPerson(String name, int age) {
            return new Person(name, age);
        }

        // Returns an array of Strings
        public String[] getColors() {
            return new String[] { "Red", "Green", "Blue" };
        }

        // Returns a generic List of Strings
        public List<String> getItems() {
            return List.of("Apple", "Banana", "Cherry");
        }

        public static void main(String[] args) {
            ReferenceExamples examples = new ReferenceExamples();

            // String return
            System.out.println("Greeting: " + examples.getGreeting());

            // Custom Person return
            Person person = examples.createPerson("Alice", 30);
            System.out.println("Created Person: " + person);

            // Array return
            System.out.println("Colors: " + Arrays.toString(examples.getColors()));

            // List return
            System.out.println("Items: " + examples.getItems());
        }
    }

    // Simple Person class with a toString override
    class Person {
        private String name;
        private int age;

        public Person(String name, int age) {
            this.name = name;
            this.age = age;
        }

        @Override
        public String toString() {
            return "Person{name='" + name + "', age=" + age + "}";
        }
    }
    ```

* **If non-void**, use return statement to exit and provide value.
    #### 📌 Example:
    ```java
    public class ReturnExample {
        public int absolute(int x) {
            if (x < 0) 
            /* If x is negative, we flip it to positive using -x. 
              * Example: if x = -5, then -x = 5.*/
            {
                return -x; // exit early and provide value
            }
            return x; //If x is already positive or zero, we just return it as-is.
        }

        public static void main(String[] args) {
            ReturnExample obj = new ReturnExample();

            System.out.println(obj.absolute(5));   // Output: 5
            System.out.println(obj.absolute(-8));  // Output: 8
            System.out.println(obj.absolute(0));   // Output: 0
        }
    }
    ```
### ◻ Method Name:
* Must be a valid identifier (starts with ***letter***/***underscore***/***$***).
* Convention: ***camelCase*** (e.g., *calculateSum*).
* For constructors: Same as class name.
    #### 📌 Example:
    ```java
    public class MethodNameExamples {

        // 1. Valid identifiers: start with a letter, underscore, or dollar sign
        public void myMethod() {
            System.out.println("Called myMethod()");
        }

        public void _underscoreMethod() {
            System.out.println("Called _underscoreMethod()");
        }

        public void $dollarMethod() {
            System.out.println("Called $dollarMethod()");
        }

        // 2. camelCase convention: multi-word names start lowercase then capitalize each   subsequent word
        public int calculateSum(int a, int b) {
            return a + b;
        }

        // 3. Constructor: name must match the class exactly
        public MethodNameExamples() {
            System.out.println("Constructor MethodNameExamples() called");
        }

        public static void main(String[] args) {
            MethodNameExamples example = new MethodNameExamples();

            example.myMethod();
            example._underscoreMethod();
            example.$dollarMethod();

            int sum = example.calculateSum(5, 7);
            System.out.println("Sum: " + sum);
        }
    }
    ```  

### ◻ Parameter List (optional):
* **Comma-separated**: `type1` `param1`, `type2` `param2`.
    #### 📌 Example:
    ```java
    public class CommaSeparatedExample {
        // A method with multiple parameters separated by commas
        public int compute(int a, int b, int c) // type1 param1, type2 param2, type3 param3
        {
            return a * b + c;
        }
    }
    ```

* Parameters are local variables passed by value (primitives) or reference (objects). 
    #### 📌 Example:
    ```java
    public class PassByExample {
    public void modify(int num, StringBuilder sb) {
        num = num + 5;            // changes only local copy of num
        sb.append(" world");      // mutates the same StringBuilder object
    }

        public static void main(String[] args) {
            int originalNum = 10;
            StringBuilder originalSb = new StringBuilder("Hello");
            
            new PassByExample().modify(originalNum, originalSb);
            
            System.out.println(originalNum);   // prints 10
            System.out.println(originalSb);    // prints "Hello world"
        }
    }
    //NOTE: Primitives are passed by value (a copy), object references are passed by value too—so you can modify the object but not reassign the caller’s reference:
    ```

* Can include generics (e.g., `<T>` T genericMethod(`T arg`)).
    #### 📌 Example:
    ```java
    public class GenericExample {
    
    //A method that works with any reference type via a type parameter <T>
    public <T> T identity(T arg) // <T> declares a type parameter; T arg is the generic parameter
    {
        return arg;
    }

        public static void main(String[] args) {
            GenericExample ex = new GenericExample();
            
            String text = ex.identity("Hello");
            Integer number = ex.identity(42);
            
            System.out.println(text);    // Hello
            System.out.println(number);  // 42
        }
    }
    ```

### ◻ Throws Clause (optional):
* `throws` `ExceptionType1`, `ExceptionType2`: Declares checked exceptions the method may throw.
    #### 📌 Example:
    ```java 
    import java.io.BufferedReader;
    import java.io.FileReader;
    import java.io.IOException;

    /*Method Declaration with `throws`*/
    public class ThrowsExample {

        // Declares that this method can throw IOException
        public String readFirstLine(String filePath) throws IOException
        //This method declares that it may throw an IOException. Any code that calls it must either handle that exception or declare it as well.
        {
            BufferedReader br = new BufferedReader(new FileReader(filePath));
            String line = br.readLine();
            br.close();
            return line;
        }
    }
    ```

* Caller must handle via `try-catch` or `propagate`.
    * **Handling the Exception with `try-catch`**
     #### 📌 Example:
    ```java 
    public class MainWithTryCatch {
        // Here the caller (main method) uses a try-catch block to catch and handle the checked exception
        public static void main(String[] args) {
            ThrowsExample example = new ThrowsExample();

            try {
                String firstLine = example.readFirstLine("input.txt");
                System.out.println("First line: " + firstLine);
            } catch (IOException e) {
                System.err.println("Error reading file: " + e.getMessage());
            }
        }
    }
    ```   

    * **Propagating the Exception to the Caller**: Instead of catching it, you can propagate the exception further up by declaring `throws` in `main`. In this case, the JVM will handle it if no other code catches it.
    #### 📌 Example:
    ```java 
   public class MainWithThrows {
        public static void main(String[] args) throws IOException {
            String firstLine = new ThrowsExample().readFirstLine("input.txt");
            System.out.println("First line: " + firstLine);
        }
    }
    ```   
    
## 🔖 Types and Vocabulary of Java Methods

### 📚 Overview
Java methods are more than syntax; they express intent. Classifying methods by role (**query**, **command**, **factory**, etc.) improves readability, testing, and design. Below are precise definitions, short example code, when to use each type, and practical notes for design and testing.

### 1. Query Methods
* **Definition**: Return information about object state without causing observable side effects.
* **Characteristics**: Pure or nearly pure, should not modify object state, frequently used in expressions and assertions.
    #### 📌 Example:
    ```java
    public class Account {
        private final int balance;
        public int getBalance() { return balance; }
    }
    ```
* When to use: Expose state or computed values; implement equals/hashCode/getters.
#### 👉 Notes: Prefer immutability and idempotence; name with get/is/has.
#### # Naming Conventions: get-, is-, has-
↳ Consistency in naming turns your methods into a self-documenting API:
* `getXxx()`
    * Standard for any non-boolean property.
    * Example: `getBalance()`, `getItems()`

* `isXxx()`
    * Preferred for boolean flags.
    * Example: `isActive()`, `isEmpty()`

* `hasXxx()`
    * Conveys possession or availability.
    * Example: `hasChildren()`, `hasErrors()`

#### 👉 Notes: Picking the right prefix helps automated tools (like JavaBeans introspectors) and colleagues immediately grasp purpose.

### 2. Command Methods
* **Definition**: Perform actions or mutate state; their value is the effect, not a return value.
* **Characteristics**: May modify fields, trigger I/O, or send events.
    #### 📌 Example:
    ```java
    public void withdraw(int amount) {
        if (amount > 0) balance -= amount;
    }
    ```
* **When to use**: Any operation that changes state or interacts with external systems.
#### 👉 Notes: Name with verbs (set, add, delete, send); keep side effects documented and minimal.

### 3. Factory Methods
* **Definition**: Create and return instances, often hiding constructor complexity or implementing caching/singleton logic.
* **Characteristics**: Static or instance methods that encapsulate object creation and configuration.

    #### 📌 Example: Static Factory with Caching
    ```java
    import java.util.Map;
    import java.util.concurrent.ConcurrentHashMap;
    
    public final class Color {
        private final int red;
        private final int green;
        private final int blue;
    
        // Cache to reuse Color instances
        private static final Map<String, Color> CACHE = new ConcurrentHashMap<>();
    
        // Private constructor hides instantiation details
        private Color(int red, int green, int blue) {
            this.red = red;
            this.green = green;
            this.blue = blue;
        }
    
        // Static factory method
        public static Color of(int red, int green, int blue) {
            String key = red + "-" + green + "-" + blue;
            // computeIfAbsent handles caching: returns existing or creates new
            return CACHE.computeIfAbsent(key, k -> new Color(red, green, blue));
        }
    
        @Override
        public String toString() {
            return "Color(" + red + ", " + green + ", " + blue + ")";
        }
    }
    
    // Usage:
    // Color c1 = Color.of(255, 0, 0);
    // Color c2 = Color.of(255, 0, 0);
    // c1 == c2  // true, same cached instance
    ```

    #### 📌 Example: Instance-Based Factory
    ```java
    public class ShapeFactory {
        private final String theme;

        // Constructor configures the factory
        public ShapeFactory(String theme) {
            this.theme = theme;
        }

        // Factory method for Circle
        public Shape createCircle(double radius) {
            // Hides the complexity of choosing a Circle subclass or setting defaults
            return new Circle(theme, radius);
        }

        // Factory method for Rectangle
        public Shape createRectangle(double width, double height) {
            return new Rectangle(theme, width, height);
        }
    }

    // Supporting classes
    interface Shape {
        void draw();
    }

    class Circle implements Shape {
        private final String theme;
        private final double radius;

        Circle(String theme, double radius) {
            this.theme = theme;
            this.radius = radius;
        }

        @Override
        public void draw() {
            System.out.println("Drawing " + theme + " circle of radius " + radius);
        }
    }

    class Rectangle implements Shape {
        private final String theme;
        private final double width;
        private final double height;

        Rectangle(String theme, double width, double height) {
            this.theme = theme;
            this.width = width;
            this.height = height;
        }

        @Override
        public void draw() {
            System.out.println("Drawing " + theme + " rectangle " + width + "x" + height);
        }
    }

    // Usage:
    // ShapeFactory factory = new ShapeFactory("Cartoon");
    // Shape circle = factory.createCircle(5.0);
    // circle.draw();
    ```

