package com.revature.Utils;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionUtil {
	public static Connection getConnection() {

		try {
			Properties props = new Properties();
			InputStream fileInputStream = ConnectionUtil.class.getClassLoader()
					.getResourceAsStream("connection.properties");
			props.load(fileInputStream);
			String details = props.getProperty("condetails");
			Connection conn = DriverManager.getConnection(details);
			return conn;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return null;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}

	}
}
