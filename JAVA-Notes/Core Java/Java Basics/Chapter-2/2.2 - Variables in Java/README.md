# **Varaibles in Java** 

Variable is the name of memeory location where we stored different type of values.

![IMG](https://github.com/user-attachments/assets/b48b141f-4c60-4ade-a47f-321d129f1971)


### ▸ Varaible declaration & Initialization:
* Syntax: 
```
    DataType variableName [= value];
```

<img width="244" height="170" alt="IMG1" src="https://github.com/user-attachments/assets/f1984a7d-6cbc-4e1a-aef4-5ae0464945d9" />

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

## # **Key rules for variables in Java**   

### 1. **Naming Rules**
* Start with a **letter**, **underscore** (`_`), or **dollar sign** (`$`).
    - ✅ Valid: `age`, `_value`, `$amount`.
    - ❌ Invalid: `1stPlace`, `-count`.
    #### 📌 Example:
    ```Java
    int age = 25;        // Valid
    double $salary = 5000.0; // Valid but discouraged
    ```
* Subsequent characters can include **letters**, **digits**, `$`, or `_`.
    - ✅ Valid: `user1`, `total_sum`, `index2`
    - ❌ Invalid: `my-var`, `hello!`
    #### 📌 Example:
    ```Java
    String user1 = "Alice"; // Valid
    ```

* Cannot use Java keywords. Keywords like `int`, `class`, `if`, `return`, etc., are reserved.
    ❌ Invalid: `int class = 5;`
    ✅ Valid alternative: `int grade = 5;`

* **Case-sensitive**
    - ***Counter*** and ***counter*** are distinct variables.
    #### 📌 Example:
    ```Java
    int Counter = 0;
    int counter = 10; // Different from Counter
    ```

* **No spaces allowed**: Use **camelCase (preferred)** or **underscores**.
    - ✅ Valid: `studentName`, `student_name`
    - ❌ Invalid: `student name`
        #### 📌 Example:
    ```Java
    String studentName = "John"; // CamelCase
    ```

### 2. **Declaration Rules**
* **Specify a data type**
    - Every variable must have a declared type (e.g., `int`, `double`, `String`).
    ```Java
    int age;  // Declaration
    age = 25; // Assignment
    ```
* **Declare before use**
    - Attempting to use an undeclared variable causes a compile-time error.
    ```Java
    System.out.println(x); // ❌ Error: x not declared
    int x = 10;
    ```
* **Multiple declarations (same type)**
    - We can declare multiple variables in one line:
    ```Java
    int x = 1, y = 2, z = 3;
    ```
### 3. **Initialization Rules**
* **Local variables must be initialized before use**.
    * Local variables (inside methods) have no default value.
        #### 📌 Example:
        ```Java
        int count;
        System.out.println(count); // ❌ Compile error: variable not initialized
        ```
* **Instance/Static Variables**:
    * Automatically assigned default values if not initialized:
        * `0` (numeric), `false` (boolean), `null` (objects).
        #### 📌 Example:
        ```Java
        class Test {
        int a;       // Default: 0
        String name; // Default: null
        }  
        ```

### 4. **Final Variables (Constants)**
* Must be initialized:
    * At declaration or in the constructor (for instance variables).
* Cannot be reassigned:
    ```Java
    final int MAX_VALUE = 100;
    MAX_VALUE = 200; // Compile error
    ```

### 5. **var Keyword (Java 10+)**
* **Local Variables Only**:
    * Cannot be used for static/instance variables or method parameters.

* **Requires Immediate Initialization**:
    ```Java
    var name = "Java"; // Inferred as String
    var x;             // Error: Cannot infer type
    ```

### 6. **Other Key Rules**
* **No Redeclaration in Same Scope**:
    ```Java
    {
        int x = 10;
        int x = 20; // Error: Duplicate variable
    }
    ```
* **Method Parameters**: Treated as local variables (initialized by the caller).
* **Interface Variables**: Implicitly public static final.
    ```Java
    interface MyInterface {
        int MAX = 100; // Automatically public static final
    }
    ```
