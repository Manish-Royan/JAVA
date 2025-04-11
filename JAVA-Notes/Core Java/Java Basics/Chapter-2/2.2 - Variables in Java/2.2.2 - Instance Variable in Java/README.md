# 🧩 Instance Variable in Java

## 📚 Overview
* An instance variable is a variable that belongs to an instance of a class that's why the are '**class-level**' variable. It is declared inside a class but outside any method, constructor, or block. 

* Instance variables are fundamental to object-oriented programming in Java. 

* **They define the state of an object and are tied to individual instances of a class**. 

* Each object of the class gets its own copy of the instance variables.

## 🧾 Declaration

📝 It is declared within a class but oiutside any method, constructor or block.

```Java
public class Car {
    // Instance variable
    private String model; 
    protected int year;   
    public double price;  
}
```


## 🔭 Scope

* It's scope is inside all method/blocs & constructors within class.

* Instance variable are asccessible throughout the class in which they are declared.

    ```Java
    class Person {
        // Instance variables
        String name;
        int age;

         // Constructor accessing instance variables
        public Person(String name, int age) {
            this.name = name; // Accessible inside constructor
            this.age = age;
        }

        // Method accessing instance variables
         void displayInfo() {
            System.out.println("Name: " + name); // Accessible inside method
            System.out.println("Age: " + age);
        }

        // Another method accessing the same instance variables
        void changeName(String newName) {
            name = newName; // Accessible and modified within the class
        }

        public static void main(String[] args) {
            // Creating an object
            Person p1 = new Person("Alice", 25);
            p1.displayInfo(); // Instance variables are accessible inside displayInfo()

            // Modifying instance variable inside another method
            p1.changeName("Bob");
            p1.displayInfo(); // Updated instance variable value is accessible
        }
    }       
    ```
    ### 💡 Explaination of above code:

    - Instance variables `name` and `age` are declared inside the class `Person`.
    - These instance variables are accessible inside:
        - Constructor (`Person`): Directly assigned via `this.name = name;`
        - Method (`displayInfo`): Directly accessed using `name` and `age`.
        - Method (`changeName`): Modified using `name = newName;`.
        - The main method creates an object and interacts with the instance variables using object methods.

    ### 🔍 Key Takeaways: 
    - Instance variables belong to an instance (object) of the class.
    - They are accessible throughout the class in constructors and methods.
    - Any method inside the class can read or modify them unless restricted by access modifiers (`private`, `protected`).

* 🔐 Accessible throughtout the enitre class (unless restricted by access modifier).

    ```Java
    class Person {
        // Private instance variable
        private String name;

        // Constructor to initialize name
        public Person(String name) {
            this.name = name; // Accessible inside constructor
        }

        // Getter method to retrieve the private variable
        public String getName() {
            return name; // Accessible inside the same class
        }

        // Setter method to update the private variable
        public void setName(String newName) {
            this.name = newName; // Accessible inside the same class
        }
    }

    public class Main {
        public static void main(String[] args) {
            // Creating an object of Person class
            Person p1 = new Person("Alice");

            // Attempting direct access (will cause an error)
            // System.out.println(p1.name); // ❌ Not allowed (private access)

            // Correct way: Accessing via public methods (getter/setter)
            System.out.println("Name: " + p1.getName()); // ✅ Allowed
            p1.setName("Bob"); // Updating private variable via setter
            System.out.println("Updated Name: " + p1.getName()); // ✅ Allowed
        }
    }
    ```

    ### 💡 Explaination of above code:

    - name is marked as private, meaning it cannot be accessed directly outside the class.
    - Encapsulation is used via getter (`getName()`) and setter (`setName()`) methods:
        - Getter allows read access to `name`.
        - Setter allows modification of `name`.
        - Method (`changeName`): Modified using `name = newName;`.
    - Direct access to `p1.name`from the main method would cause an error, as instance variables marked private can only be accessed inside the class.

    ### 🔍 Key Takeaways: 
    - Private instance variables ensure data hiding and encapsulation.
    - They can only be accessed using methods within the class (e.g., getters and setters).
    - Direct access outside the class leads to compilation errors.

