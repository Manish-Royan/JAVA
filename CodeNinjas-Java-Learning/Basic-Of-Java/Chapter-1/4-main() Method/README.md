# main() Method

A simple program to print Hello World, The main() method in java is :

```Java
public static void main(String args[]).
public class HelloWorld {
    public static void main(String args[]) {
        System.out.println("Hello World");
    }
}
```
 

* **public**: *The public is an **access modifier*** that can be used to specify who can access this main() method. It simply defines the visibility of the method. **The JVM calls the main() method outside the class**. Therefore **it is necessary to make the java main() method as public**.
 
* **static**: **Static is a keyword in java**. We can make static variables and static methods in java with the help of the static keyword. *The main advantage of a static method is that we can call this method without creating an instance of the class*. JVM calls the main() method without creating an instance of the class, therefore *it is necessary to make the java main() method static*.
 
* **void**: Void is a return type of method. *The java main() method doesn’t return any value*. Therefore, it is necessary to have a void return type.
 
* **main**: **Main is the name of the method**. It is a method where program execution starts.
 
* **String args[]**: **String in java is a class that is used to work on Strings and args is a reference variable that refers to an array of type String**. If you want to pass the argument through the command line then it is necessary to make the argument of the main() method as String args[].
