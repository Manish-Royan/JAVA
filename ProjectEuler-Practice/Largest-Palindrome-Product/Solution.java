public class Solution {
    public static void main(String[] args) {
        int largestPalindrome = 0;
        int factor1 = 0;
        int factor2 = 0;

        for (int i = 999; i >= 100; i--) {
            for (int j = i; j >= 100; j--) {
                int product = i * j;
                if (isPalindrome(product) && product > largestPalindrome) {
                    largestPalindrome = product;
                    factor1 = i;
                    factor2 = j;
                }
            }
        }

        System.out.println("The largest palindrome made from the product of two 3-digit numbers is: " + largestPalindrome);
        System.out.println("The factors are: " + factor1 + " and " + factor2);
    }

    public static boolean isPalindrome(int number) {
        int original = number;
        int reversed = 0;

        while (number != 0) {
            int digit = number % 10;
            reversed = reversed * 10 + digit;
            number /= 10;
        }

        return original == reversed;
    }
}