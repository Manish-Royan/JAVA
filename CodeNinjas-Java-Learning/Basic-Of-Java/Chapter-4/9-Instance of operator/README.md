# Instance of operator

The instanceof operator in java is used to compare an object to a specified type. We can use it to check if an object is an instance of a class, an instance of a subclass, or an instance of a class that implements a particular interface. The instanceof operator is either returned true or false. 

#### Example:
 
```Java
public class InstanceOfExample {
   
   public static void main(String args[]) {
      
      // Creating the objects of parent and the child class
      ParentClass obj1 = new ParentClass();  
      ChildClass obj2 = new ChildClass();

      // Comparing the object to a specified type
      System.out.println("obj1 is instance of ParentClass = " + (obj1 instanceof ParentClass));
      System.out.println("obj1 is instance of ChildClass = " +  (obj1 instanceof ChildClass));
      System.out.println("obj1 is instance of MyInterface = " + (obj1 instanceof MyInterface));
      System.out.println("obj2 is instance of ParentClass = " + (obj2 instanceof ParentClass));
      System.out.println("obj2 is instance of ChildClass = " +  (obj2 instanceof ChildClass));
      System.out.println("obj2 is instance of MyInterface = " + (obj2 instanceof MyInterface));
  }
}

// Creating parent class
class ParentClass {

}

// Creating an interface
interface MyInterface {

}

// Creating child class
class ChildClass extends ParentClass implements MyInterface {

}
```

```
Output:
obj1 is instance of ParentClass = true
obj1 is instance of ChildClass = false
obj1 is instance of MyInterface = false
obj2 is instance of ParentClass = true
obj2 is instance of ChildClass = true
obj2 is instance of MyInterface = true
```

**Note: In the case of null value, it returns false because null is not an instance of anything**.

 

#### Example:
```Java 
public class InstanceOfOperator {

   public static void main(String args[]) {

      // Creating an object of class and assigning it with null.
      InstanceOfOperator obj = null;
 
      // Returns false
      System.out.println(obj instanceof InstanceOfOperator);
  }
}
 
```

```
Output:
false
```