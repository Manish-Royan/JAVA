# **Primitive Data type**

Java has 8 primitive data types, which are predefined, non-object types used to store simple values directly in memory. They are the building blocks for data manipulation in Java and are optimized for speed and memory efficiency. A primitive data type specifies the type of a variable and the kind of values it can hold.


## Q. Why Java Has Primitive Types?

* **Efficiency**: Primitive types are faster than objects since they store values directly in memory (no overhead like objects).

* **Memory Optimization**: They consume less memory compared to objects.

* **Platform Independence**: Java ensures that primitive types have fixed sizes across all platforms (unlike C/C++ where sizes depend on the OS and CPU architecture).


### ▸ There are 8 primitive data types, and they are:

### ↳ Integer types (Whole Numbers): `byte`, `short`, `int`, `long`

1️⃣ **`byte`**: The `byte` data type is an 8 bit two's complement integer—variables of type byte store numeric values between -128 to 127 (inclusive). The range defines the minimum (-128) and maximum (127) value a `byte` can store. The default value of a `byte` variable is 0, which is used to save space in large arrays, which is mainly beneficial in integers since a `byte` is four times smaller than an integer. The `byte` data type is useful for saving memory in large arrays.

*"Since the 'byte' data type in Java allocates 8 bits, it is called a byte."* The term "byte" refers to a unit of digital information that consists of 8 bits, which is why the byte data type in Java is 8 bits long.

### » Syntax:
```Java
byte byteVar;
```

### » Size: 1 byte (8 bits) 

### » Range: -128 to 127 (signed 2's complement integer).

```Java
byte b = 127;   // Max value
byte c = -128;  // Min value
// byte d = 128; // ERROR! Out of range
```

### » Default value: 0

### » Purpose:

* Smallest integer type in Java.

* Stores small integers to save memory (e.g., pixel values, file I/O streams).

* Useful in arrays where memory conservation is critical.


### Q. When to use such data-type?
→ Use byte for large arrays (e.g., image processing) or when interfacing with 8-bit data streams.

### 📌 Example:
```Java
class ByteDemo {
  public static void main(String[] args) {
    
        byte byteVar = 126;
  
        // byteVar is of 8 bit
        System.out.println(byteVar);
        // Increment of byteVar value
        byteVar++;
        System.out.println(byteVar);
  
        // It overflows here because
        // byte hold values of range -128 to 127
        byteVar++;
        System.out.println(byteVar);
  
        // Looping back within the range
        byteVar++;
        System.out.println(byteVar);
  }
}
```

### The code above outputs the following ⬇️:
```
126

127

-128

-127
```

## Q. Why the statement `byte b = 'c';` does not throw an error❓ 
⇛ In Java, the statement `byte b = 'c';` does not throw an error because of **implicit narrowing conversion for constant expressions** that fit within the target type's range.

### # Let’s break this down 🧐:

### Q. Why `byte b = 'c';` Works❓
1. **`char` to `byte` Conversion**:

    * A `char` in Java is a **16-bit unsigned integer** representing a Unicode character (range: 0 to 65,535).

    * A `byte` is an **8-bit signed integer** (range: -128 to 127).

    * The character '*c*' has a Unicode/ASCII value of *99*, which is within the valid range of a `byte`.

2. **Implicit Narrowing**:

    * Java allows **implicit narrowing** (converting a larger type to a smaller type) **only for constant expressions** (literals or compile-time constants) that fit within the target type’s range.

    * Since '***c***' is a literal and its value (*99*) fits in a `byte`, Java automatically converts the `char` to a `byte`.

### 📌Example Code:
```Java
byte b = 'c'; // Valid: 'c' is 99 (within -128 to 127)
System.out.println(b); // Output: 99
```

### # Key Rules in Java:

1. **Implicit Narrowing**:

    * Allowed only for literals/constants (e.g., `byte b = 100;`, `byte b = 'c';`).

    * If the value exceeds the target type’s range, it will fail at compile-time:
    ```Java
    byte b = 200; // Error: 200 > 127 (byte's max value)
    byte b = '€'; // Error: '€' is Unicode 8364 (> 127)
    ```

