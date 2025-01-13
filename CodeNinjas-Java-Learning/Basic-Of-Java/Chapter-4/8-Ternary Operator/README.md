# Ternary Operators

Java Ternary operator is *also known as the conditional operator*: This operator is **the only conditional operator in java that takes three operands**. We can use ternary operator in **place of if-else statement or even switch statement**. The syntax of the ternary operator is shown below:

```Syntax
Variable = expression1 ? expression2 : expression3
```

**Note: Here, if expression1 is true then expression2 is evaluated, else expression3 is evaluated.**

#### Example:
 
```Java
public class TernaryOperator {
   public static void main(String args[]) {
      int a = 50;
      int b = 100;
      int minimum;


      // Printing the value of a and b
      System.out.println(“First Number = “ + a);
      System.out.println(“Second Number = “ + b);
     
      // Find the minimum number 
      minimum = (a < b) ? a : b;


     // Printing the minimum number
     System.out.println(“Minimum Number = “ + minimum);
   }
}     
``` 

```
Output:
First Number = 50
Second Number = 100
Minimum Number = 50
``` 