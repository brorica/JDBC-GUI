import java.awt.*; 
import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

public class view extends JFrame{
    String[] name = { "����", "fname", "lname", "salary", "addr" };
    private Object [][] datas = {{false,"ȫ�浿","20","��"},{true,"�踻��","18","��"}};
    DefaultTableModel dt;
    JTable jt;
    public JScrollPane jsp;
    public view(){
        dt = new DefaultTableModel(datas, name);
        jt = new JTable(dt);
        jsp = new JScrollPane(jt);
        
        DefaultTableCellRenderer renderer = new MyDefaultTableCellRenderer();
        jt.getColumn("����").setCellRenderer(renderer);
        add(jsp,BorderLayout.CENTER);
	}
}
