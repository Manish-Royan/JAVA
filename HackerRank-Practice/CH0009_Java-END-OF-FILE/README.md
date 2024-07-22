# # Challenge 9 - Java END-OF-FILE

In computing, End Of File (commonly abbreviated `EOF`) is a condition in a computer operating system where no more data can be read from a data source.

## *@Challenge*

The challenge here is to read `n` lines of input until you reach `EOF`, then number and print all `n` lines of content.

* The task is to read unknown `n` number of input lines until EOF is reached.
* After reading, the program should number and print all the lines of content.
* Input is read from stdin (System.in) and each line contains a non-empty string.
* Output should display the line number, followed by a space, then the line content.
* A hint suggests using Java's `Scanner.hasNext()` method to solve the problem.

### Input Format:

Read some unknown `n` lines of input from stdin(System.in) until you reach EOF; each line of input contains a non-empty String.

### Sample Input
    Hello world
    I am a file
    Read me until end-of-file.



### Explaination:
The program should reads input lines continuously from the standard input (typically the console) until there is no more input (EOF). It should prints each line preceded by its line number. The line numbering starts at *1* and increments with each new line.

The output format is achieved by the `while loop`, which reads each line of input, increments the line number, and prints the line number followed by the line content.
    
### Output Format:

For each line, print the line number, followed by a single space, and then the line content received as input.

### Sample Output:
    1 Hello world
    2 I am a file
    3 Read me until end-of-file.