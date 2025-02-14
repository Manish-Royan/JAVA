# COMMAND-LINE ARGUMENT in JAVA

Java command-line argument is an argument *i.e. passed at the time of running the Java program*. In Java, **the command line arguments passed from the console** can be received in the Java program and **they can be used as input**. The users can **pass the arguments during the execution** **bypassing the command-line arguments inside the main() method**.

## ▸ Simple example of command-line argument in java
In this example, we are receiving only one argument and printing it. To run this java program, you must pass at least one argument from the command prompt.

```JAVA
class CLA{  
public static void main(String args[]){  
System.out.println("Your first argument is: "+args[0]);  
    }  
}  
```

### » How to run this program❓
If you save this file as CLA.java, compile and run it as follows:

```
javac CLA.java
java CLA Hello
```

* First, we compile source by (**javac CLA.java**). The **javac** invoke the Java-complier to complie the **.java** file and after compiling, complier creates coressponding **.class** file which total length of instruction is 8bits (4bits+4bits) which is eqivalent to 1Byte that's why **.class** file is also know as **byte code**. If there is syntax error the source code won't be complie (*compile time error*) and throw exception and no **.classs** will be created by complier.

* Second, we run code by (**java CLA Hello**). The **java** command is *Java program luncher* which command to execute the program. **CLA** is **.class** file of **CLA.java**, we don't need to write **CLA.class** because **JVM** dynamically loads and recogonize **.class** file. ***"Hello"*** is passed as argument, it is stored at index value [0] in *args* array. Anything written after **.class** file with space is considered as input or first commmand-line argument. Since ***args[]*** datatype is **String** in *main method* **anything written even integer value will considered as String argument**. If there is an error then terminal will throw **run-time-error**.
 
```
Output: Your first argument is: Hello
```
### 👉 Here, args[0] = "Hello", so it prints *Hello*.


## ▸ How does it work❓
* ***args*** is an array of strings **(String[] args)**, which stores command-line arguments passed to the program.
* ***args[0]*** refers to the first argument provided when running the program.
* **System.out.print(args[0]);** prints the first argument to the console.

## ▸ When do we use such a program❓
1. **Command-line input handling**: When we need to pass input directly from the terminal instead of taking input from the user at runtime using Scanner or BufferedReader.

2. **Script-like execution**: Useful in automation or batch processing where we pass arguments dynamically while running the program.

3. **Configurable program execution**: Instead of hardcoding values in the code, we can pass them as arguments, making the program more flexible.

## ▸ Running this program on IDE will throw exception ❌
⇛ Yes, when you run this code in an IDE without passing any command-line arguments, it will throw the following exception:

```
Exception in thread "main" java.lang.ArrayIndexOutOfBoundsException: Index 0 out of bounds for length 0
```

### 👉 Because it was looking something in array that wasn't there.


### » Why does this happen?
* ***args*** is an array **(String[] args)** that holds command-line arguments.
* When you run the program without passing any arguments, ***args*** is an empty array **(args.length == 0)**.
* ***args[0]*** tries to access the first element, but since there are no elements, it causes an **ArrayIndexOutOfBoundsException**.

### » How to Fix It?
### ⛔ To avoid the error, you need to pass arguments when running the program:

➡️ In IntelliJ IDEA
- Go to Run → Edit Configurations
- In the Program arguments field, enter a value (e.g., Hello)
- Click Apply → OK and run the program

➡️ In Eclipse
-Go to Run → Run Configurations
- Select Arguments tab
- Enter a value (e.g., Hello) in the Program Arguments section
- Click Run

## ▸ Let Suppose different scenarios: 

### # Program 1: With index value set [0] in parameter.

```JAVA
class A{  
public static void main(String args[]){  
System.out.println(args[0]);  
    }  
}  
```

```
compile by> javac A.java
run by> java A Hello User
output: Hello
```
### 👉 Here, args[0] = "Hello", args[1] = "User"; so it prints *Hello*.

⇛ This program will print *'Hello'* instead of *'Hello User* since we pass parameter set to print *args[0]*, array's zero index value which is *'Hello'* will be printed.  


### # Program 2: With index value set [1] in parameter.

```JAVA
class A{  
public static void main(String args[]){  
System.out.println(args[1]);  
    }  
}  
```

```
compile by> javac A.java
run by> java A Hello User
output: User
```
### 👉 Here, args[0] = "Hello", args[1] = "User"; so it prints *User*.

⇛ This program will print *'User'* instead of *'Hello User* since we pass parameter set to print *args[1]* array's one index value which is *'User'* will be printed.

### # Program 3: What happens if no argument is given❓

```JAVA
class A{  
public static void main(String args[]){  
System.out.println(args[0]);  
    }  
}  
```

```
compile by> javac A.java
run by> java A 
```
⇛ It will throw **ArrayIndexOutOfBoundsException** because args[0] does not exist.

### ⛔ To prevent this, we should check if the array has arguments before accessing them:

```JAVA
class A {
    public static void main(String[] args) {
        if (args.length > 0) {
            System.out.println(args[0]);
        } else {
            System.out.println("No command-line arguments provided.");
        }
    }
}
```
```
compile by> javac A.java
run by> java A 
output: No command-line arguments provided.
```

### # Program 4: What if we want to print multiple value❓
⇛ We will traversed the array using loop.

```JAVA
class A {
    public static void main(String[] args) {
        if (args.length > 0) {
            for(int i = 0; i< args.length; i++) {
                System.out.println(args[i]);
            }
        } else {
            System.out.println("No command-line arguments provided.");
        }
    }
}
```
```
compile by> javac A.java
run by> java A You are doing great
output: You
        are 
        doing 
        great
```

### # Program 5: What if we want to sum given integer argument❓
⇛ We will use *wrapper class* by **Integer.parseInt(args[i])**, since *args* datatype is **String** and if we try to add those given argument it will be concatenate.

```JAVA
class Main {
    public static void main(String[] args) {
        int sum = 0;

        if (args.length > 0) {
            for(int i = 0; i< args.length; i++) {
                sum = sum + Integer.parseInt(args[i]);
                System.out.println("Sum of arguments is: "+ sum);
            }
        } else {
            System.out.println("No command-line arguments provided.");
        }
    }
}
```
```
compile by> javac Main.java
run by> java Main 1 2 3
output: Sum of arguments is: 6
```