package com.learning.main;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.learning.beans.Student;
import com.learning.resources.SpringConfigurationFile;

public class Main //Initialize Spring Container and Access Bean
{
	
	ApplicationContext context = new AnnotationConfigApplicationContext(SpringConfigurationFile.class);
	// We will not use `ClassPathXmlApplicationContext` since we are not using XML-based config

	Student stud = (Student) context.getBean("StudID"); //We passed the MethodName
	stud.display();
	
	/*Another Technique to Access Bean*/
	// Student stud = (Student) context.getBean(Student.class);
	// stud.display();
	
}

//Note: For Java-Based Configuration, we will need "Spring-AOP" jar/Maven.