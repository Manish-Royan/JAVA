package com.learning.main;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.learning.beans.Student;

public class Main 
{
	public static void main(String[] args)
	{
		// Load Spring Configuration file
		String config = "/com/learning/resources/applicationContext.xml";
		ApplicationContext context = new ClassPathXmlApplicationContext(config); //Initiate Spring Container
		
//		Student obj = new Student(); //(applicationContext) Spring Container builds Object itself
		
		// Access Class Object (bean) via context
		Student obj = (Student)context.getBean("uniqueID"); //must type cast
		obj.display();
	}
}

/*
1. Download Jar: 
	- spring-bean-xx.jar
	- spring-core-xxx.jar
	- spring-context-xxx.jar
	- common-logging-xxx.jar
	- spring-expression-xxx.jar
*/