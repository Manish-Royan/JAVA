import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.text.PlainDocument;

public class HomePage {
	
	JFrame frame = new JFrame();
	JLabel greetingLable = new JLabel("");
	
	/*Constructor*/
	HomePage(String userID) //this constructor will accpet the String from userID
	{
		greetingLable.setBounds(0, 0, 200, 35);
		greetingLable.setFont(new Font(null, Font.PLAIN, 25));
		greetingLable.setText("Hello "+ userID);
		
		
		frame.add(greetingLable);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(420, 420);
		frame.setLayout(null);
		frame.setVisible(true);
	}
	
}
