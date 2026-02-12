package com.learning.main;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.learning.beans.Student;
import com.learning.resources.SpringConfigurationFile;

public class Main 
{
	public static void main(String[] args) 
	{
		ApplicationContext context = new AnnotationConfigApplicationContext(SpringConfigurationFile.class);
		
		Student obj = (Student) context.getBean("student");
		obj.display();
	}
}