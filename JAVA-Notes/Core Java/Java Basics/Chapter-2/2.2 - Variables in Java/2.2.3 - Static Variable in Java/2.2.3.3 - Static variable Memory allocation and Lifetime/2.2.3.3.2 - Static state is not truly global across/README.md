# # Static State is not truly global across the entire JVM

Let’s unpack the subtle truth behind the idea that **static state is not truly global across the entire JVM**, even though it might *look* like it is.

## Q. What Does “Global” Really Mean❓

Static variables are often described as “***global***” because:

- They’re shared across all instances of a class.
- They persist for the lifetime of the class in the JVM.
- They can be accessed from anywhere the class is visible.

#### 👉 But here’s the catch: **they’re only global within the scope of a single class loader**.

## 🔃 JVM Class Loaders: The Hidden Boundary

Java uses **class loaders** to isolate classes. Each class loader maintains its own namespace. That means:

- If the same class is loaded by two different class loaders, **each gets its own copy of the static variables**.
- Static fields are tied to the *class object*, and each class loader creates a separate class object.

### 🧩 Real-World Example: Web Servers

In application servers like **Tomcat**, each web app runs in its own class loader. If two apps use the same class (say, `Config`), they each get their own version of `Config.staticField`.

So even though the class name and code are the same, their static states are **isolated**.

---

## 🧪 Code Illustration (Conceptual)

```java
ClassLoader loader1 = new CustomClassLoader();
ClassLoader loader2 = new CustomClassLoader();

Class<?> classA1 = loader1.loadClass("com.example.Config");
Class<?> classA2 = loader2.loadClass("com.example.Config");

// These are two distinct class objects
System.out.println(classA1 == classA2); // false

// Each has its own static field storage
Field staticField1 = classA1.getDeclaredField("staticValue");
Field staticField2 = classA2.getDeclaredField("staticValue");
```

---

## 🔐 Why This Matters

- **Security**: Class loader isolation prevents one app from tampering with another’s static state.
- **Modularity**: You can reload or unload modules independently.
- **Memory Management**: Unused classes and their static fields can be garbage collected when their class loader is discarded.

---

## ✅ Summary: Static ≠ JVM-Wide Global

| Scope                     | Static Variable Behavior                          |
|--------------------------|----------------------------------------------------|
| Within one class loader  | Shared across all instances of the class           |
| Across class loaders     | Isolated copies; not shared                        |
| JVM-wide                 | Not truly global; depends on class loader context  |


---
# # Static State Isolated by Class Loaders

Even though static variables feel “global,” they’re scoped to **each class loader’s namespace**. Here’s a hands-on example that shows two separate class loaders loading the same class—and each keeps its own static state.

## 1. Create the “shared” class

Save this as `SharedResource.java` in a folder, say `/tmp/classes/com/example/`:

```java
package com.example;

public class SharedResource {
    public static int count = 0;

    public void increment() {
        count++;
    }

    public static int getCount() {
        return count;
    }
}
```

Compile it:
```bash
javac -d /tmp/classes SharedResource.java
```

---

## 2. The Class-Loader Demo

Save this as `ClassLoaderDemo.java` anywhere (it will load the above class from `/tmp/classes`):

```java
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;

public class ClassLoaderDemo {
    public static void main(String[] args) throws Exception {
        // 1. Point both loaders at the same folder
        URL classesDir = new URL("file:///tmp/classes/");
        URL[] urls = { classesDir };

        // 2. Create two independent loaders (no parent delegation)
        URLClassLoader loader1 = new URLClassLoader(urls, null);
        URLClassLoader loader2 = new URLClassLoader(urls, null);

        // 3. Load the identical class twice
        Class<?> classA = loader1.loadClass("com.example.SharedResource");
        Class<?> classB = loader2.loadClass("com.example.SharedResource");

        // 4. Instantiate and increment via each class
        Object objA = classA.getDeclaredConstructor().newInstance();
        Object objB = classB.getDeclaredConstructor().newInstance();

        Method incA = classA.getMethod("increment");
        Method incB = classB.getMethod("increment");

        incA.invoke(objA);
        incA.invoke(objA);   // loader1’s count → 2

        incB.invoke(objB);   // loader2’s count → 1

        // 5. Fetch static counts from each “namespace”
        Method getCountA = classA.getMethod("getCount");
        Method getCountB = classB.getMethod("getCount");

        System.out.println("Loader1 count: " + getCountA.invoke(null)); // prints 2
        System.out.println("Loader2 count: " + getCountB.invoke(null)); // prints 1
    }
}
```

Compile and run:
```bash
javac ClassLoaderDemo.java
java ClassLoaderDemo
```

---

## 3. What Just Happened❓

- We used two distinct `URLClassLoader` instances, **each with no parent**, so they each loaded `SharedResource` independently.
- Although it’s the *same* `.class` file, the JVM treats them as **different class definitions**.
- Each copy got its *own* static `count` slot:
  - **Loader1’s** `count` went to 2  
  - **Loader2’s** `count` stayed at 1  

---

## 4. Why This Matters❓

- Static variables aren’t JVM-wide singletons—they’re **class-loader singletons**.
- Frameworks or app servers use separate class loaders to isolate modules/plugins. Each module gets its own static state.
- This prevents one component from stomping on another’s globals and allows safe unloading of classes along with their statics.

---