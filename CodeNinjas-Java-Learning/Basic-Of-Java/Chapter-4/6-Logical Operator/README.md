# Logical Operators

These operators are used to perform logical operations such as ***OR***, ***AND***, and ***NOT*** operation. **It operates on two boolean values, which return true or false as a result**. There are three types of Logical Operators in Java:


i. **Logical AND operator (&&)**: This operator **returns true(1)**, if **both the conditions are true else returns false(0)**. 

#### Example:

```Java
public class Solution {
	
   public static void main(String args[]) {
      int a = 10;
      int b = 20;
      int c = 30;
      
      System.out.println((b > a) && (c > b));  // true
      System.out.println((b > a) && (c < b));; // false
   }
}
```

```
Output: 

true
false
```


ii. **Logical OR operator (||)**: This operator **returns true(1) if any one of the conditions is true**. .

#### Example:
 
```Java
public class LogicalOrOperator {
   public static void main(String args[]) {
      int a = 10;
      int b = 20;
      int c = 30;

      System.out.println((b > a) || (c < b));  // true
      System.out.println((b < a) || (c < b));  // false
  }
}
``` 

```
Output:

true
false
```


iii. **Logical NOT operator (!)**: This operator is used to **reverse the operand’s value**. **If the operand’s value is true, it returns false(0)**, and **if the value of the operand is false, it returns true(1)**.

#### Example:
 
```Java
public class LogicalOrOperator {
   public static void main(String args[]) {
      int a = 10;
      int b = 20;

      System.out.println( a!= b );  // true
      System.out.println( a== b );  // false
  }
}
``` 

```
Output:

true
false
``` 
