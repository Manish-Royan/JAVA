# 🔭 Spring Bean Scope

## **1. What Is Bean Scope?**
- **Scope** determines the **lifecycle and visibility** of a bean within the Spring Container.
- It answers: 
> *When is a bean created? How many instances exist? Who can access it?*

> *“When I ask the container for this bean, do I get the same instance or a new one—and how long does it live?”*

💭 Think of it like this: 
* When you define a bean, you're giving the Spring Container a blueprint. 
* The **scope** is the instruction that tells the container how to use that blueprint. ***Should it build just one object for everyone to share***, or ***should it build a fresh new object for everyone who asks***❓

### ⁉️Bean scope answers three fundamental questions:

- How many instances of this bean should exist?
- When is a new instance created?
- When (if ever) is the bean destroyed?

---

### \# Spring currently supports **six built-in scopes** (as of 2025/2026 releases — no major changes since ~Spring 4/5 era regarding the standard set).

| Scope          | # Instances                          | Created when?                          | Destroyed when?                        | Available in                  | Most common use case                              | Thread-safe concern? |
|----------------|--------------------------------------|----------------------------------------|----------------------------------------|-------------------------------|---------------------------------------------------|----------------------|
| **singleton**  | 1 per IoC container                  | First request (lazy) or startup (eager)| When container closes                  | All contexts                  | Stateless services, repositories, utilities       | Must be thread-safe  |
| **prototype**  | New instance **every time** requested| Every time `getBean()` or injection    | **Never** managed by container         | All contexts                  | Stateful beans, heavy objects you want fresh      | Usually safe         |
| **request**    | 1 per HTTP request                   | Start of each HTTP request             | End of HTTP request                    | Web-aware only                | Request-scoped data (e.g. current user context)   | Safe per request     |
| **session**    | 1 per HTTP session                   | First request in session               | HTTP session timeout / invalidation    | Web-aware only                | Shopping cart, user preferences during login     | Safe per session     |
| **application**| 1 per ServletContext                 | First access                           | ServletContext destroyed               | Web-aware only                | Global application-level caches, config           | Must be thread-safe  |
| **websocket**  | 1 per WebSocket session              | WebSocket connection established       | WebSocket connection closed            | WebSocket-aware only          | Per-client WebSocket state                        | Safe per connection  |

### 📝Note:
➡️ In pure Spring Core (no Spring Boot), the most important scopes are:
- `singleton` (default)
- `prototype`

➡️ **(Other scopes like `request`, `session`, etc. exist, but they require a web-aware context, so we’ll keep them aside.)**

---
# 1. `singleton` Scope (The Default — 90%+ of real-world beans)

✅ What it means in Spring
**Singleton** means:
> **one instance per Spring container** (per `ApplicationContext`), cached and reused.

❌ It does **NOT** mean:
- one instance per JVM
- a classic `static` singleton
- automatically thread-safe


➡️ This is the most common and the default scope in Spring.

*   **What it is:** Only **one single instance** of the bean is created for the entire Spring IoC container.
*   **💭Analogy:** Think of a central water cooler in an office. Everyone in the office goes to the *same* water cooler to get water.
*   **How it works:** When the Spring container starts, it creates the singleton bean. It stores this single instance in a cache. Every time any part of your application asks for this bean, the container returns the exact same instance from the cache.
*   **📌Example:**

    ```java
    @Component
    // or @Scope("singleton") — redundant but explicit
    public class UserService { ... }
    ```

    ```java
    // Java Config equivalent
    @Bean
    @Scope("singleton")   // also redundant
    public UserService userService() { ... }
    ```

    ```java
    // In your application
    UserService service1 = context.getBean(UserService.class);
    UserService service2 = context.getBean(UserService.class);

    // This will print "true" because both variables point to the same object
    System.out.println(service1 == service2);
    ```

*   **When to use it:** Almost always, for any bean that doesn't need to hold user-specific data (i.e., it's "stateless"). This is perfect for services, repositories (`@Repository`), and configuration (`@Configuration`) beans. Using singletons is highly efficient.

* **Important behaviors**
    - One instance **per ApplicationContext**
    - Created lazily (on first getBean / injection) unless you set `@Lazy(false)` or use eager initialization
    - Spring **does not** synchronize method calls — you must make singleton beans thread-safe yourself (immutable fields, synchronized blocks, ConcurrentHashMap, etc.)
    - Most stateless services (controllers, services, repositories) should be singleton

* **Common mistake** — putting mutable state in singleton beans without synchronization

```java
@Service
public class BadCounterService {
    private int counter = 0;           // ← race condition danger

    public void increment() {
        counter++;                     // not thread-safe!
    }
}
```

