# Taking input in Java 

## In Java, there are mainly two ways to get the input from the user:

1. **Using Scanner class**: **Scanner is a class in java that is used to take input from the user**. It is present in the **java.util package**. *Scanner class is one of the most preferable ways to take input from the user*. This class is *used to read the input of primitive types such as `int`, `double`, `long`, etc. and `String`*. *We need to import the java.util package before using the Scanner class*.

#### Methods of Scanner class in Java:

##### Java Scanner class provides various methods to read different primitive data types from the user.

![Java-Scanner-Class](https://harnarayanvishwakarma.wordpress.com/wp-content/uploads/2012/05/keywords1.png)


#### Example 1: Taking int value from the user

 
```Java
import java.util.Scanner;
class TakingInputFromUser {
     public static void main(String argo[]) {
 
            // Creating an object of Scanner class
            Scanner sc = new Scanner(System.in);
 
            // Read integer value from the user
            System.out.println(“Enter first number :”);
            int a = sc.nextInt();
 
            System.out.println(“Enter second number :”);
            int b = sc.nextInt();
 
            // Adding two values
            int c = a + b;
 
            // Printing the sum
            System.out.println(“Sum is : “ +c);
    }
}
```
```
Output:

Enter first number : 10
Enter second number : 20
Sum is : 30
```


#### Example 2: Taking String from the user

```Java
import.java.util.Scanner;
class TakingInputFromUser {
      public static void main(String arg[]) {
             Scanner sc = new Scanner(System.in);
             System.out.println(“Enter a String : “);
             // Read a string from the user
             String str = sc.nextLine();
             System.out.println(“Your entered string is : “ + str);
    }
}
```

```
Output:

Enter a String : Coding Ninjas
Your entered string is : Coding Ninjas
``` 
2. **Using BufferedReader class**
