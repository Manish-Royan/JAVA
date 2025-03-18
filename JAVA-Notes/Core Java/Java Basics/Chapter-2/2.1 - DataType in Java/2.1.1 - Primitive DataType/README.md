# **Primitive Data type**

Java has 8 primitive data types, which are predefined, non-object types used to store simple values directly in memory. They are the building blocks for data manipulation in Java and are optimized for speed and memory efficiency. A primitive data type specifies the type of a variable and the kind of values it can hold.


## Q. Why Java Has Primitive Types❓

* **Efficiency**: Primitive types are faster than objects since they store values directly in memory (no overhead like objects).

* **Memory Optimization**: They consume less memory compared to objects.

* **Platform Independence**: Java ensures that primitive types have fixed sizes across all platforms (unlike C/C++ where sizes depend on the OS and CPU architecture).


## ▸ Key Characteristics of Primitive Data Types:
* **Fixed Size**: Sizes are platform-independent (unlike **C/C++**).

* **Stack Allocation**: Stored in stack memory for fast access.

* **No Methods**: They are not objects and cannot call methods (e.g., `5.toString()` **is invalid**).

* **Performance**: Faster than objects (**no heap memory overhead**).


## Q. When to Use Which Primitive Type❓
|  **Scenario** |  **Recommended Type** | **Reason**
|:-----|:--------|:-----
| Small integers | `byte`/`short` | Save memory in large arrays.
| General integers | `int` | Default for whole numbers.
| Very large integers | `long` | **Timestamps**, **unique IDs**.
| General decimals | `double` | Higher precision.
| Memory-sensitive decimals | `float` | SRare (e.g., 3D graphics).
| Single characters | `char` | Text processing.
| True/false logic | `boolean` | Conditional checks.

## ▸ Common Mistakes & Best Practices:

1. Avoid `float` for Precision: Use `double` unless memory is critical.
2. Use Underscores for Readability:
    ```Java
    int billion = 1_000_000_000; // Better than 1000000000.
    ```
3. Explicit Suffixes:
    ```Java
    long bigNumber = 100L; // 'L' suffix for long.
    float pi = 3.14f;      // 'f' suffix for float.
    ```
4. No Boolean Casting:
    ```Java
    // Invalid in Java (valid in C/C++):
    // if (1) { ... } 
    // Use:
    if (isValid) { ... }
    ```


### 📌 Example: Primitive Types in Action:

```Java
public class PrimitiveDemo {
  public static void main(String[] args) {
    byte seats = 100;          // Stadium seating capacity
    int daysInYear = 365;      // Default integer
    long nationalDebt = 28_834_000_000_000L; // Huge number
    double gravity = 9.80665;  // Precise decimal
    char grade = 'A';          // Student grade
    boolean isRaining = false; // Weather status

    System.out.println("National Debt: " + nationalDebt);
  }
}
```

## ▸ There are 8 primitive data types, and they are:

### ↳ Integer types (Whole Numbers): `byte`, `short`, `int`, `long`

## 1️⃣ **`byte`**: 
The `byte` data type is an 8 bit two's complement integer—variables of type byte store numeric values between -128 to 127 (inclusive). The default value of a `byte` variable is 0, which is used to save space in large arrays, which is mainly beneficial in integers since a `byte` is four times smaller than an integer. The `byte` data type is useful for saving memory in large arrays.

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


### Q. When to use such data-type❓
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

### ⁕ Summary📖
⇛ Java allows `byte b = 'c';` because:

i. '*c*' is a **literal** (constant expression).

ii. Its Unicode value (**99**) fits within the byte range (-128 to 127).

iii. Java implicitly narrows the `char` to `byte` in this specific case.

👉 For non-literal values or out-of-range characters, you must use an **explicit cast** (e.g., `(byte)`), but this can lead to data loss.


## 2️⃣ **`short`**: 
The `short` data type is a 16-bit signed two’s complement integer. Similar to byte, a `short` is used when memory savings matter, especially in large arrays where space is constrained. 


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

### Q. When to use such data-type❓
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


## 3️⃣ **`int`**:
The `int` data type is a 32 bit signed two's complement integer. This numeric data type is used to store numbers that lie between the range of -2,147,483,648 to 2,147,483,647 (inclusive). The range defines the minimum and maximum value of the `int` **array** in Java for storing. 


👉 Remember:  In Java SE 8 and later, we can use the int data type to represent an unsigned 32-bit integer, which has a value in the range [0, 2^32  -1]. Use the Integer class to use the int data type as an unsigned integer.


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

## Q. When to use such data-type❓
→ Use `int` as the default for whole numbers unless memory constraints or larger ranges are required.

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