* Use of `AtomicInteger` for Race-Condition (Modern Way)
    - For simple counters, this is the gold standard. It uses low-level CPU instructions (Compare-And-Swap) to ensure thread safety without the heavy overhead of locking.
```java
@Service
public class SafeCounterService {
    private final AtomicInteger counter = new AtomicInteger(0);

    public void increment() {
        counter.incrementAndGet(); // Thread-safe and high-performance
    }

    public int getCount() {
        return counter.get();
    }
}
```

* Use of `synchronized` (Traditional Way)
```java
@Service
public class SynchronizedCounterService {
    private int counter = 0;

    public synchronized void increment() {
        // Only one thread can enter this block at a time
        counter++;
    }
}
```

### `AtomicInteger` v/s `synchronized`  
| Feature            | `AtomicInteger`       | `synchronized`            |
|--------------------|-----------------------|---------------------------|
| **Performance**    | High                  | Lower                     | 
| **Complexity**     | Low                   | Medium                    | 
| **Best Use Case**  | Simple counters/logic | Complex multi-step logic  | 
---

# 2. `prototype` Scope



✅ What it means
**Prototype** means:
> a **new instance** is created **every time the container is asked** for that bean.

🛄 Example cases where prototype makes sense:
- objects that hold temporary state
- builders
- per-operation strategies
- non-thread-safe objects you don’t want shared

➡️ This is the opposite of `singleton`.

*   **What it is:** A **new instance** of the bean is created **every single time** it is requested from the container.
*   **💭Analogy:** Think of a vending machine that dispenses a new can of soda every time you press the button. Each person gets their own can; they don't share one.
*   **How it works:** Every time your code calls `context.getBean("myPrototypeBean")`, Spring creates a new object, wires its dependencies, and hands it over to you.
*   **⚠️Important Lifecycle Note:** For prototype beans, the Spring container does **not** manage the full lifecycle. It creates and configures the object, but it's up to your application to handle its destruction. Spring will not call the `@PreDestroy` method on a prototype bean.
*   **📌Example:**

    ```java
    @Component
    @Scope("prototype") // or @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    public class ShoppingCart { ... }
    ```
    ```java
    // In your application
    ShoppingCart cart1 = context.getBean(ShoppingCart.class);
    ShoppingCart cart2 = context.getBean(ShoppingCart.class);

    // This will print "false" because they are two separate objects
    System.out.println(cart1 == cart2);
    ```

*   **When to use it:** For any bean that needs to hold state for a specific task or user. A `ShoppingCart` is the classic example because each user must have their own separate cart.

* **Key characteristics**
    - New instance created **every single time** the bean is requested (via `getBean()`, `@Autowired`, constructor-arg, etc.)
    - Container **does not** call destruction callbacks (`@PreDestroy`, `DisposableBean`)
    - Very useful when you want beans to hold conversational / per-use state

* **Real-world examples**

    ```java
    @Component
    @Scope("prototype")
    public class TicketGenerator {
        private final String ticketId = UUID.randomUUID().toString();
        // ...
    }
    ```
    - Report generators that accumulate temporary data
    - Builders / factories that should not be reused
    - Some machine-learning / simulation objects per calculation

### ⚠️ Most important prototype gotcha (lifecycle)
✅Spring will:
- create the prototype bean
- inject dependencies
- run initialization callbacks

❌ But Spring will **not** manage the full lifecycle afterward:
> It generally will **not** call destroy callbacks automatically for prototype beans

### 👉 So if your prototype bean holds resources (files, sockets, etc.), you must close them yourself.

> **Memory/resource gotcha:** Prototype scope is easy to misuse and cause leaks if you assume Spring will clean it up like singletons.

## 📌Example: Singleton vs Prototype

### Singleton Bean
```java
@Configuration
public class AppConfig {
    @Bean
    @Scope("singleton")  // default
    public HelloService helloService() {
        return new HelloService();
    }
}

public class Main {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

        HelloService s1 = context.getBean(HelloService.class);
        HelloService s2 = context.getBean(HelloService.class);

        System.out.println(s1 == s2); // true → same instance
    }
}
```

> 👉 **Singleton** beans are shared — every request gets the same object.

---

### Prototype Bean
```java
@Configuration
public class AppConfig {
    @Bean
    @Scope("prototype")
    public HelloService helloService() {
        return new HelloService();
    }
}

public class Main {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

        HelloService s1 = context.getBean(HelloService.class);
        HelloService s2 = context.getBean(HelloService.class);

        System.out.println(s1 == s2); // false → different instances
    }
}
```
> 👉 **Prototype** beans are created fresh every time you ask for them.