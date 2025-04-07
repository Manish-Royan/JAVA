public class PrimitiveDataType {
	public static void main(String[] args) {
		/*Numberic Datatype*/
			/*Integeric part*/
		
		//BYTE (hold 8bits which is 1Byte)
		byte myByte = 127; // -128 to 127 
		byte charac = 'C';	//byte can also technically hold things like characters but generally for character we use 'char'
		
		//if we need ASCII value of character 'C', we can cast it to a 'byte; like this
		byte character = (byte) 'C'; // This will store the ASCII value of 'C' which is 67

		
		short myShort = 32767; // -32,768 to 32,767
		
		int myInt = 2147483647; // -2,147,483,648 to 2,147,483,647
		
		long myLong = 123; 
		
		
	}
}


/*
* There are 8 types of Primitive DataType but generally defined in 2 parts Numberic and non-numberic:
* i. Numberic DataType have also 2 parts:
* 	a. Integeric:
* 		- byte (1byte)
* 		- short (2bytes)
* 		- int (4bytes)
* 		- lobg (8bytes)
* 	b. Decimal: 
* 		- float (4bytes)
* 		- double (8bytes)
* ii. Non-Numberic DataType have:
* 	- char (2bytes)
* 	- boolean (1bit) {remember it's only 1bit not 1byte because it only stores 1 and 0}
*/