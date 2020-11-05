//MenuJTabaleExam.java
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
 
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
 
 
public class main extends JFrame implements ActionListener {
    JLabel searchRange = new JLabel("�˻� ����");
    JMenu m = new JMenu("����");
    JMenuItem update = new JMenuItem("����");
    JMenuItem delete = new JMenuItem("����");
    JMenuBar mb = new JMenuBar();

 
    String[] name = { "fname", "lname", "salary", "addr" };
 
    DefaultTableModel dt = new DefaultTableModel(name, 0);
    JTable jt = new JTable(dt);
    JScrollPane jsp = new JScrollPane(jt);
 
    /*
     * Select ���� ǥ��
     * ���� ��ư������ �ٲٱ�
     */
    JPanel p = new JPanel();
    String[] comboName = { "��ü", "�μ���" };
 
    JComboBox combo = new JComboBox(comboName);
    JTextField jtf = new JTextField(20);
    JButton serach = new JButton("�˻�");
 
    db dao = new db();
 
    /**
     * ȭ�鱸�� �� �̺�Ʈ���
     */
    public main() {
        //�޴��������� �޴��� �߰�
        m.add(update);
        m.add(delete);
        //�޴��� �޴��ٿ� �߰�
        mb.add(m);
       
        //�����쿡 �޴��� ����
        setJMenuBar(mb);
 
        p.add(searchRange);
        // South����
        p.setBackground(Color.yellow);
        p.add(combo);
        p.add(jtf);
        p.add(serach);
 
        add(jsp, "Center");
        add(p, "North");
 
        setSize(500, 400);
        setVisible(true);
 
        super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
 
        // �̺�Ʈ���
        update.addActionListener(this);
        delete.addActionListener(this);
        serach.addActionListener(this);
 
        // ��緹�ڵ带 �˻��Ͽ� DefaultTableModle�� �ø���
        dao.userSelectAll(dt);
       
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
    	if (e.getSource() == update) {// ���� �޴������� Ŭ��
            new gui(this, "����");
        } 
    	else if (e.getSource() == delete) {// ���� �޴������� Ŭ��
            // ���� Jtable�� ���õ� ��� ���� ���� ���´�.
            int row = jt.getSelectedRow();
            System.out.println("������ : " + row);
 
            Object obj = jt.getValueAt(row, 0);// �� ���� �ش��ϴ� value
            System.out.println("�� : " + obj);
 /*
            if (dao.userDelete(obj.toString()) > 0) {
                UserJDailogGUI.messageBox(this, "���ڵ� �����Ǿ����ϴ�.");
               
                //����Ʈ ����
                dao.userSelectAll(dt);
                if (dt.getRowCount() > 0)
                    jt.setRowSelectionInterval(0, 0);
 
            } else {
                UserJDailogGUI.messageBox(this, "���ڵ尡 �������� �ʾҽ��ϴ�.");
            }
 */
        }
        else if (e.getSource() == serach) {// �˻� ��ư Ŭ��
        // JComboBox�� ���õ� value ��������
        String fieldName = combo.getSelectedItem().toString();
        System.out.println("�ʵ�� " + fieldName);
 
            if (fieldName.trim().equals("ALL")) {// ��ü�˻�
            dao.userSelectAll(dt);
            if (dt.getRowCount() > 0)
                jt.setRowSelectionInterval(0, 0);
        } 
        else {
            if (jtf.getText().trim().equals("")) {
                gui.messageBox(this, "�˻��ܾ �Է����ּ���!");
                jtf.requestFocus();
            } 
            else {// �˻�� �Է��������
                dao.getUserSearch(dt, fieldName, jtf.getText());
                if (dt.getRowCount() > 0)
                    jt.setRowSelectionInterval(0, 0);
            }
        }
    }
 }
}