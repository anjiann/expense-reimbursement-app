package services;

import repositories.ReimbursementDAO;
import repositories.UserDAO;

public class ServiceProvider {
	private static ServiceProvider instance = new ServiceProvider();
	private UserService userService;
	private ReimbursementService reimbursementService;
	
	private ServiceProvider() {
		UserDAO userDAO = new UserDAO();
		userService = new UserService(userDAO);
		
		ReimbursementDAO rd = new ReimbursementDAO();
		reimbursementService = new ReimbursementService(rd);
	}
	
	public static ServiceProvider getInstance() {
		return instance;
	}
	
	public UserService getUserService() {
		return userService;
	}

	public ReimbursementService getReimbursementService() {
		return reimbursementService;
	}
}
