package models;

import java.sql.Timestamp;

public class ReimbursementRequest {

	private int authorId;
	private double amount;
	private Timestamp submitted;
	private String description;
	private int typeId;

	public ReimbursementRequest() {
		super();
	}

	public ReimbursementRequest(String authorId, String amount, String submitted, String description, int typeId) {
		super();
		this.authorId = Integer.parseInt(authorId);
		this.amount = Double.parseDouble(amount);
		this.submitted = Timestamp.valueOf(submitted);
		this.description = description;
		this.typeId = typeId;
	}
	
	public int getAuthorId() {
		return authorId;
	}

	public void setAuthorId(int authorId) {
		this.authorId = authorId;
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getTypeId() {
		return typeId;
	}

	public void setTypeId(int typeId) {
		this.typeId = typeId;
	}


	@Override
	public String toString() {
		return "ReimbursementRequest [authorId=" + authorId + ", amount=" + amount + ", submitted=" + submitted
				+ ", description=" + description + ", typeId=" + typeId + "]";
	}
}
