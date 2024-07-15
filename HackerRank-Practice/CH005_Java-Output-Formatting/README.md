# # Challenge 5 - Java Output Formatting

Java's `System.out.printf` function can be used to print formatted output. The purpose of this exercise is to test the understanding of formatting output using `printf`.

## *@Challenge*

Program must reads a series of strings and integers from user input, and then prints them in a formatted manner where each string is left-justified within a 15-character field and each integer is displayed as a 3-digit number with leading zeros if necessary.

### Input Format

Every line of input will contain a String followed by an integer means one column with **String** and another with **Intger** value. 

Each **String** will have a maximum of `10` alphabetic characters, and each **integer** will be in the inclusive range from `0` to `999`, which means **String** will have a maximum of `10` alphabetic characters which integer must be less than `1000` .

#### Sample Input

    java 100
    cpp 65
    python 50


### Output Format

In each line of output there should be two columns:

The first column contains the String and is left justified using exactly `15` characters.

The second column contains the integer, expressed in exactly `3` digits; if the original input has less than three digits, we must paded output's leading digits with zeroes `0`.

#### Sample Output

    ================================
    java           100 
    cpp            065 
    python         050 
    ================================


## Explanation
    Each String is left-justified with trailing whitespace through the first  characters means we have to give `%-15s` left-justified. The leading digit of the integer is the  character, and each integer that was less than  digits now has leading zeroes means if value if less than 3 digit than it have to converted into 3 digit with leading zero by `%03d` - zero will assign in front of two/lessthan two integer number.