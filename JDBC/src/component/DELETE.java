package component;
import javax.swing.*;
public class DELETE {
	public static JFrame frame;
	public static JPanel updatePanel;	
	public DELETE(JFrame frame)
	{
		UPDATE.frame = frame;
		updatePanel = new JPanel();	
		JLabel updateText = new JLabel("선택한 데이터 삭제");	
		JButton updateButton = new JButton("DELETE");
		
		updatePanel.add(updateText);
		updatePanel.add(updateButton);
		
		frame.add(updatePanel);
		frame.pack();
	}

}
