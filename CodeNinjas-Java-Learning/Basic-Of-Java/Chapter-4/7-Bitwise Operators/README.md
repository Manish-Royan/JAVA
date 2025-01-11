# Bitwise Operators

The Bitwise operators are **used to perform bit manipulation on numbers**. There are various types of Bit operators that are used in Java.:


i. **Bitwise AND operator (&)**: It takes two numbers as operands and **does *AND* on every bit of two numbers**. The result of ***AND* is 1 only if both bits are 1**. Mind that the commutative property holds true here.

    That is,

              1 & 1 = 1

              1 & 0 = 0 

#### Example:

```Java
public class BitwiseAndOperator {
   public static void main(String args[]) {
      int a = 6;
      int b = 7;
 
      // Binary representation of 6 is 0110
      // Binary representation of 7 is 0111
      // Result is                     0110 = 6
      System.out.println(“a & b = “ + (a & b));
  }
}
```

```
Output: 
a & b = 6
```


ii. **Bitwise OR operator (|)**: It takes two numbers as operands and **does *OR* on every bit of two numbers**. The result of ***OR* is 1 if any of the two bits is 1**. Mind that the commutative property holds true here.

    That is,

              1 | 1 = 1

              1 | 0 = 1

              0 | 0 = 0 


#### Example:
 
```Java
public class BitwiseOrOperator {
  public static void main(String args[]) {
     int a = 6;
     int b = 7;

     // Binary representation of 6 is 0110
     // Binary representation of 7 is 0111
     // Result is                     0111 = 7
     System.out.println(“a | b = “ + (a | b));
  }
}
``` 

```
Output:
a | b = 7
```


iii. **Bitwise NOT operator (~)**: It takes one number and inverts all bits of it.

#### Example:
 
```Java
public class ComplementOperator {
   public static void main(String args[]) {
      int a = 6;

      // Binary representation of 6 is 000000000000000000000000000000110                                           // 32 bit representation
      // complement is invert the bits 111111111111111111111111111111001, which is binary representation of -7      
      // Result is -7
      System.out.println(“ ~a = “ + ~a);
   }
}
``` 

```
Output:
~a = -7
``` 


iv. **Bitwise XOR  operator (^)**: It takes two numbers as operands and **does *XOR* on every bit of two numbers**. The result of ***XOR* is 1 if the two bits are different**. Mind that the commutative property holds true here.

    That is,

              1 ^ 1 = 0

              1 ^ 0 = 1

              0 ^ 0 = 0 


#### Example:
 
```Java
public class BitwiseExclusiveOr {
  public static void main(String arsg[]) {
     int a = 6;
     int b = 7;
     
     // Binary representation of 6 is 0110
     // Binary representation of 7 is 0111
     // Result is                     0001 = 1
     System.out.println(“a ^ b = “ + (a ^ b));
  }
}
``` 

```
Output:
a ^ b = 1
``` 


v. **Left shift operator (<<)**: **It takes two numbers, the left shift operator shifts the bits of the first operand, the second operand decides the number of places to shift**.


#### Example:
 
```Java
public class LeftShiftOperator {
  public static void main(String args[]) {
     int a = 8;

     // Binary representation of 8 is 1 0 0 0
     // Left shift means append number of 
     // 0’s to the right. 1 0 0 0 0 0 = 32
     System.out.println(“a << 2 = “ + (a << 2));
  }
}
``` 

```
Output:
a << 2 = 32
``` 


vi. **Right shift operator (>>)**: **It takes two numbers; the right shift operator shifts the bits of the first operand, the second operand decides the number of places to shift**.



#### Example:
 
```Java
public class SignedRightShift {
  public static void main(String args[]) {
     int a = 8;

     // Binary representation of 8 is 1 0 0 0
     // signed right shift means remove
     // the number of 0’s to the right. 1 0  = 2
     System.out.println(“a >> 2 = “ + (a >> 2));
  }
}
``` 

```
Output:
a >> 2 = 2
``` 

```
NOTE: The left shift and right shift operators should not be used for negative numbers. If any of the operands is a negative number, it results in undefined behaviour.

For example results of both -1 << 1 and 1 << -1 is undefined. Also, if the number is shifted more than the integer’s size, the behaviour is undefined. For example, 1 << 33 is undefined if integers are stored using 32 bits.
```

vii. **Unsigned Right shift operator ( >>>)**: **This operator is used to shift the bits of the number to the right and fills 0 in the void spaces that are left as a result. The leftmost bit is set to be 0**.


#### Example:
 
```Java
public class UnsignedRightShift {
   public static void main(String args[]) {
      int a = 240;


      // Binary representation of 240 is 1 1 1 1 0 0 0 0
      // Unsigned right shift means remove 
      // number of bits to the right and 
      // append into the left 0 0 1 1 1 1 0 0 = 60.
      System.out.println(“a >>> 2 = “ + (a >>> 2));
   }
}
``` 

```
Output:
a >>> 2 = 60
``` 