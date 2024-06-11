public class Solution {
    public static void main(String[] args) {
        long lcm = 1;
        for (int i = 1; i <= 20; i++) {
            lcm = lcm(lcm, i);
        }
        System.out.println("The smallest positive number that is evenly divisible by all of the numbers from 1 to 20 is: " + lcm);
    }

    public static long gcd(long a, long b) {
        while (b != 0) {
            long temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }

    public static long lcm(long a, long b) {
        return a * (b / gcd(a, b));
    }
}
