# **Understanding: "Spring beans are Singleton by default"**

## 🧩 **What is a Singleton in General (Design Pattern)?**
- A **Singleton** is a design pattern where **only one instance of a class exists** in the entire application (or within a given scope).
- Instead of creating multiple objects with `new`, you reuse the same single object and always get the **same single instance**.

> ### Only ONE object (instance) of a class exists. 

> ### Even, if you try to create it multiple times, you still get the same object.

## 🫤 Why Would We Ever Want Only One Object?

💭 Think about real-world services:

* Database connection manager
* Logging service
* Configuration loader
* Cache manager

😵‍💫 If every class creates its own database manager:

* Too many connections
* Memory waste
* Performance problems
* Inconsistent state

So we say:

> “No matter how many times someone asks, give the SAME object.”

### 💯 That’s the idea behind Singleton.

---

# 🧩 Let's revise **Java-Singleton (Classic Design Pattern)** before understanding **Bean-Singleton**

➡️ This is the traditional Singleton pattern in core Java.

➡️ In pure Java:

* The class itself controls object creation
* The constructor is private
* A static method returns the same instance

❗Important:

> ### Java Singleton means: One object per JVM.

```
If your application runs in one JVM → one instance.

If you start another JVM → another instance.
```

> ### So control is inside the class itself.

The class says:
> *“I will allow only one object of myself.”*

### **This is a design pattern.**

👉 Example:  
```java
Singleton s1 = Singleton.getInstance();
Singleton s2 = Singleton.getInstance();
System.out.println(s1 == s2); // true
```


## 📜 Java Singleton Demo Program

```java
// Step 1: Define a Singleton class
public class Singleton {
    // 1. Create a private static instance (only one object)
    private static Singleton instance;

    // 2. Private constructor (no one can call 'new' from outside)
    private Singleton() {
        System.out.println("Singleton instance created!");
    }

    // 3. Public static method to provide access to the single instance
    public static Singleton getInstance() {
        if (instance == null) {
            instance = new Singleton(); // create only once
        }
        return instance;
    }

    // Example method
    public void showMessage() {
        System.out.println("Hello from Singleton!");
    }
}
```

---

## 📜 Using the Singleton

```java
public class Main {
    public static void main(String[] args) {
        // Request Singleton instance multiple times
        Singleton s1 = Singleton.getInstance();
        Singleton s2 = Singleton.getInstance();

        // Both references point to the same object
        System.out.println(s1 == s2); // true

        // Call method
        s1.showMessage();
    }
}
```

### ✅ Output
```
Singleton instance created!
true
Hello from Singleton!
```

> 👉 Here, no matter how many times you call `getInstance()`, you always get the same object.


- **Who controls it?** → The **class itself** controls its single instance using:
  - Private constructor
  - Static field
  - Static `getInstance()` method
- **Scope** → Truly global across the JVM. Only one instance exists for the entire application.
- **Responsibility** → You (the developer) write the logic to enforce Singleton.
- **Limitations**:
  - Hard to extend or test (tight coupling).
  - You must handle thread-safety manually if multiple threads access `getInstance()`.



### 📜 The Code Again: Main Logic
```java
public static Singleton getInstance() {
    if (instance == null) {
        instance = new Singleton(); // create only once
    }
    return instance;
}
```

### 🪜 Step-by-Step Explanation

1. **`instance` is a static variable**  
   - It belongs to the class, not to any object.  
   - It will hold the single object of the `Singleton` class.

2. **First time you call `getInstance()`**  
   - `instance` is still `null` (no object created yet).  
   - The `if (instance == null)` condition is **true**.  
   - So, `new Singleton()` is executed → one object is created and stored in `instance`.

3. **Next time you call `getInstance()`**  
   - Now `instance` is **not null** (it already holds the object).  
   - The `if` condition is **false**, so no new object is created.  
   - The method just returns the existing object.

4. **Result**  
   - No matter how many times you call `getInstance()`, you always get the **same object reference**.  
   - That’s why it’s called a **Singleton**—only one instance exists.


### ❓Why This Matters
- The `if (instance == null)` check is the **gatekeeper**.  
- It ensures the object is created **only once**.  
- Without it, every call to `getInstance()` would create a new object, breaking the Singleton pattern.


### 👉 So the logic is:  
- **Check if the object exists (`instance == null`).**  
- ***If not, create it.***  
- ***If yes, just return the existing one.***  

> ### 👉 That’s how Singleton guarantees **one object only**.
---

## 🌱 Why This Matters for Spring
- In **Spring**, beans are **Singleton by default** (per container).  
- That means when you ask for a bean multiple times, Spring gives you the **same instance**, just like the Singleton pattern.  
- But instead of you writing the Singleton code, the **Spring Container manages it for you**.

