package com.learning.resources;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.learning.beans.Student;
@Configuration
public class SpringConfigurationFile 
{
	@Bean //this annotation in Spring is used to declare a method as factory for Creating and configuring a bean managed by the SpringContainer
	public Student StudID() //The object created by StudID() method, which is annotated with @Bean, is managed and created by the Spring Container
	{ //When the Spring container initializes, it will invoke this method to create the Student object
		Student obj = new Student();
		
		/*Setting Property*/
		obj.setId(101);
		obj.setName("Lulu");
		obj.setGpa(4.0);
		
		return obj;
	}
}


/*
- `@Configuration` indicates that the class is SpringConfiguration class.
- It means that annotated java class contains bean definitions and configuration settings
  for the Spring ApplicationContext.
- `@Configuration` allows us to define beans and their dependencies in Java-Based way
   instead of using XML configuration files.
- We can also use `@Bean("Self-DefineClassObjectName")` to set custom object name instead using MethodName as default BeanName
*/


/*
1. In XML-based Spring configuration, we use <bean> tags to define beans.
2. In Java-based configuration, we use methods annotated with @Bean to define beans.
3. In XML configuration, we provide an "id" attribute to specify the bean name,
   and we specify the "class" name using the class attribute.
4. In Java configuration, the "methodName" becomes the default bean name, 
   and the "return type" of method determines the class of the object 
   that will be created as the bean.
*/