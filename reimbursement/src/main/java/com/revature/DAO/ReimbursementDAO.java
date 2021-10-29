package com.revature.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.revature.Entities.Reimbursement;

public class ReimbursementDAO {
    
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

	public static List<Reimbursement> readAll() {
		List<Reimbursement> reimbursements = new ArrayList<Reimbursement>();
		String query = "select * from reimbursement_management.\"Reimbursement\"";
		connect();
		try {
			stmt = connection.createStatement();
			rs = stmt.executeQuery(query);

			while (rs.next()) {
				Reimbursement reimbursement = new Reimbursement();
				reimbursement.setRid(rs.getInt("rid"));
				reimbursement.setUid(rs.getInt("uid"));
				reimbursement.setAmount(rs.getDouble("amount"));
				reimbursement.setDate(rs.getString("date"));
				reimbursement.setStatus(rs.getInt("status"));
				reimbursement.setComment(rs.getString("comment"));
				reimbursements.add(reimbursement);
			}
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
		close();
		return reimbursements;
	}

	public static Reimbursement readByRid(int rid) {
		Reimbursement reimbursement = new Reimbursement();
		String query = "select * from reimbursement_management.\"Reimbursement\" where rid=" + rid;
		connect();
		try {
			stmt = connection.createStatement();
			rs = stmt.executeQuery(query);
			if (rs.next()) {
				reimbursement.setRid(rs.getInt("rid"));
				reimbursement.setUid(rs.getInt("uid"));
				reimbursement.setAmount(rs.getDouble("amount"));
				reimbursement.setDate(rs.getString("date"));
				reimbursement.setStatus(rs.getInt("status"));
				reimbursement.setComment(rs.getString("comment"));
			}
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
		close();
		return reimbursement;
	}

	public static List<Reimbursement> readByUid(int uid) {
		List<Reimbursement> reimbursements = new ArrayList<Reimbursement>();
		String query = "select * from reimbursement_management.\"Reimbursement\" where uid=" + uid;
		connect();
		try {
			stmt = connection.createStatement();
			rs = stmt.executeQuery(query);

			while (rs.next()) {
				Reimbursement reimbursement = new Reimbursement();
				reimbursement.setRid(rs.getInt("rid"));
				reimbursement.setUid(rs.getInt("uid"));
				reimbursement.setAmount(rs.getDouble("amount"));
				reimbursement.setDate(rs.getString("date"));
				reimbursement.setStatus(rs.getInt("status"));
				reimbursement.setComment(rs.getString("comment"));
				reimbursements.add(reimbursement);
			}
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
		close();
		return reimbursements;
	}

	public static void update(String comment, int status, Reimbursement reimbursement) {
		
		String query = "update reimbursement_management.\"Reimbursement\" set comment=?, status=? where rid=?";
		connect();
		try {
			pstmt = connection.prepareStatement(query);
			pstmt.setString(1, comment);
			pstmt.setInt(2, status);
			pstmt.setInt(3, reimbursement.getRid());
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		close();
	}

	public static void save(Reimbursement reimbursement) {
		String query = "insert into reimbursement_management.\"Reimbursement\" (uid,amount,date,status,comment) values (?,?,?,?,?)";
		connect();
		try {
			pstmt = connection.prepareStatement(query);
			pstmt.setInt(1, reimbursement.getUid());
			pstmt.setDouble(2, reimbursement.getAmount());
			pstmt.setString(3, reimbursement.getDate());
			pstmt.setInt(4, reimbursement.getStatus());
			pstmt.setString(5, reimbursement.getComment());
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		close();
	}
}
