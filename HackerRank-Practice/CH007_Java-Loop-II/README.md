# # Challenge 7 - Java Loops II

## *@Challenge*
The task involves creating a series using integers `a`, `b`, and `n`.

The series is defined as: ***`(a + 2^0 * b), (a + 2^0 * b + 2^1 * b), ..., (a + 2^0 * b + 2^1 * b + ... + 2^(n-1) * b)`***

### Constraints:

    0 ≤ q ≤ 500

    0 ≤ a, b ≤ 50

    1 ≤ n ≤ 15

### Input Format:

* First line: an integer q (denoting number of queries).

* Next `q` lines: three space-separated integers `a`, `b`, and `n` for each query.

### Sample Input

    2
    0 2 10
    5 3 5



### Explaination:
* **First query**: *a* = *0*, *b* = *2*, *n* = *10*

    `s₀` = 0 + 1 * 2 = 2

    `s₁` = 0 + 1 * 2 + 2 * 2 = 6

    `s₂` = 0 + 1 * 2 + 2 * 2 + 4 * 2 = 14

    **This continues until 10 terms are calculated.**

    **The result is printed as a single line of 10 space-separated integers.**

 ```
 Mathematically: 
s₀ = (5 + 2^0 * 3) → (5 + 1 * 3) //because {2^0 isEquals to 1}.
s₁ = (5 + 1 * 3 + 2 * 3) //the same first series 's₀ {which is (5 + 1 * 3)}' is repeated on second `s₁`. 
s₂ = (5 + 1 * 3 + 2 * 3 + 4 * 3) //the same second series 's₁ {which is (5 + 1 * 3 + 2 * 3)}' is repeated on third `s₂` and so on.     
 ```
* **Second query**: *a* = *5*, *b* = *3*, *n* = *5*

    `s₀` = 5 + 1 * 3 = 8 **{meaning `s₀` = `(a + 2^0 * b)`}**

    `s₁` = 5 + 1 * 3 + 2 * 3 = 14 **{meaning `s₁` = `(a + 2^0 * b + 2^1 * b)`}**

    `s₂` = 5 + 1 * 3 + 2 * 3 + 4 * 3 = 26 **{and so on....`(a + 2^0 * b + 2^1 * b + ... + 2^(n-1) * b)`}**

    `s₃` = 5 + 1 * 3 + 2 * 3 + 4 * 3 + 8 * 3 = 50

    `s₄` = 5 + 1 * 3 + 2 * 3 + 4 * 3 + 8 * 3 + 16 * 3 = 98

    **The result is printed as a single line of 5 space-separated integers**
    
```
# Hence, we won't repet the series calculation on another series, meaning:

    `s₀` = (a + 2^0 * b)

    `s₁` = (s₀ + 2^1 * b) // we will add the previous series directly to another series.

    `s₂` = (s₁ + 2^2 * b) //the power of `2^(n-1) * b` increase as number of series continues.

→ We'll stored the previous solved series in a variable and then it pass to another series.
```
    



### Output Format:

**For each query, print the corresponding series on a new line.**

**Each series should be printed as `n` space-separated integers.**

### Sample Output
    2 6 14 30 62 126 254 510 1022 2046
    8 14 26 50 98

* First Query (n=10):  2 6 14 30 62 126 254 510 1022 2046

* Second Query (n = 5):  8 14 26 50 98