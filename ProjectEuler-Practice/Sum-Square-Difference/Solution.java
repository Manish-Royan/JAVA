public class Solution {
    public static void main(String[] args) {
        int n = 100;

        // Calculate the sum of the squares
        long sumOfSquares = (n * (n + 1) * (2 * n + 1)) / 6;

        // Calculate the square of the sum
        long sum = (n * (n + 1)) / 2;
        long squareOfSum = sum * sum;

        // Find the difference
        long difference = squareOfSum - sumOfSquares;

        System.out.println("The difference between the sum of the squares and the square of the sum of the first " + n + " natural numbers is: " + difference);
    }
}
