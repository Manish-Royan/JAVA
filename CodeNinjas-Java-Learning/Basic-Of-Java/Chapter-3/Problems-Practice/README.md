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