* ⏳ Exists as long as the object exists.
    - Instance variables exist as long as the object exists because they are tied to the lifecycle of the object itself. When an object is created, memory is allocated for its instance variables. When the object is destroyed, the instance variables are removed from memory.

   ### 🔑 Key Points on Instance Variable Lifetime:
    - **Created** when the object is instantiated using new.
    - **Exists** as long as the object remains in memory.
    - **Destroyed** when the object is garbage collected.

    ```Java
    /*Demonstration Code: Instance Variables and Object Lifecycle*/
    class Example {
        String message; // Instance variable
    
        // Constructor
        public Example(String msg) {
            this.message = msg;
            System.out.println("Object created: " + message);
        }
    
        // Method to display the message
        void displayMessage() {
            System.out.println("Message: " + message);
        }
    
        public static void main(String[] args) {
            // Creating an object
            Example obj1 = new Example("Hello, Java!");
    
            // Accessing instance variable
            obj1.displayMessage();
    
            // Object will be eligible for garbage collection after this
            obj1 = null; // Removing reference to the object
            System.gc(); // Requesting garbage collection (not guaranteed)
    
            System.out.println("Object reference removed. Instance variable no longer exists.");
        }
    }
    ```

    ### 💡 Explaination of above code:
    - An instance variable `message` exists for the object `obj1`.
    - `message` is allocated memory when `new Example("Hello, Java!");` runs.
    - Once `obj1 = null;` is executed, the object loses its reference, making it eligible for garbage collection.
    - Calling `System.gc();` requests garbage collection, which will eventually remove obj1 from memory.

    ### 🔍 Key Takeaways: 
    - Instance variables remain in memory as long as the object exists.
    - When an object is garbage collected, instance variables are also removed.
    - Explicitly setting an object to null makes it eligible for cleanup.


## 💾 Memory Allocation 

* When object is created instance variable allocated, when object get destroyed, variable release from meomory because Instance variables in Java exist as long as the object exists because they are tied to the object's lifecycle. Let's break this down:

    ### ⏳ Lifecycle of Instance Variables:
    1. **When an Object is Created (new)**
        - Memory is allocated in the **heap** for the object.
        - Instance variables get their space within the object's memory.
        - If not explicitly initialized, they take default values.
    2. **When the Object is Used**
        - Instance variables can be accessed via the object's methods.
        - Each object has its own unique copy of instance variables.
    3. **When the Object is Destroyed (Garbage Collection)**
        - The object becomes **eligible for garbage collection** when it has no more references.
        - Once garbage collected, the memory allocated for its instance variables is released.

    ```Java
    /*Demonstration Code: Instance Variable Lifecycle*/
    class Example {
        String data; // Instance variable

        // Constructor
        public Example(String data) {
            this.data = data;
            System.out.println("Object Created with data: " + data);
        }

        // Display instance variable
        void showData() {
            System.out.println("Data: " + data);
        }

        public static void main(String[] args) {
            // Creating an object
            Example obj1 = new Example("Hello, Java!");

            // Accessing instance variable
            obj1.showData();

            // Object loses reference, making it eligible for garbage collection
            obj1 = null; // Removing reference
            System.gc(); // Requesting garbage collection

            System.out.println("Object reference removed. Instance variables no longer exist.");
        }
    }
    ```
    ### 💡 Explaination of above code:
    - Memory is allocated for obj1 when new Example("Hello, Java!") is executed.
    - Instance variable (data) is initialized and accessed via showData().
    - Object loses reference (obj1 = null), so the instance variable is marked for removal.
    - Garbage collection (System.gc()) clears memory, removing the instance variable.

* 🗃️ Instance variables are stored in the heap memory as part of the object.

* 📦 **Each object gets its own unique copy of instance variables**, which means changes to one object’s instance variables do not affect another object’s instance variables. **This is a fundamental concept in object-oriented programming (OOP)**.

    ### Q. How It Works❓
    👉 When you create multiple objects of a class, each object has its own separate memory for instance variables. Even if two objects belong to the same class, their instance variables do not share memory.

    ```Java
    /*Demonstration Code: Each Object Has Its Own Instance Variables*/
    class Person {
        String name; // Instance variable

        public Person(String name) {
            this.name = name; // Each object stores its own name
        }

        void display() {
            System.out.println("Name: " + name);
        }

        public static void main(String[] args) {
            // Creating two different objects
            Person p1 = new Person("Alice");
            Person p2 = new Person("Bob");

            // Accessing instance variables
            p1.display(); // Outputs: Name: Alice
            p2.display(); // Outputs: Name: Bob

            // Modifying one object's instance variable
            p1.name = "Charlie";
            p1.display(); // Outputs: Name: Charlie
            p2.display(); // Outputs: Name: Bob (p2 remains unchanged)
        }
    }
    ```

    ### 💡 Explanation of above code:
    - Each object (`p1`, `p2`) has its own memory for name.
    - Changing `p1.name` to "`Charlie`" does not affect `p2.name`.
    - Instance variables are stored separately in heap memory for each object.
    - **This behavior is different from static variables**, which are shared across all objects of a class.


