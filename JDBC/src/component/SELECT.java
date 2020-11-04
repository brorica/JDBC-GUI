package component;
import java.awt.Frame;
import java.awt.Panel;
import java.awt.Choice;
import java.awt.Checkbox;
import javax.swing.JButton;
import javax.swing.JLabel;

public class SELECT extends Frame{
	public static Frame frame;
	public static Panel selectPanel;	
	public SELECT(Frame frame) {
		SELECT.frame = frame;
		selectPanel = new Panel();
		selectRange();
		selectItem();
	}
	public void selectRange() {	
		JLabel searchRange = new JLabel("검색 범위");	
		Choice choice = new Choice();
		choice.addItem("전체");
		choice.addItem("부서별");
		selectPanel.add(searchRange);
		selectPanel.add(choice);
		
		frame.add(selectPanel);
		frame.pack();
	}
	
	public void selectItem() {
		JLabel searchRange = new JLabel("검색 항목");	
		Checkbox name = new Checkbox("NAME");
		Checkbox ssn = new Checkbox("SSN");
		Checkbox bdate = new Checkbox("BDATE");
		Checkbox address = new Checkbox("ADDRESS");
		Checkbox sex = new Checkbox("SEX");
		Checkbox salary = new Checkbox("SALARY");
		Checkbox supervisor = new Checkbox("SUPERVISOR");
		Checkbox department = new Checkbox("DEPARTMENT");
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
