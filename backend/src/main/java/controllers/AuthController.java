package controllers;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.ObjectMapper;

import models.Credentials;
import models.User;
import services.ServiceProvider;
import services.UserService;

public class AuthController {
	
	private UserService us = ServiceProvider.getInstance().getUserService();
	
	private ObjectMapper om = new ObjectMapper();
	
	//actually do the request
	public void userLogin(HttpServletRequest req, HttpServletResponse res) throws IOException {
		Credentials cred = om.readValue(req.getInputStream(), Credentials.class);
		User u = us.login(cred.getUsername(), cred.getPassword());
		
		HttpSession sess = req.getSession();
		sess.setAttribute("User-Role", "Admin");
		
		if (u != null) {
			sess.setAttribute("User-Role", u.getRoleId());
			res.setStatus(200);
			res.getWriter().write(om.writeValueAsString(u));
		}
		else {
			res.sendError(401, "Invalid Authentication");
			res.getWriter().write(om.writeValueAsString(u));
		}
	}
}
