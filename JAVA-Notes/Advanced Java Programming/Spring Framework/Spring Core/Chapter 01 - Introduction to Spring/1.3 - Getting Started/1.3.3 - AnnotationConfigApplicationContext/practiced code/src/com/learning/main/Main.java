package com.learning.main;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.learning.beans.Student;

public class Main 
{
	public static void main(String[] args)
	{
		String resource = "/com/learning/resources/applicationContext.xml";
		ApplicationContext context = new ClassPathXmlApplicationContext(resource);
		
		Student obj =  (Student) context.getBean("student");
		obj.display();
	}
}

/*
* Note: If we run this Program directyl it will throw: 'NoSuchBeanDefinitionException' 
  meaning it didn't find object/bean as 'student'.
*/