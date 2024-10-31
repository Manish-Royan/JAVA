
public class Main {

	public static void main(String[] args) {
		//CREATING INSTANCE OF 'HashMapUserInfo' CLASS
		HashMapUserInfo IDandPasswords = new HashMapUserInfo();
		
		
		//CREATING INSTANCE OF LoginPage CLASS - we are going to send our login-info as an argument to our login-page
		LoginPage loginPage = new LoginPage(IDandPasswords.getLoginInfo()); //inside the constructor we passed instance of 'HashMapUserInfo' to retreive data from that 'HashMapUserInfo' class and use 'getLoginInfo' to send the information to 'loginPage'
		
	}

}
