### • MCQ: Invalid variable name

#### Problem statement:

Which of the following is invalid variable naming convention?

**Options:**

    1. int x = 10;
    2. int $x = 10;
    3. int 1x = 10 ➡ [Answer]
    4. int _x = 10;

```
Solution description:

In Java, a variable name can start with alphabets, underscore (_), and dollar ($) sign and in the option '3' variable name starts with a number that is invalid
```


### • MCQ: Valid variable name

#### Problem statement:

Which of the following is a valid variable naming convention?

**Options:**

    1. int 1x = 10;
    2. int x = 10; ➡ [Answer]
    3. int 5number = 10;
    4. int %x = 10;

```
Solution description:

In Java, a variable name can start with alphabets, underscore (_), and dollar ($) sign and in the option '2' variable name starts with an alphabet therefore it is a valid answer
```


### • MCQ: Largest decimal number

#### Problem statement:

Which data type stores the largest decimal number?

**Options:**

    1. long
    2. float
    3. double ➡ [Answer]
    4. short

```
Solution description:

In Java, there are only two data types that can hold the decimal number. These are float and double, but float can hold the decimal digits upto 7 and double can hold the decimal digits up to 16. Therefore the correct answer is 'double'.
```


### • MCQ: Default int value

#### Problem statement:

What is the default value of int data type?

**Options:**

    1. 2
    2. 1
    3. 0 ➡ [Answer]
    4. 0.0

```
Solution description:

The default value of int in Java is 0.
```


### • MCQ: Default boolean value

#### Problem statement:

What is the default value of boolean?


**Options:**

    1. true
    2. false ➡ [Answer]

```
Solution description:

The default value of boolean in Java is false.
```


### • MCQ: Non-primitive data type

#### Problem statement:

Which data type is considered as a non-primitive data type?

**Options:**

    1. int
    2. long
    3. float
    4. class ➡ [Answer]


```
Solution description:

n Java, there are two types of data types. These are primitive data types and Non-Primitive data types. Int,long, float,double, etc are the primitive data types and Arrays, String, and class are non-primitive data types. Therefore the correct answer is 'class'.
```


### • MCQ: True or False

#### Problem statement:

Can we access the variables outside the method body which are defined inside the method body?

**Options:**

    1. Yes
    2. No ➡ [Answer]

```
Solution description:

Variables declared inside a method have a method level scope, and cannot be accessed outside the method body.
```


### • MCQ: Find the output 1

#### Problem statement:

What is the output of the following code?

```Java
public class Solution {
    public void display() {
        int x = 10;
    }

    public static void main(String args[]) {
        System.out.println(x);
    }
}
```

**Options:**

    1. 10
    2. 0
    3. Compile Time Error ➡ [Answer]
    4. Runtime Error

```
Solution description:

The variable 'x' has a method level scope and we can not access it outside the method body.
```


### • MCQ: Find the output 2

#### Problem statement:

What is the output of the following code?

```Java
public class Solution {
    int x = 10; 

    public static void main(String args[]) {
        Solution obj = new Solution();
        System.out.println(obj.x);
    }
}
```

**Options:**

    1. 10 ➡ [Answer]
    2. 0
    3. Compile Time Error 
    4. Runtime Error


```
Solution description:

'x' has a class-level scope and hence can be accessed with the object reference.
```


### • MCQ: Find the output 3

#### Problem statement:

What is the output of the following code?

```Java
public class Solution {

    public static void main(String args[]) {

        int x;
        x = 10;
        {
            int y = 20;
            System.out.print(x + " " + y);
        }

        System.out.print(x + " " + y);
    }

}

```
**Options:**

    1. 10 
    2. 0
    3. Compile Time Error ➡ [Answer]
    4. Runtime Error

```
Solution description:

The second print statement doesn't have access to 'y', because the scope of the 'y' is limited to block only.
```
