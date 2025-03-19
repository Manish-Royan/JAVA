# **Non-Primitive Data type**

Non-primitive data types are also called ***reference types*** because they refer to **objects**. They are created using defined constructors of classes. These  **reference types**, are quite different from primitive data types and form the backbone of Java programming.

## Q.  What Are Non-Primitive Data Types❓
⇛ Non-primitive (or reference) data types in Java are types that refer to **objects** created in the heap memory. Unlike primitives (such as `int`, `char`, `boolean`, etc.), non-primitive types do not store the actual values. Instead, they store a reference (or address) to where the data is located in memory. Unlike primitives, they are not predefined (except `String` and `arrays`) and are used to store complex data. 

### # They include:

## 1. `String` (Special Class in Java):

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

✅ When you need to store and manipulate textual data.

✅ When you require string operations like concatenation, substring, etc.

🔹`StringBuffer` & `StringBuilder` are used for mutable strings (i.e., strings that can be modified).


## `Arrays`:

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

### Q. When to Use Strings❓

✅ When you need to store multiple values of the same type in a single variable.
✅ When you need fast access to elements using an index.

