# Comments in Java

Comments are statements that are not executed by the compiler. Comments make the program more human-readable by including the details of the code involved. The proper use of comments makes maintenance and debugging of the code easier.


## Types of Comments
 
### There are three types of comments in java

    * Single line comments
    * Multi-line comments
    * Documentation comments
 

* **Single line comment**: The single line comment is used to comment only one line. A beginner-level programmer uses mostly single-line comments for describing the code functionality. 

```
Syntax: 

// write your comment here
```


*Example:*

```Java 

// Java program to show single line comment
public class HelloWorld {
    public static void main(String args[]) {

        // Print "Hello World" on console
        System.out.println("Hello World");
    }
}
```

* **Multi-line comments**: The multi-line comments are used to comment multiple lines of code.
 
```
Syntax: 

/*

This 
is
multiline
comment

*/
``` 

*Example:*
 
```Java
public class HelloWorld {
    public static void main(String args[]) {

       /*

       Here, we have
       declared a 
       variable and we are
       printing its value

       */
       int a = 10;
       System.out.println(a);
    }
}
```

* **Documentation Comments**: This type of comment is used generally when we write the code for projects. It helps to generate a documentation page for reference, which can be used for getting information about methods present, their parameters, etc.
 
```
Syntax:

/**Comment start
*
*tags are used in order to specify a parameter
*or method or heading.
*HTML tags can also be used 
*such as <h1>
*
*comment ends*/
```

*Example:*
 
```Java
// Java program to illustrate documentation comments 
/**
 * Find product of four numbers! The FindPro program implements an application
 * that simply calculates product of four integers and Prints the output on the
 * screen.
 * 
 * @author Prashant Srivastava
 * @version 1.0
 * @since 2021-02-26
 */
public class FindPro {
    /**
     * This method is used to find the product of four integers.
     * 
     * @param num1 This is the first parameter to FindPro method
     * @param num2 This is the second parameter to FindPro method
     * @param num3 This is the third parameter to FindPro method
     * @param num4 This is the fourth parameter to FindPro method
     * @return int This returns average of numA, numB and numC.
     */
    public int FindPro(int num1, int num2, int num3, int num4) {
        return (num1 * num2 * num3 * num4);
    }

    /**
     * This is the main method which makes use of the FindPro method.
     * 
     * @param args Unused.
     * @return Nothing.
     */

    public static void main(String args[]) {
        FindPro obj = new FindPro();
        int pro = obj.FindPro(10, 20, 30, 40);

        System.out.println("Product of 10, 20, 30 and 40 is : " + pro);
    }
}
```