### » Note 📝
→ "Unlike other programming languages like C and C++, the data type system in Java is different. In programming languages like C and C++, the size of the int data type depends on the operating system and architecture (e.g., 16-bit, 32-bit, or 64-bit systems). However, in Java, int is always 32 bits (4 bytes) regardless of the platform because Java is platform-independent and runs on the JVM, which ensures uniform data type sizes across all operating systems."

## Q. Did you that 💭❓
→ PSY's "Gangnam Style" became so popular that it broke YouTube's original view counter. Initially, YouTube used a **32-bit** signed integer to store view counts, which has a maximum value of *2,147,483,647*. When "Gangnam Style" surpassed this number in 2014, YouTube had to upgrade its system to use a **64-bit signed integer**, which can handle up to 9 quintillion views. Quite the milestone for a viral video, isn't it?

After "Gangnam Style" went viral, YouTube switched to a **64-bit** signed integer, akin to `long` in Java, to accommodate much larger numbers. This was a technical adjustment to ensure their system could handle future viral hits without breaking! It's a great example of how real-world usage can push software systems to evolve.


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


### 📌 Example Code to Prove Java `int` is Always 4 Bytes

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


## 4️⃣ **`long`**:
The `long` data type can store whole numbers from *-9223372036854775808* to *9223372036854775807*. This is used when `int` is not large enough to store the value. 

#### 👉 Note that you should end the value with an `"L"`. The `Long` class also contains methods like *compareUnsigned*, *divideUnsigned* etc to support arithmetic operations for unsigned `long`.

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

### » Note 📝
→ In Java 8 and later, you can represent an unsigned 64-bit long using the long data type, which has value in the range [0, 2^64-1].

##
### ↳ Floating-Point Data Types (Decimals): `float`, `double`

## 5️⃣ **`float`**: 
The float data type is a single-precision 32-bit IEEE 754 floating-point. Use a float (instead of **double**) if you need to save memory in large arrays of floating-point numbers. The size of the float data type is **4 bytes (32 bits)**. Its default value is `0.0f`. However, it's important to note that the float data type is not suitable for precise values, such as **currency**, due to potential **rounding errors** in floating-point arithmetic.


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
→ Use float for memory-sensitive applications requiring fractional values. We should use a floating point type whenever you need a number with a decimal, such as **9.99** or **3.14515**.


### 📌 Example:
```Java
class FloatDemo {
  public static void main(String[] args) {
    float pi = 3.1415f; // Precision loss after 6-7 decimals.
    System.out.println(pi);
  }
}
```

### » Note 📝
→ In the code example above, the `F` suffix of the floatVar variable tells the compiler that it's a float literal. Without it, the above code will return an error.



### The code above outputs the following ⬇️:
```
3.1415
```

