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

## 🪤The famous trap: prototype bean injected into a singleton

➡️ This is one of the most frequently asked interview / debugging questions.

### 🤔 What beginners expect
> “If I inject a prototype into a singleton, I’ll get a new prototype each time I use it.”

### ⁉️What actually happens
Spring resolves dependencies **at injection time**. So if a singleton depends on a prototype, Spring typically creates **one prototype instance** and injects it into the singleton **once**.

Result:
- your singleton holds a single prototype instance
- you do **not** get a fresh prototype per method call

#### 🫨 This surprises many people.

### 📌Example of: The Classic "Singleton depends on Prototype" Problem

```java
@Service
public class ReportService {                // ← singleton (default)

    @Autowired
    private ReportGenerator generator;      // ← prototype

    public Report createDailyReport() {
        // Does NOT create new generator each time!
        return generator.generate();
    }
}
```

👉 Even though `ReportGenerator` is prototype, because `ReportService` is singleton, Spring injects **only one** instance of `generator` when `ReportService` is created — and keeps reusing it.

## ✅ Correct ways to use prototype from singleton (Core solutions)

### Option A: `ObjectProvider<T>` (recommended, modern Spring)
Instead of injecting the prototype directly, inject a provider and request a new instance when needed.

Conceptually:
- singleton has `ObjectProvider<PrototypeBean>`
- each time you call `getObject()`, Spring creates a new prototype

Why this is good:
- lazy creation
- no tight coupling to the container APIs like `ApplicationContext` (still Spring-specific, but clean)
- easy to test (you can fake provider)

**Gotcha:** You still own destruction/cleanup for prototype instances.

```java
@Service
public class ReportService {

    private final ObjectProvider<ReportGenerator> generatorProvider;

    public ReportService(ObjectProvider<ReportGenerator> generatorProvider) {
        this.generatorProvider = generatorProvider;
    }

    public Report createDailyReport() {
        ReportGenerator fresh = generatorProvider.getObject();   // ← new each time
        return fresh.generate();
    }
}
```
---

### Option B: Lookup method injection (`@Lookup`) (works, but more “magic”)
Spring can override a method to return a new prototype each time.

It’s valid, but for learning and maintainability, `ObjectProvider` is usually clearer.

```java
@Component
public abstract class ReportService {

    // Spring will override this method at runtime
    @Lookup
    protected abstract ReportGenerator getGenerator();

    public Report createDailyReport() {
        return getGenerator().generate();   // ← fresh instance every call
    }
}
```
---

### Option C: Ask the container directly (`ApplicationContext#getBean`)
It works, but it’s the most coupled option.
Your singleton now “knows about the container”, which hurts design/testability.

⚠️ Use only when necessary.

```java
@Autowired
private ApplicationContext ctx;

public Report createDailyReport() {
    return ctx.getBean(ReportGenerator.class).generate();
}
```

## `@Lazy` and scopes (interaction)
`@Lazy` controls **when** a bean is created, not **how many** instances exist.

- `@Lazy` + singleton: created when first used
- `@Lazy` + prototype: still new per request, but each instance is created lazily at request time anyway

> **Gotcha:** `@Lazy` can shift errors from startup-time to runtime, making failures appear later.

## How scopes relate to bean identity and caching (mental model)
Spring’s container maintains internal caches for singletons:
- first creation → store instance → return same instance every time

For prototype:
- no cache (in general)
- creation happens on each request

> **Deep insight:** This is why prototype doesn’t get full lifecycle management: there’s no central place where the container “owns” the instance long-term.

## 🧐 Choosing scope: practical rules
### Use `singleton` when:
- the bean is stateless
- the bean is thread-safe
- you want shared infrastructure (services, repositories, config)

### Use `prototype` when:
- you need a fresh instance per operation
- the bean is stateful and not safe to share
- you’re okay managing cleanup manually (if it holds resources)

> **Rule of thumb:** Prefer singleton unless you have a clear reason.


## 📃 Interview-grade gotchas (but also real-world)
1. **Singleton scope != thread safety**
2. **Prototype injected into singleton behaves like singleton** unless you use `provider`/`lookup`
3. **Prototype destroy methods not called automatically**
4. **Multiple ApplicationContexts → multiple singleton instances**
5. **Scopes are a container concept**; `new` ignores them completely


# 3. Web-Aware Scopes (request, session, application, websocket)

These scopes are only available in a web application environment (i.e., when you are using `spring-web`). So we'll only have a quick overview of this scope.

### 1️⃣ `request` Scope
*   **What it is:** A single bean instance is created for the lifecycle of a single HTTP request.
*   **💭Analogy:** A hotel key card that works only for the duration of your stay. A new guest gets a new key card for their stay.
*   **How it works:** When a web request hits your application, Spring creates a new instance of the request-scoped bean. This bean is available anywhere within that same request (e.g., from the controller to the service layer). Once the server sends the response back to the client, the bean is destroyed.
*   **When to use it:** For holding request-specific data, like user authentication details for a single request or theming information.

### 2️⃣ `session` Scope
*   **What it is:** A single bean instance is created for the lifecycle of a user's HTTP Session.
*   **💭Analogy:** A shopping cart at an online store. It's yours and follows you around the site as long as you're logged in or your session is active. A different user gets a completely different cart.
*   **How it works:** Spring creates a new instance for a new user session. That same instance is returned for all requests that belong to that same session. When the user logs out or the session times out, the bean is destroyed.
*   **When to use it:** For holding user-specific data that needs to persist across multiple requests, like a user profile or, again, a shopping cart.

### 3️⃣ `application` Scope
*   **What it is:** A single bean instance is created for the lifecycle of the entire `ServletContext` (i.e., for the entire time your web application is running).
*   **How it works:** This is very similar to `singleton`, but it's specifically tied to the web application's `ServletContext`. For most practical purposes in a single web app, it behaves just like a singleton.
*   **When to use it:** For application-wide data that needs to be shared across all users and all requests, like a cache of country codes or some global configuration.

---

### 💬 Quick Memory Aid

- **singleton** = one forever (default)
- **prototype** = new every time I ask
- **request** = reborn with every HTTP request
- **session** = lives as long as browser tab/session
- **application** = one per deployed web app
- **websocket** = one per open socket

---

## 👀 Quick Comparison Table — When to Use Which
| Scope | Description | Example Use Case | Analogy          |
|-------|-------------|------------------|------------------|
| **Singleton** (default) | One shared instance per Spring Container. | Services, utility classes. | Office Water Cooler    |
| **Prototype** | A new instance every time `getBean()` is called. | Stateful objects, per-request data. | Vending Machine Soda   |
| **Request** (Web only) | One bean per HTTP request. | Web controllers handling request data. |Hotel Key Card         |
| **Session** (Web only) | One bean per HTTP session. | User session data. | Online Shopping Cart   |
| **Application** (Web only) | One bean per ServletContext (shared across app). | Global caches, app-wide resources. | Public Notice Board    |
| **Websocket** (Web only) | One bean per WebSocket lifecycle. | Real-time chat or streaming sessions. | ❌  |

---

##  Why Scope Matters
- **Singleton** → efficient, memory-friendly, but not good for stateful data.  
- **Prototype** → flexible, but can be heavy if overused.  
- **Web scopes** → perfect for request/session-specific data in web apps.  

---

> ### Understanding bean scopes is crucial for building robust and memory-efficient Spring applications. It gives you precise control over the lifecycle of your objects.