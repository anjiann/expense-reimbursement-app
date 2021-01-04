package services;

import java.util.List;

import models.*;
import repositories.UserDAO;

public class UserService {	
	private UserDAO userDAO;
    User currentUser;
	
	public UserService(UserDAO userDAO) {
		this.userDAO = userDAO;
	}
	
	public User login(String username, String password) {
		User user = userDAO.findUserByCredentials(username, password);
        currentUser = user;

		return user;
	}
	
	public List<User> findAllUsers() {
        if (currentUser.getRoleId() != 2) {
            return userDAO.GetAllUsers();
        }
        return null;
	}
}
