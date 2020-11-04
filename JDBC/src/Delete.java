import java.sql.*;

public class Delete {
	public static Connection connect;
	Delete(Connection connect) {
		Delete.connect = connect;
	}
	
	public static void deleteRequest(String tableName, String primaryKeyName, String primaryKeyValue )  {
		String deleteSQL = "DELETE FROM " + tableName 
				+ " WHERE " + primaryKeyName + " = " + primaryKeyValue;
		System.out.println(deleteSQL);
		try {
			PreparedStatement deleteStatement = connect.prepareStatement(deleteSQL);
			deleteStatement.executeUpdate();
		} catch(SQLException deleteRequestError) {
			System.out.println("delete error : " + deleteRequestError);
		}
	}

}
