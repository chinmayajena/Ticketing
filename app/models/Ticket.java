package models;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the tickets database table.
 * 
 */
@Entity
@Table(name="tickets")
public class Ticket implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="ticket_id")
	private String ticketId;
	
	private String customerName;
	
	private String assignedTo;

	private String comments;

	private String queryContext;

	private String status;

	//bi-directional many-to-one association to Csruser
	@ManyToOne
	@JoinColumn(name="user_id")
	private Csruser csruser;

	public Ticket() {
	}

	public String getTicketId() {
		return this.ticketId;
	}

	public void setTicketId(String ticketId) {
		this.ticketId = ticketId;
	}
	
	
	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getAssignedTo() {
		return this.assignedTo;
	}

	public void setAssignedTo(String assignedto) {
		this.assignedTo = assignedto;
	}

	public String getComments() {
		return this.comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public String getQueryContext() {
		return this.queryContext;
	}

	public void setQueryContext(String querycontext) {
		this.queryContext = querycontext;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Csruser getCsruser() {
		return this.csruser;
	}

	public void setCsruser(Csruser csruser) {
		this.csruser = csruser;
	}

}