2. **Explicit Cast Required for Variables**:
    * If the value is stored in a `char` variable (not a **literal**), you must explicitly cast it to `byte`:
    ```Java
    char c = 'c';
    byte b = (byte) c; // Explicit cast required
    ```

### Q. Why This Works for **'c'** but Not **'€'**❓

|  **Character** |  **Unicode Value** | **Fits in `byte`?** | **Result**
|:-----|:--------|:-----|:--------
| **'c'** | 99 | Yes | Allowed (implicit narrowing).
| **'€'** | 8364 | No | Compile-time error.

## # Important Notes 📝

1. **Lossy Conversion**:

    * Even though '*c*' works, this is technically a **lossy conversion** because a `char` (16 bits) is being truncated to a `byte` (8 bits). However, Java allows it for constants that fit.

    * If the `char` value exceeds **127**, it would wrap around due to `byte`’s signed nature (e.g., `char c = 200; byte b = (byte) c; gives b = -56`).

2. **Avoid Mixing `char` and `byte`**:

    * Use `char` for characters and `byte` for raw binary data (e.g., **file I/O**). Mixing them can lead to unintended behavior:
    ```Java
    byte b = 'ñ'; // Unicode 241 → exceeds 127 → Compile-time error!
    ```

### ▸ Summary📖
⇛ Java allows `byte b = 'c';` because:

i. '*c*' is a **literal** (constant expression).

ii. Its Unicode value (**99**) fits within the byte range (-128 to 127).

iii. Java implicitly narrows the `char` to `byte` in this specific case.

» For non-literal values or out-of-range characters, you must use an **explicit cast** (e.g., `(byte)`), but this can lead to data loss.


2️⃣ **`short`**: The `short` data type is a 16-bit signed two’s complement integer. Similar to byte, a `short` is used when memory savings matter, especially in large arrays where space is constrained. 


### » Syntax:
```Java
short shortVar;
```

### » Size: 2 bytes (16 bits)

### » Range: -32,768 to 32,767

```Java
short s = 32000;
// short x = 40000; // ERROR! Out of range
```

### » Default value: 0

### » Purpose:

* Stores medium-sized integers (rarely used directly; int is preferred for general cases).

* Legacy systems or protocols requiring 16-bit values (e.g., old file formats).

### Q. When to use such data-type?
→ Use short for memory optimization in large arrays or when interfacing with legacy protocols.

### 📌 Example:
```Java
class ShortDemo {
  public static void main(String[] args) {
    short shortVar = 134;  
    System.out.println(shortVar);
  }
}
```

### The code above outputs the following ⬇️:
```
134
```


3️⃣ **`int`**: The `int` data type is a 32 bit signed two's complement integer. This numeric data type is used to store numbers that lie between the range of -2,147,483,648 to 2,147,483,647 (inclusive). The range defines the minimum and maximum value of the `int` **array** in Java for storing. 

```
Remember:  In Java SE 8 and later, we can use the int data type to represent an unsigned 32-bit integer, which has a value in the range [0, 2  32  -1]. Use the Integer class to use the int data type as an unsigned integer. 
```

### » Syntax:
```Java
int intVar
```

### » Size: 4 bytes (32 bits)

### » Range: -2³¹ to 2³¹–1 (≈ -2.1 billion to 2.1 billion).

```Java
int num = 2147483647;
// int num2 = 2147483648; // ERROR! Out of range
```

### » Default value: 0

### » Purpose:

* Default choice for integer values (e.g., loop counters, arithmetic operations).

* Used in most calculations due to its balance of range and memory usage.

## Q. When to use such data-type?
→ Use int as the default for whole numbers unless memory constraints or larger ranges are required.

### 📌 Example:
```Java
class IntDemo {
  public static void main(String[] args) {
    int wholeNum = 132;
    System.out.println(wholeNum);
  }
}
```

### The code above outputs the following ⬇️:
```
132
```

