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
 
 
    JTextField fname=new JTextField();
    JTextField name=new JTextField();
    JTextField age=new JTextField();
    JTextField salary=new JTextField();
   
 
    JButton confirm;
    JButton reset=new JButton("���");
 
   main me;
 
   JPanel idCkP =new JPanel(new BorderLayout());
   JButton idCkBtn = new JButton("IDCheck");
   
   db dao =new db();
   
 
    public gui(main me, String index){
        super(me,"���̾�α�");
    this.me=me;
    if(index.equals("����")){
        confirm=new JButton(index);
    }else{
        confirm=new JButton("����"); 
       
        //text�ڽ��� ���õ� ���ڵ��� ���� �ֱ�
        int row = me.jt.getSelectedRow();//���õ� ��
        fname.setText( me.jt.getValueAt(row, 0).toString() );
        name.setText( me.jt.getValueAt(row, 1).toString() );
        age.setText( me.jt.getValueAt(row, 2).toString() );
        salary.setText( me.jt.getValueAt(row, 3).toString() );
       
        //id text�ڽ� ��Ȱ��
        fname.setEditable(false);
   
            //IDCheck��ư ��Ȱ��ȭ
        idCkBtn.setEnabled(false);
    }
   
   
    //Label�߰��κ�
    pw.add(lable_Id);//ID
    pw.add(lable_Name);//�̸�
    pw.add(lable_Age);//����
    pw.add(lable_Salary);//�ּ�
   
       
    idCkP.add(fname,"Center");
    idCkP.add(idCkBtn,"East");
   
    //TextField �߰�
    pc.add(idCkP);
    pc.add(name);
    pc.add(age);
    pc.add(salary);
       
       
       
    ps.add(confirm);
    ps.add(reset);
  
    add(pw,"West");
    add(pc,"Center");
    add(ps,"South");
       
    setSize(300,250);
    setVisible(true);
 
    setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
       
    //�̺�Ʈ���
    confirm.addActionListener(this); //����/���� �̺�Ʈ���
    reset.addActionListener(this); //��� �̺�Ʈ���
    idCkBtn.addActionListener(this);// ID�ߺ�üũ �̺�Ʈ ���
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