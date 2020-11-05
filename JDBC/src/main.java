import java.awt.Checkbox;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
 
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
 
 
public class main extends JFrame implements ActionListener {

    String[] name = { "fname", "lname", "salary", "addr" };
 
    DefaultTableModel dt = new DefaultTableModel(name, 0);
    JTable jt = new JTable(dt);
    JScrollPane jsp = new JScrollPane(jt);
 
    /*
     * Select ���� ǥ��
     * ���� ��ư������ �ٲٱ�
     */
    JPanel p = new JPanel();
    JPanel updatePanel = new JPanel();
    
    JLabel searchRangeLabel = new JLabel("�˻� ����");
    String[] comboName = { "��ü", "�μ���" };
    JComboBox combo = new JComboBox(comboName);
    
    JLabel searchItemLabel = new JLabel("�˻� �׸�");
    Checkbox fname = new Checkbox("fname");
    Checkbox lname = new Checkbox("lname");
    JButton serach = new JButton("�˻�");
    
    JLabel SalaryLabel = new JLabel("���ο� Salary : ");
	JTextField SalaryTextField = new JTextField(20);
	JButton update = new JButton("UPDATE");
	
    JLabel DeleteLabel = new JLabel("������ ������ ����");
	JButton delete = new JButton("delete");
 
    db db = new db();
 
    /**
     * ȭ�鱸�� �� �̺�Ʈ���
     */
    public main() {
        //�޴��������� �޴��� �߰�

        p.add(searchRangeLabel);
        p.add(combo);
        p.add(searchItemLabel);
        p.add(fname);
        p.add(lname);
        p.add(serach);

        updatePanel.add(SalaryLabel);
        updatePanel.add(SalaryTextField);
        updatePanel.add(update);
        updatePanel.add(DeleteLabel);
        updatePanel.add(delete);
 
        add(jsp, "Center");
        add(p, "North"); // �뾲�����! (6��)
        add(updatePanel,"South");
 
        setSize(500, 400);
        setVisible(true);
 
        super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
 
        // �̺�Ʈ���
        update.addActionListener(this);
        serach.addActionListener(this);
        delete.addActionListener(this);
 
        // ��緹�ڵ带 �˻��Ͽ� DefaultTableModle�� �ø���
        db.userSelectAll(dt);
       
        //ù���� ����.
        if (dt.getRowCount() > 0)
            jt.setRowSelectionInterval(0, 0);
 
    }// �����ڳ�
 
    /**
     * main�޼ҵ� �ۼ�
     */
    public static void main(String[] args) {
        new main();
    }
 
    /**
     * ����/����/����/�˻������ ����ϴ� �޼ҵ�
     * */
 
    public void actionPerformed(ActionEvent e) {
    	String [] whereArray = new String[8];
    	int index = 0;
		if (e.getSource() == serach) {// �˻� ��ư Ŭ��
			// System.out.println(fname.getState());
			if(fname.getState())
				whereArray[index++] = "fname";
			if(lname.getState())
				whereArray[index++] = "lname";
			// JComboBox�� ���õ� value ��������
			String fieldName = combo.getSelectedItem().toString();
			db.getUserSearch(dt, fieldName, whereArray);
		}
		else if (e.getSource() == update) {// �˻� ��ư Ŭ��
			int newSalary = Integer.valueOf(SalaryTextField.getText());
			db.salaryUpdate(newSalary);
		}
		else if (e.getSource() == delete) {// �˻� ��ư Ŭ��
			String ssn = "000000000";
			db.deleteEmployee(ssn);
		}
    }
}