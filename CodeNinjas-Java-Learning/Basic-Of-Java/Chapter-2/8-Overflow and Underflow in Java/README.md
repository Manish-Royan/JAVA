# Overflow and Underflow in Java

Overflow in java happens when we assign a value to a variable that is more than its range and Underflow is opposite to overflow. In case of overflow and underflow, the Java compiler doesn’t throw any error. It simply changes the value.  

For example, in the case of an `int` variable, its *size is 4 bytes or 32 bits*. The maximum value of `int` data type *is 2,147,483,647*  (**Integer.MAX_VALUE**) and *after incrementing 1 on this value, it will return -2,147,483,648* (**Integer.MIN_VALUE**). This is known as **overflow**. The minimum value of `int` data type is *-2,147,483,648* (**Integer.MIN_VALUE**) and *after decrementing 1 on this value, it will return 2,147,483,647* (**Integer.MAX_VALUE**). This is known as **underflow** in Java.

#### [Example]:

```Java
public class OverflowExample {

	public static void main(String args[]) {
	
		// Overflow
		int overFlow = 2147483647;
		System.out.println(“Overflow : “ + (overFlow + 1));         
		
		// Underflow
		int underFlow = -2147483648;
		System.out.println(“Underflow : “ + (underFlow - 1));
     	
 	}
}
```
 
```
Output: 

Overflow : -2147483648
Underflow : 2147483647
```