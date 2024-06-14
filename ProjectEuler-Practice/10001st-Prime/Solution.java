public class Solution {
    public static void main(String[] args) {
        int count = 0; // Count of found primes
        int number = 1; // Number to be tested for primality
        int nthPrime = 10001; // We are looking for the 10,001st prime
        
        while (count < nthPrime) {
            number++;
            if (isPrime(number)) {
                count++;
            }
        }
        
        System.out.println("The " + nthPrime + "st prime number is: " + number);
    }

    
    // Method to check if a number is prime
    public static boolean isPrime(int num) {
        if (num <= 1) {
            return false;
        }
        if (num <= 3) {
            return true;
        }
        if (num % 2 == 0 || num % 3 == 0) {
            return false;
        }
        for (int i = 5; i * i <= num; i += 6) {
            if (num % i == 0 || num % (i + 2) == 0) {
                return false;
            }
        }
        return true;
    }
}