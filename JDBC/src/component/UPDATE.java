package component;

import javax.swing.*;

public class UPDATE {
	public static JFrame frame;
	public static JPanel updatePanel;	
	public UPDATE(JFrame frame)
	{
		UPDATE.frame = frame;
		updatePanel = new JPanel();	
		JLabel updateText = new JLabel("���ο� Salary : ");	
		JTextField textField = new JTextField(20);	
		JButton updateButton = new JButton("UPDATE");
		
		updatePanel.add(updateText);
		updatePanel.add(textField);
		updatePanel.add(updateButton);
		
		frame.add(updatePanel);
		frame.pack();
	}

}
