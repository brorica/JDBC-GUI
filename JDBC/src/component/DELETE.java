package component;
import javax.swing.*;
public class DELETE {
	public static JFrame frame;
	public static JPanel updatePanel;	
	public DELETE(JFrame frame)
	{
		UPDATE.frame = frame;
		updatePanel = new JPanel();	
		JLabel updateText = new JLabel("������ ������ ����");	
		JButton updateButton = new JButton("DELETE");
		
		updatePanel.add(updateText);
		updatePanel.add(updateButton);
		
		frame.add(updatePanel);
		frame.pack();
	}

}
