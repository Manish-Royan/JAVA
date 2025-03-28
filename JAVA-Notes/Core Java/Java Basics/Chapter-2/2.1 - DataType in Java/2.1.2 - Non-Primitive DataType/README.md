# **Non-Primitive Data type**

Non-primitive data types are also called ***reference types*** because they refer to **objects**. They are created using defined constructors of classes. These  **reference types**, are quite different from primitive data types and form the backbone of Java programming.

## Q.  What Are Non-Primitive Data Types❓
⇛ Non-primitive (or reference) data types in Java are types that refer to **objects** created in the heap memory. Unlike primitives (such as `int`, `char`, `boolean`, etc.), non-primitive types do not store the actual values. Instead, they store a reference (or address) to where the data is located in memory. Unlike primitives, they are not predefined (except `String` and `arrays`) and are used to store complex data. 

### # They include:

## 1️⃣ `String` (Special Class in Java):

The String data type stores a sequence or array of characters. A string is a non-primitive data type, but it is predefined in Java. String literals are enclosed in double quotes.

In Java, String is an immutable object, meaning once created, it cannot be changed.

The String type is so much used and integrated in Java, that some call it "***the special ninth type***".

A String in Java is actually a non-primitive data type, because it refers to an object. The String object has methods that are used to perform certain operations on strings.

### » Syntax:
```Java
String s1 = "Hello";       // String literal (stored in the string pool)
String s2 = new String("Hello"); // Object in heap
```

### » Key Feature:
* **Immutable**: Once created, its value cannot be modified.
* **Methods**: Supports methods like length(), substring(), equals(), etc.

### » Purpose:
* Represents an immutable (unchangeable) sequence of Unicode characters.
* Stored in the **string pool** (for literals) or heap (for new instances).


### 📌 Example:
```Java
public class StringExample {
    public static void main(String[] args) {
        String message = "Hello, Java!";
        System.out.println(message.length()); // Output: 12
        System.out.println(message.toUpperCase()); // Output: HELLO, JAVA!
    }
}
```

### The code above outputs the following ⬇️:
```
12
HELLO, JAVA!
```

### Q. When to Use Strings❓

✅ When we need to store and manipulate textual data.

✅ When we require string operations like concatenation, substring, etc.

🔹`StringBuffer` & `StringBuilder` are used for mutable strings (i.e., strings that can be modified).


## 2️⃣ `Arrays` (Collection of Elements of Same Type):

An array is a collection of elements of the same data type, stored in contiguous memory locations. 

It is an object in Java, and the array name (used for declaration) is a reference value that carries the base address of the continuous location of elements of an array.

### 📌 Example:
```Java
int Array_Name = new int[7];
```
[IMG]

### » Syntax:
```Java
int[] numbers = new int[5];       // Declare & initialize
String[] names = {"Alice", "Bob"}; // Shortcut syntax
```

### » Key Feature:
* **Zero-based indexing**: Access elements via array[index].
* **Length**: Fixed size (use array.length to get size).
* **Default Values**:
    * Numeric arrays: `0`
    * Boolean arrays: `false`
    * Object arrays: `null`

### » Purpose:
* Stores a fixed-size collection of elements of the same type.

### 📌 Example:
```Java
public class ArrayExample {
    public static void main(String[] args) {
        int[] numbers = {10, 20, 30, 40, 50}; // Array of integers

        // Accessing elements
        System.out.println("First number: " + numbers[0]); // Output: 10

        // Looping through array
        for (int num : numbers) {
            System.out.print(num + " ");  // Output: 10 20 30 40 50
        }
    }
}

```

### Q. When to Use Array❓

✅ When we need to store multiple values of the same type in a single variable.
✅ When we need fast access to elements using an index.


## 3️⃣ Classes (User-Defined Data Type):

A class is a blueprint for creating objects. It defines properties (fields) and behaviors (methods).

A class is a user-defined data type from which objects are created. It describes the set of properties or methods common to all objects of the same type. It contains fields and methods that represent the behaviour of an object. A class gets invoked by the creation of the respective object.

