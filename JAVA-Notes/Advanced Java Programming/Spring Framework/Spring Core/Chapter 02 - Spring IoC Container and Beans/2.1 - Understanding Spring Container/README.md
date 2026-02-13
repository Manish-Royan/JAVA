# Understanding Spring Container
➡️ We previously have an [`Overview of Spring Container`](https://github.com/Manish-Royan/JAVA/tree/main/JAVA-Notes/Advanced%20Java%20Programming/Spring%20Framework/Spring%20Core/Chapter%2001%20-%20Introduction%20to%20Spring/1.3%20-%20Getting%20Started) followed by multiple Spring program using: ***XML-based***, ***Java-based*** & ***Annotation-based*** configurations.   
➡️ Now in this section, we are going to properly understand Spring Container.


## ❤️ **Why Spring Container is the "Heart of Spring"**
- The **Spring Container** is the **central engine** of the framework.  
- It’s responsible for:  
    * Creating objects
    * Connecting objects together
    * Managing object lifecycle
    * Managing configuration
    * Managing dependencies
- Without the container, Spring is just a set of libraries. With it, Spring becomes a **living framework** that wires everything together.  
- It reads **configuration metadata** (***XML***, ***annotations***, or ***Java config***) to know what beans to create and how to wire them.  
- In practice, the container is represented by the **`ApplicationContext` interface**.
- That’s why it’s called the **heart of Spring**—everything else (Spring MVC, Spring Data, Spring Security) sits on top of it.

---

## 📋 **Responsibilities of the Spring Container**
The container does much more than just `new` objects:

1. **Object Creation**  
   - Instantiates beans defined in configuration (XML, annotations, or Java config).

2. **Dependency Injection (DI)**  
   - Wires beans together by injecting dependencies (constructor, setter, or field injection).

3. **Lifecycle Management**  
   - Controls bean lifecycle: creation → initialization → destruction.  
   - Calls lifecycle callbacks (`@PostConstruct`, `@PreDestroy`, `InitializingBean`, etc.).

4. **Configuration Management**  
   - Reads metadata (annotations, XML, Java config) to know how beans should be created and wired.

5. **Cross-Cutting Concerns**  
   - Applies AOP proxies for transactions, logging, security, etc.

6. **Environment & Profiles**  
   - Supports different configurations for dev, test, and production environments.

> 👉 In short: The container is the **orchestrator** that ensures all beans work together smoothly.

---

## 🧩 Two Types of Spring Container

### 1. **BeanFactory (Old, Low-Level)**
**What it is:** the simplest Spring IoC container interface.

**What it does well:**
- Registers bean definitions
- Creates beans
- Resolves dependencies
- Supports scopes and lifecycle callbacks

**Key characteristic:** it is **lazy by default** (beans are created when requested).

**When you’d care:**
- very constrained environments
- learning internals
- custom frameworks

> **Rarely used directly today—mostly for very resource-constrained environments.**

📌 Example:
```java
BeanFactory factory = new XmlBeanFactory(new FileSystemResource("beans.xml"));
MyService service = (MyService) factory.getBean("myService");
```

---

### 2. **ApplicationContext (Modern, Preferred)**
**What it is:** a richer container built on top of BeanFactory.

**Adds:**
- **event publishing** (ApplicationEventPublisher)
- **internationalization (i18n)** (MessageSource)
- **resource loading** (ResourceLoader)
- Annotation scanning (`@Component`, `@Autowired`).
- AOP and lifecycle callbacks.
- **environment & profiles**
- better integration with annotation-based configuration

**Default characteristic:** most singleton beans are **eagerly created at startup** (pre-instantiated), which helps fail fast.

**What it does well:**
- Provides **all BeanFactory features + enterprise extras**:

📌 Example:
```java
ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
MyService service = context.getBean(MyService.class);
```
- Common implementations:
  - `ClassPathXmlApplicationContext`
  - `FileSystemXmlApplicationContext`
  - `AnnotationConfigApplicationContext`

> 👉 **Rule of thumb:** If you’re building an application, use `ApplicationContext`.

---