# Arithmetic Operators 

Arithmetic Operators in Java are used to perform mathematical operations, *i.e. Addition, Subtraction, Multiplication, and Division etc*. 

### The basic arithmetic operators in Java are given below:

* Addition Operator (+) : It is used to add two numbers.
* Subtraction Operator (-) : It is used to subtract two numbers.
* Multiplication Operator (*) : It is used to multiply two numbers.
* Division Operator (/) : It is used to divide two numbers.
* Modulus Operator (%) : It is used to return the remainder of the division operation.
Example:

 

#### In this example, we are going to perform basic mathematical operations on two numbers:
 
```Java
public class ArithmeticOperators {
   public static void main(String args[]) {
      
      // Taking two numbers
      int a = 50;
      int b = 20;
      
      // Performing addition operation
      System.out.println(“Addition of “ +a+ “ and” +b+ “ is : “ +(a+b));


      // Performing subtraction operation
      System.out.println(“Subtraction of “ +a+ “ and” +b+ “ is : “ +(a-b));


      // Performing multiplication operation
      System.out.println(“Multiplication of “ +a+ “ and” +b+ “ is : “ +(a*b));


      // Performing division operation
      System.out.println(“Division of “ +a+ “ and” +b+ “ is : “ +(a/b));


      // Performing modulus operation
      System.out.println(“Modulus of “ +a+ “ and” +b+ “ is : “ +(a%b));
   }
}
``` 

```
Output:

Addition of 50 and 20 is : 70
Subtraction of 50 and 20 is : 30
Multiplication of 50 and 20 is : 1000
Division of 50 and 20 is : 2
Modulus of 50 and 20 is : 10
```