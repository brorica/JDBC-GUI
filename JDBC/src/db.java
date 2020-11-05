import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
 
import javax.swing.table.DefaultTableModel;
 
public class db {
   
    /**
     * �ʿ��� ��������
     * */
	Connection connect;
	Statement state;
	PreparedStatement preState;
	ResultSet result;
 
    /**
     * �ε� ������ ���� ������
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
     * DB�ݱ� ��� �޼ҵ�
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
     * employee�� ��� ���ڵ� ��ȸ
     * */
    public void userSelectAll(DefaultTableModel t_model) {
        try {
        	state = connect.createStatement();
            result = state.executeQuery("select fname, lname from employee");
 
            // DefaultTableModel�� �ִ� ���� ������ �����
            for (int i = 0; i < t_model.getRowCount();) {
                t_model.removeRow(0);
            }
 
            while (result.next()) {
                Object data[] = { result.getString(1), result.getString(2) };
 
                t_model.addRow(data); //DefaultTableModel�� ���ڵ� �߰�
            }
        } catch (SQLException e) {
            System.out.println(e + "=> useresultelectAll fail");
        } finally {
            dbClose();
        }
    }//useresultelectAll()
 
    /**
     * ID�� �ش��ϴ� ���ڵ� �����ϱ�
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
     * ID�� �ش��ϴ� ���ڵ� �����ϱ�
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
     * �˻��ܾ �ش��ϴ� ���ڵ� �˻��ϱ� (like�����ڸ� ����Ͽ� _, %�� ����Ҷ��� PreparedStatemnet�ȵȴ�. �ݵ��
     * Statement��ü�� �̿���)
     * */
    public void getUserSearch(DefaultTableModel dt, String fieldName, String word) {
    	System.out.println("filed Name : " + fieldName);
        String sql = "SELECT * FROM employee WHERE " + fieldName.trim()
                + " LIKE '%" + word.trim() + "%'";
 
        try {
        	state = connect.createStatement();
            result = state.executeQuery(sql);
 
            // DefaultTableModel�� �ִ� ���� ������ �����
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