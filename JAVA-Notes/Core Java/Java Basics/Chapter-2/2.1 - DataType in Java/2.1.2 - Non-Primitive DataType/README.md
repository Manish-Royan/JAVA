# **Non-Primitive Data type**

Non-primitive data types are also called ***reference types*** because they refer to **objects**. They are created using defined constructors of classes. These  **reference types**, are quite different from primitive data types and form the backbone of Java programming.

## Q.  What Are Non-Primitive Data Types❓
⇛ Non-primitive (or reference) data types in Java are types that refer to **objects**. Unlike primitives (such as `int`, `char`, `boolean`, etc.), non-primitive types do not store the actual values. Instead, they store a reference (or address) to where the data is located in memory. 

### # They include:

## 1. `String`:

A String is a sequence of characters. In Java, String is an immutable object, meaning once created, it cannot be changed.

The String type is so much used and integrated in Java, that some call it "***the special ninth type***".

A String in Java is actually a non-primitive data type, because it refers to an object. The String object has methods that are used to perform certain operations on strings.

```Java
public class StringExample {
    public static void main(String[] args) {
        String message = "Hello, Java!";
        System.out.println(message.length()); // Output: 12
        System.out.println(message.toUpperCase()); // Output: HELLO, JAVA!
    }
}
```

### When to Use Strings❓

✅ When you need to store and manipulate textual data.

✅ When you require string operations like concatenation, substring, etc.

