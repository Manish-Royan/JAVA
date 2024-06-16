# Input / Output In Java
 
* let’s first understand how to print a statement in java. 


1. **Using** `println()` **method**: In java, we usually use `println()` method to print the text on the console. *The text is passed as the parameter to this method in the form of string*. This method prints the text on the console, and **after printing the text, the cursor remains at the start of the next line at the console**. The next printing takes place from the next line.

#### *Example*:

```Java
System.out.println(“Coding”);
System.out.println(“Ninjas”);
```

```
Output:

Coding
Ninjas
```


2. **Using** `print()` **method**: In java, we usually use `print()` method to print the text on the console. The text is passed as the parameter to this method in the form of string. This method prints the text on the console, and **after printing the text, the cursor remains at the end of the text at the console**. The next printing takes place from just here.

#### *Example*:
 
```Java
System.out.print(“Coding”);
System.out.print(“Ninjas”);
```

```
Output: 

CodingNinjas
``` 

 
3. ***Using*** `printf()` ***method***: The `printf()` method in java is used to print formatted data on the console. **The** `print()` **and** `println()` **method take single arguments, but** `printf()` **method may take multiple arguments**.

#### *Example*:

```Java
// this will print upto 2 decimal places
System.out.printf(“"Formatted with precision: PI = %.2f\n", Math.PI”);


// Automatically appends Zeros to the 
// rightmost part of the decimal
float n = 5.2f;
System.out.printf(“Formatted to specific width: n = %.4f\n", n”);
```

```
Output:

Formatted with precision: PI = 3.14
Formatted to specific width: n = 5.2000
``` 

#### *Example*: 

```Java
public class Test {

	public static void main(String args[]) {

		int age = 20;
		String firstName = "Manish";
		String lastName = "Royan";

		System.out.println("My name is " + firstName + " " + lastName);
		System.out.println("My age is " + age);
	}
	
}
```

```
Output:

My name is Manish Royan
My age is 20

In the above program, we have declared and initialized three variables: one int variable (age) and two String variables (firstName and lastName). Then, we are printing messages along with the values of these variables by using concatenation. We use String concatenation operator + for concatenating strings in java. 
```

