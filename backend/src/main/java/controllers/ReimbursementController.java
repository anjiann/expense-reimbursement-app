package controllers;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import exceptions.UnauthenticatedException;
import exceptions.UnauthorizedException;
import models.*;
import services.ReimbursementService;
import services.ServiceProvider;

public class ReimbursementController {
	private ReimbursementService rs = ServiceProvider.getInstance().getReimbursementService();
	
	private ObjectMapper om = new ObjectMapper();
	

	public void findAllReimbursements(HttpServletRequest req, HttpServletResponse res) 
			throws IOException {
		
//		HttpSession sess = req.getSession();
//		
//		if(sess.getAttribute("User-Role") == null) {
//			throw new UnauthenticatedException();
//		} else if(!sess.getAttribute("User-Role").equals(2)) {
//			throw new UnauthorizedException();
//		}
		List<Reimbursement> allReimbursements = rs.findReimbursements();
		res.setStatus(200);
		res.getWriter().write(om.writeValueAsString(allReimbursements));
		
	}

	public void findAllStatuses(HttpServletRequest req, HttpServletResponse res) 
		throws IOException {
		List<Status> allStatuses = rs.findStatuses();
		res.setStatus(200);
		res.getWriter().write(om.writeValueAsString(allStatuses));
		
	}

	public void saveReimbursement(HttpServletRequest req, HttpServletResponse res) 
			throws IOException {
		res.setStatus(200);
		ReimbursementRequest rq = om.readValue(req.getInputStream(), ReimbursementRequest.class);
		rs.submitReimbursement(rq);
		res.setStatus(200);
		res.setHeader("Content-Type", "application/json");
		res.getWriter().write(om.writeValueAsString(rq));
	}

	public void updateReimbursement(HttpServletRequest req, HttpServletResponse res, int reimbId) 
			throws IOException {
		Reimbursement r = om.readValue(req.getInputStream(), Reimbursement.class);
		r.setId(reimbId);
		rs.UpdateReimbursement(r);
		res.setStatus(200);
		res.setHeader("Content-Type", "application/json");
		res.getWriter().write(om.writeValueAsString(r));
	}
	
}
