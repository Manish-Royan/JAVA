# # Challenge 12 - Java Date and Time

The `Calendar` class is an abstract class that provides methods for converting between a specific instant in time and a set of calendar fields such as *`YEAR`*, *`MONTH`*, *`DAY_OF_MONTH`*, *`HOUR`*, and so on, and for manipulating the calendar fields, such as **getting the date** of the **next week**x.


## *@Challenge*
We are given a date. We just need to write the method, , which returns the day on that date. 

### Function Description

`findDay` has the following parameters:

* `int` : `month`
* `int` : `day`
* `int` : `year`

It should returns `string` : *the day of the week in **capital letters***.

### Input Format:
A single line of input containing the space separated *month*, *day* and *year*, respectively, in **MM** **DD** **YYYY** format.

### Constraints:

*2000 < year < 3000*


### Sample Input:

   08 05 2015

    
### Sample Format:

    WEDNESDAY