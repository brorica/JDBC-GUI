import java.awt.FlowLayout;
import java.sql.*;
import java.util.Scanner;

import component.*;
import SQL.*;

import javax.swing.*;

public class main {
	public static JFrame frame;
	public static void main(String args[]) {
		Scanner scanner = new Scanner(System.in);
		frame = new JFrame();
		frame.setTitle("Information Retrieval System");
		frame.setLayout(new FlowLayout());
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		SELECT select = new SELECT(frame);
		UPDATE update = new UPDATE(frame);
		DELETE delete = new DELETE(frame);
		
		frame.setSize(1000, 500);
		frame.setVisible(true);

		Connection connect = null;
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
			SelectSQL SELECT_SQL = new SelectSQL(connect);
		}catch(SQLException connectError) {
			System.out.println("connect error : " + connectError);
			connectError.printStackTrace();
		}
		
		// connect close
		try {
			if(connect != null)
				connect.close();	
		}catch(SQLException connectCloseError) {
			System.out.println("connection close error : " + connectCloseError);
		}
	}

}
