package controllers;

import com.avaje.ebean.Ebean;

import models.Csruser;
import play.*;
import play.mvc.*;
import play.data.*;
import static play.data.Form.form;

import views.html.*;

public class Application extends Controller {
  
   	 public static Result index() {
        	return ok(index.render(session("email")));
    		}
	 public static Result login() {
	    return ok(login.render(form(Login.class),""));
	}
	public static Result authenticate() {
	    Form<Login> loginForm = form(Login.class).bindFromRequest();
	    
	    String q = "find con where userEmail=:email and userPassword=:pwd"; 
				
				Csruser con = Ebean
						.createQuery(Csruser.class, q)
						.setParameter("email",loginForm.get().email)
						.setParameter("pwd",loginForm.get().password)
						.findUnique();
	    
		    if (con==null) {
		    	Logger.info("Anonymous USER");
			return badRequest(login.render(loginForm, "Authorization Failed!"));
			
		    } else {
			session().clear();
			session("email", con.getUserName());
			return redirect("/index");
    }
	}

	/*public String validate() {
	    if (User.authenticate(email, password) == null) {
	      return "Invalid user or password";
	    }
	    return null;
	}
*/


public static class Login {

    public String email;
    public String password;

}

}
