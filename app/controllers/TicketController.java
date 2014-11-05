package controllers;
import static play.data.Form.form;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

import models.Csruser;
import models.Ticket;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.login;
import views.html.logticket;
import views.html.tickets;
import views.html.success;


import com.avaje.ebean.Ebean;
import com.avaje.ebean.TxRunnable;

import controllers.Application.Login;



public class TicketController extends Controller {

	 public static Result logTicktet() {
		 
		if(session("email")==null) {
	     	return ok(login.render(form(Login.class),""));

		}else{
			return ok(logticket.render(form(Ticket.class),session("email")));
		}
 	}
	 
	 public static Result saveTicktet() {
		 final Form<Ticket> logTicketForm = form(Ticket.class).bindFromRequest();
		
		 Ebean.execute(new TxRunnable() {  
			  public void run() {   
		 String q = "find con where userEmail=:email"; 
			
			Csruser con = Ebean
					.createQuery(Csruser.class, q)
					.setParameter("email",session("email"))
					.findUnique();
			
			
				String id = logTicketForm.get().getTicketId();
				if (id !=null){
				String q1 = "find con where ticketId=:id"; 
				Ticket con1 = Ebean
						.createQuery(Ticket.class, q1)
						.setParameter("id",id).findUnique();
				 con1.setCsruser(con);
				 con1.setCustomerName(logTicketForm.get().getCustomerName());
				 con1.setQueryContext(logTicketForm.get().getQueryContext());
				 con1.setComments(logTicketForm.get().getComments());
				 con1.setAssignedTo(logTicketForm.get().getAssignedTo());
				 con1.setStatus(logTicketForm.get().getStatus());
				 Ebean.save(con1);
					
				
				}else{
			
		 Ticket newTicket = new Ticket();
		 UUID idOne = UUID.randomUUID();
		 newTicket.setTicketId(String.valueOf(idOne));
		 newTicket.setCsruser(con);
		 newTicket.setCustomerName(logTicketForm.get().getCustomerName());
		 newTicket.setQueryContext(logTicketForm.get().getQueryContext());
		 newTicket.setComments(logTicketForm.get().getComments());
		 newTicket.setAssignedTo(logTicketForm.get().getAssignedTo());
		 newTicket.setStatus(logTicketForm.get().getStatus());
		 Ebean.save(newTicket);
				}
			  }
		});
		 
		 return ok(success.render());
		 
	 }
	 
	 
	 public static Result tickets() {
		 
		 return ok(tickets.render(form(Ticket.class),""));
	 }
	 
	 
	 public static Result showTicket(){
		 String id = request().getQueryString("id");
		 
		// String q = "find con where ticketId=:id"; 
			
			Ticket con = Ebean
					.find(Ticket.class, id);
					
			
			String jsonResponse = "\"id\":\""+con.getTicketId()+"\","+
					"\"area\":\""+con.getQueryContext()+"\","+
					"\"custname\":\""+con.getCustomerName()+"\","+
					"\"comment\":\""+con.getComments()+"\","+
					"\"assign\":\""+con.getAssignedTo()+"\","+
					"\"status\":\""+con.getStatus()+"\"";
		
				
		 return ok("{"+jsonResponse+"}");
	 }
	 
	 public static Result getTickets(){
		 String status = request().getQueryString("status");
		 StringBuilder sb = new StringBuilder();
		 String q = "find con where status=:stat"; 
			
			List<Ticket> con = Ebean
					.createQuery(Ticket.class, q)
					.setParameter("stat",status).findList();
			Iterator<Ticket> it = con.iterator();
			int counter = 0;
			while(it.hasNext()){
				Ticket t = it.next();
				sb.append("\""+counter+"\":\""+t.getTicketId()+"\",");
				counter++;
				
			}
		 
		String jsonString =sb.toString();
		String jsonResponse = jsonString.substring(0, jsonString.length()-1);
				
		 return ok("{"+jsonResponse+"}");
	 }
	 
}
