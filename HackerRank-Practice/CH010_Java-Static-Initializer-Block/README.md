# # Challenge 10 - Java Static Initializer Block

Static initialization blocks are executed when the class is loaded, and you can initialize static variables in those blocks.

## *@Challenge*

The goal is to calculate and output the area of a parallelogram using two input values: breadth `B` and height `H`. The challenge likely involves using a static initialization block to read the input, perform the necessary checks, and set up the calculation or exception handling.

### Constraints:

Both `B` and `H` can range from -100 to 100

    100 ≤ B ≤ 100
    -100 ≤ H ≤ 100

### Input Format:

* Two lines of input from standard input (stdin)
* First line: `B` (breadth of parallelogram)
* Second line: `H` (height of parallelogram)

### Sample Input 1 
    1
    3


### Sample input 2
    -1
     2

    
### Output Format:

* If `B` > 0 and `H` > 0: Output the calculated area of the parallelogram
* If `B` ≤ 0 or `H` ≤ 0: Output the exact string *`"java.lang.Exception: Breadth and height must be positive"`* (without quotes)

### Sample Output 1:
    3

### Sample output 2
    java.lang.Exception: Breadth and height must be positive