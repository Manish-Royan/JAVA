### • Problem: Find Area of Rectangle

#### Problem statement:

Take the length `L` and breadth `B` of the rectangle as input and find its area.

    Note:
    Length and breadth must be an integer value and the area will always be in the range of integers.

```Java
import java.util.Scanner;

class Solution {
    public static void main(String args[]) {
        //delcaring variable
        int L, B, area;

        //scanning user input
        Scanner sc = new Scanner(System.in);
        L = sc.nextInt();
        B = sc.nextInt();

        //implementing formula logic
        area = L * B;

        //printing output
		System.out.println(area);
    }
}
```


### • Problem: Print Name and age

#### Problem statement:

Take the person's `name` and `age` as input and print out the name and age as specified in the output format.

```Java
import java.util.Scanner;
class Solution {
	
	public static void main(String args[]) {
		//declaring variable
		String name;
		int age;

		//scanning user input
		Scanner sc = new Scanner(System.in);
		name = sc.nextLine();
		age = sc.nextInt();

        //printing output
		System.out.print("The name of the person is "+ name + " and the age is "+ age +"." );
	}
}
```


### • Problem: Swap Two Numbers

#### Problem statement:

You are given two numbers `a` and `b` as input. You must swap the values of `a` and `b`.

```Java
public class Solution {
    public static void swapNumber(int[] a, int[] b) {
        // Use XOR swapping algorithm
        a[0] = a[0] ^ b[0];
        b[0] = a[0] ^ b[0];
        a[0] = a[0] ^ b[0];
    }

    public static void main(String[] args) {
        int[] a = {8};
        int[] b = {5};

        System.out.println("Before swapping:");
        System.out.println("a = " + a[0] + ", b = " + b[0]);

        swapNumber(a, b);

        System.out.println("After swapping:");
        System.out.println("a = " + a[0] + ", b = " + b[0]);
    }
}
```


### • Problem: Calculate Simple Interest

#### Problem statement:
Take the principal amount, rate of interest, and the time period as input and calculate the Simple Interest.

    Note: Return answer as Floor integer value.

```Java
class Solution {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Input principal amount
        double principal = scanner.nextDouble();

        // Input rate of interest
        double rate = scanner.nextDouble();

        // Input time period
        double time = scanner.nextDouble();

        // Calculate Simple Interest
        double simpleInterest = (principal * rate * time) / 100;

        // Round down to nearest integer using Math.floor()
        int result = (int) Math.floor(simpleInterest);

        // Print the result
        System.out.println(result);

        scanner.close();
    }
}
```