# 🌱 Spring Core with `@Component` and `@ComponentScan`

➡️ This project demonstrates how to **create and manage a JavaBean using Spring Core** with **annotation-based configuration**.  
➡️ Here, beans are auto-detected via `@Component` and registered into the Spring Container using `@ComponentScan`.   
➡️ We use **Maven** for dependency management and **AnnotationConfigApplicationContext** to bootstrap the container.

---

## 📦 **Project Setup**

### 1. Maven `pom.xml`
Dependencies required:
- `spring-core` ➡️ Core utilities (IoC, DI, resource handling).
- `spring-beans` ➡️ Bean definitions and wiring.
- `spring-context` ➡️ ApplicationContext, component scanning.
- `spring-expression` ➡️ SpEL for dynamic values.
- `commons-logging` ➡️ Logging abstraction used by Spring.
- `spring-aop` ➡️ Required for annotation-based configuration.

---

## 🧑‍💻 **JavaBean Example**

### `Student.java`
```java
@Component
public class Student implements Serializable {

    @Value("101")
    private int id;

    @Value("ChuChu")
    private String name;

    @Value("4.0f")
    private double gpa;

    public Student() {}

    // Getters & Setters...

    public void display() {
        System.out.println("ID: " + id);
        System.out.println("Name: " + name);
        System.out.print("GPA: " + gpa);
    }
}
```

### 📝 Notes:
- `@Component` marks this class as a Spring-managed bean.
- Bean name defaults to class name with lowercase first letter → `"student"`.
- `@Value` injects values directly into fields.

---

## ⚙️ **Java-Based Spring Configuration**

### `SpringConfigurationFile.java`
```java
@Configuration
@ComponentScan("com.learning.beans")
public class SpringConfigurationFile {
}
```

### 📝 Notes:
- `@Configuration` marks this class as a configuration source.
- `@ComponentScan` tells Spring to scan the specified package for `@Component` classes.
- No need to manually declare beans with `@Bean` or `<bean>`.

---

## 🚀 **Main Program**

### `Main.java`
```java
ApplicationContext context = new AnnotationConfigApplicationContext(SpringConfigurationFile.class);

Student obj = (Student) context.getBean("student");
obj.display();
```

### 📝 Notes:
- Container is initialized with `AnnotationConfigApplicationContext`.
- Bean `"student"` is auto-detected and injected.

### 🎯 Output
```
ID: 101
Name: ChuChu
GPA: 4.0
```

---

## 🔍 **Comparison: All Styles**

| Feature | XML-Based | Java-Based (`@Configuration`) | Annotation-Based (`@ComponentScan`) |
|--------|-----------|-------------------------------|--------------------------------------|
| **Bean Declaration** | `<bean>` tag | `@Bean` method | `@Component` class |
| **Bean Name** | `id` attribute | Method name | Class name (lowercase) |
| **Property Injection** | `<property>` | Setter inside method | `@Value` annotation |
| **Configuration Location** | XML file | Java class | Java class with `@ComponentScan` |
| **Container Used** | `ClassPathXmlApplicationContext` | `AnnotationConfigApplicationContext` | `AnnotationConfigApplicationContext` |
| **Flexibility** | Verbose | Type-safe | Auto-discovery |
| **Best For** | Legacy projects | Modular config | Modern, annotation-driven apps |

---
## 📚 **Fun Fact**
- `@ComponentScan` is the **annotation equivalent** of `<context:component-scan>` in XML.  
- Together with `@Component`, it enables **automatic bean discovery**—no manual bean definitions required.

---

## 🗝️ Key Takeaways
- `@Component` marks a class as a bean.  
- `@ComponentScan` tells Spring where to look for such beans.  
- This style is the most **modern and concise** way to configure Spring Core applications.  

---