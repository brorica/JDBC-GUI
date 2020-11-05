import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
 
import javax.swing.table.DefaultTableModel;
 
public class db {
   
    /**
     * 필요한 변수선언
     * */
	Connection connect;
	Statement state;
	PreparedStatement preState;
	ResultSet result;
 
    /**
     * 로드 연결을 위한 생성자
     * */
    public db() {
		// Get Driver
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch(ClassNotFoundException jdbcDriverError) {
			System.out.println("jdbc Driver Load error : " + jdbcDriverError);
			jdbcDriverError.printStackTrace();
		}
		// connect
		try {
			String user = "root";
			String password = "123456";
			String database = "mydb";
			String url = "jdbc:mysql://localhost:3306/" + database + "?serverTimezone=UTC";
			
			connect = DriverManager.getConnection(url,user,password);			
		}catch(SQLException connectnectError) {
			System.out.println("connectnect error : " + connectnectError);
			connectnectError.printStackTrace();
		}
    }
 
    /**
     * DB닫기 기능 메소드
     * */
    public void dbClose() {
        try {
            if (result != null) 
            	result.close();
            if (state != null) 
            	state.close();
            if (preState != null)
            	preState.close();
        } catch (Exception closeError) {
            System.out.println(closeError + "=> dbClose fail");
        }
    }
    /**
     * employee의 모든 레코드 조회
     * */
    public void userSelectAll(DefaultTableModel t_model) {
        try {
        	state = connect.createStatement();
            result = state.executeQuery("select fname, lname from employee");
 
            // DefaultTableModel에 있는 기존 데이터 지우기
            for (int i = 0; i < t_model.getRowCount();) {
                t_model.removeRow(0);
            }
 
            while (result.next()) {
                Object data[] = { result.getString(1), result.getString(2) };
 
                t_model.addRow(data); //DefaultTableModel에 레코드 추가
            }
        } catch (SQLException e) {
            System.out.println(e + "=> useresultelectAll fail");
        } finally {
            dbClose();
        }
    }//useresultelectAll()
 
    /**
     * ID에 해당하는 레코드 삭제하기
     * */
    public int userDelete(String id) {
        int result = 0;
        try {
            preState = connect.prepareStatement("delete employee where ssn = ? ");
            preState.setString(1, id.trim());
            result = preState.executeUpdate();
 
        } catch (SQLException e) {
            System.out.println(e + "=> userDelete fail");
        }finally {
            dbClose();
        }
 
        return result;
    }//userDelete()
 
    /**
     * ID에 해당하는 레코드 수정하기
     * */
    public int userUpdate(gui user) {
        int result = 0;
        String sql = "UPDATE employee SET Salary=?";
 
        try {
            preState = connect.prepareStatement(sql);
            preState.setString(1, user.salary.getText());
            result = preState.executeUpdate();
 
        } catch (SQLException updateError) {
            System.out.println(updateError + "=> userUpdate fail");
        } finally {
            dbClose();
        }
 
        return result;
    } 
    /**
     * 검색단어에 해당하는 레코드 검색하기 (like연산자를 사용하여 _, %를 사용할때는 PreparedStatemnet안된다. 반드시
     * Statement객체를 이용함)
     * */
    public void getUserSearch(DefaultTableModel dt, String fieldName, String word) {
    	System.out.println("filed Name : " + fieldName);
        String sql = "SELECT * FROM employee WHERE " + fieldName.trim()
                + " LIKE '%" + word.trim() + "%'";
 
        try {
        	state = connect.createStatement();
            result = state.executeQuery(sql);
 
            // DefaultTableModel에 있는 기존 데이터 지우기
            for (int i = 0; i < dt.getRowCount();) {
                dt.removeRow(0);
            }
 
            while (result.next()) {
                Object data[] = { result.getString(1), result.getString(2),
                        result.getInt(3), result.getString(4) };
 
                dt.addRow(data);
            }
 
        } catch (SQLException e) {
            System.out.println(e + "=> getUseresultearch fail");
        } finally {
            dbClose();
        }
    }
 
}