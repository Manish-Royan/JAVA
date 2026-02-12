# 🌱 **Spring Core with Annotation-Based Configuration (`@Component` + `@Value`)**

➡️ This project demonstrates how to **create and manage a JavaBean using Spring Core** with **annotation-based configuration**, where the bean is auto-detected via `@Component` and its properties are injected using `@Value`.

➡️ We use **Maven** for dependency management and **ClassPathXmlApplicationContext** to scan annotations via XML.

---

## 📦 **Project Setup**

### 1. Maven `pom.xml`
Dependencies required:
- `spring-core` → Core utilities (IoC, DI, resource handling).
- `spring-beans` → Bean definitions and wiring.
- `spring-context` → ApplicationContext, component scanning.
- `spring-expression` → SpEL for dynamic values.
- `commons-logging` → Logging abstraction used by Spring.
- `spring-aop` → Required for annotation-based configuration.

---

## 🧑‍💻 **JavaBean Example**

### `Student.java`
```java
@Component
public class Student implements Serializable {

    @Value("101")
    private int id;

    @Value("Chupuf")
    private String name;

    @Value("3.50f")
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
- Spring will automatically create the object: `Student student = new Student();`
- Bean name defaults to class name with lowercase first letter → `"student"`.
- `@Value` injects values into fields (required to avoid `null`).

---

## ⚙️ **Spring XML Configuration**

### `applicationContext.xml`
```xml
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:context="http://www.springframework.org/schema/context"
       xsi:schemaLocation="http://www.springframework.org/schema/beans 
                           http://www.springframework.org/schema/beans/spring-beans.xsd
                           http://www.springframework.org/schema/context 
                           http://www.springframework.org/schema/context/spring-context.xsd">

    <context:component-scan base-package="com.learning.beans"/>
</beans>
```

### 📝 Notes:
- No `<bean>` tag needed.
- `component-scan` tells Spring to look for `@Component` annotations in the specified package.
- Without this scan, `@Component` alone won’t register the bean.

---

## 🚀 **Main Program**

### `Main.java`
```java
ApplicationContext context = new ClassPathXmlApplicationContext("/com/learning/resources/applicationContext.xml");

Student obj = (Student) context.getBean("student");
obj.display();
```

### 📝 Notes:
- Bean name `"student"` is derived from class name.
- If not scanned properly, will throw `NoSuchBeanDefinitionException`.

### 🎯 Output
```
ID: 101
Name: Chupuf
GPA: 3.5
```

---

## 🔍 **Comparison: All Three Configuration Styles**
| Feature | XML-Based | Java-Based (`@Configuration`) | Annotation-Based (`@Component`) |
|--------|-----------|-------------------------------|----------------------------------|
| **Bean Declaration** | `<bean>` tag | `@Bean` method | `@Component` class |
| **Bean Name** | `id` attribute | Method name | Class name (lowercase) |
| **Property Injection** | `<property>` | Setter inside method | `@Value` annotation |
| **Configuration Location** | XML file | Java class | Java class + XML scan |
| **Container Used** | `ClassPathXmlApplicationContext` | `AnnotationConfigApplicationContext` | `ClassPathXmlApplicationContext` |
| **Flexibility** | Verbose | Type-safe | Auto-discovery |
| **Best For** | Legacy projects | Modular config | Component scanning & DI |

---

## 📚 **Fun Fact**
Spring’s `@Component` annotation is part of the **stereotype annotations** family (`@Service`, `@Repository`, `@Controller`) that help organize beans by role and enable **auto-detection** during component scanning.

---

## 🗝️ **Key Takeaways**
- `@Component` + `@Value` enables **declarative bean creation and property injection**.
- Requires **component scanning** in XML to register beans.
- This style is ideal for **modular, annotation-driven Spring applications**.

---