# Data Type in Java

## Q. What is Data Type❓
Data types define the type and value range of the data for the different types of *variables*, *constants*, *method parameters*, *return types*, etc. The data type tells the compiler about the type of data to be stored and the required memory. To store and manipulate different types of data, all variables must have specified data types. It define the nature of variable.


## ▸ Data Type in Java 
**Java is a strongly typed language**. This means that every **variable must have a declared type**. Based on the data type of a variable, the operating system allocates memory and decides what can be stored in the reserved memory. Therefore, by assigning different data types to variables, we can store *integers*, *decimals*, or *characters* in these variables.

[IMG]

Data types in Java are of different sizes and values that can be stored in the variable that is made as per convenience and circumstances to cover up all test cases. Java has two categories in which data types are segregated:

1. **Primitive Data Type**: Primitive data types are predefined by the language and named by a keyword. There are eight primitive data types supported by Java such as `boolean`, `char`, `int`, `short`, `byte`, `long`, `float`, and `double`. *The `Boolean` with uppercase **B** is a **wrapper class** for the primitive data type boolean in Java*.

2. **Non-Primitive Data Type or Object Data type**: The non-primitive data types are not predefined. The reference data types are created using defined constructors of the classes. They are used to access objects. These variables are declared to be of a specific type that cannot be changed (*For example, Employee, Puppy, etc*). Non-Primitive Data Type such as `String`, `Array`, etc.


## ▸  Difference between **primitive** and **non-primitive** (reference) data types in Java🧐

|  **Primitive Data type** |  **Non-primitive Data type**
|:-----|:--------
| 1. **Definition**: Basic data types predefined by Java. They represent raw values and are not objects. | **1. Definition**: Created by the programmer (or Java itself). They are objects or references to objects.
| 2. **Memory Allocation**: Stored in stack memory (fast access).| 2. **Memory Allocation**: Stored in heap memory (objects), with references stored in the stack.
| 3. **Size**: Size is fixed (*e.g., `int` is 4 bytes*). | 3. **Size**: Size depends on the object (*e.g., a `String` with 10 characters uses more memory*).  
| 4. **Default Values**: Always has a default value.  (*e.g., 0 for `int`*) | 4. **Default Values**: Typically initialized to `null`.
| 5. **Performance**: Faster and memory-efficient. It Directly holds the value.| 5. **Performance**: Slower due to object overhead (*e.g., headers in heap*). It Holds a reference (address) to the object.
| 6. **Nullability**: Cannot be assigned `null`. (*eg. `int` **x** = `null`; → Error*)| 6. **Nullability**: Can be assigned `null`. (*eg. `String` **s** = `null`; → Valid*).
| 7. **Methods & Functionality**: No methods (*e.g., 5. has no methods*). It use wrapper classes (*e.g., `Integer`*) to treat primitives as objects.| 7. **Methods & Functionality**: Comes with methods for manipulation (*e.g., (e.g., `String.length()`).*). It has built-in methods for manipulation (*e.g., `ArrayList.add()`*).
| 8. **Parameter Passing**: Passed by value (copy of the value is sent). Changes in a method do not affect the original variable.| 8. **Parameter Passing**: Passed by reference (copy of the memory address is sent). Changes to the object in a method affect the original object.
| 9. **Usage in Collections**: Cannot be used directly in collections (*e.g., `ArrayList<int>` is invalid*). Require **wrapper classes** (*e.g., `Integer`, `Double`*).| 9. **Usage in Collections**: Used in collections (*e.g., `ArrayList<Integer>`*).
| 10. **Example**: `int`, `char`, `boolean`| 10. **Example**: `String`, `Arrays`, `Classes`

### #Example for pass by value of primitive data-type:
```Java
// Primitive (passed by value)
void increment(int x) { x++; }
int num = 5;
increment(num); // num remains 5
```
### #Example for pass by reference of non-primitive data-type:
```Java
// Non-Primitive (passed by reference)
void addElement(ArrayList<Integer> list) { list.add(10); }
ArrayList<Integer> nums = new ArrayList<>();
addElement(nums); // nums now contains 10
```

### #Example of Primitive data-type usage in collection:

```Java
// Primitive
int a = 10;       // Direct value
char c = 'A';     // Stack-stored
```

### #Example of Non-Primitive data-type usage in collection:

```Java
// Non-Primitive
String s = "Java"; // Object in heap, reference in stack
int[] arr = {1, 2}; // Array object in heap
```

## Q. Why Does This Matter🤷‍♀️❓
* **Primitives**: Use for efficiency (*e.g., math operations, loops*).

* **Non-Primitives**: Use for complex data structures (*e.g., String, collections*) and object-oriented programming.


## ▸ Conversion table:

|  **No. of bits** |  **Representation**
|:-----|:--------
| 1 | bit
| 4 | Nibble
| 8 | 1 byte
| 16 | WORD


## ▸ Data-type & their sizes:
|  **Data Type** |  **Size (in bytes)** | **Power** | **Range** | **Default Value** | **Description** 
|:-----|:--------|:-----|:--------|:----- |:-----
| byte | 1(8bits) | 2^8 | -2^7(-128) to 2^7-1(127) | 0 | 8-bit signed integer.			
| short | 2(16bits) | 2^16 | -2^15(-32,768) to 2^15–1(32,767) | 0 | 16-bit signed integer. Suitable for medium-sized numbers.			
| int | 4(32bits) | 2^32 | -2³¹ (–2,147,483,648) to 2³¹–1(2,147,483, 647) (≈±2.1 billion) | 0 | 32-bit integer. Default choice for whole numbers.	
| long | 8(64bits) | 2^64 | -2⁶³(–9,223,372,036,854,775,808) to 2⁶³–1(9,223,372,036,854,775,807)  | 0L | 64-bit integer. Used for very large numbers (e.g., timestamps).			
| float | 4(32bits) | ±3.4e38  |  Approximately ±3.40282347E+38F (6–7 significant decimal digits) | 0.0f | 32-bit IEEE 754 floating-point. Sacrifices precision for range.			
| double | 8(64bits) | ±1.7e308 |  Approximately ±1.79769313486231570E+308 (15 significant decimal digits) | 0.0d | 64-bit IEEE 754 floating-point. Default for decimals (higher precision).			
| char | 2(16bits) | ❌ | 0 to 65,535 (Unicode characters) | '\u0000' | 16-bit Unicode character (*e.g., `A`, `€`, `\n`*).		
| boolean | 1 bit (JVM-dependent - Not precisely defined) | ❌ | **true** or **false** | false | Represents logical values. Size is JVM-dependent (often 1 byte in practice).			
