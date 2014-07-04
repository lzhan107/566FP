package dbutil;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JdbcUtilities {
	public static Connection getConnection(){
		Connection conn = null;
		try {
			Class.forName("org.hsqldb.jdbcDriver");
			conn = DriverManager.getConnection("jdbc:hsqldb:hsql://localhost/", "sa", "");
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return conn;
	}
	
	public static void closeConnectoin(Connection conn){
		if (conn != null){
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void closeStatement(Statement stmt){
		if (stmt != null){
			try {
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void closeResultSet(ResultSet rs){
		if (rs != null){
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void closePreparedStatement(PreparedStatement ps){
		if (ps != null){
			try {
				ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
