package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import dbutil.JdbcUtilities;

@Component
public class UserManager {

	public void createUsers(List<User> users) {
		Connection conn = JdbcUtilities.getConnection();
		Statement stmt = null;
		try {
			stmt = conn.createStatement();
			for (int i = 0; i < users.size(); i++) {
				stmt.executeQuery("insert into user values ("
						+ users.get(i).getId() + ",'" + users.get(i).getName()
						+ "'," + users.get(i).getRoleId() + ","
						+ users.get(i).getRatings() + ")");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtilities.closeStatement(stmt);
			JdbcUtilities.closeConnectoin(conn);
		}
	}
	
	public void creatUser(User user) {
		Connection conn = JdbcUtilities.getConnection();
		Statement stmt = null;
		try {
			stmt = conn.createStatement();
				stmt.executeQuery("insert into user values ("
						+ user.getId() + ",'" + user.getName()
						+ "'," + user.getRoleId() + ","
						+ user.getRatings() + ")");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtilities.closeStatement(stmt);
			JdbcUtilities.closeConnectoin(conn);
		}
	}

	public void deleteUser(int id) {
		Connection conn = JdbcUtilities.getConnection();
		Statement stmt = null;
		try {
			stmt = conn.createStatement();
			stmt.executeQuery("delete from user where user_id = " + id);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtilities.closeStatement(stmt);
			JdbcUtilities.closeConnectoin(conn);
		}
	}

	public void editUser(User user) {
		Connection conn = JdbcUtilities.getConnection();
		PreparedStatement pstmt = null;
		String sql = "update user set user_name = ?, user_role_id = ?, ratings = ? where user_id = ?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, user.getName());
			pstmt.setInt(2, user.getRoleId());
			pstmt.setInt(3, user.getRatings());
			pstmt.setInt(4, user.getId());
			pstmt.executeUpdate();
			System.out.println(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtilities.closeStatement(pstmt);
			JdbcUtilities.closeConnectoin(conn);
		}
	}

	public void copyUser(User user) {
		Connection conn = JdbcUtilities.getConnection();
		Statement stmt = null;
		ResultSet rs = null;
		int maxId = 0;
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery("select max(user_id) from user");
			while (rs.next()) {
				maxId = rs.getInt(1);
			}
			maxId += 1;
			stmt.executeQuery("insert into user values (" + maxId + ",'"
					+ user.getName() + "'," + user.getRoleId() + ","
					+ user.getRatings() + ")");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtilities.closeResultSet(rs);
			JdbcUtilities.closeStatement(stmt);
			JdbcUtilities.closeConnectoin(conn);
		}
	}

	public void createUserRole(List<UserRole> roles) {
		Connection conn = JdbcUtilities.getConnection();
		Statement stmt = null;
		try {
			stmt = conn.createStatement();
			for (int i = 0; i < roles.size(); i++) {
				stmt.executeQuery("insert into USER_ROLE values ("
						+ roles.get(i).getId() + "," + "'"
						+ roles.get(i).getName() + "'" + ")");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtilities.closeStatement(stmt);
			JdbcUtilities.closeConnectoin(conn);
		}
	}

	public boolean hasUser() {
		Connection conn = JdbcUtilities.getConnection();
		Statement stmt = null;
		ResultSet rs = null;
		int num = 0;
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery("select count(*) from user;");
			while (rs.next()) {
				num = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtilities.closeResultSet(rs);
			JdbcUtilities.closeStatement(stmt);
			JdbcUtilities.closeConnectoin(conn);
		}

		if (num == 0)
			return false;
		else
			return true;
	}

	public boolean hasUserRole() {
		Connection conn = JdbcUtilities.getConnection();
		Statement stmt = null;
		ResultSet rs = null;
		int num = 0;
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery("select count(*) from user_role;");
			while (rs.next()) {
				num = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtilities.closeResultSet(rs);
			JdbcUtilities.closeStatement(stmt);
			JdbcUtilities.closeConnectoin(conn);
		}

		if (num == 0)
			return false;
		else
			return true;
	}

	public User getUserById(int id){
		Connection conn = JdbcUtilities.getConnection();
		Statement stmt = null;
		ResultSet rs = null;
		User user = new User();
		try {
			stmt = conn.createStatement();
				rs = stmt
						.executeQuery("select * from user where user_id = " + id);
			while (rs.next()) {
				user.setId(rs.getInt(1));
				user.setName(rs.getString(2));
				user.setRoleId(rs.getInt(3));
				user.setRatings(rs.getInt(4));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtilities.closeResultSet(rs);
			JdbcUtilities.closeStatement(stmt);
			JdbcUtilities.closeConnectoin(conn);
		}
		return user;
	}
	
	public List<User> getUsersByName(String name) {
		Connection conn = JdbcUtilities.getConnection();
		Statement stmt = null;
		ResultSet rs = null;
		List<User> users = new ArrayList<User>();
		try {
			stmt = conn.createStatement();
			if (name.length() > 0) {
				rs = stmt
						.executeQuery("select * from user where user_name like '"
								+ name + "%'");
			}
			while (rs.next()) {
				User user = new User();
				user.setId(rs.getInt(1));
				user.setName(rs.getString(2));
				user.setRoleId(rs.getInt(3));
				user.setRatings(rs.getInt(4));
				users.add(user);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtilities.closeResultSet(rs);
			JdbcUtilities.closeStatement(stmt);
			JdbcUtilities.closeConnectoin(conn);
		}
		return users;
	}

	public List<UserRole> gerUserRoles(){
		Connection conn = JdbcUtilities.getConnection();
		Statement stmt = null;
		ResultSet rs = null;
		List<UserRole> roles = new ArrayList<UserRole>();
		
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery("select * from user_role;");
			while (rs.next()){
				UserRole role = new UserRole();
				role.setId(rs.getInt(1));
				role.setName(rs.getString(2));
				roles.add(role);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return roles;
	}
	
	public List<User> getUsers(){
		Connection conn = JdbcUtilities.getConnection();
		Statement stmt = null;
		ResultSet rs = null;
		List<User> users = new ArrayList<User>();
		
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery("select * from user;");
			while (rs.next()){
				User user = new User();
				user.setId(rs.getInt(1));
				user.setName(rs.getString(2));
				user.setRoleId(rs.getInt(3));
				user.setRatings(rs.getInt(4));
				users.add(user);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return users;
	}
}
