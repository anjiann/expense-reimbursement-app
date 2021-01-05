package repositories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import models.*;

public class ReimbursementDAO extends DAO {

	public List<Reimbursement> getAllReimbursements() {
	    Connection conn = cf.getConnection();
		String sql = "SELECT * FROM reimbursements;";
		List<Reimbursement> reimbursements = new ArrayList<Reimbursement>();
		
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ResultSet res = ps.executeQuery();
			
			while (res.next()) {
                int id = res.getInt("reimb_id");
                double amount = res.getDouble("amount");
                Timestamp submitted = res.getTimestamp("submitted");
                Timestamp resolved = res.getTimestamp("resolved");
                String description = res.getString("description");
                int authorId = res.getInt("author_id");
                int typeId = res.getInt("type_id");
                int resolverId = res.getInt("resolver_id");
                int statusId = res.getInt("status_id");
                
                description = description == null ? "" : description;
                statusId = statusId == 0 ? 2 : statusId;
                
                Reimbursement reimbursement = new Reimbursement(id, amount, submitted, 
                		resolved, description, authorId, typeId, resolverId, statusId);
                reimbursements.add(reimbursement);
			}
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
		return reimbursements;
	}

	public List<Status> getAllStatuses() {
	    Connection conn = cf.getConnection();
		List<Status> allStatuses = new ArrayList<>();
		String sql = "SELECT * FROM reimbursement_status;";
		
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ResultSet res = ps.executeQuery();
			
			while (res.next()) {
				int statusId = res.getInt("status_id");
				String statusType = res.getString("reimb_status");
				
				Status status = new Status(statusId, statusType);
				allStatuses.add(status);
			}
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
		return allStatuses;
	}

	public void postReimbursement(ReimbursementRequest rq) {
		Connection conn = cf.getConnection();
		String sql = "insert into reimbursements(\"amount\", \"submitted\", \"description\", \"author_id\", \"status_id\", \"type_id\")"
				+ "values(?, ?, ?, ?, ?, ?)";
		
		System.out.println(rq.toString());
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setDouble(1, rq.getAmount());
			ps.setTimestamp(2, rq.getSubmitted());
			ps.setString(3, rq.getDescription());
			ps.setInt(4, rq.getAuthorId());
			ps.setInt(5, 2);
			ps.setInt(6, rq.getTypeId());
			System.out.println("type id before db: " + rq.getTypeId());
			ps.executeUpdate();
		} catch(SQLException e) {
			e.printStackTrace();
		}
	}

	public void updateReimbursement(Reimbursement reimbursement) {
		Connection conn = cf.getConnection();
		String sql = "UPDATE reimbursements SET amount = ?, submitted = ?, resolved = ?, description = ?, author_id = ?, resolver_id = ?, status_id = ?, type_id = ? WHERE reimb_id = ?;";
		System.out.println(reimbursement);

		try {	
			PreparedStatement ps = conn.prepareStatement(sql);
	        ps.setDouble(1, reimbursement.getAmount());
	        ps.setTimestamp(2, reimbursement.getSubmitted());
	        ps.setTimestamp(3, reimbursement.getResolved());
	        ps.setString(4, reimbursement.getDescription());
	        ps.setInt(5, reimbursement.getAuthorId());
	        ps.setInt(6, reimbursement.getResolverId());
	        ps.setInt(7, reimbursement.getStatusId());
	        ps.setInt(8, reimbursement.getTypeId());
	        ps.setInt(9, reimbursement.getId());
			
			ps.executeUpdate();
		} catch(SQLException e) {
			e.printStackTrace();
		}
	}

}
