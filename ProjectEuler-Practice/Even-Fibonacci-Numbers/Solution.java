public class Solution {
    public static void main(String[] args) {
        int limit = 4000000;
        int sum = 0;
        
        int x = 1; // First term in Fibonacci sequence
        int y = 2; // Second term in Fibonacci sequence
        
        while (x <= limit) {
            if (x % 2 == 0) {
                sum += x;
            }
            int next = x + y;
            x = y;
            y = next;
        }
        
        System.out.println("The sum of the even-valued terms in the Fibonacci sequence not exceeding four million is: " + sum);
    }
}