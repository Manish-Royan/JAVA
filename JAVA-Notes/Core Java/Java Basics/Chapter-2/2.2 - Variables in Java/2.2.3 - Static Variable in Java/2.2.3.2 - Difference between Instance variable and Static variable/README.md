# How Static Variable is different from other variables

## ▸ Difference between Static variable and Instance variable in Java:

### 1. Instance Variable (local to the object)

- Belongs to an **object (instance)** of a class.
- Each object has its own **separate copy** of the instance variable.
- Declared **inside a class but outside any method, constructor, or block**.
- Memory is allocated when the object is created using the `new` keyword.

### 📌Example:

```Java
class Student {
    String name;  // instance variable
}

public class Main {
    public static void main(String[] args) {
        Student s1 = new Student();
        Student s2 = new Student();

        s1.name = "Alice";
        s2.name = "Bob";

        System.out.println(s1.name); // Alice
        System.out.println(s2.name); // Bob
    }
}
```

#### 👉 Here, `s1.name` and `s2.name` are independent of each other.

### 2. Static Variable (Class Variable)

- Belongs to the class , not to any specific object.
- All instances of the class share the **same copy** of the static variable.
- Declared using the `static` keyword.
- Memory is allocated once when the class is loaded into memory.

### 📌Example:

```JAVA
class Student {
    static String schoolName = "XYZ School";  // static variable
}

public class Main {
    public static void main(String[] args) {
        Student s1 = new Student();
        Student s2 = new Student();

        System.out.println(s1.schoolName); // XYZ School
        System.out.println(s2.schoolName); // XYZ School

        s1.schoolName = "ABC School";

        System.out.println(s1.schoolName); // ABC School
        System.out.println(s2.schoolName); // ABC School
    }
}
```

#### 👉 Here, changing schoolName via one object affects all objects since it's shared.

## ⁕ Key Differences

| Feature               | Instance Variable                 | Static Variable                               |
| --------------------- | --------------------------------- | --------------------------------------------- |
| Belongs to            | Object (instance)                 | Class                                         |
| Memory allocation     | Every time an object is created   | Once when class is loaded                     |
| Keyword used          | Object reference                  | Class name (or object, not preferred)         |
| Accessed by           | No static keyword                 | static keyword                                |
| Shared among objects? | No – each object has its own copy | Yes – all objects share the same copy objects |
| Number of copies      | One per object                    | One for all objects                           |
| Typical use           | Unique data per object            | Shared data across all objects                |
| Lifetime              | As long as the object exists      | As long as the class is loaded                |

## ▸ Difference between Static variable and Local variable in Java:

### ↳ The difference between a Static variable and a Local variable in Java lies in their scope , lifetime , accessibility , and memory allocation . Here's a clear comparison:

### 1. Static Variable (Class-Level Variable)

- Declared **inside a class but outside any method**, using the `static` keyword.
- Belongs to the class, not to any object.
- Shared among all instances of the class.
- **Memory is allocated once**, when the class is loaded into memory.

### 📌Example:

```JAVA
class Demo {
    static int count = 0; // static variable

    Demo() {
        count++;
    }

    void display() {
        System.out.println("Count = " + count);
    }
}

public class Main {
    public static void main(String[] args) {
        Demo d1 = new Demo();
        Demo d2 = new Demo();
        d2.display(); // Output: Count = 2
    }
}
```

#### 👉 Here, count is shared across all objects.

### 2. Local Variable

- Declared **inside a method**, **constructor**, or **block**.
- Exists **only within that block/method** where it's declared.
- Accessible** only inside that method/block**.
- Created when the method/block is entered and destroyed when exited.
- **Not accessible outside its scope**.

### 📌Example:

```JAVA
class Demo {
    void show() {
        int x = 100; // local variable
        System.out.println(x);
    }
}

public class Main {
    public static void main(String[] args) {
        Demo d = new Demo();
        d.show(); // Output: 100

        // System.out.println(x); // ❌ Compilation error - x not accessible here
    }
}
```

#### 👉 Here, `x` cannot be accessed outside the `show()` method.

## # This Java program demonstrates the lifetime and memory allocation of local and static variables.

```JAVA

public class lifeOfLocalVariable {

	static int a = 10; //it has lifetime in whole program, and it is stored in Heap memory
	void fun() {
		int b = 10; //local variable lifetime limits within this method, it is stored in Stack memory
		System.out.println(a + " " + b); // it will print 10 10 then 11 10
		++a; //11
		++b; //10
	}

	public static void main(String[] args) {
		lifeOfLocalVariable ref = new lifeOfLocalVariable();
		ref.fun(); // it will print 10 10
		ref.fun(); // it will print 11 10;
	}
}
```

### 💡 Explaination of above code:

1️⃣ Static Variable (`a`)
- Defined as `static int a = 10;`, meaning it belongs to the class and is **shared across all instances**.
- Stored in the **heap memory**, so it persists throughout the program's execution.
- Every time `fun()` is called, `a` retains its updated value because it's not tied to any specific method scope.

2️⃣ Local Variable (`b`)
- Defined inside the `fun()` method as `int b = 10;`, meaning it **only exists within this method's scope**.
- Stored in **stack memory**, and **recreated** every time `fun()` runs.
- Since it's local, after the method execution completes, `b` is discarded, and when `fun()` is called again, `b` starts fresh at ***10***.


## ⁕ Key Differences

| Feature           | Static Variable                 | Local Variable                   |
| ----------------- | ------------------------------- | -------------------------------- |
| Declaration Location             | "Inside class, outside methods with "static                    | "Inside a method, constructor, or block"         |
| Scope             | Entire class (shared by all instances)                   | Only inside method/block         |
| Memory Allocation | Once (when class is loaded)     | Each time method/block is called |
| Default Value     | Has default value               | Must be initialized explicitly   |
| Belongs To        | Class                           | Method/Block                     |
| Lifetime          | Till program ends/class unloads | Till method/block finishes       |
| Accessed By       | Class name or object            | Directly inside method           |
| Access Modifier   | Can have access modifiers (`private`", etc.)            | No access modifiers allowed           |
| Can be static?    | Yes                             | NO                               |
