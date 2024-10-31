import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class LoginPage implements ActionListener{
	
	//Instancting JFrame
	JFrame frame = new JFrame();
	JButton loginBtn = new JButton("LOGIN");
	JButton resetBtn = new JButton("RESET");
	
	JTextField userIDFeild = new JTextField();
	JPasswordField userPasswordFeild = new JPasswordField();
	
	//CREATING LABLE
	JLabel userIDLable = new JLabel("User ID: ");
	JLabel userPasswordLable = new JLabel("Password: ");
	
	//Message lable to notify if "Login was sucessful or failed"
	JLabel messageLabel = new JLabel("");
	
	//CREATING A COPY OF 'LoginInfoOriginal'
	HashMap<String, String> loginInfoCopy = new HashMap<String, String>(); //this is globally avaible inorder to be accessible by other class
	
	
	
	/*Constructor*/
	LoginPage(HashMap<String, String> loginInfoOriginal) {
		loginInfoCopy = loginInfoOriginal;
		
		/*Adding Component*/
		userIDLable.setBounds(50, 100, 75, 25);
		userPasswordLable.setBounds(50, 150, 75, 25);
		
		messageLabel.setBounds(125, 250, 250, 35);
		messageLabel.setFont(new Font(null, Font.ITALIC, 25));
		
		/*Setting Fields*/
		userIDFeild.setBounds(125, 100, 200, 25);
		userPasswordFeild.setBounds(125, 150, 200, 25);
		
		/*Setting Button*/
		loginBtn .setBounds(125, 200, 100, 25);
		loginBtn.setFocusable(false);
		//Adding actionlistener to Login-btn
		loginBtn.addActionListener(this);
		
		resetBtn .setBounds(225, 200, 100, 25);
		resetBtn.setFocusable(false);
		//Adding actionlistener to Login-btn
		resetBtn.addActionListener(this);
		
		
		
		/*FRAME*/
		frame.add(userIDLable);
		frame.add(userPasswordLable);
		frame.add(messageLabel);
		frame.add(userIDFeild);
		frame.add(userPasswordFeild);
		frame.add(loginBtn);
		frame.add(resetBtn);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(450, 450);
		frame.setLayout(null);
		frame.setResizable(false);
		frame.setVisible(true);
		
		
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource() == loginBtn)
		{
			String userID = userIDFeild.getText();
			String userpassword = String.valueOf(userPasswordFeild.getPassword()); //Since we are using JPasswordFeild we are going to use 'String.valueOf(userPasswordFeild.getPassword())', so it's going to retrive the password that we typed in and convert it to a String and then store it within a String called 'userpassword' and then we are actually going to verify the userID and passwords
		
			if(loginInfoCopy.containsKey(userID)) //so the keys are our user ID, if our user ID is within our logininfo we also at that point want to verify the password is correct
			{
				if(loginInfoCopy.get(userID).equals(userpassword)) //if we get the user ID and the associated password is equal that means thier credentials are verified then
				{
					messageLabel.setForeground(Color.green);
					messageLabel.setText("Login Successful");
					
					frame.dispose(); //it will close the current login page
					
					//Instancing 'HomePage' because after sucessful login we have to move to HomePage
					HomePage homePage = new HomePage(userID);
				} else {
					messageLabel.setForeground(Color.RED);
					messageLabel.setText("Wrong Password");
				}
			} 
			else 
			{
				messageLabel.setForeground(Color.RED);
				messageLabel.setText("User name not found");
			}
		}
		
		if(e.getSource() == resetBtn)
		{
			userIDFeild.setText(null); //as we reset we are clearing it
			userPasswordFeild.setText(null);
		}
		
	}
}