## 🟢 Initialization & ⚙️ Default Values

* 🔄 If not explicitly initialized, instance variables take default values:
    - Numeric types (e.g., `int`, `double`, `float`) → 0, 0.0
    - Boolean → `false`
    - Object references → `null`

    ```Java
    class DefaultValues {
        // Instance variables (not explicitly initialized)
        byte b;
        short s;
        int i;
        long l;
        float f;
        double d;
        char c;
        boolean bool;
        String str; // Object reference

        void display() {
            System.out.println("Default byte: " + b);
            System.out.println("Default short: " + s);
            System.out.println("Default int: " + i);
            System.out.println("Default long: " + l);
            System.out.println("Default float: " + f);
            System.out.println("Default double: " + d);
            System.out.println("Default char: [" + c + "]");
            System.out.println("Default boolean: " + bool);
            System.out.println("Default String: " + str); // null for objects
        }

        public static void main(String[] args) {
            // Creating an object
            DefaultValues obj = new DefaultValues();
            obj.display(); // Display default values
        }
    }
    ```

* 🔧 Explicit Initialization: Can be initialized at declaration, in a constructor, or via instance blocks.

    ```Java
    public class BankAccount {
        private double balance = 0.0; // Initialized at declaration
        public BankAccount(double balance) {
            this.balance = balance; // Initialized via constructor
        }
        { balance = 100.0; } // Instance initializer block (rarely used)
    }
    ```
    ### 💡 Important Notes
    - **Local variables (inside methods) do not get default values**—they must be explicitly initialized.
    - Default values exist because **instance variables are stored in the heap memory**, unlike local variables (which are in the stack memory).



## 🔐 Access Modifiers

* Can have different access modifiers (`private`, `protected`, `public`, `default`).
* ✅ Best practice: Encapsulate with `private` and use getters/setters.

    ```Java
    class Employee {
        private String name; // Encapsulated
        public String getName() { return name; }
        public void setName(String name) { this.name = name; }
    }

    class Main {
        public void static main(String[] main)
        {
            Employee emp1 = new Employee();
            emp1.setName("Josh");
            System.out.println(emp1.getName());

            Employee emp2 = new Employee();
            emp2.setName("Jane");
            System.out.println(emp2.getName());
        }
    }
    ```


## 🌟 Key Features 

* 🔒 `final` Instance Variables: Must be initialized by the end of constructor execution.

    ```Java
    public class Circle {
        // Final instance variables
        final double PI = 3.14159; // Initialized at declaration
        final double radius; // Must be initialized in constructor

        // Constructor ensures `radius` is initialized
        public Circle(double radius) {
            this.radius = radius; // Mandatory initialization
        }

        // Method to calculate area
        public double calculateArea() {
            return PI * radius * radius;
        }

        // Method to display circle details
        public void display() {
            System.out.println("Radius: " + radius);
            System.out.println("Area: " + calculateArea());
        }

        public static void main(String[] args) {
            // Creating object with radius 5.0
            Circle c1 = new Circle(5.0);
            c1.display();

            // Creating object with radius 7.5
            Circle c2 = new Circle(7.5);
            c2.display();

            // Attempting to modify final variable (will cause error)
            // c1.radius = 10.0; // ❌ Compilation error: cannot assign a value to final variable
        }
    }
    ```

    ### 🔑 Key Takeways:
    - `final` instance variables must be initialized by the end of constructor execution.
    - `PI` is initialized at the time of declaration, so it cannot change.
    - `radius` is initialized inside the constructor, ensuring each object has its unique 
    - Attempting to change a `final` variable after initialization **results in a compilation error**.


