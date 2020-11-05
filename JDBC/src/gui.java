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
 
 
    JTextField fname=new JTextField();
    JTextField name=new JTextField();
    JTextField age=new JTextField();
    JTextField salary=new JTextField();
   
 
    JButton confirm;
    JButton reset=new JButton("취소");
 
   main me;
 
   JPanel idCkP =new JPanel(new BorderLayout());
   JButton idCkBtn = new JButton("IDCheck");
   
   db dao =new db();
   
 
    public gui(main me, String index){
        super(me,"다이어로그");
    this.me=me;
    if(index.equals("가입")){
        confirm=new JButton(index);
    }else{
        confirm=new JButton("수정"); 
       
        //text박스에 선택된 레코드의 정보 넣기
        int row = me.jt.getSelectedRow();//선택된 행
        fname.setText( me.jt.getValueAt(row, 0).toString() );
        name.setText( me.jt.getValueAt(row, 1).toString() );
        age.setText( me.jt.getValueAt(row, 2).toString() );
        salary.setText( me.jt.getValueAt(row, 3).toString() );
       
        //id text박스 비활성
        fname.setEditable(false);
   
            //IDCheck버튼 비활성화
        idCkBtn.setEnabled(false);
    }
   
   
    //Label추가부분
    pw.add(lable_Id);//ID
    pw.add(lable_Name);//이름
    pw.add(lable_Age);//나이
    pw.add(lable_Salary);//주소
   
       
    idCkP.add(fname,"Center");
    idCkP.add(idCkBtn,"East");
   
    //TextField 추가
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
       
    //이벤트등록
    confirm.addActionListener(this); //가입/수정 이벤트등록
    reset.addActionListener(this); //취소 이벤트등록
    idCkBtn.addActionListener(this);// ID중복체크 이벤트 등록
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