import java.sql.*;

public class Update {
	public static Connection connect;
	Update(Connection connect) {
		Update.connect = connect;
	}
	public static void updateRequest(String tableName, String updateColumn, String updateValue, 
			String primaryKeyName, String primaryKeyValue ) {
		String updateSQL = "UPDATE " + tableName 
				+ " SET " + updateColumn + " = " +  updateValue
				+ " WHERE " + primaryKeyName + " = " + primaryKeyValue;
		try {
			PreparedStatement updateStatement = connect.prepareStatement(updateSQL);
			updateStatement.executeUpdate();
		} catch(SQLException deleteRequestError) {
			System.out.println("delete error : " + deleteRequestError);
		}
	}

}
