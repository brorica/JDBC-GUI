package SQL;
import java.sql.*;
import java.util.Scanner;
import javax.swing.table.DefaultTableModel;
import component.DELETE;
import component.SELECT;
import component.UPDATE;

public class SQL {
	Scanner scanner = new Scanner(System.in);
	Connection connect;
	Statement state;
	PreparedStatement preState;
	ResultSet result;
	
	public SQL(){
		// Get Driver
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch(ClassNotFoundException jdbcDriverError) {
			System.out.println("jdbc Driver Load error : " + jdbcDriverError);
			jdbcDriverError.printStackTrace();
		}
		// Connect
		try {
			String user = "root";
			String password = "123456";
			String database = "mydb";
			String url = "jdbc:mysql://localhost:3306/" + database + "?serverTimezone=UTC";
			
			connect = DriverManager.getConnection(url,user,password);			
		}catch(SQLException connectError) {
			System.out.println("connect error : " + connectError);
			connectError.printStackTrace();
		}
	}
	public void dbClose() {
		// connect close
		try {
			if(connect != null)
				connect.close();	
		}catch(SQLException connectCloseError) {
			System.out.println("connection close error : " + connectCloseError);
		}
	}
	
	public void requestSELECT(DefaultTableModel model) {
		try {
			String dbacct, password, dbname, lname, ssn;
			Double salary;
			String sql1 = "select Lname, Salary from Employee where Ssn=?";
			String[] commands = new String [4];
			PreparedStatement p = connect.prepareStatement(sql1);
			System.out.println("SSn : ");
			//ssn = scanner.nextLine();
			
			ResultSet result = p.executeQuery();
			
			while(result.next()) {
				lname = result.getString(1);
				salary=result.getDouble(2);
				System.out.println(lname + " " + salary);
			}
		} catch(SQLException e) {
			e.printStackTrace();
		}
	}
	public void SelectAll(DefaultTableModel model) {
		String sql1 = "select * from employee";
		try {
			state = connect.createStatement();
			result = state.executeQuery(sql1);
			// 기존 뷰 지우고
            for (int i = 0; i < model.getRowCount();) {
                model.removeRow(0);
            }
            // 다시 보여줌
			while(result.next()) {
                Object employeeTuple[] = { result.getString(1), result.getString(3) };
                model.addRow(employeeTuple);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
