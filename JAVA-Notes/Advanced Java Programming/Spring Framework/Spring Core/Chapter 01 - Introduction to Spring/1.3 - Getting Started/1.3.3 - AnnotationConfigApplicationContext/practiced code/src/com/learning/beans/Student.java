package com.learning.beans;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/* The moment we decalre @Component, Spring container will automatically create object for this class
   so, we don't need to create <bean> manually in applicationContext.xml
 * Spring will automatically create Student(Class) Object like `Student student = new Student`
 * The object will be created same name of Class but starting letter will be lower case (eg. Class → class)
 * But just declaring @Component won't create bean so it must be scanned in applicationContext.xml using <context-component scan>
 */
@Component 
public class Student implements Serializable {

	
	@Value("101")
    private int id;
	
	@Value("Chupuf")
    private String name;
	
	@Value("3.50f")
    private double gpa;


    public Student() {
    }

    // Getter and Setter methods
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getGpa() {
        return gpa;
    }

    public void setGpa(double gpa) {
        this.gpa = gpa;
    }
    
    public void display()
    {
    		System.out.println("ID: "+ id);
    		System.out.println("Name: "+ name);
    		System.out.print("GPA: "+ gpa);
    }
}

/*
- The @Component annotation in Spring is used to declare a class as a Spring bean,
  which is managed component in the Spring application context.
- It helps Spring automatically detect and manage these beans during application startup, 
  making them available for dependency injection and other Spring features.
- Add this on XML:
	-  xmlns:context="http://www.springframework.org/schema/context"
	-  http://www.springframework.org/schema/context http://www.springframework.org/schema/context/spring-context.xsd"
*/


/*Note:
1. Just declaring @Component won't create bean so it must be scanned in applicationContext.xml.
2. After setting up XML and <context:component-scan base-package="com.learning.beans"></context:component-scan>
   the program will run but will provide `null` value that must be set using @Value annotation.
*/


/*
- The @Value() annotation in Spring is used to inject values into Spring bean fields or methods.
- @Value is mostly used to inject values form external sources (eg. properties files or environment variables)
*/