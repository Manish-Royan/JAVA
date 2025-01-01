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
