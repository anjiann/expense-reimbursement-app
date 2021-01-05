package services;

import java.util.List;

import models.*;
import repositories.ReimbursementDAO;

public class ReimbursementService {
	private ReimbursementDAO reimbursementDAO;
	
	public ReimbursementService(ReimbursementDAO reimbursementDAO) {
		this.reimbursementDAO = reimbursementDAO;
	}
	
	public List<Reimbursement> findReimbursements() {
		return reimbursementDAO.getAllReimbursements();
	}

	public List<Status> findStatuses() {
		return reimbursementDAO.getAllStatuses();
	}

	public void submitReimbursement(ReimbursementRequest rq) {
		reimbursementDAO.postReimbursement(rq);
	}

	public void UpdateReimbursement(Reimbursement r) {
		reimbursementDAO.updateReimbursement(r);
	}
}