```
Note: 
"Unlike other programming languages like C and C++, the data type system in Java is different. In programming languages like C and C++, the size of the int data type depends on the operating system and architecture (e.g., 16-bit, 32-bit, or 64-bit systems). However, in Java, int is always 32 bits (4 bytes) regardless of the platform because Java is platform-independent and runs on the JVM, which ensures uniform data type sizes across all operating systems."
```

## Q. Why Does `int` Size Depend on OS in C and C++❓

⇛ In C and C++, the size of `int` is not fixed because:
* It depends on the compiler and operating system.
* The compiler sets `int` to match the system’s word size for efficiency.

### 📌 Examples:
|  **System Architecture** |  **`int` Size (C/C++)** |
|:-----|:--------
| 16-bit System | 2 bytes (16 bits)
| 32-bit System | 4 bytes (32 bits)
| 64-bit System | 4 or 8 bytes (varies)

### 👉Thus, the same C/C++ program may give different results when compiled on different platforms.

## Q. Why Is `int` Fixed in Java?

⇛ **In Java, all primitive data types have a fixed size, regardless of the platform**.

### » Java Primitive Data Types and Their Sizes:

|  **Data Type** |  **Size (Always)** | **Example Vlaues** 
|:-----|:--------|:-----
| `byte` |	8 bits (1 byte) | -128 to 127
| `short` |	16 bits (2 bytes) | -32,768 to 32,767
| `int` |	32 bits (4 bytes) | -2,147,483,648 to 2,147,483,647
| `long` |	64 bits (8 bytes) | Large integer values
| `float` |	32 bits (4 bytes) | Decimal values (single-precision)
| `double` |	16 bits (2 bytes) | Unicode characters
| `boolean` |	1 bit (JVM-dependent) | *true* or *false*

### Q. Why Is This Important❓

*  **Java was designed to be platform-independent (*Write Once, Run Anywhere*)**.
* The JVM (Java Virtual Machine) ensures that **`int` is always 32 bits**, no matter the OS (Windows, Linux, macOS).
* This eliminates compatibility issues across different systems.


### Q. How Java Achieves Platform Independence❓
⇛ Java code runs inside the JVM, which acts as a bridge between the code and the operating system.

* Instead of compiling code into machine-dependent binary (like C/C++), Java compiles into bytecode.
* The JVM interprets the bytecode, ensuring uniformity across all platforms.

### 👉Thus, data type sizes remain the same, avoiding the variability seen in C and C++.


### 📌 Example Code to Prove Java int is Always 4 Bytes

⇛ We can check the size of `int` in Java using:

```JAVA
class DataTypeSize {
    public static void main(String[] args) {
        System.out.println("Size of int in Java: " + (Integer.SIZE / 8) + " bytes");
    }
}
```

### Output (on any system, any OS)⬇️:
```
Size of int in Java: 4 bytes
```

### 🔖Final Summary: 
✅ In C/C++, `int` size depends on OS & architecture (16-bit, 32-bit, 64-bit).

✅ In Java, `int` is always 4 bytes (32 bits), no matter the platform.

✅ This is because Java is platform-independent, running inside the **JVM**.

✅ This design removes compatibility issues and ensures code runs the same everywhere.


4️⃣ **`long`**: The `long` data type can store whole numbers from *-9223372036854775808* to *9223372036854775807*. This is used when `int` is not large enough to store the value. Note that you should end the value with an `"L"`. The `Long` class also contains methods like ***compareUnsigned***, ***divideUnsigned*** etc to support arithmetic operations for unsigned `long`.

### » Syntax:
```Java
long longVar;
```

### » Size: 8 bytes (64-bit)

### » Range: -9,223,372,036,854,775,808 to 9,223,372,036,854,775,807

```Java
long l = -9223372036854775808L;
long m = 9223372036854775807L;
// long n = 9223372036854775808L; // ERROR! Out of range
```

### » Default value: 0

### » Purpose:

* Stores very large integers (e.g., timestamps, astronomical calculations).

* Requires an `L` suffix for literals to distinguish from int.

### Q. When to use such data-type?
→ Use long for very large numbers (e.g., global population counts) or 64-bit systems.

