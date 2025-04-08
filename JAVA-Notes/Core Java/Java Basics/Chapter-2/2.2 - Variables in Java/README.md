# **Varaibles in Java** 

Variable is the name of memeory location where we stored different type of values.

[IMG]

### ▸ Varaible declaration & Initialization:
* Syntax: 
```
    DataType variableName [= value];
```

[IMG1]

#### 📌 Example:
```Java
int age = 25; // Declaration + initialization
String name;  // Declaration only (instance variable defaults to null)

int a,b,c; //multiple variable declaration
int x=10,y=20,z=30; //multiple variable declaration + initialization
```

### ▸ The variable is the combination of "vary + able" which means it's value can be changed:

#### 📌 Example:
```Java
int a = 10;
a = a+10;
System.out.println(a); //Output: 20

int b = 10;
b = 20; //value changed from '10' to '20'
System.out.println(b); //Output: 20
```



## # **Java is a strongly typed language.**
This means it enforces strict type rules to ensure type safety and prevent type-related errors which means that the type of a variable is explicitly declared and is generally checked at compile time. It is a variabel assigned with a **data-type**.

### ▸ Key Characteristics of Strong Typing in Java: 

1. **Type Declaration is Mandatory**: We must declare the data type of a variable (like `int`, `String`, `boolean`, etc.) before we can use it.

2. **Compile-Time Type Checking**: The Java compiler checks type compatibility during compilation. Operations on incompatible types (e.g., adding a `String` to an `int`) result in compile-time errors.

    #### 📌 Example:
    ```Java
        int a = 5;
        a = "hello"; // Error: incompatible types (String cannot be converted to int)
    ```

3. **Reduced Runtime Errors**: Because many type-related issues are caught during compilation, strongly typed languages tend to have fewer type-related errors during runtime.

4. **No Implicit Conversions Between Unrelated Types**: Java disallows automatic conversions between types that are not logically related (e.g., `int` to `String`).

    #### 📌 Example of allowed conversion (widening):
    ```Java
       double b = 5; // Valid: int is implicitly widened to double.
    ```

    #### 📌 Example of disallowed conversion::
    ```Java
      String c = 5; // Error: int cannot be converted to String.
    ```

5. **Explicit Casting Required for Compatible Types**: Even for related types, explicit casting is required where data loss might occur (e.g., `double` to `int`).

    #### 📌 Example:
    ```Java
        double d = 5.7;
        int e = (int) d; // Explicit cast needed; e becomes 5.
    ```

6. **Type Safety in Methods**: Method arguments and return types are strictly enforced.

    #### 📌 Example:
    ```Java
        public int add(int a, int b) { return a + b; }
        add(3, "5"); // Error: String is not compatible with int.
    ```

7. **Generics for Type-Safe Collections**: Generics ensure collections like `List<String>` can only hold `String` elements, enforced at compile time.

    #### 📌 Example:
    ```Java
        List<String> names = new ArrayList<>();
        names.add(42); // Error: int cannot be converted to String.
    ```
8. **Runtime Type Enforcement**: Even when using polymorphism or casting, type mismatches result in runtime exceptions (e.g., `ClassCastException`).

    #### 📌 Example:
    ```Java
        Object obj = "Hello";
        Integer num = (Integer) obj; // Compiles but throws ClassCastException at runtime.
    ```

9. **Increased Code Reliability**: The strict type checking helps ensure that data is handled in a consistent and predictable way, leading to more reliable and maintainable code.

### ▸ Contrast with Weakly Typed Languages: 
↳ In weakly typed languages like JavaScript, variables can change types freely:
```JavaScript
let x = 5;    // x is a number
x = "hello";  // Valid: x is now a string (no error).
```
👉 Java prohibits such behavior, ensuring variables retain their declared type unless explicitly converted.

### ▸ Benefits of Strong Typing in Java: 
* Early Error Detection: Catches type mismatches at compile time, reducing runtime bugs.
* Code Clarity: Explicit types make code more readable and self-documenting.
* Performance: Compiler optimizations based on known types improve efficiency.
* Maintainability: Reduces unexpected behaviors due to accidental type changes.

