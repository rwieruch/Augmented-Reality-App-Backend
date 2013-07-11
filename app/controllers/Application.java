package controllers;

import org.codehaus.jackson.JsonNode;

import flexjson.JSONSerializer;

import models.Note;
import models.ViewableType;
import models.Session;
import models.User;
import play.*;
import play.data.Form;
import play.libs.Json;
import play.mvc.*;

import views.html.*;

public class Application extends Controller implements ViewableType {
  
    public static Result index() {	
    	return ok("Everything works just fine!");
    }
    
	/**
	 * Initialize DB with sample data.
	 */
    public static Result initializeDB() {
  		
    	//Create user.
    	
		// Required values.
		String email = "bob@gmx.de";
		String name = "bob";
		String password = "geheim";
		
		// Additional values.
		String type = "";
		String realname = "";
		String image = "";
		String info = "";
		float x = 10;
		float y = 11;
		float z = 12;		

		User user = new User(email, name, password, type, realname, image, info, x, y, z);
		User.create(user);
				
		// Create notes.
		
		Note note1 = new Note("Notiz Einsä", "text", 4, 5, 6, PRIVATE, true, user);
		Note note2 = new Note("Notiz Zweiö", "text", 2, 3, 6, PUBLIC, true, user);
		Note note3 = new Note("Notiz Dreiü", "text", 1, 5, 7, PROTECTED, false, user);
		Note.create(note1);
		Note.create(note2);
		Note.create(note3);
		
		User postedUser = User.get("bob@gmx.de");
		
		Session session = new Session("bob@gmx.de", "geheim", postedUser);
		Session.create(session);
		
		return ok(new JSONSerializer().include("notes").exclude("password", "class", "notes.class").serialize(session));
    }
}
