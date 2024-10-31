import java.util.HashMap;

//This will contain userID and Password stored in HashMap

public class HashMapUserInfo {
	
	//The 'HashMap' will perfect for this project because they store key value pairs the key could be the username and the value can be the password
	HashMap<String, String> loginInfo = new HashMap<String, String>(); 	
	
	//CREATING CONSTRUCTOR
	HashMapUserInfo() {
		loginInfo.put("admin", "admin");
		loginInfo.put("user", "user");
		loginInfo.put("employee", "emp");
	}
	
	
	//Now with this HashMap we want to send this to any class that requests it so we are goint to create 'getter' method for this HashMap
	protected HashMap<String, String> getLoginInfo() //the return type of this method is 'HashMap'
	{
		return loginInfo; //we are going to return our HashMap
	}
	
}