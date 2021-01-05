package controllers;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.ObjectMapper;

import exceptions.*;
import models.User;
import services.ServiceProvider;
import services.UserService;

public class UserController {

	private UserService us = ServiceProvider.getInstance().getUserService();
	
	private ObjectMapper om = new ObjectMapper();
	

	public void findAllUsers(HttpServletRequest req, HttpServletResponse res) throws IOException {
		
		HttpSession sess = req.getSession();
		
		if(sess.getAttribute("User-Role") == null) {
			throw new UnauthenticatedException();
		} else if(!sess.getAttribute("User-Role").equals(2)) {
			throw new UnauthorizedException();
		}
		List<User> allusers = us.findUsers();
		res.setStatus(200);
		res.getWriter().write(om.writeValueAsString(allusers));
		
	}


	public void findUserById(HttpServletRequest req, HttpServletResponse res, int userId)
		throws IOException {
		User user = us.findUserById(userId);
		res.setStatus(200);
		res.getWriter().write(om.writeValueAsString(user));
	}
	
}
