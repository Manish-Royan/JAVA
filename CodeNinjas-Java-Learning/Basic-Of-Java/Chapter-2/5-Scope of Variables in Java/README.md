# Scope of Variables in Java
 
The variable scope is the part of the program where the variable is accessible. The scope of the variable can be *determined at compile time*. There are mainly two types of variable scope:

1) **Local Variables Scope**: A variable that is defined inside a block, method body, or constructor is called a local variable. **These variables can’t be accessed outside the method**. 

*Example*:

```Java
public class VariableScope {
	void method() {
		// local variable (Method Level Scope)
		// This method can’t be accessed outside
		// method body.
		int x;
	}
}
```

2) **Member/Class Level Variable Scope**: A variable that is declared inside the class but outside the method body, block, or constructor is known as member/class level variable. T**hese variables can be directly accessed anywhere in the class**.

*Example*:

```Java
class VariableScope {

	// variable defined inside the class
	int x;
}

public class VariableScopeDemo {
	public static void main(String args[]) {
	
		// Creating VariableScope class object
		VariableScope obj = new VariableScope();
		
		// Assigning values in the variable
		obj.x = 10;
		
		// Printing the value
		System.out.println(obj.x);
	}
}
```

    Output:

    10