There are two types of classes: a blueprint and a template. For instance, the architectural diagram of a building is a class, and the building itself is an object created using the architectural diagram.

### 📌 Demonstartion:
[IMG 2]
[IMG 3]

### » Syntax:
```Java
class Student {
  String name;  // Field
  void study() { // Method
    System.out.println(name + " is studying.");
  }
}

// Create an object
Student student1 = new Student();
student1.name = "Alice";
student1.study(); // Output: Alice is studying.
```

### » Key Feature:
* **Constructors**: Special methods to initialize objects.
* **Encapsulation**: Use `private` fields with public getters/setters.
* **Inheritance**: Classes can extend other classes.

### » Purpose:
* User-defined blueprints for creating objects (e.g., Student, Car).
* Contains fields (variables) and methods.

### 📌 Example:
```Java
class Car {
    // Fields (attributes)
    String brand;
    int speed;

    // Constructor (special method to initialize objects)
    public Car(String brand, int speed) {
        this.brand = brand;
        this.speed = speed;
    }

    // Method (behavior)
    public void displayCarInfo() {
        System.out.println("Car: " + brand + ", Speed: " + speed + " km/h");
    }
}

public class Main {
    public static void main(String[] args) {
        // Creating an object (instance) of the class
        Car myCar = new Car("Tesla", 200);
        myCar.displayCarInfo();  // Output: Car: Tesla, Speed: 200 km/h
    }
}
```

### Q. When to Use Class❓
✅ When we need to define complex objects (e.g., real-world entities like Cars, Students).
✅ When we want to group data and behaviors together.


## 4️⃣ `Interfaces` (Abstract Data Type)
An interface is declared like a class. The key difference is that the interface contains **abstract methods by default**.

An interface is a contract that defines methods without implementation. Classes that "**implement**" an interface must provide an implementation for its methods.

### » Syntax:
```Java
interface Animal {
  void eat(); // Abstract method (no body)
}

class Dog implements Animal {
  public void eat() {
    System.out.println("Dog eats bones.");
  }
}
```

### » Key Feature:
* No Instantiation: Cannot create objects directly.
* `Default/Static` Methods: Added in Java 8 for backward compatibility.
```Java
interface Vehicle {
  default void start() {
    System.out.println("Vehicle started.");
  }
}
```

### » Purpose:
* Defines a **contract** (abstract methods) that classes must implement.
* Supports polymorphism and multiple inheritance.

### 📌 Example:
```Java
interface Drawable {
  void draw();
}

class Circle implements Drawable {
  public void draw() {
    System.out.println("Drawing a circle.");
  }
}

public class InterfaceDemo {
  public static void main(String[] args) {
    Drawable shape = new Circle();
    shape.draw(); // Output: Drawing a circle.
  }
}
```

### Q. When to Use Interface❓
✅ When we want to define a common structure that multiple classes should follow.
✅ When we need multiple inheritance (since Java doesn’t support multiple class inheritance).


## 5️⃣ Enums (Fixed Set of Constants)

An Enum is a special data type that represents a group of fixed constants.

An enum, similar to a class, has attributes and methods. However, unlike classes, enum constants are public, static, and final (unchangeable – cannot be overridden). Developers cannot use an enum to create objects, and it cannot extend other classes. But, the enum can implement interfaces.

### » Syntax:
```Java
enum Day {
  MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY
}

Day today = Day.MONDAY;
```

### » Key Feature:
* **Type-Safe**: Compile-time checks prevent invalid values.
* **Methods**: Can have methods and constructors.
```Java
enum Color {
  RED("#FF0000"), GREEN("#00FF00");
  private String hexCode;
  Color(String hexCode) {
    this.hexCode = hexCode;
  }
  public String getHexCode() {
    return hexCode;
  }
}
```

### » Purpose:
* Represents a fixed set of constants (e.g., days of the week).

### 📌 Example:
