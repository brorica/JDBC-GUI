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
		String[] choiceArray = { "전체", "부서별" };
		selectPanel.setLayout(new FlowLayout());
		JLabel searchRange = new JLabel("검색 범위");	
		JComboBox<String> choice = new JComboBox<String>(choiceArray);
		selectPanel.add(searchRange);
		selectPanel.add(choice);
		frame.add(selectPanel);
		frame.pack();
	}
	
	public void selectItem() {
		selectPanel.setLayout(new FlowLayout());
		JLabel searchRange = new JLabel("검색 항목");	
		JCheckBox name = new JCheckBox("NAME");
		JCheckBox ssn = new JCheckBox("SSN");
		JCheckBox bdate = new JCheckBox("BDATE");
		JCheckBox address = new JCheckBox("ADDRESS");
		JCheckBox sex = new JCheckBox("SEX");
		JCheckBox salary = new JCheckBox("SALARY");
		JCheckBox supervisor = new JCheckBox("SUPERVISOR");
		JCheckBox department = new JCheckBox("DEPARTMENT");
		JButton updateButton = new JButton("검색");	
		
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
