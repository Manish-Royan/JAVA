class count {
	private static int  count; //comman for all object. static variable reference to static method only.
	
	
	//Staitc block - it will execute first
	static {
		System.out.println("This is static block"); //it will be called only one time
	}
	
	//Constructor
	count()
	{
		System.out.println("This is constructor"); //it will be called crossponding to the object created - in this program we created object 5 times then it will also executed 5times
		count++;
	}
	
	static void display()
	{
		System.out.println("\nHow many times an object is created: "+ count +" times.");
	}
}


public class CountingNumberOfObjectCreated {
	public static void main(String[] args) {
		count CNO1 = new count();
		count CNO2 = new count();
		count CNO3 = new count();
		count CNO4 = new count();
		count CNO5 = new count();
		
		count.display(); //prints '5'
		
	}
}