* 🧵 **Thread Safety**: **Not thread-safe by default**. Use `synchronization` or `volatile` for shared access.

    ```Java
    /*Thread-Safe Counter Using `synchronized` & `volatile`*/
    public class Counter {
        private volatile int count; // Ensures visibility across threads

        // Synchronized method for thread-safe increment
        public synchronized void increment() {
            count++; // Ensures atomic operation
        }

        // Synchronized method for retrieving count safely
        public synchronized int getCount() {
            return count;
        }

        public static void main(String[] args) {
            Counter counter = new Counter();

            // Creating multiple threads to increment the counter
            Thread t1 = new Thread(() -> {
                for (int i = 0; i < 1000; i++) {
                    counter.increment();
                }
            });

            Thread t2 = new Thread(() -> {
                for (int i = 0; i < 1000; i++) {
                    counter.increment();
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

            // Getting the final count value
            System.out.println("Final Count: " + counter.getCount());
        }
    }
    ```
    ### 🔑 Key Takeaways:
    - `volatile` ensures visibility of changes across threads.
    - `synchronized` guarantees atomicity for `increment()` and `getCount()`.
    - Multiple threads (`t1`, `t2`) modify the counter safely without race conditions.
    - Using `join()` ensures threads complete execution before reading the final count

## 👨‍👦Inheritance & Shadowing 

* **Inheritance**: Subclasses inherit instance variables (unless `private`).

    1. Inheritance of instance variables (`protected`, `public`, and `private`).
    2. How overridden methods interact with inherited instance variables.
    3. Using `super` to reference a parent class instance variable.

    ```Java
    /*Extended Example: Inheritance with Instance Variables*/
    class Vehicle {
        protected String type = "Generic"; // Inherited by subclasses
        private int wheels = 4; // Not inherited (private)

        public void displayInfo() {
            System.out.println("Vehicle Type: " + type);
            System.out.println("Number of Wheels: " + wheels); // Accessible within class
        }
    }

    class Car extends Vehicle {
        String brand;

        // Constructor
        public Car(String brand) {
            this.brand = brand;
        }

        void display() {
            System.out.println("Car Type: " + type); // Inherited from Vehicle
            System.out.println("Brand: " + brand);

            // Attempting to access private variable 'wheels' from parent class (will cause an  error)
            // System.out.println("Wheels: " + wheels); // ❌ Compilation error: wheels is private
        }
    }

    class Truck extends Vehicle {
        int capacity;

        public Truck(int capacity) {
            this.capacity = capacity;
        }

        // Overriding method from Vehicle
        @Override
        public void displayInfo() {
            super.displayInfo(); // Calls the parent class method
            System.out.println("Truck Capacity: " + capacity + " tons");
        }
    }

    public class Main {
        public static void main(String[] args) {
            Car car = new Car("Toyota");
            Truck truck = new Truck(10);

            car.display();
            truck.displayInfo();
        }
    }
    ```
* 👥 **Shadowing**: Subclasses can redeclare variables with the same name (not recommended).

    ```Java
    class Parent {
        int x = 10; // Instance variable in Parent class
    }

    class Child extends Parent {
        int x = 20; // ❗ Shadows Parent.x 

        void display() {
            System.out.println("Child's x: " + x); // Accesses Child's x
            System.out.println("Parent's x using super: " + super.x); // Accesses Parent's x
        }
    }

    public class Main {
        public static void main(String[] args) {
            Child child = new Child();
            child.display();
        }
    }
    ```


## 🏆 Best Practices