## 6️⃣ **`double`**: 
The double data type is a double-precision **64-bit** IEEE 754 floating-point. For decimal values, **this data type is generally the default choice**.


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
class DoubleDemo {
  public static void main(String[] args) {
    double doubleVar = 2.71828;  
    System.out.println(doubleVar);
  }
}
```

### The code above outputs the following ⬇️:
```
2.71828
```


### Q. When to use float or double❓

The precision of a floating point value indicates how many digits the value can have after the decimal point. The precision of `float` is only six or seven decimal digits, while `double` variables have a precision of about 16 digits. Therefore it is safer to use `double` for most calculations.

### » Scientific Numbers:
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


### # While float and double in Java are similar to C and C++ in terms of precision and behavior, they are not exactly the same due to the platform-independent nature of Java. Let’s dive deep into this:

### 1️⃣ Similarities Between Java and C/C++ `float` & `double`
⇛ In both Java and C/C++, the `float` and `double` types follow the IEEE 754 floating-point standard, meaning:

* `float` (Single Precision, 32-bit)
  * Takes 4 bytes (32 bits)
  * Approximate 7 decimal digits of precision

* `double` (Double Precision, 64-bit)
  * Takes 8 bytes (64 bits)
  * Approximate 15–16 decimal digits of precision

* Both support:
  * Positive & Negative numbers
  * Special values like Infinity, -Infinity, and NaN (Not a Number)

### 2️⃣ Differences Between Java and C/C++ `float` & `double`

### ✅ Java Has Fixed Sizes (Unlike C/C++)

|  **Data Type** |  **Java Size (Fixed)e** | **C/C++ Size (Depends on OS)**
|:-----|:--------|:-----
| `byte` | 4 bytes (32 bits) | 4 bytes (usually)
| `double` | 	8 bytes (64 bits) | 4 bytes (usually)

  * In C/C++, float and double sizes can vary on some platforms (though modern systems typically use 32-bit and 64-bit).
  * In Java, float and double are always the same size, ensuring platform independence.

### ✅ Java Uses Strict Floating-Point Calculations

  * In C/C++, floating-point arithmetic depends on the CPU and compiler settings, so results may vary slightly across platforms.
  * In Java, floating-point arithmetic is strictly defined to be consistent across all systems due to the JVM’s strict floating-point model.

### 📌 Example (C++ and Java might give different results):
```Java
class FloatPrecision {
    public static void main(String[] args) {
        float f = 0.1f + 0.2f;
        System.out.println(f);  // Might output 0.30000001 due to precision errors
    }
}
```

👉 The result is always the same in Java, while in C++, it might slightly differ due to compiler optimizations.


### ✅ Java Defaults to `double` for Decimal Values

  * In C/C++, you can use `float` or `double` freely.
  * In Java, any decimal literal (`3.14`) is automatically a `double`, unless you add `f` (3.14f).

### 📌 Example:
```Java
float f = 3.14;   // Compilation Error! Needs 'f'
float f = 3.14f;  // Correct
```

👉 This avoids implicit precision loss.


### ✅  Java Has strictfp for Floating-Point Consistency

  * Java introduced the `strictfp` keyword to force strict IEEE 754 calculations.
  * In C/C++, floating-point operations might vary slightly based on CPU architecture (e.g., Intel vs ARM).
  * In Java, using `strictfp` ensures the same floating-point results on every machine.

### 📌 Example:
```Java
strictfp class Example {
    public static void main(String[] args) {
        double num = 10.0 / 3.0;
        System.out.println(num);  // Ensures exact precision on all platforms
    }
}
```

### ⁕ Summary📖
✅ Java float and double behave similarly to C/C++ but are strictly defined and platform-independent.

  * Same size everywhere (4 & 8 bytes) (Unlike C/C++, where sizes depend on OS)
  * More consistent floating-point calculations
  * Uses `strictfp` for uniform results across all systems
  * Defaults to `double` for `decimals`, unlike C/C++

|  **Feature** |  **Java `float` & `double`** | **C/C++ `float` & `double`**
|:-----|:--------|:-----
| Size | C/C++ float & double | Depends on OS/compiler
| Floating-Point Model | Floating-Point Model | Floating-Point Model
| Defaults to `double`? | Yes, decimals are `double` | No, compiler-dependent
| Precision Differences? | No, same result everywhere | Yes, may change on different CPUs
| Supports `strictfp`? | Yes, for predictable calculations | No equivalent keyword

### ↳ Other types: `char`, `boolean`

## 7️⃣ **`char`**: 
The `char` data type is a single 16-bit Unicode character with the size of 2 bytes (16 bits).

### » Syntax:
```Java
char charVar;
```

### » Size: 2 bytes (16 bits)

### » Range: 0 to 65,535 (Unicode characters)

```Java
char letter = 'A';
char symbol = '$';
char A = '\u0041'; // Unicode for 'A'  
char unicodeChar = '\u03A9'; // Unicode for 'Ω' (Omega)
// char c = -1; // ERROR! Negative values invalid  
System.out.println(unicodeChar); // Prints Ω
```

### » Default value: '\u0000' (null character)

### » Purpose:

* Represents Unicode characters for international text.

* Used in string manipulation and GUI applications.

### Q. When to use such data-type?
→ Use char for text processing or handling individual symbols.

### 📌 Example:
```Java
class CharDemo {
  public static void main(String[] args) {
    char grade = 'A';  
    System.out.println(grade);
  }
}
```

### The code above outputs the following ⬇️:
```
A
```

## Q. Why is the Size of char 2 bytes in Java❓
⇛ Unlike languages such as C or C++ that use the **ASCII** character set, Java uses the Unicode character set to support internationalization. Unicode requires more than 8 bits to represent a wide range of characters from different languages, including Latin, Greek, Cyrillic, Chinese, Arabic, and more. As a result, Java uses 2 bytes to store a `char`, ensuring it can represent any Unicode character.


## Q. Why Java Uses Unicode Instead of ASCII❓
1. ASCII Limitation:

    * ASCII (used in C, C++) is only 7-bit (or 8-bit in extended ASCII) and supports only 128 (or 256) characters.
    * It can represent basic English letters, numbers, and symbols but not characters from other languages.

2. Unicode Advantage:

    * Unicode is a universal character set that includes characters from all major writing systems.
    * Java’s `char` uses 16 bits (UTF-16), allowing it to represent 65,536 characters (much more than ASCII).
    * It supports Latin, Greek, Cyrillic, Arabic, Chinese, Japanese, Hindi, and even emojis!


### 📌 Example of Unicode in Java:
```Java
public class UnicodeExample {
    public static void main(String[] args) {
        char letter = 'A';       // Normal ASCII character
        char hindiChar = '\u0939'; // Unicode for 'ह' (Hindi character)
        char omega = '\u03A9';    // Unicode for 'Ω' (Greek Omega)
        char smiley = '\u263A';   // Unicode for ☺ (Smiley Face)

        System.out.println("Letter: " + letter);
        System.out.println("Hindi Character: " + hindiChar);
        System.out.println("Greek Letter: " + omega);
        System.out.println("Smiley Face: " + smiley);
    }
}
```

### The code above outputs the following ⬇️:
```
Letter: A
Hindi Character: ह
Greek Letter: Ω
Smiley Face: ☺
```

### ▸ Unicode Helps in Internationalization
⇛ Because of Unicode, Java applications can: 

✅ Support multiple languages (useful in global applications).

✅ Display special symbols, scientific notations, and emojis.

✅ Handle different scripts like Devanagari, Chinese, Japanese, Arabic, etc.

### # Different languages use different Unicode encodings, and memory allocation depends on the encoding format they use. Let’s explain in depth:

### 🔹 Unicode and Different Encoding Formats
Unicode itself is just a standard for representing characters. It doesn't specify how characters should be stored in memory. Instead, different encoding formats like **UTF-8**, **UTF-16**, and **UTF-32** determine how many bytes are used for each character.

### 1. Java (UTF-16) → Uses 2 Bytes (or sometimes 4 Bytes)
  * Java's char uses **UTF-16** encoding.
  * Most characters (like English letters) are stored in 2 bytes (16 bits).
  * Some special Unicode characters (like emojis, rare Chinese characters) require 4 bytes because they fall into the "supplementary range."

### 📌 Example of Unicode in Java:
```Java
char ch1 = 'A';       // Takes 2 bytes
char ch2 = 'ह';       // Takes 2 bytes (Devanagari script)
char ch3 = '😀';     // Takes 4 bytes (Emoji - supplementary character)
```

### 2. C, C++ (ASCII / UTF-8) → Uses 1 Byte (or Variable)
  * By default, C and C++ char is 1 byte (8 bits) and follows ASCII.
  * If Unicode is required, wchar_t or UTF-8 encoding is used, which can take 1 to 4 bytes per character depending on the character.
  * UTF-8 is variable-length encoding:
    * English letters → 1 byte (A → 0x41)
    * Latin, Greek, Cyrillic → 2 bytes
    * Chinese, Japanese → 3 bytes
    * Emojis → 4 bytes


### 📌 Example of Unicode in C++:
```cpp
#include <iostream>
using namespace std;
int main() {
    wchar_t ch1 = L'ह';   // Hindi character (may take 2 or 4 bytes depending on system)
    char32_t ch2 = U'😀'; // Emoji (4 bytes in UTF-8)
    cout << "Size of wchar_t: " << sizeof(wchar_t) << " bytes" << endl;
    cout << "Size of char32_t: " << sizeof(char32_t) << " bytes" << endl;
    return 0;
}
```


### The code above outputs the following ⬇️:
```
Size of wchar_t: 2 bytes (or 4 bytes, depending on OS)
Size of char32_t: 4 bytes
```

## 8️⃣ `Boolean`:
The `boolean` data type represents a logical value that can be either *true* or *false*. Conceptually, it represents a single bit of information, but the actual size used by the virtual machine is implementation-dependent and typically at least one byte (eight bits) in practice. Values of the boolean type are not implicitly or explicitly converted to any other type using casts. However, programmers can write conversion code if needed.

### » Syntax:
```Java
boolean booleanVar;
```

### » Size: ~1 bit (logically), but JVM typically uses 1 byte (8bits)

### » Range: *true* or *false*

```Java
boolean b = true;  
boolean b = false;  
// boolean x = 0; // ERROR! Not allowed in Java  
```

### » Default value: `false`

### » Purpose:

* Controls program flow in conditionals and loops.

* Represents binary states (e.g., *on/off*, *yes/no*).


### Q. When to use such data-type?
→ Use `boolean` for logical conditions or flags.

### 📌 Example:
```Java
class BooleanDemo {
  public static void main(String[] args) {
    boolean isJavaFun = true;  
    System.out.println(isJavaFun);
  }
}
```

### The code above outputs the following ⬇️:
```
true
```

## ▸ Memory Usage & Performance of Primitive Data Type ⏳

|  **Type** |  **Size** | **Performance** | **Used When**
|:-----|:--------|:-----|:-----
| `byte` | 1B | Very Fast | Large data sets, memory-sensitive apps
| `short` | 2B | Fast | Small numbers, memory-limited apps
| `int` | 4B | Default | General-purpose whole numbers
| `long` | 8B | Slower | Very large numbers
| `float` | 4B | Medium | Less precise decimals
| `double` | 8B | Slow | High-precision calculations
| `char` | 2B | Fast | Unicode characters
| `boolean` | 1B (approx.) | Fast | True/false conditions
