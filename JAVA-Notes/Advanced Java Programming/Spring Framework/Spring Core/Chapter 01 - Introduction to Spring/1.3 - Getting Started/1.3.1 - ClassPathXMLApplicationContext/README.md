# 🌱 Getting Started with Spring Core (JavaBean + ApplicationContext + Maven) using XML-Based Configuration

➡️ This project demonstrates how to **create and manage a JavaBean using the Spring Core Framework**.  
➡️ We use **Maven** for dependency management and **ApplicationContext** as the Spring Container.  
➡️ We will use `XML` based configuration.

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

<img width="1919" height="1079" alt="Screenshot 2026-02-09 232227" src="https://github.com/user-attachments/assets/303a24e7-28f7-4c09-a7a2-0bc9872bb823" />

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
