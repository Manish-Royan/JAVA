### • MCQ: Arithmetic operators

#### Problem statement:

Which of the following are arithmetic operators in java?

**Options:**

    1. Addition and subtraction operator
    2. Multiplication and division operator
    3. Modulus operator
    4. All the above ➡ [Answer]

```
Solution description:

There are 5 basic arithmetic operators in java which are addition, subtraction, multiplication, division, and modulus operator.
```


### • MCQ: Remainder of division

#### Problem statement:

Which is the arithmetic operator in java that gives the remainder of division?

**Options:**

    1. /
    2. % ➡ [Answer]
    3. &
    4. |

```
Solution description:

The modulus operator in java is used to find the remainder of division.
```


### • MCQ: Find the output

#### Problem statement:

What is the output of the following code?

```Java
public class Solution {

    public static void main(String args[]) {
        int a = 10;
        int b = 5;

        int c = a + b * 10;

        System.out.println(c);
    }
}
```

**Options:**

    1. 150
    2. 60 ➡ [Answer]
    3. 105
    4. Compilation error

```
Solution description:

The operator * has higher priority than the + operator. So, the multiplication operation is performed first with (b*10) = 50, then (+ a) which is 50+10 = 60. 
```


### • MCQ: Find the output

#### Problem statement:

What is the output of the following code?

```Java
public class Solution {

    public static void main(String args[]) {

        int x = 7--2;

        System.out.println(x);
    }
}
```

**Options:**

    1. 9
    2. 14
    3. 49
    4. Compilation error ➡ [Answer]

```
Solution description:

There is no such type (--) of operator in java.
```


### • MCQ: Operands in unary operators

#### Problem statement:

How many operand unary operators take?

**Options:**

    1. 1 ➡ [Answer]
    2. 2
    3. 3
    4. None of the above

```
Solution description:

Unary operator in java takes only one operand.
```


### • MCQ: Types of decrement operators

#### Problem statement:

How many types of decrement operators in java?How many types of decrement operators in java?

**Options:**

    1. 1 
    2. 2 ➡ [Answer]
    3. 3
    4. None of the above

```
Solution description:

There are two types of decrement operators in java. These are pre-decrement operators and post-decrement operators.
```


### • MCQ: Find the output

#### Problem statement:

What is the output of the following code?

```Java
public class Solution {

    public static void main(String args[]) {

        int x = 5;
        int y  = 6;

        if(x++ == --y) {
            System.out.println("Coding Ninjas");
        }
        else {
            System.out.println("Ninjas");
        }
    }
}
```

**Options:**

    1. Ninjas
    2. Coding Ninjas ➡ [Answer]
    3. Compile time error
    4. Runtime error

```
Solution description:

At the time of evaluating the expression (x++ == --y), x (5) is compared to y (6-1 = 5). Therefore the condition passes and prints "Coding Ninjas".
```


### • MCQ: Find the output

#### Problem statement:

What is the output of the following code?

```Java
public class Solution {

    public static void main(String args[]) {

        int x = 6;
        int y  = 5;

        if(++y == x--) {
            System.out.println("Coding Ninjas");
        }
        else {
            System.out.println("Ninjas");
        }
    }
}
``` 

**Options:**

   1. Ninjas
    2. Coding Ninjas ➡ [Answer]
    3. Compile time error
    4. Runtime error

```
Solution description:

At the time of evaluating the expression (++y == x--), y (6) is compared to x (6). Therefore the condition passes and prints "Coding Ninjas".
```


### • MCQ: Find the output

#### Problem statement:

What is the output of the following code?

```Java
public class Solution {
    public static void main(String args[]) {
        int a = 10, b = 1;
        System.out.println(!(a < b));
    }
}
```
**Options:**

    1. true ➡ [Answer]
    2. false
    3. Compilation error
    4. Runtime error

```
Solution description:

In the above program, we are using unary not operators that change the true to false and vice versa. Since the condition ( a < b) is false but we are using an unary not operator that changes the result to true.
```


### • MCQ: Find the output

#### Problem statement:

What is the output of the following code?