1. 🔒 **Encapsulation**: Use `private` access and expose via methods.
2. ⛔ **Avoid Public Variables**: Prevents unintended external modifications.
3. 🚫 **Avoid excessive usage**: Only create instance variables when they truly belong to an object.
4. 📝 **Meaningful Names**: E.g., `employeeId` instead of `id`.
5. 🏗️ **Initialize them properly**: Prefer initializing through constructors rather than direct assignments.
4. 🦥 **Lazy Initialization**: Lazy Initialization is a design pattern used to delay the initialization of an instance variable until it is first accessed. This approach is particularly useful when dealing with heavy resources, such as large objects, database connections, or file operations, to optimize performance and memory usage.

    ### Q. Why Use Lazy Initialization❓
    - ⚡Improves performance: Avoids unnecessary memory allocation if the resource isn't used.
    - 📊Optimizes resource utilization: Prevents premature object creation, reducing startup costs.
    - 🛠️Helps with expensive operations: Especially useful for loading large datasets or establishing connections.

    ```Java
    class DatabaseConnection {
        private static DatabaseConnection instance; // Instance variable (not initialized yet)

        // Private constructor to restrict direct instantiation
        private DatabaseConnection() {
            System.out.println("Database Connection Created!");
        }

        // Lazy Initialization method
        public static DatabaseConnection getInstance() {
            if (instance == null) { // Initializes only on first access
                instance = new DatabaseConnection();
            }
            return instance;
        }

        public void connect() {
            System.out.println("Connected to the Database.");
        }
    }

    public class Main {
        public static void main(String[] args) {
            System.out.println("Application Started.");

            // Database object isn't created yet
            DatabaseConnection db1 = DatabaseConnection.getInstance(); // First access initializes  the object
            db1.connect(); // Using the initialized object

            // Second call doesn't create a new object, reuses existing instance
            DatabaseConnection db2 = DatabaseConnection.getInstance();
            db2.connect();
        }
    }
    ```

    ### 💡 Explanation of above code:
    - **Object is NOT created** until `getInstance()` is called for the first time.
    - **Subsequent calls** reuse the same initialized instance, avoiding unnecessary creation.
    - Prevents **resource wastage** in cases where the object may never be used.

    ### 💡 Best Practices for Lazy Initialization
    - Use `synchronized` for thread safety if multiple threads might access the variable.
    - Avoid unnecessary lazy initialization for lightweight objects (it adds complexity).
    - Use `volatile` if using lazy initialization for shared objects in a multithreaded environment.

## 📚  Examples & Use Cases:

```Java
public class Book {
    private String title; // Instance variable
    private String author;
    private int pageCount;
    // Constructor to initialize instance variables
    public Book(String title, String author, int pageCount) {
        this.title = title;
        this.author = author;
        this.pageCount = pageCount;
    }
    // Getter methods (Encapsulation)
    public String getTitle() {
        return title;
    }
    public String getAuthor() {
        return author;
    }
    public int getPageCount() {
        return pageCount;
    }
    // Setter methods (Allows modification if needed)
    public void setTitle(String title) {
        this.title = title;
    }
    public void setAuthor(String author) {
        this.author = author;
    }
    public void setPageCount(int pageCount) {
        if (pageCount > 0) { // Ensuring valid page count
            this.pageCount = pageCount;
        }
    }
    // Method to display book details
    public void displayBookInfo() {
        System.out.println("Title: " + title);
        System.out.println("Author: " + author);
        System.out.println("Page Count: " + pageCount);
    }
    // Main method for demonstration
    public static void main(String[] args) {
        // Creating Book object
        Book myBook = new Book("Effective Java", "Joshua Bloch", 412);
        // Displaying initial book details
        myBook.displayBookInfo();
        // Modifying instance variables using setters
        myBook.setTitle("Clean Code");
        myBook.setAuthor("Robert C. Martin");
        myBook.setPageCount(464);
        // Displaying updated book details
        System.out.println("\nUpdated Book Info:");
        myBook.displayBookInfo();
    }
}
```
### 🎯 **Use Cases**:

- Storing object-specific data (e.g., a user’s email in a *User* class).
- Defining properties that vary between objects (e.g., bank account balances).


## ❌ Common Mistakes
⚠️ **Unintended Sharing**: Using static incorrectly (turns it into a class variable).
```Java
public class Counter {
    public static int count; // 😱 Shared across all instances!
}
```

⚠️ **NullPointerException**: Accessing uninitialized object references.

```Java
public class Test {
    private String name; // Default: null
    public void printName() {
        System.out.println(name.length()); // NullPointerException -> 💥 If name is null
    }
}
```

## Q. How Instance Variables Differ from Other Variables❓
|  **Variable Type** |  **Scope** | **Memory Location** | **Default Value** | **Declaration Location**
|:-----|:--------|:-----|:--------|:-----
| Instance Variable | Class level, tied to object | Heap memory | Yes | Inside class, outside methods
| Static Variable | Class level, shared by all objects | Method area | Yes | Inside class, marked with static
| Local Variable | Method/block level | Stack memory | No (must be initialized) | Inside method/block
