package models;

import java.sql.Timestamp;
import java.util.Arrays;

import services.ServiceProvider;
import services.UserService;

public class Reimbursement {
    private int id;
	private double amount;
    private Timestamp submitted;
    private Timestamp resolved;
    private String description;
    private Byte[] receipt;
    private int authorId;
    private int resolverId;
    private int statusId;
    private int typeId; 
    
    //==== hack to finish frontend quickly =====
	private String author;
    private String resolver;
    private String status;
    private String type;
    
    // ==========================
    public Reimbursement() {
    	
    }
    public Reimbursement(int id, double amount, Timestamp submitted, Timestamp resolved, String description, int authorId, int typeId, int resolverId, int statusId) {
        this.id = id;
        this.amount = amount;
        this.submitted = submitted;
        this.resolved = resolved;
        this.description = description;
        this.authorId = authorId;
        this.resolverId = resolverId;
        this.statusId = statusId;
        this.typeId = typeId;
        
        UserService us = ServiceProvider.getInstance().getUserService();
        this.author = us.findUserById(authorId).getUsername();
        
        User resolver = us.findUserById(resolverId);
        this.resolver = resolver == null ? "" : resolver.getUsername();
        
        switch(statusId) {
	        case 1: status = "Approved"; break;
	        case 3: status = "Denied"; break;
	        default: status = "Pending"; break;
        }
        
        switch(typeId) {
	        case 1: setType("Lodging"); break;
	        case 2: setType("Food"); break;
	        case 3: setType("Travel"); break;
	        case 4: setType("Other"); break;
        }
    }
    
    public Reimbursement(int id, double amount, String submitted, int authorId, int typeId) {
        this(id, amount, Timestamp.valueOf(submitted), null, "", authorId, 0, 2, typeId);
    }
    
    public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public Timestamp getSubmitted() {
		return submitted;
	}

	public void setSubmitted(Timestamp submitted) {
		this.submitted = submitted;
	}

	public Timestamp getResolved() {
		return resolved;
	}

	public void setResolved(Timestamp resolved) {
		this.resolved = resolved;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Byte[] getReceipt() {
		return receipt;
	}

	public void setReceipt(Byte[] receipt) {
		this.receipt = receipt;
	}

	public int getAuthorId() {
		return authorId;
	}

	public void setAuthorId(int authorId) {
		this.authorId = authorId;
	}

	public int getResolverId() {
		return resolverId;
	}

	public void setResolverId(int resolverId) {
		this.resolverId = resolverId;
	}

	public int getStatusId() {
		return statusId;
	}

	public void setStatusId(int statusId) {
		this.statusId = statusId;
	}

	public int getTypeId() {
		return typeId;
	}

	public void setTypeId(int typeId) {
		this.typeId = typeId;
	}
	
	@Override
	public String toString() {
		return "Reimbursement [id=" + id + ", amount=" + amount + ", submitted=" + submitted + ", resolved=" + resolved
				+ ", description=" + description + ", receipt=" + Arrays.toString(receipt) + ", authorId=" + authorId
				+ ", resolverId=" + resolverId + ", statusId=" + statusId + ", typeId=" + typeId + ", author=" + author
				+ ", resolver=" + resolver + ", status=" + status + ", type=" + type + "]";
	}
	
    public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getResolver() {
		return resolver;
	}

	public void setResolver(String resolver) {
		this.resolver = resolver;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
}