```Java
public class Solution {

    public static void main(String args[]) {

        int a = 7, b = 18, c = 45;
        a -= 4;
        b *= 2;
        c /= 9;
        System.out.println(a + " " + b + " " + c);
    }
}
```
**Options:**

    1. 3 18 5 
    2. 3 36 5 ➡ [Answer]
    3. 3 9 9
    4. None of the above

```
Solution description:

In the above program, we are using assignment operators, So that a -= 4 means a = a - 4 = 3, b *= 2 means b = b * 2 = 36, and c /= 9 means c = c / 9 = 5.
```


### • MCQ: Find the output

#### Problem statement:

What is the output of the following code?

```Java
public class Solution {

    public static void main(String args[]) {

        int num = 18;
        num %= 2;
        System.out.println(num);
    }
}
```
**Options:**

    1. 0
    2. 9 ➡ [Answer]
    3. 18
    4. Compile time error

```
Solution description:

In the above program, we are using a modulo assignment operator. It returns the remainder. But when we use modulo 18 % 2. It returns 0.
```


### • MCQ: Types of assignment operators

#### Problem statement:

How many types of assignment operators in java?

**Options:**

    1. 3
    2. 5 ➡ [Answer]
    3. 4
    4. 6

```
Solution description:

There are 5 assignment operators in java. These are +=, -=, *=, /=, and %=.
```


### • MCQ: Find the output

#### Problem statement:

What is the output of the following code?

```Java
public class Solution {

    public static void main(String args[]) {

        int num = 18;
        num *= 2;
        System.out.println(num);
    }
}
```
**Options:**

    1. 18
    2. 36 ➡ [Answer]
    3. 9
    4. Compile time error

```
Solution description:

In the above program, we are using a multiplication assignment operator. So that, num *= 2 means num = num * 2 = 36.
```


### • MCQ: Result of relational operators

#### Problem statement:

The result of a relational operator is always?

**Options:**

    1. int 
    2. boolean ➡ [Answer]
    3. float
    4. Compile Time Error

```
Solution description:

The result of a relational operator is always a boolean value. It either returns true or false.
```


### • MCQ: Operands in relational operators

#### Problem statement:

Minimum number of operands are required to use relational operators in Java?

**Options:**

    1. 2 ➡ [Answer]
    2. 3
    3. 1
    4. None of the above

```
Solution description:

There are 2 operands required to use relational operators.
```


### • MCQ: Find the output

#### Problem statement:

What is the output of the following code?

```Java
public class Solution {

    public static void main(String args[]) {

        char ch = 'a';

        if(ch > 70) {
            System.out.println("Coding Ninjas");
        }

        else {
            System.out.println("Ninjas");
        }
    }
}
```

**Options:**

    1. Coding Ninjas ➡ [Answer]
    2. Ninjas
    3. Compilation error
    4. Runtime error

```
Solution description:

The ASCII value of a is 97, which is greater than 70. So that it passes the condition and prints the message.
```


### • MCQ: Find the output

#### Problem statement:

What is the output of the following code?

```Java
public class Solution {

    public static void main(String args[]) {

        int a = 10;
        int b = 20;

        if(a != b) {
            System.out.println("Ninjas");
        }

        else {
            System.out.println("Coding Ninjas");
        }
    }
}
```

**Options:**

    1. Coding Ninjas
    2. Ninjas ➡ [Answer]
    3. Compilation error
    4. Runtime error

```
Solution description:

The value of a is not equal to b. Therefore the condition fails and an else block is executed and prints the result.
```


### • MCQ: Find the output

#### Problem statement:

What is the output of the following code?

```Java
public class Solution {

    public static void main(String arg[]) {
        int a = 10;
        int b = 20;
        int c = 30;

        System.out.println((b > a) && (c > b));  
    }
}
```

**Options:**

    1. true ➡ [Answer]
    2. false
    3. Compilation error
    4. Runtime error

```
Solution description:

Logical AND operator returns true if both the condition becomes true. In the above example, both the conditions are true. Therefore it returns true.
```


### • MCQ: Find the output

#### Problem statement:

What is the output of the following code?

