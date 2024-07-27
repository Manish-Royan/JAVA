# # Challenge 13 - Java Currency Formatter

Given a double-precision number, `payment`, denoting an amount of money, use the NumberFormat class' getCurrencyInstance method to convert `payment` into the US, Indian, Chinese, and French currency formats. Then print the formatted values as follows:

    US: formattedPayment
    India: formattedPayment
    China: formattedPayment
    France: formattedPayment

where `formattedPayment` is `payment` formatted according to the appropriate `Locale's` currency.


    Note: India does not have a built-in Locale, so you must construct one where the language is en (i.e., English).

## *@Challenge*
* We need to work with a double-precision number called `payment` representing an amount of money.
* We should use the `NumberFormat` class's `getCurrencyInstance` method to convert `payment` into US, Indian, Chinese, and French currency formats.
* The formatted values should be printed in a specific format for each country.
* For India, we need to construct a custom Locale with English as the language.
* The input is a single double-precision number (payment) between 0 and 10^9.
* The output should be formatted as specified, with each country's currency on a separate line.


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