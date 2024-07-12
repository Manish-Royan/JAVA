# Relational Operators

The Relational operators are used to check the relationship between two operands. **This operator is also called a comparison operator because it is used to make a comparison between two operands**. **The result of these operators is always boolean value**. These operators are used in `if` statements and `loops`. There are many types of relational operators, which are given below:

i. **Equal to operator (==)**: This operator is used to check whether the two operands are equal or not. **If they are equal, it returns true; otherwise, it returns false**.

#### Example:

```Java
public class EqualToOperator {
   public static void main(String args[]) {
      int a = 10;
      int b = 20;

      // Check two operands are equal or not
      // if they equal return true, else return false
      System.out.println(a == b);
  }
}
```

```
Output: 

false
```


ii. **Not Equal to operator (!=)**: This operator is used to check whether the two operands are equal or not. **It returns true(1) if the left operand is not equal to the right operand; otherwise, it returns false(0)**.

#### Example:
 
```Java
public class NotEqualToOperator {
   public static void main(String args[]) {
      int a = 10;
      int b = 20;

      // Returns true if left operand
      // is not equal to right operand
      System.out.println(a != b);
  }
}
``` 

```
Output:

true
```


iii. **Greater than operator (>)**: This operator is used to check whether the first operand is greater than the second operand or not. **It returns true(1) if the first operand is greater than the second operand and false(0) if not**.

#### Example:
 
```Java
public class GreaterThanOperator {
   public static void main(String args[]) {
      int a = 10;
      int b = 20;

      // Returns true if left operand 
      // greater than the right operand
      // else return false
      System.out.println(a > b);
  }
}
``` 

```
Output:

false
``` 


iv. **Greater than equal to the operator (>=)**: This operator is used to check whether the first operand is greater than or equal to the second operand or not. **It returns true(1) if the first operand is greater than or equal to the second operand; otherwise, it returns false(0)**.

#### Example:
 
```Java
public class GreaterThanEqualTo {
   public static void main(String args[]) {
      int a = 10;
      int b = 8;

      // It returns true because the 
      // first operand is greater than
      // second operand
      System.out.println(a >= b);
   }
}
``` 

```
Output: 

true
```


v. **Less than operator (<)**: This operator is used to check whether the first operand is less than the second operand or not. **It returns true(1) if the first operand is less than the second operand else returns false(0)**.

#### Example:
 
```Java
public class LessThanOperator {
   public static void main(String args[]) {
      int a = 10;
      int b = 15;

      // It returns true because
      // first operand is smaller 
      // than the second operand
      System.out.println(a < b); 
  }
}
```

```
Output: 

true
```


vi. **Less than or equal to operator (<=)**: This operator is used to check whether the first operand is less than or equal to the second operand or not. **It returns true(1) if the first operand is less than or equal to the second operand; else, return false(0)**.

#### Example:
 
```Java
public class LessThanEqualTo {
   public static void main(String args[]) {
      int a = 10;
      int b = 5;

      // Returns false because first operand
      // is not less than or equal to
      // the second operand
      System.out.println(a <= b);
  }
}
```

```
Output:

false
```