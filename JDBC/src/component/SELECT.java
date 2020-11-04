package component;
import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.*;

public class SELECT {
	public static JFrame frame;
	public static JPanel selectPanel;	
	public SELECT(JFrame frame) {
		SELECT.frame = frame;
		selectPanel = new JPanel();
		selectRange();
		selectItem();
	}
	public void selectRange() {	
		String[] choiceArray = { "��ü", "�μ���" };
		selectPanel.setLayout(new FlowLayout());
		JLabel searchRange = new JLabel("�˻� ����");	
		JComboBox<String> choice = new JComboBox<String>(choiceArray);
		selectPanel.add(searchRange);
		selectPanel.add(choice);
		frame.add(selectPanel);
		frame.pack();
	}
	
	public void selectItem() {
		selectPanel.setLayout(new FlowLayout());
		JLabel searchRange = new JLabel("�˻� �׸�");	
		JCheckBox name = new JCheckBox("NAME");
		JCheckBox ssn = new JCheckBox("SSN");
		JCheckBox bdate = new JCheckBox("BDATE");
		JCheckBox address = new JCheckBox("ADDRESS");
		JCheckBox sex = new JCheckBox("SEX");
		JCheckBox salary = new JCheckBox("SALARY");
		JCheckBox supervisor = new JCheckBox("SUPERVISOR");
		JCheckBox department = new JCheckBox("DEPARTMENT");
		JButton updateButton = new JButton("�˻�");	
		
		selectPanel.add(searchRange);
		selectPanel.add(name);
		selectPanel.add(ssn);
		selectPanel.add(bdate);
		selectPanel.add(address);
		selectPanel.add(sex);
		selectPanel.add(salary);
		selectPanel.add(supervisor);
		selectPanel.add(department);
		selectPanel.add(updateButton);
		
		frame.add(selectPanel);
		frame.pack();
	}
}
