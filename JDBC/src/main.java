import java.sql.*;
import java.util.Scanner;

import component.SELECT;
import component.UPDATE;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.*;

public class main {
	public static Frame frame;
	public static void main(String args[]) {
		Scanner scanner = new Scanner(System.in);
		frame = new Frame();
		frame.setTitle("Information Retrieval System");
		frame.setLayout(new FlowLayout());
		frame.setBounds(500, 100, 400, 400);
		

		SELECT select = new SELECT(frame);
		UPDATE update = new UPDATE(frame);
		
		frame.setLayout(null);
		frame.setVisible(true);
		
		frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});

		/*
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
			String password = "root";
			String database = "mydb";
			String url = "jdbc:mysql://localhost:3306/" + database + "?serverTimezone=UTC";
			connect = DriverManager.getConnection(url,user,password);
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
		*/
	}

}
