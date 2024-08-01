# # Challenge 14 - Java String Introduction

A string is traditionally a sequence of characters, either as a literal constant or as some kind of variable.

#### Given: 
    Two strings A and B, consisting of lowercase English letters.

## *@Challenge*
*  Calculate and sum the lengths of strings `A` and `B`.
*  Compare `A` and `B` lexicographically (alphabetical order).
*  Capitalize the first letter of both `A` and `B` and display them.   

## Input Format

Two lines, each containing one string (`A` and `B` respectively).

## Sample Input
    hello
    java

## Constraints

0 <= payment <=10^9

## Output Format
* Line 1: Sum of the lengths of `A` and `B`.
* Line 2: "*`Yes`*" if A comes after B alphabetically, "*`No`*" otherwise.
* Line 3: `A` and `B` with their first letters capitalized, sep arated by a space.

## Sample Output
    9
    No
    Hello Java

## Explanation

String `A` is "*`hello`*" and `B` is "*`java`*".

* Length calculation:

    A has a length of 5
    B has a length of 4
    The sum of their lengths is 9 (5 + 4 = 9)

* Lexicographical comparison:

When sorted alphabetically, "*`hello`*" comes before "*`java`*"

Therefore, `A` is not greater than `B` lexicographically so, The answer is "*`No`*"

* Capitalization:

Capitalizing the first letter of both `A` and `B` results in "*`Hello Java`*"

This explanation clarifies how the sample output was derived from the sample input, following the rules specified in the problem statement.