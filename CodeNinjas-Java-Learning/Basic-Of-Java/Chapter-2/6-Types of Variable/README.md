# Types of Variable

A variable is a name given to memory location. There are three types of variables in java.

    • Local Variable.
    • Instance Variable.
    • Static variable
 

1. **Local Variables**: **A variable that is defined inside a block, method body, or constructor is called a local variable**. *These variables are created when the methods are called and they get destroyed when the methods are executed and return to the caller*. The initialization of the local variable is mandatory. If you don’t initialize the variable before use, the compiler will give a compile-time error.


[*Example*]:

```Java
public class Addition {


   // Function to add two numbers
   public void add() {


      // Local variables
      int a = 10;
      int b = 20;
      int c = a + b;


      // Printing the sum
      System.out.println(c);
   }


   // Driver Code
   public static void main(String args[]) {
      // Creating an object of Addition class
      Addition obj = new Addition();
      // Function Call
      obj.add();
   }
}
``` 

```
Output:

30
``` 


2. **Instance Variables**: **A variable that is declared inside the class but outside the method body, block, or constructor is known as an instance variable**. *It is a non-static variable*. These variables are created when an instance (object) of the class is created and are destroyed when the object is destroyed. Initialization of the instance variable is not mandatory. *Even If you don’t initialize the instance variable, it has a default value in it*. **Instance variables can be accessed only by creating the object of the class**.

[*Example*]:

```Java
class Student {

	// These are instance variables
	// these are declared inside the 
	// class but outside the method body
	String name;
	int rollno;
}


public class StudentRecords {
	public static void main(String args[]) {

      	// Creating Student class object
    	Student obj = new Student();

       // Assigning values in the variables
    	obj.name = "Ram";
    	obj.rollno = 10;

      	// Printing name and rollno
    	System.out.println(obj.name);
    	System.out.println(obj.rollno);
	}
}
``` 

```
Output:

Ram
10
``` 

3. **Static Variables**: **A variable that is declared as static is known as a static variable**. *It is also known as a class variable*. **These variables are created at the beginning of the program execution and destroyed automatically when the program execution ends**. *We can create only a single copy of a static variable*. To access the static variables, we don’t need to create the object of the class. We can simply access the static variable as `class_Name.variable_Name;`

[*Example*]:

```Java
class Student { 


	// static variables 
	public static int rollno; 
	public static String name = "Ram"; 
} 


public class StudentDemo { 
	public static void main(String args[]) 
	{ 


		// accessing static variable without creating object 
		Student.rollno = 10; 
		System.out.println(Student.name + " 's rollno is :" + Student.rollno); 
	} 
} 
```

```
Output:

Ram’s rollno is 10
``` 
