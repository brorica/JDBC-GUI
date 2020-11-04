import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Select {
	public static Connection connect;
	Select(	Connection connect){
		Select.connect = connect;
	}
	/*
	try {
		String dbacct, password, dbname, lname, ssn;
		Double salary;		
		String sql1 = "select Lname, Salary from Employee where Ssn=?";
		
		String[] commands = new String [4];
		PreparedStatement p = connect.prepareStatement(sql1);
		System.out.println("SSn : ");
		ssn = scanner.nextLine();
		
		ResultSet r = p.executeQuery();
		
		while(r.next()) {
			lname = r.getString(1);
			salary=r.getDouble(2);
			System.out.println(lname + " " + salary);
		}
	} catch(SQLException e) {
		
	}
	*/
}
