import java.sql.*;
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
    }
 
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
    public int salaryUpdate(int newSalary) {
        int result = 0;
        System.out.println(newSalary);
        String sql = "UPDATE employee SET Salary=? where Fname=?";
 
        try {
            preState = connect.prepareStatement(sql);
            preState.setInt(1, newSalary);
            preState.setString(2, "TEST");
            result = preState.executeUpdate();
 
        } catch (SQLException updateError) {
            System.out.println(updateError + "=> userUpdate fail");
        } finally {
            dbClose();
        }
 
        return result;
    }
    public int deleteEmployee(String ssn) {
        int result = 0;
        String sql = "DELETE FROM employee where Ssn=?";
        System.out.println(ssn);
        try {
            preState = connect.prepareStatement(sql);
            preState.setString(1, ssn);
            result = preState.executeUpdate();
 
        } catch (SQLException updateError) {
            System.out.println(updateError + "=> userUpdate fail");
        } finally {
            dbClose();
        }
 
        return result;
    } 
    public void getUserSearch(DefaultTableModel dt, String fieldName, String[] whereArray) {
    	int index=0; // where element count
        String sql = "SELECT ";
        for(index=0;index<whereArray.length;index++)
        {
        	if(whereArray[index] == null)
        		break;
        	sql+=whereArray[index];
        	sql+=',';
        }
        sql = sql.substring(0, sql.length() - 1);
        sql+=" from employee";
        System.out.println(sql);
        try {
        	state = connect.createStatement();
            result = state.executeQuery(sql);
            // DefaultTableModel�� �ִ� ���� ������ �����
            for (int i = 0; i < dt.getRowCount();) {
                dt.removeRow(0);
            }
            while (result.next()) {
            	Object data[] = new String[index];
            	for(int i=1;i<=index;i++)
            		data[i-1] = result.getString(i);
                dt.addRow(data);
            } 
        } catch (SQLException e) {
            System.out.println(e + "=> getUseresultearch fail");
        } finally {
            dbClose();
        }
    }
}