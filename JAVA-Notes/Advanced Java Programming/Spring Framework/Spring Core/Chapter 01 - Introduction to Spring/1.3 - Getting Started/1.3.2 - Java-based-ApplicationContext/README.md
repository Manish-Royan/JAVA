# 🌱 **Spring Core with Java-Based Configuration (Annotation-Driven)**

➡️ This project demonstrates how to **create and manage a JavaBean using Spring Core** with **Java-based configuration** via annotations.  
➡️ We use **Maven** for dependency management and **AnnotationConfigApplicationContext** as the Spring Container.

---

## 📦 **Project Setup**

### 1. Maven `pom.xml`
Dependencies required:
- `spring-core` → Core utilities (IoC, DI, resource handling).
- `spring-beans` → Bean definitions and wiring.
- `spring-context` → ApplicationContext, annotation support.
- `spring-expression` → SpEL for dynamic values.
- `commons-logging` → Logging abstraction used by Spring.
- **`spring-aop` → Required for annotation-based configuration.**

---

## **🧑‍💻 JavaBean Example**

### `Student.java`
```java
public class Student implements Serializable {
    private int id;
    private String name;
    private double gpa;

    public Student() {}

    // Getters & Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public double getGpa() { return gpa; }
    public void setGpa(double gpa) { this.gpa = gpa; }

    public void display() {
        System.out.println("ID: " + id);
        System.out.println("Name: " + name);
        System.out.print("GPA: " + gpa);
    }
}
```

---

## ⚙️ **Java-Based Spring Configuration**

### `SpringConfigurationFile.java`
```java
@Configuration
public class SpringConfigurationFile {
    @Bean
    public Student StudID() {
        Student obj = new Student();
        obj.setId(101);
        obj.setName("Lulu");
        obj.setGpa(4.0);
        return obj;
    }
}
```

### 📝 Notes:
- `@Configuration` marks this class as a Spring configuration source.
- `@Bean` declares a method as a factory for creating and configuring a bean.
- The method name (`StudID`) becomes the default bean name.
- You can override the bean name using `@Bean("CustomName")`.

---

## 🚀 **Main Program**

### `Main.java`
```java
ApplicationContext context = new AnnotationConfigApplicationContext(SpringConfigurationFile.class);
// We will not use ClassPathXmlApplicationContext since we are not using XML-based config

Student stud = (Student) context.getBean("StudID"); // Method name used as bean ID
stud.display();

/* Alternative way to access bean */
Student stud = context.getBean(Student.class);
stud.display();
```
## 🎯 Output
```
ID: 101
Name: Lulu
GPA: 4.0
```


---

## 🔍 **Comparison: XML vs Java-Based Configuration**

<img width="1919" height="1079" alt="Screenshot 2026-02-12 150327" src="https://github.com/user-attachments/assets/f56b69ce-e805-47f7-8bda-47d0db2e2097" />

| Feature | XML-Based | Java-Based (Annotation) |
|--------|-----------|--------------------------|
| **Configuration File** | `applicationContext.xml` | `@Configuration` class |
| **Bean Declaration** | `<bean>` tag | `@Bean` method |
| **Bean Name** | `id` attribute | Method name (or custom via `@Bean("name")`) |
| **Bean Class** | `class` attribute | Method return type |
| **Flexibility** | Verbose, externalized | Type-safe, IDE-friendly |
| **Required Container** | `ClassPathXmlApplicationContext` | `AnnotationConfigApplicationContext` |

---

## 🗝️ **Key Takeaways**
- Spring supports both **XML-based** and **Java-based** configuration styles.
- Java-based configuration is **type-safe**, **refactor-friendly**, and **preferred in modern Spring projects**.
- Spring Container handles object creation, wiring, and lifecycle—whether via XML or annotations.

---

## 📚 **Fun Fact**
> Spring’s annotation-based configuration was introduced to **reduce XML verbosity** and make Spring development more **declarative and intuitive**—especially for large-scale enterprise apps.

---
