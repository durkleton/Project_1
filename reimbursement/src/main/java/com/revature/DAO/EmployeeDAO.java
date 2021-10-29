package com.revature.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.revature.Entities.Employee;

public class EmployeeDAO {

	private static Connection connection = null;
	private static Statement stmt = null;
	private static PreparedStatement pstmt = null;
	private static ResultSet rs = null;

	public static Connection connect() {
		String dbUrl = "jdbc:postgresql://database-1.ckiv1deqqrok.us-east-1.rds.amazonaws.com:5432/postgres";
		try {
			connection = DriverManager.getConnection(dbUrl, "root", "NyiTJEMVt4tUeZU");
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
		return connection;
	}

	public static void close() {
		try {
			if (connection != null)
				connection.close();
			if (stmt != null)
				stmt.close();
			if (pstmt != null)
				pstmt.close();
			if (rs != null)
				rs.close();

		} 
		catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static List<Employee> readAll() {
		List<Employee> employees = new ArrayList<Employee>();
		String query = "select * from reimbursement_management.\"Employee\"";
		connect();
		try {
			stmt = connection.createStatement();
			rs = stmt.executeQuery(query);

			while (rs.next()) {
				Employee employee = new Employee();
				employee.setid(rs.getInt("id"));
				employee.setEmail(rs.getString("email"));
				employee.setPassword(rs.getString("password"));
				employee.setName(rs.getString("name"));
				employee.setManager(rs.getBoolean("manager"));
				employees.add(employee);
			}
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
		close();
		return employees;
	}
	
	public static Employee readById(int id) {
		Employee employee = new Employee();
		String query = "select * from reimbursement_management.\"Employee\" where id=" + id;
		connect();
		try {
			stmt = connection.createStatement();
			rs = stmt.executeQuery(query);
			if (rs.next()) {
				employee.setid(rs.getInt("id"));
				employee.setEmail(rs.getString("email"));
				employee.setPassword(rs.getString("password"));
				employee.setName(rs.getString("name"));
				employee.setManager(rs.getBoolean("manager"));
			}
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
		close();
		return employee;
	}

}
