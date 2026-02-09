# 📦 Spring Container Overview
➡️ Before creating our first program, let's have a quick glimpse about **Spring Container**.

---
## **1. What is the Spring Container?**
➡️ The **Spring Container** is the **core engine** of the Spring Framework.  
➡️ It is responsible for:
- Creating and managing **beans** (objects).
- Handling **dependency injection (DI)**.
- Managing the **bean lifecycle** (creation → initialization → destruction).
- Providing **configuration and context** for the application.

### 💭 Think of the container as the **“brain” of Spring**—it knows how to assemble and manage all the parts of your application.

---

## **2. Responsibilities of the Spring Container**
➡️ The container does much more than just object creation:
- **Instantiate beans** → Creates objects defined in configuration.
- **Configure beans** → Injects dependencies (DI).
- **Manage lifecycle** → Calls initialization and destruction callbacks.
- **Provide context** → Supplies environment info, resources, and events.
- **Enable modularity** → Supports profiles, scopes, and lazy initialization.
- **Support AOP & Events** → Allows cross-cutting concerns and event-driven programming.

> ### 👉 In short: The container is both a **factory** and a **manager** for your application objects.

---

## **3. Types of Spring Containers**
Spring provides **two main types of containers**:

### 1. **BeanFactory**
- The simplest container.
- Provides basic DI functionality.
- Lazily initializes beans (created only when requested).
- Lightweight, suitable for simple applications.

> ### ⚠️ Using `BeanFactory` directly may cause missing features like event handling and annotation support.

> ### ⚠️ Rarely used directly in modern apps.

---

### 2. **ApplicationContext**
- A **superset of BeanFactory**.
- Provides advanced features:
  - Event propagation.
  - Internationalization (i18n).
  - Annotation-based configuration.
  - AOP integration.
- Eagerly initializes beans (created at startup).
- Common implementations:
  - `ClassPathXmlApplicationContext`
  - `FileSystemXmlApplicationContext`
  - `AnnotationConfigApplicationContext`

* **Fun Fact 💡**: `ApplicationContext` internally uses `BeanFactory`—so you can think of it as **BeanFactory + extra features**.
> ### 👉 Almost all modern Spring applications use **ApplicationContext**.

---
# 🌱 Getting Started with Spring Core (JavaBean + ApplicationContext + Maven)

➡️ This project demonstrates how to **create and manage a JavaBean using the Spring Core Framework**.  
➡️ We use **Maven** for dependency management and **ApplicationContext** as the Spring Container.

---

## 📦 Project Setup

### 1. Maven `pom.xml`
Dependencies required:
- `spring-core` → Provides the fundamental classes and utilities (IoC, DI, resource handling) that power the Spring Framework.
- `spring-beans` → Manages bean definitions, wiring, and the BeanFactory for object creation.
- `spring-context` → Extends BeanFactory with ApplicationContext, events, i18n, and annotation support.
- `spring-expression` → Enables SpEL (Spring Expression Language) for dynamic values and property injection.
- `commons-logging` → Supplies a unified logging abstraction used internally by Spring for consistent logging.

```xml
<dependencies>
    <!-- Spring Bean -->
    <dependency>
        <groupId>org.springframework</groupId>
        <artifactId>spring-beans</artifactId>
        <version>7.0.1</version>
    </dependency>

    <!-- Spring Core -->
    <dependency>
        <groupId>org.springframework</groupId>
        <artifactId>spring-core</artifactId>
        <version>7.0.1</version>
    </dependency>

    <!-- Spring Context -->
    <dependency>
        <groupId>org.springframework</groupId>
        <artifactId>spring-context</artifactId>
        <version>7.0.1</version>
    </dependency>

    <!-- Spring Expression -->
    <dependency>
        <groupId>org.springframework</groupId>
        <artifactId>spring-expression</artifactId>
        <version>7.0.1</version>
    </dependency>

    <!-- Common Logging -->
    <dependency>
        <groupId>commons-logging</groupId>
        <artifactId>commons-logging</artifactId>
        <version>1.3.5</version>
    </dependency>
</dependencies>
```

### 👉 These JARs are the **minimum required** to run Spring Core with ApplicationContext.
### 📝 Every Dependency-Version must be same (like in the above Maven we are using 7.0.1v)

---

## 🧑‍💻 JavaBean Example

### `Student.java`
```java
package com.learning.beans;

import java.io.Serializable;

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

## ⚙️ Spring Configuration

### `applicationContext.xml`
```xml
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xsi:schemaLocation="http://www.springframework.org/schema/beans 
                           http://www.springframework.org/schema/beans/spring-beans.xsd">

    <bean id="uniqueID" class="com.learning.beans.Student">
        <property name="id" value="101"/>
        <property name="name" value="Lulu"/>
        <property name="gpa" value="4"/>
    </bean>
</beans>
```

### 👉 By default, bean properties are `null` unless explicitly set using `<property>`.
### Point to be Remember:

[IMG]

---

## 🚀 Main Program

### `Main.java`
```java
package com.learning.main;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import com.learning.beans.Student;

public class Main {
    public static void main(String[] args) {
        // Load Spring Configuration file
        String config = "/com/learning/resources/applicationContext.xml";
        ApplicationContext context = new ClassPathXmlApplicationContext(config); // Initiate Spring Container

        // Access Class Object (bean) via context
        Student obj = (Student) context.getBean("uniqueID"); // must type cast
        obj.display();
    }
}
```

### 📝 Notes & Insights
- **Spring Container builds objects itself** → No need for `new Student()`.
- **ApplicationContext** is the preferred container over `BeanFactory` because it supports:
  - Annotation-based configuration
  - Event propagation
  - Internationalization

### 🎯 Output
When you run the program:
```
ID: 101
Name: Lulu
GPA: 4.0
```

---

## 🌟 Quick Recap
1. Define a **JavaBean** (`Student`).
2. Configure it in **applicationContext.xml**.
3. Load the configuration using **ApplicationContext**.
4. Retrieve the bean using `getBean()` and use it.