```Java
public class Solution {

    public static void main(String arg[]) {
        int a = 10;
        int b = 20;
        int c = 30;

        System.out.println((b > a) || (c < b));  
    }
}
```

**Options:**

    1. true ➡ [Answer]
    2. false
    3. Compilation error
    4. Runtime error

```
Solution description:

Logical OR operator returns true if any one condition becomes true. In the above example, one condition is true and the other condition is false. Therefore it returns true.
```


### • MCQ: Find the output

#### Problem statement:

What is the output of the following code?

```Java
public class Solution {
    public static void main(String arg[]) {
        int a = 6;
        int b = 7;

        System.out.println("a | b = " + (a | b));
    }
}
```

**Options:**

    1. a | b = 7 ➡ [Answer]
    2. a | b = 6
    3. a | b = 0
    4. a | b = 9

```
Solution description:

Binary representation of 6 is 0110 and binary representation of 7 is 0111. This operator returns 1 if either one of the bits is 1. So after comparing every bit it returns 7 as an answer.
```


### • MCQ: Find the output

#### Problem statement:

What is the output of the following code?

```Java
public class Solution {
    public static void main(String args[]) {
        int a = 6;

        System.out.println(" ~a = " + ~a);
    }
}
```

**Options:**

    1. 9 
    2. 6    
    3. -7 ➡ [Answer]
    4. -8

```
Solution description:

In the above program, we are using the Bitwise complement operator. It simply inverts the bits. #### The binary representation of 6 is 000000000000000000000000000000110, after performing inverting the bits, it becomes 111111111111111111111111111111001. So the decimal representation would be -7.
```


### • MCQ: Find the output

#### Problem statement:

What is the output of the following code?

```Java
public class Solution {
    public static void main(String arg[]) {
        int a = 8;
        System.out.println("a >> 2 = " + (a >> 2));
    }
}
```

**Options:**

    1. a >> 2 = 8
    2. a >> 2 = 5 
    3. a >> 2 = 2 ➡ [Answer]
    4. a >> 2 = 7    

```
Solution description:

In the above program, we are using the Signed right shift operator (>>). This operator is used to shift the bits of the number to the right and fills 0 in the void spaces that are left as a result. The binary representation of 8 is 1 0 0 0, signed right shift removes the number of 0's to the right, therefore, it becomes 1 0, the decimal representation of 1 0 is 2.
```


### • MCQ: Guess the operator

#### Problem statement:

Which of the following operators is return 1 if the corresponding bits are different?

**Options:**

    1. Bitwise complement operator
    2. Unsigned right shift operator
    3. Signed right shift operator
    4. Bitwise Exclusive Or operator ➡ [Answer]

```
Solution description:

The Bitwise Exclusive Or operator is returned 1 if the corresponding bits are different.
```


### • MCQ: Other name of ternary operator

#### Problem statement:

Java ternary operator sometimes called?

**Options:**

    1. Relational operator
    2. Logical operator
    3. Conditional operator ➡ [Answer]
    4. None of the above

```
Solution description:

Java ternary operator is also called Conditional operator.
```


### • MCQ: Other name of ternary operator

#### Problem statement:

Java ternary operator sometimes called?

**Options:**

    1. Relational operator
    2. Logical operator
    3. Ternary operator ➡ [Answer]
    4. None of the above

```
Solution description:

The ternary operator is the only operator in java that takes three operands.
```


### • MCQ: Find the output

#### Problem statement:

What is the output of the following code?

```Java
public class Solution {
    public static void main(String argo[]) {
        int a = 50;
        int b = 100;
        int c = (a > b) ? a : b;

        System.out.println(c);
    }
} 
```

**Options:**

    1. 100 ➡ [Answer]
    2. 50
    3. Compilation error
    4. Runtime error  

```
Solution description:

The above program simply finds the maximum among three numbers. It first checks if a is greater than b or not, if yes then print a else print b.
```


### • MCQ: Use of ternary operator

#### Problem statement:

We can use ternary operator in place of?

**Options:**

    1. If else
    2. Switch statement
    3. Both ➡ [Answer]
    4. None of the above

```
Solution description:

We can use ternary operator in place of if else and switch statement.
```