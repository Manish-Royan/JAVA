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


### • MCQ: Local variable

#### Problem statement:

Which of the following variables is a local variable?

```Java
public class Example {
    int num = 10;
    float b = 10.9f;

    public void method() {
        int x = 20;
        int y = 30;
    }
}
```

**Options:**

    1. num
    2. x
    3. x and y both ➡ [Answer]
    4. b    

```
Solution description:

Local variables are the variables that are defined inside the method body, constructor, or a block. In the above code, 'x' and 'y' variables are defined inside the method body, therefore 'x' and 'y' both are the local variables.
```


### • MCQ: Class level variable

#### Problem statement:

Which of the following variables is a class level variable?

```Java
public class Example {
    int num = 10;
    float b = 10.9f;

    public void method() {
        int x = 20;
        int y = 30;
    }
}
```

**Options:**

    1. num
    2. num and b both ➡ [Answer]
    3. a and y both    
    4. b   

```
Solution description:

Class level variables are the variables that are defined inside the class but outside the method body. In the above code, the variables 'num' and 'b' both are declared inside the class and outside the method body. Therefore option '2' is the correct answer.
``` 


### • MCQ: Find the output

#### Problem statement:

What is the output of the following code?

```Java
public class Solution {
    static int x = 10;

    public static void main(String args[]) {
        System.out.println(x);
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

The variable 'x' is a static variable and it can easily accessed inside the static method without creating any object of the class.
```


### • MCQ: Types of type casting

#### Problem statement:

Which of the following Type Casting is available in Java?

**Options:**

    1. Widening Type Casting
    2. Narrowing Type Casting
    3. Both a and b ➡ [Answer]
    4. None of the above

    
```
Solution description:

In Java, both widening and narrowing type casting are available.
```


### • MCQ: Type casting losing data

#### Problem statement:

Which of the following Type Casting is responsible for loss of data?


**Options:**

    1. Narrowing Type Casting ➡ [Answer]
    2. Widening Type Casting
    3. No Type Conversion
    4. None of the above

```
Solution description:

In narrowing type casting, we assign a value of large data type into a small data type. So, there may be a chance of losing the data.
```


### • MCQ: Find the output

#### Problem statement:

What is the output of the following java code?

public class Example {
    public static void main(String args[]) {
        char ch = 'a';
        int num = 10;
        ch = num;
        System.out.println(ch);
    }
}

**Options:**

    1. 10
    2. 0
    3. compile time error ➡ [Answer]
    4. None of the above

```
Solution description:

In Java, char and boolean data types are not compatible with numeric data types. Therefore, when we cast char with num, it gives the compile-time error.
```


### • MCQ: True or False

#### Problem statement:

Is type casting possible from boolean to char?

**Options:**

    1. Yes ➡ [Answer]
    2. No

```
Solution description:

In Java char and boolean data types are not compatible with each other. Therefore type casting is not possible between them.
```


### • MCQ: Overflow in java

#### Problem statement:

Which of the following is true about overflow?

**Options:**

    1. Overflow in java happens when we assign a value to a variable which is more than its range ➡ [Answer]
    2. Overflow in java happens when we assign a value to a variable which is less than its range
    3. Both of the above
    4. None of the above

```
Solution description:

for example, the maximum value of int data type is 2,147,483,647 (Integer.MAX_VALUE) and after incrementing 1 on this value, it will return -2,147,483,648 (Integer.MIN_VALUE). This is known as overflow.

```


### • MCQ: Find the output

#### Problem statement:

Problem statement
What is the output of the following code?

public class Solution {
    public static void main(String args[]) {

        int x = -2147483648;
        System.out.println(x - 1);
    }
}

**Options:**

    1. 2147483647 ➡ [Answer]
    2. -2147483647
    3. Error
    4. None

```
Solution description:

The above program is the example of underflow in java.
```


### • MCQ: True or False

#### Problem statement:

In case of overflow and underflow, does the Java compiler throw any error?

**Options:**

    1. True
    2. False ➡ [Answer]

```
Solution description:

In case of overflow and underflow, the Java compiler doesn't throw any error. It simply changes the value.
```
