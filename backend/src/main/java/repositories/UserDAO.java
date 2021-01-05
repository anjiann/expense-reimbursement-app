package repositories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import models.User;

public class UserDAO extends DAO {

	public User findUserByCredentials(String username, String password) {
		Connection conn = cf.getConnection();
		String queryString = "SELECT * FROM users WHERE \"username\" = ? "
				+ "AND \"password\" = ?;";

		User user = null;
		try {
			PreparedStatement query = conn.prepareStatement(queryString);
			query.setString(1, username);
			query.setString(2, password);
			
			ResultSet res = query.executeQuery();

			if(res.next()) {
                int id = res.getInt("user_id");
                int roleId = res.getInt("role_id");
                String firstName = res.getString("first_name");
                String lastName = res.getString("last_name");
				String email = res.getString("email");

				user = new User(id, roleId, username, firstName, lastName, email);
			} 
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
		return user;
	}

	public List<User> GetAllUsers() {
		Connection conn = cf.getConnection();
		
		String sql = "SELECT * FROM users;";

		List<User> users = new ArrayList<User>();
		
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ResultSet rs = ps.executeQuery();
			
			while (rs.next()) {
				int id = rs.getInt("users_id");
                int roleId = rs.getInt("role_id");
				String username = rs.getString("username");
                String firstName = rs.getString("first_name");
                String lastName = rs.getString("last_name");
				String email = rs.getString("email");

				User u = new User(id, roleId, username, firstName, lastName, email);
                users.add(u);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return users;
	}

	public User getUserById(int userId) {
		try {
			Connection conn = cf.getConnection();

			String queryString = "select * from users where \"user_id\" = ? ;";
			PreparedStatement query = conn.prepareStatement(queryString);
			query.setInt(1, userId);
			
			ResultSet rs = query.executeQuery();

			if(rs.next()) {
				int id = rs.getInt("user_id");
                int roleId = rs.getInt("role_id");
				String username = rs.getString("username");
                String firstName = rs.getString("first_name");
                String lastName = rs.getString("last_name");
				String email = rs.getString("email");

				User user = new User(id, roleId, username, firstName, lastName, email);
				return user;
			} 
			
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}	
}
