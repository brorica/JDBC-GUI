import java.awt.*; 
import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

public class view extends JFrame{
    String[] name = { "선택", "fname", "lname", "salary", "addr" };
    private Object [][] datas = {{false,"홍길동","20","남"},{true,"김말자","18","여"}};
    DefaultTableModel dt;
    JTable jt;
    public JScrollPane jsp;
    public view(){
        dt = new DefaultTableModel(datas, name);
        jt = new JTable(dt);
        jsp = new JScrollPane(jt);
        
        DefaultTableCellRenderer renderer = new MyDefaultTableCellRenderer();
        jt.getColumn("선택").setCellRenderer(renderer);
        add(jsp,BorderLayout.CENTER);
	}
}
