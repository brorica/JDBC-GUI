package component;
import java.awt.GridLayout;
import javax.swing.*;

import SQL.*;

public class VIEW {
	static JFrame frame;
	static JPanel attribute;
	static SQL sql = new SQL();
	
    JLabel lable_Fname = new JLabel("Fname");
    JLabel lable_Lname=new JLabel("Lname");
    JTextField id=new JTextField();
    JTextField name=new JTextField();
	
	public VIEW(JFrame frame) {
		VIEW.frame = frame;
		attribute = new JPanel(new GridLayout(2,1));
		attribute.add(lable_Fname);
		attribute.add(lable_Lname);
		frame.add(attribute);
	}
	
	public void showAll() {
		
	}
}
