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
    JLabel searchRange = new JLabel("검색 범위");
    JMenu m = new JMenu("관리");
    JMenuItem update = new JMenuItem("수정");
    JMenuItem delete = new JMenuItem("삭제");
    JMenuBar mb = new JMenuBar();

 
    String[] name = { "fname", "lname", "salary", "addr" };
 
    DefaultTableModel dt = new DefaultTableModel(name, 0);
    JTable jt = new JTable(dt);
    JScrollPane jsp = new JScrollPane(jt);
 
    /*
     * Select 조건 표시
     * 여기 버튼식으로 바꾸기
     */
    JPanel p = new JPanel();
    String[] comboName = { "전체", "부서별" };
 
    JComboBox combo = new JComboBox(comboName);
    JTextField jtf = new JTextField(20);
    JButton serach = new JButton("검색");
 
    db dao = new db();
 
    /**
     * 화면구성 및 이벤트등록
     */
    public main() {
        //메뉴아이템을 메뉴에 추가
        m.add(update);
        m.add(delete);
        //메뉴를 메뉴바에 추가
        mb.add(m);
       
        //윈도우에 메뉴바 세팅
        setJMenuBar(mb);
 
        p.add(searchRange);
        // South영역
        p.setBackground(Color.yellow);
        p.add(combo);
        p.add(jtf);
        p.add(serach);
 
        add(jsp, "Center");
        add(p, "North");
 
        setSize(500, 400);
        setVisible(true);
 
        super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
 
        // 이벤트등록
        update.addActionListener(this);
        delete.addActionListener(this);
        serach.addActionListener(this);
 
        // 모든레코드를 검색하여 DefaultTableModle에 올리기
        dao.userSelectAll(dt);
       
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
    	if (e.getSource() == update) {// 수정 메뉴아이템 클릭
            new gui(this, "수정");
        } 
    	else if (e.getSource() == delete) {// 삭제 메뉴아이템 클릭
            // 현재 Jtable의 선택된 행과 열의 값을 얻어온다.
            int row = jt.getSelectedRow();
            System.out.println("선택행 : " + row);
 
            Object obj = jt.getValueAt(row, 0);// 행 열에 해당하는 value
            System.out.println("값 : " + obj);
 /*
            if (dao.userDelete(obj.toString()) > 0) {
                UserJDailogGUI.messageBox(this, "레코드 삭제되었습니다.");
               
                //리스트 갱신
                dao.userSelectAll(dt);
                if (dt.getRowCount() > 0)
                    jt.setRowSelectionInterval(0, 0);
 
            } else {
                UserJDailogGUI.messageBox(this, "레코드가 삭제되지 않았습니다.");
            }
 */
        }
        else if (e.getSource() == serach) {// 검색 버튼 클릭
        // JComboBox에 선택된 value 가져오기
        String fieldName = combo.getSelectedItem().toString();
        System.out.println("필드명 " + fieldName);
 
            if (fieldName.trim().equals("ALL")) {// 전체검색
            dao.userSelectAll(dt);
            if (dt.getRowCount() > 0)
                jt.setRowSelectionInterval(0, 0);
        } 
        else {
            if (jtf.getText().trim().equals("")) {
                gui.messageBox(this, "검색단어를 입력해주세요!");
                jtf.requestFocus();
            } 
            else {// 검색어를 입력했을경우
                dao.getUserSearch(dt, fieldName, jtf.getText());
                if (dt.getRowCount() > 0)
                    jt.setRowSelectionInterval(0, 0);
            }
        }
    }
 }
}