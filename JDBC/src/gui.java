import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
 
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
 
public class gui extends JDialog implements ActionListener{
   
    JPanel pw=new JPanel(new GridLayout(4,1));
    JPanel pc=new JPanel(new GridLayout(4,1));
    JPanel ps=new JPanel();
 
    JLabel lable_Id = new JLabel("Fname");
	JLabel lable_Name=new JLabel("Lname");
	JLabel lable_Age=new JLabel("����");
	JLabel lable_Salary=new JLabel("Salary");
	
   
	main me;   
	db dao =new db();
   
 
    public gui(main me, String index){
	    //Label�߰��κ�
	    pw.add(lable_Id);//ID
	    pw.add(lable_Name);//�̸�
	    pw.add(lable_Age);//����
	    pw.add(lable_Salary);//�ּ�
	  
	    add(pw,"West");
	    add(pc,"South");
	       
	    setSize(300,250);
	    setVisible(true);
	 
	    setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
	       
	    //�̺�Ʈ���
	}
   
 /**
 * ����/����/���� ��ɿ� ���� �κ�
 * */
@Override
public void actionPerformed(ActionEvent e) {
	String btnLabel =e.getActionCommand();//�̺�Ʈ��ü ���� Label ��������
	if(btnLabel.equals("����")){
		if( dao.userUpdate(this) > 0){
		    messageBox(this, "�����Ϸ�Ǿ����ϴ�.");
		    dispose();
		    dao.userSelectAll(me.dt);
		    if(me.dt.getRowCount() > 0 ) me.jt.setRowSelectionInterval(0, 0);
		   
		}
   }
}
   
    /**
 * �޽����ڽ� ����ִ� �޼ҵ� �ۼ�
 * */
    public static void messageBox(Object obj , String message){
        JOptionPane.showMessageDialog( (Component)obj , message);
    }

}