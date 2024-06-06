public class Solution {
    public static void main(String[] args) {
        long number = 600851475143L;
        long largestPrimeFactor = 1;

        // Start with the smallest prime factor
        long factor = 2;
        
        while (factor * factor <= number) {
            if (number % factor == 0) {
                largestPrimeFactor = factor;
                number /= factor;
            } else {
                factor++;
            }
        }
        
        // If there's any factor left larger than sqrt(number)
        if (number > largestPrimeFactor) {
            largestPrimeFactor = number;
        }
        
        System.out.println("The largest prime factor of 600851475143 is: " + largestPrimeFactor);
    }
}