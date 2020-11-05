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
	JLabel lable_Age=new JLabel("나이");
	JLabel lable_Salary=new JLabel("Salary");
	
   
	main me;   
	db dao =new db();
   
 
    public gui(main me, String index){
	    //Label추가부분
	    pw.add(lable_Id);//ID
	    pw.add(lable_Name);//이름
	    pw.add(lable_Age);//나이
	    pw.add(lable_Salary);//주소
	  
	    add(pw,"West");
	    add(pc,"South");
	       
	    setSize(300,250);
	    setVisible(true);
	 
	    setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
	       
	    //이벤트등록
	}
   
 /**
 * 가입/수정/삭제 기능에 대한 부분
 * */
@Override
public void actionPerformed(ActionEvent e) {
	String btnLabel =e.getActionCommand();//이벤트주체 대한 Label 가져오기
	if(btnLabel.equals("수정")){
		if( dao.userUpdate(this) > 0){
		    messageBox(this, "수정완료되었습니다.");
		    dispose();
		    dao.userSelectAll(me.dt);
		    if(me.dt.getRowCount() > 0 ) me.jt.setRowSelectionInterval(0, 0);
		   
		}
   }
}
   
    /**
 * 메시지박스 띄워주는 메소드 작성
 * */
    public static void messageBox(Object obj , String message){
        JOptionPane.showMessageDialog( (Component)obj , message);
    }

}