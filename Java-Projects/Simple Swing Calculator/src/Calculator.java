import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


public class Calculator implements ActionListener{
	
	JFrame frame;
	JTextField textField;
	
	//Array of JButton that will hold the number button
	JButton[] numberBtn = new JButton[10]; //from 0 to 9 buttons. The number buttons will make anonymous
	//Array of JButton that will hold the function button
	JButton[] functionBtn = new JButton[9]; //function like add, sub, div, etc...
	
	JButton addBtn, subBtn, mulBtn, divBtn;
	JButton decBtn, equBtn, delBtn, clrBtn, negBtn; //'delBtn, clrBtn' are not going to be in grid layout of panel
	
	//CREATING JPLANE TO HOLD SEPERATE BUTTONS
	JPanel panel;
	
	
	Font myFont = new Font("Ink Free", Font.BOLD, 30);
	
	double num1 = 0, num2 = 0, result = 0;
	char operator; //this will hold either multiply character, subtract, addition and then division, so it's gonna hold one of those
	
	Calculator() {
		frame = new JFrame("Calculator");
		frame.setSize(420, 550);
		frame.setLayout(null);
		
		
		textField = new JTextField();
		textField.setBounds(50, 25, 300, 50);//Since we are using no Layout manager we are going to use 'setBounds' as where it will located in width and the height
		textField.setFont(myFont);
		//Disabling interaction that bring change to textField by disabling the editiability of it
		textField.setEditable(false);
		
		//After the textField
		addBtn = new JButton("+");
		subBtn = new JButton("-");
		mulBtn = new JButton("*");
		divBtn = new JButton("/");
		decBtn = new JButton(".");
		equBtn = new JButton("=");
		delBtn = new JButton("del");
		clrBtn = new JButton("C"); //these are buttons related array of JButton - functionBtn
		
		negBtn = new JButton("(-)");
		
		/*Assign the above JButtons to array of JButton called functionBtn */
		functionBtn[0] = addBtn;
		functionBtn[1]= subBtn;
		functionBtn[2]= mulBtn;
		functionBtn[3]= divBtn;
		functionBtn[4]= decBtn;
		functionBtn[5]= equBtn;
		functionBtn[6]= delBtn;
		functionBtn[7]= clrBtn;
		functionBtn[8]= negBtn;
		
		//CREATING A FOR-LOOP TO ITERATE THROUGH THIS ARRAY OF JBUTTON
		for(int i = 0; i<9; i++)
		{
			functionBtn[i].addActionListener(this);
			functionBtn[i].setFont(myFont);
			functionBtn[i].setFocusable(false);
		}
		
		for(int i = 0; i<10; i++)
		{
			//Instancing Number buttons
			numberBtn[i] = new JButton(String.valueOf(i));
			
			numberBtn[i].addActionListener(this);
			numberBtn[i].setFont(myFont);
			numberBtn[i].setFocusable(false);
		}
		
		
		//Since, 'delBtn, clrBtn' are not going to be in grid layout of panel, we will setBounds to it
		negBtn.setBounds(50, 430, 100, 50);
		delBtn.setBounds(150, 430, 100, 50);
		clrBtn.setBounds(250, 430, 100, 50);
		
		//CREATING PANEL THAT HOLD'S ALL THE OTHER BUTTONS
		panel = new JPanel();
		panel.setBounds(50, 100, 300, 300);
		panel.setLayout(new GridLayout(4, 4, 10, 10)); //*row, columns, space
//		panel.setBackground(Color.gray);
		
		//1st ROW
		panel.add(numberBtn[1]);
		panel.add(numberBtn[2]);
		panel.add(numberBtn[3]);
		panel.add(addBtn);
		
		//2nd ROW
		panel.add(numberBtn[4]);
		panel.add(numberBtn[5]);
		panel.add(numberBtn[6]);
		panel.add(subBtn);
		
		//3rd ROW
		panel.add(numberBtn[7]);
		panel.add(numberBtn[8]);
		panel.add(numberBtn[9]);
		panel.add(mulBtn);
		
		
		//4th ROW
		panel.add(decBtn);
		panel.add(numberBtn[0]);
		panel.add(equBtn);
		panel.add(divBtn);



		
		/*Adding COmponent to Frame*/
		frame.add(panel); //adding panel to the frame
		frame.add(negBtn);
		frame.add(delBtn);
		frame.add(clrBtn);
		frame.add(textField);
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null); // this method display the JFrame to center position of a screen
	    frame.setVisible(true);
	}
	
	public static void main(String[] args)
	{
		Calculator calculator = new Calculator();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		//CHECKING IF WHETHER NUMBERBTN BEEN CLICKED
		for(int i = 0; i < 10; i++)
		{
			if(e.getSource() == numberBtn[i]) //the number  button of the current index of 'i'
			{
				textField.setText(textField.getText().concat(String.valueOf(i)));
			}
		}
		
		if(e.getSource() == decBtn) 
		{
			textField.setText(textField.getText().concat("."));
		}
		
		if(e.getSource() == addBtn) 
		{
			//we have to retrive number 1
			num1 = Double.parseDouble(textField.getText());
			//assinging our operator which is character
			operator = '+';
			textField.setText("");
		}
		
		if(e.getSource() == subBtn) 
		{
			//we have to retrive number 1
			num1 = Double.parseDouble(textField.getText());
			//assinging our operator which is character
			operator = '-';
			textField.setText("");
		}
		
		if(e.getSource() == mulBtn) 
		{
			//we have to retrive number 1
			num1 = Double.parseDouble(textField.getText());
			//assinging our operator which is character
			operator = '*';
			textField.setText("");
		}
		
		if(e.getSource() == divBtn) 
		{
			//we have to retrive number 1
			num1 = Double.parseDouble(textField.getText());
			//assinging our operator which is character
			operator = '/';
			textField.setText("");
		}
		
		if(e.getSource() == equBtn) 
		{
			//we have to retrive number 1
			num2 = Double.parseDouble(textField.getText());
			
			//Using Switch statement for the operator to determine what mathematical operation to perform on choice of operator
			switch(operator)
			{
				case'+':
					result = num1+num2;
					break;
					
				case'-':
					result = num1-num2;
					break;
				case'*':
					result = num1*num2;
					break;
				case'/':
					result = num1/num2;
					break;	
			}
			
			//Update the textfield after calculation
			textField.setText(String.valueOf(result));
			num1 = result;
		}
		
		if(e.getSource() == clrBtn)
		{
			textField.setText(""); //clear the textField
		}
		
		if(e.getSource() == delBtn)
		{
			String string = textField.getText();
			
			textField.setText("");
			
			//Creating a for-loop that going through the length of the string-1
			for(int i = 0; i<string.length()-1; i++)
			{
				textField.setText(textField.getText()+string.charAt(i));
			}		
		}
		
		if(e.getSource() == negBtn)
		{
			//First we want to retrive whatever text within textField
			
			double temp = Double.parseDouble(textField.getText()); //it's going to take the vlaue from textField and stored it 
			
			//we want to flip the sign on our 'temp' variable by mutiply this variable by -1
			temp*=-1;
			
			//now setting the 'temp' text to current textField
			textField.setText(String.valueOf(temp));
			 
			
		}
	}
}
