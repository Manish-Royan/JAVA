# Java Stdin and Stdout I

Most HackerRank challenges require to read input from `stdin`**(standard input)** and write output to `stdout` **(standard output)**.

In computer programming, standard streams are preconnected input and output communication channels between a computer program and its environment when it begins execution. 

The three input/output (I/O) connections are called **standard input** `(stdin)`, **standard output** `(stdout)` and **standard error** `(stderr)`.

One popular way to read input from `stdin` is by using the `Scanner class` and specifying the Input Stream as `System.in`

##  Example
```Java
Scanner scanner = new Scanner(System.in);
String myString = scanner.next();
int myInt = scanner.nextInt();
scanner.close();

System.out.println("myString is: " + myString);
System.out.println("myInt is: " + myInt);
```

### Explaination
The code above creates a Scanner object named *scanner*  and uses it to read a String and an int. It then closes the Scanner object because there is no more input to read, and prints to stdout using `System.out.println`(String).

## *@Challenge*

In this challenge, you must read  integers from `stdin` and then print them to `stdout`. Each integer must be printed on a new line. 

