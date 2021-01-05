package servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controllers.*;

/**
 * Servlet implementation class FrontController
 */
public class FrontController extends HttpServlet {
	private AuthController authController = new AuthController();
	private ErrorController errorController = new ErrorController();
	private UserController userController = new UserController();
	private ReimbursementController reimbursementController = new ReimbursementController();

	protected void directControlRouter(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		//how to get a value from your init params
//		System.out.println(this.getInitParameter("DefaultRole"));
//		ServletContext sc = this.getServletContext();
//		
//		System.out.println(sc.getInitParameter("JavaCoolFactor"));
		
		//be our front controller
		String URI = req.getRequestURI().substring(req.getContextPath().length(), 
													req.getRequestURI().length());
		String[] splitURI = URI.split("/");
		System.out.println(req.getMethod() + " URI: " + URI);
		switch (splitURI[1]) {
			case "login":{
				switch (req.getMethod()) {
					case "GET":{
						res.setStatus(400);
						res.getWriter().write("Method Not Supported");
						break;
					}
					case "POST":{
						authController.userLogin(req, res);
						break;
					}
					case "PUT":{
						res.setStatus(400);
						res.getWriter().write("Method Not Supported");
						break;
					}
					case "DELETE":{
						res.setStatus(400);
						res.getWriter().write("Method Not Supported");
						break;
					}
					default:{
						res.setStatus(400);
						res.getWriter().write("Method Not Supported");
						break;
					}
				
				}
				break;
			}
			case "users": {
				switch (req.getMethod()) {
					case "GET":{
						if(splitURI.length == 2) {
							userController.findAllUsers(req, res);
						} else if(splitURI.length == 3) {
							int id = Integer.parseInt(splitURI[2]);
							userController.findUserById(req, res, id);
						} else {
							res.setStatus(400);
							res.getWriter().write("Method Not Supported");
						}
						break;
					}
					default:{
						res.setStatus(400);
						res.getWriter().write("Method Not Supported");
						break;
					}
				}

				break;
			}
			case "reimbursements" : {
				switch (req.getMethod()) {
					case "GET": {
						reimbursementController.findAllReimbursements(req, res);
						break;
					}
					case "POST": {
						reimbursementController.saveReimbursement(req, res);
						break;
					}
					case "PUT": {
						reimbursementController.updateReimbursement(req, res, Integer.parseInt(splitURI[2]));
						break;
					}
					default: { 
						res.setStatus(400);
						res.getWriter().write("Method Not Supported");
					}
				}
				break;
			}
			case "statuses" : {
				switch (req.getMethod()) {
					case "GET": {
						reimbursementController.findAllStatuses(req, res);
						break;
					}
					default: { 
						res.setStatus(400);
						res.getWriter().write("Method Not Supported");
					}
				}
				break;
			}
			default:{
				res.setStatus(404);
				res.getWriter().write("No Such Resource");
				break;
			}
			
		}
		
		
		
	}
	
	
	protected void directControl(HttpServletRequest request, HttpServletResponse response) throws IOException {
		//to handle all internal errors/exceptions
		try {
			directControlRouter(request, response);
		}catch (Throwable t) {
			errorController.handle(request, response, t);//go to the error controller
		}
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		directControl(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		directControl(request, response);
	}

	/**
	 * @see HttpServlet#doPut(HttpServletRequest, HttpServletResponse)
	 */
	protected void doPut(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		directControl(request, response);
	}

	/**
	 * @see HttpServlet#doDelete(HttpServletRequest, HttpServletResponse)
	 */
	protected void doDelete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}

}