### 👉 So now we’ve seen a **pure Java Singleton**. Next, we can connect this to **Spring’s default bean scope** and show how Spring automatically behaves like this pattern.  

---
# 🍃 Spring Singleton (Completely Different Philosophy)

Now comes the important part.

### In Spring:
```
❌ The class DOES NOT control singleton behavior.

✅ Spring Container controls it.
```
That is the big difference.

## ⚙️ What Does "Spring Beans are Singleton by Default" Mean?
- In **Spring**, when you define a bean (via XML, annotations, or Java config), the **Spring Container creates only one instance of that bean per container**.
- Whenever you request that bean (`context.getBean(...)`), Spring returns the **same object reference**.
- This is not the same as the strict Singleton design pattern (private constructor, static instance). Instead, it’s **Spring-managed Singleton scope**.

👉 Example:
```java
@Component
public class StudentService {
    // business logic
}
```

```java
ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
StudentService s1 = context.getBean(StudentService.class);
StudentService s2 = context.getBean(StudentService.class);

System.out.println(s1 == s2); // true → same object
```
### 🔭 Spring Singleton (Bean Scope)
- **Who controls it?** → The **Spring Container** controls bean instances.
- **Scope** → One instance **per Spring Container**.  
  - If you have multiple containers, each container has its own Singleton instance.
- **Responsibility** → You don’t write Singleton logic. You just declare a bean (`@Component`, `@Bean`), and Spring ensures only one instance exists in that container.
- **Flexibility**:
  - Easy to test (you can spin up a new container with fresh beans).
  - You can change scope (`@Scope("prototype")`, `@Scope("request")`, etc.) without rewriting code.
  - Thread-safety is handled differently—Spring beans are not inherently thread-safe, but you can configure them.



## 🌱 But What If You Have Multiple Containers?
- It’s possible (though less common) to create **multiple Spring Containers** in one application.  
- Each container manages its own beans independently.  
- So if you define the same bean in two different containers, each container will create **its own Singleton instance**.

👉 Example:
```java
ApplicationContext context1 = new ClassPathXmlApplicationContext("beans.xml");
ApplicationContext context2 = new ClassPathXmlApplicationContext("beans.xml");

StudentService s1 = context1.getBean(StudentService.class);
StudentService s2 = context2.getBean(StudentService.class);

System.out.println(s1 == s2); // false → different containers, different singletons
```
- In practice, most apps use **one container**, so you usually don’t notice this distinction.  
- But technically, if you spin up multiple containers, each container will have its own “singleton” copy of that bean.

> 👉 If you create multiple containers, each container will have its own Singleton instance of that bean.

---
## 🧩 Why Singleton by Default?
- **Efficiency**: Creating one bean and reusing it saves memory and CPU.
- **Consistency**: Shared state across the application is managed centrally.
- **Lifecycle control**: The container manages initialization and destruction once.

---
## 🌱 Important Clarification
- **Singleton in Spring ≠ Singleton Design Pattern in Java**  
  - Java Singleton: enforced by private constructor + static instance.  
  - Spring Singleton: enforced by the **container** (one bean per container).  

- **Java Singleton** → one instance per JVM (global).  
- **Spring Singleton** → one instance per bean definition **per container**.  

---
## ⚖️ Key Differences: Java vs Spring Singleton

| Aspect                | Java Singleton (Pattern) | Spring Singleton (Default Scope) |
|------------------------|---------------------------|----------------------------------|
| **Control**            | Class itself (static logic) | Spring Container manages it |
| **Instance Count**     | One per JVM               | One per Spring Container |
| **Creation**           | Manual (`getInstance()`) | Automatic (via DI & reflection) |
| **Flexibility**        | Rigid, hard to change     | Configurable (`@Scope`) |
| **Thread Safety**      | Must be coded manually    | Not guaranteed, depends on usage |
| **Testing**            | Harder (global state)     | Easier (new container = fresh beans) |

---
## 🔄 Other Bean Scopes in Spring
➡️ Spring lets you override the default Singleton scope:
- **Prototype** → new bean instance every time you request it.
- **Request** → one bean per HTTP request (web apps).
- **Session** → one bean per HTTP session.
---
### 👉 So when we say **“Spring beans are Singleton by default”**, it means:  
- Within a single Spring Container, there is **only one instance of each bean definition**. 
- Spring will create **one object per bean definition per container**, and every request for that bean returns the same object—similar to Singleton, but managed by the framework, not by your code. 
- If you create multiple containers, each container manages its own single instance separately.