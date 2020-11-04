import java.sql.*;
import java.util.Date;

public class Insert {
	public static Connection connect;
	Insert(	Connection connect){
		Insert.connect = connect;
	}
	public static void employeeInsert() throws SQLException {
		String employeeInsertSQL = "insert into employee values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		PreparedStatement employeeStatement = connect.prepareStatement(employeeInsertSQL);
		String Fname, Lname, Ssn, Bdate, Address, Super_ssn;
		char Minit, Sex;
		double Salary;
		int Dno;
		
		employeeStatement.clearParameters();
		employeeStatement.setString(1, Fname);
		employeeStatement.setString(2, String.valueOf(Minit));
		employeeStatement.setString(3, Lname);
		employeeStatement.setString(4, Ssn);
		employeeStatement.setString(5, Bdate);
		employeeStatement.setString(6, Address);
		employeeStatement.setString(7, String.valueOf(Sex));
		employeeStatement.setDouble(8, Salary);
		employeeStatement.setString(9, Super_ssn);
		employeeStatement.setInt(10, Dno);
		employeeStatement.executeUpdate(); // success, return 1
	}
	public static void departmentInsert() {
		String departmentInsertSQL = "insert into department values (?, ?, ?, ?)";
		PreparedStatement departmentStatement = connect.prepareStatement(departmentInsertSQL);
		String Dname, Mgr_start_date;
		int Dnumber;
		
		departmentStatement.clearParameters();
		departmentStatement.setString(1, Dname);
		departmentStatement.setInt(2, Dnumber);
		departmentStatement.setString(3, Mgr_ssn);
		departmentStatement.setString(4, Mgr_start_date);
		departmentStatement.executeUpdate(); // success, return 1
	}
}