### 📌 Example:
```Java
class LongDemo {
  public static void main(String[] args) {
    long num = 42903420404L;
    System.out.println(num);
  }
}
```

~~~
In the code example above, the `L` suffix of the num variable tells the compiler that it's a long literal. Without it, the code will return an error.
~~~

### The code above outputs the following ⬇️:
```
42903420404
```

```
Note:
In Java 8 and later, you can represent an unsigned 64-bit long using the long data type, which has value in the range [0, 264-1].
```

##
### ↳ Floating-Point Data Types (Decimals): `float`, `double`

5️⃣ **`float`**: The float data type is a single-precision 32-bit IEEE 754 floating-point. Use a float (instead of double) if you need to save memory in large arrays of floating-point numbers. The size of the float data type is 4 bytes (32 bits). Its default value is 0.0f. However, it's important to note that the float data type is not suitable for precise values, such as currency, due to potential rounding errors in floating-point arithmetic.


### » Syntax:
```Java
float floatVar;
```

### » Size: 4 bytes (32 bits)

### » Range: ±3.4e38 (6–7 decimal digits of precision). 

```Java
float f = 3.1415927f;  
float f = -1.4e-45f;  
// float x = 3.1415926535f; // Precision loss beyond 7 digits  
```

### » Default value: 0.0f

### » Purpose:

* Stores single-precision floating-point numbers (decimal values).

* Sacrifices precision for range and memory efficiency.

* Requires an `f` suffix for literals.

### Q. When to use such data-type?
→ Use float for memory-sensitive applications requiring fractional values. We should use a floating point type whenever you need a number with a decimal, such as 9.99 or 3.14515.


### 📌 Example:
```Java
class FloatDemo {
  public static void main(String[] args) {
    float pi = 3.1415f; // Precision loss after 6-7 decimals.
    System.out.println(pi);
  }
}
```

~~~
Note: In the code example above, the F suffix of the floatVar variable tells the compiler that it's a float literal. Without it, the above code will return an error.
~~~


### The code above outputs the following ⬇️:
```
3.1415
```

6️⃣ **`double`**: The double data type is a double-precision 64-bit IEEE 754 floating-point. For decimal values, this data type is generally the default choice. The size of the double data type is 8 bytes or 64 bits.


### » Syntax:
```Java
double doubleVar;
```

### » Size: 8 bytes (64 bits)

### » Range: ±4.9E-324 to ±1.7976931348623157E308

```Java
double d = 3.141592653589793;  
double d = -4.9e-324;  
// double x = 1.7e309; // ERROR! Out of range  
```

### » Default value: 0.0d

### » Purpose:

* Default choice for decimals (higher precision than float).
* Offers high precision (15-16 decimal digits).

### Q. When to use such data-type?
→ Use `double` for scientific calculations or financial applications requiring precision.


### 📌 Example:
```Java
class FloatDemo {
  public static void main(String[] args) {
    float pi = 3.1415f; // Precision loss after 6-7 decimals.
    System.out.println(pi);
  }
}
```

~~~
Note: In the code example above, the F suffix of the floatVar variable tells the compiler that it's a float literal. Without it, the above code will return an error.
~~~


### The code above outputs the following ⬇️:
```
3.1415
```


### Use float or double?

The precision of a floating point value indicates how many digits the value can have after the decimal point. The precision of `float` is only six or seven decimal digits, while `double` variables have a precision of about 16 digits. Therefore it is safer to use `double` for most calculations.

### Scientific Numbers:
A floating point number can also be a scientific number with an "`e`" to indicate the power of 10:

```Java
public class Main {
  public static void main(String[] args) {
    float f1 = 35e3f;
    double d1 = 12E4d;
    System.out.println(f1); //35000.0
    System.out.println(d1); //120000.0
  }
}
```


## # Important Notes 📝
Both `float` and `double` data types were designed especially for scientific calculations, where approximation errors are acceptable. If accuracy is the most prior concern then, it is recommended not to use these data types and use `BigDecimal` class instead. **It is recommended to go through *rounding off errors in java*.**