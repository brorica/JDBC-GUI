package component;
import java.awt.Frame;
import java.awt.Panel;

import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JTextField;

public class UPDATE extends Frame{
	public static Frame frame;
	public static Panel updatePanel;	
	public UPDATE(Frame frame)
	{
		UPDATE.frame = frame;
		updatePanel = new Panel();	
		JLabel updateText = new JLabel("»õ·Î¿î Salary : ");	
		JTextField textField = new JTextField();	
		JButton updateButton = new JButton("UPDATE");
		
		updatePanel.add(updateText);
		updatePanel.add(textField);
		updatePanel.add(updateButton);
		
		frame.add(updatePanel);
		frame.pack();
	}

}
