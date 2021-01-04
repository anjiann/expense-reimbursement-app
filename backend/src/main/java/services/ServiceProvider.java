package services;

import repositories.UserDAO;

public class ServiceProvider {
	private static ServiceProvider instance = new ServiceProvider();
	private UserService userService;
	
	private ServiceProvider() {
		UserDAO userDAO = new UserDAO();
		userService = new UserService(userDAO);
	}
	
	public static ServiceProvider getInstance() {
		return instance;
	}
	
	public UserService getUserService() {
		return userService;
	}
}
