# # Challenge 8 - Java DataType

Java has 8 primitive data types, but this exercise focuses on 4 used for integer values: `byte`, `short`, `int`, and `long`.

### Descriptions of these types:

* `byte`: 8-bit signed integer
* `short`: 16-bit signed integer
* `int`: 32-bit signed integer
* `long`: 64-bit signed integer

## *@Challenge*
The task is to determine which of these data types can properly store a given input integer.


### Input Format:

* First line: an integer `T` (number of test cases)

* Each subsequent line: an integer `n` (can be arbitrarily large or small)


### Sample Input
    5
    -150
    150000
    1500000000
    213333333333333333333333333333333333
    -1000000000000000



### Explaination:
-150 can be stored in a `short`, an `int`, or a `long`.

*213333333333333333333333333333333333* is very large and is outside of the allowable range of values for the primitive data types discussed in this problem.

### Output Format:

* **For each input `n`, determine which primitive data types can store it, if yes then -**

* **Print the appropriate data types that can store the value.**

If there is more than one appropriate data type, print each one on its own line and order them by size (i.e.: `byte` < `short` < `int` < `long`).

If the number cannot be stored in one of the four aforementioned primitives, print the line:
*`n can't be fitted anywhere`*.

### Sample Output:
    -150 can be fitted in:
    * short
    * int
    * long
    150000 can be fitted in:
    * int
    * long
    1500000000 can be fitted in:
    * int
    * long
    213333333333333333333333333333333333 can't be fitted anywhere.
    -1000000000000000 can be fitted in:
    * long