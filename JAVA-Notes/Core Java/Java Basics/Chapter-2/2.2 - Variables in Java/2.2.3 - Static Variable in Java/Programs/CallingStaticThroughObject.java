
public class CallingStaticThroughObject {
	int a = 10;
	static int b = 15;
	static int c = 20;
	public static void main(String[] args) {
		CallingStaticThroughObject obj = new CallingStaticThroughObject();
		
		System.out.println(obj.a);
		
		//Directly Calling
		System.out.println(b); //we can directly call static varible inside a static method (Since, main method is static)
		
		//Calling by className
		System.out.println(CallingStaticThroughObject.c); //calling static variable by className
		
		//Calling static variable b objectName
		System.out.println(obj.c); //not prefered
		
	}
}