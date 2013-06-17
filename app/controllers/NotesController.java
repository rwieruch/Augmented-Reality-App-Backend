package controllers;

import java.util.Map;

import org.codehaus.jackson.JsonNode;

import flexjson.JSONSerializer;

import models.Note;
import models.Session;
import models.User;

import play.Logger;
import play.api.libs.json.JsObject;
import play.api.libs.json.JsValueSerializer;
import play.data.Form;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Http;
import play.mvc.Result;

/**
 * Controller for notes and CRUD operations.
 * @author Robin Wieruch
 *
 */
public class NotesController extends Controller {
	
	// Index
	// Returns all nodes. Later by owner, public or owner list.
	
	public static Result indexJson() {
		
		// Check for header and match with valid user in db.
		String token = request().getHeader("token");
		Session session = Session.authenticate(token); 
        if(session == null) {
        	return unauthorized("Unauthorized");
        } else { 	
        	return ok(new JSONSerializer().exclude("class", "user.class", "user.password").serialize(Note.all()));
        }
	}
    
	// Create
    
    public static Result createJson() {
    	JsonNode json = request().body().asJson();
    	if(json == null) {
    		return badRequest("Expecting Json data");
    	} else {
    		
    		// Check for header and match with valid user in db.
    		String token = request().getHeader("token");
    		Session session = Session.authenticate(token);    		
	        if(session == null) {
	        	return unauthorized("Unauthorized");
	        } else { 	
	        	User authUser = Session.getAuthUser(token);
	        	
	    		String text = json.findPath("text").getTextValue();
                String viewable = json.findPath("viewable").getTextValue();
                String editable = json.findPath("editable").getTextValue();
                String x = json.findPath("x").getTextValue();
                String y = json.findPath("y").getTextValue();
                String z = json.findPath("z").getTextValue();
	    		if(text == null | viewable == null | editable == null | x == null | y == null | z == null) {
	    			return badRequest("Missing parameter!");
	    		} else {
	    			Note note = new Note(text, x, y, z, viewable, editable, authUser);
	    			Note.create(note);
	    			
	    			JSONSerializer noteSerializer = new JSONSerializer().exclude("class", "user.class", "user.password");

	    			return ok(noteSerializer.serialize(note));
	    		}
	        }
    	}
    }

    // Get

    public static Result getJson(Long id) {
        // Check for header and match with valid user in db.
    	String token = request().getHeader("token");
		Session session = Session.authenticate(token); 
        if(session == null) {
            return unauthorized("Unauthorized");
        } else { 
        	
            Note note = Note.get(id);
            
            System.out.println("GET2");
            System.out.println("NOTE: ID: " + note.id);
            System.out.println(" text: " + note.text);
            System.out.println("note.user.email" + note.user.email);
            
            JSONSerializer noteSerializer = new JSONSerializer().include(
                            "id",
                            "text",
                            "user.email",
                            "user.name").exclude("*");
            System.out.println("GET3");
            return ok(noteSerializer.serialize(note));
        }
    }

    // Update

    public static Result updateJson(Long id) {
        JsonNode json = request().body().asJson();
        if(json == null) {
            return badRequest("Expecting Json data");
        } else {
            // Check for header and match with valid user in db.
        	String token = request().getHeader("token");
    		Session session = Session.authenticate(token); 
            if(session == null) {
                return unauthorized("Unauthorized");
            } else {    
                String text = json.findPath("text").getTextValue();
                String viewable = json.findPath("viewable").getTextValue();
                String editable = json.findPath("editable").getTextValue();
                if(text == null | viewable == null | editable == null) {
                    return badRequest("Missing parameter!");
                } else {
                    Note note = Note.get(id);
                    Note updatedNote = Note.update(note, text, viewable, editable);
                    
                    JSONSerializer noteSerializer = new JSONSerializer().include(
                            "id",
                            "text",
                            "user.email",
                            "user.name").exclude("*");
                    return ok(noteSerializer.serialize(updatedNote));
                }
            }
        }
    }
    
    // Destroy
  
    public static Result destroyJson(Long id) {
    	
    	// Check for header and match with valid user in db.
    	String token = request().getHeader("token");
		Session session = Session.authenticate(token); 
        if(session == null) {
        	return unauthorized("Unauthorized");
        } else { 
        	//Note note = Note.get(id);
        	Note.delete(id);
        	return ok();
    	}
    }
}
