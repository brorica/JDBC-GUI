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
     * Select 조건 표시
     * 여기 버튼식으로 바꾸기
     */
    JPanel p = new JPanel();
    JPanel updatePanel = new JPanel();
    
    JLabel searchRangeLabel = new JLabel("검색 범위");
    String[] comboName = { "전체", "부서별" };
    JComboBox combo = new JComboBox(comboName);
    
    JLabel searchItemLabel = new JLabel("검색 항목");
    Checkbox fname = new Checkbox("fname");
    Checkbox lname = new Checkbox("lname");
    JButton serach = new JButton("검색");
    
    JLabel SalaryLabel = new JLabel("새로운 Salary : ");
	JTextField SalaryTextField = new JTextField(20);
	JButton update = new JButton("UPDATE");
	
    JLabel DeleteLabel = new JLabel("선택한 데이터 삭제");
	JButton delete = new JButton("delete");
 
    db db = new db();
 
    /**
     * 화면구성 및 이벤트등록
     */
    public main() {
        //메뉴아이템을 메뉴에 추가

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
        add(p, "North"); // 노쓰랜드로! (6코)
        add(updatePanel,"South");
 
        setSize(500, 400);
        setVisible(true);
 
        super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
 
        // 이벤트등록
        update.addActionListener(this);
        serach.addActionListener(this);
        delete.addActionListener(this);
 
        // 모든레코드를 검색하여 DefaultTableModle에 올리기
        db.userSelectAll(dt);
       
        //첫번행 선택.
        if (dt.getRowCount() > 0)
            jt.setRowSelectionInterval(0, 0);
 
    }// 생성자끝
 
    /**
     * main메소드 작성
     */
    public static void main(String[] args) {
        new main();
    }
 
    /**
     * 가입/수정/삭제/검색기능을 담당하는 메소드
     * */
 
    public void actionPerformed(ActionEvent e) {
    	String [] whereArray = new String[8];
    	int index = 0;
		if (e.getSource() == serach) {// 검색 버튼 클릭
			// System.out.println(fname.getState());
			if(fname.getState())
				whereArray[index++] = "fname";
			if(lname.getState())
				whereArray[index++] = "lname";
			// JComboBox에 선택된 value 가져오기
			String fieldName = combo.getSelectedItem().toString();
			db.getUserSearch(dt, fieldName, whereArray);
		}
		else if (e.getSource() == update) {// 검색 버튼 클릭
			int newSalary = Integer.valueOf(SalaryTextField.getText());
			db.salaryUpdate(newSalary);
		}
		else if (e.getSource() == delete) {// 검색 버튼 클릭
			String ssn = "000000000";
			db.deleteEmployee(ssn);
		}
    }
}