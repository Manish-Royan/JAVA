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


### • MCQ: Find the output 1

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


### • MCQ: Find the output 2

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


### • MCQ:  Types of decrement operators

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


### • MCQ: Find the output 1

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


### • MCQ: Find the output 2

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


### • MCQ: Find the output 3

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
