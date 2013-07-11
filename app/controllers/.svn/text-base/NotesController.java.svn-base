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
 * 
 * @desc
 *  {"title":"Test","text":"test","viewable":0,"editable":true,"x":1.0,"y":1.0,"z":1.0}
 * 
 * @author Robin Wieruch, Richard
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
        	return ok(new JSONSerializer().exclude("class", "user.class", "user.password").serialize(Note.allPublic()));
        	//return unauthorized("Unauthorized");
        } else { 	
        	User authUser = Session.getAuthUser(token);
        	return ok(new JSONSerializer().exclude("class", "user.class", "user.password").serialize(Note.all(authUser)));
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
	        	
	        	String title = json.findPath("title").getTextValue();
	    		String text = json.findPath("text").getTextValue();
                Integer viewable = json.findPath("viewable").getIntValue();
                Boolean editable = json.findPath("editable").getBooleanValue();
                Double xD = json.findPath("x").getDoubleValue(); // No getFloatValue, getNumberValue doesnt work either.
                Double yD = json.findPath("y").getDoubleValue();
                Double zD = json.findPath("z").getDoubleValue();
        		Float x = new Float(xD); // Use Java datatype for Nullcheck below.
        		Float y = new Float(yD);
        		Float z = new Float(zD);
	    		if(title == null || text == null | viewable == null | editable == null | x == null | y == null | z == null) {
	    			return badRequest("Missing parameter - Title: " + title + " Text: " + text + " Viewable: "  + viewable + " Editable: " + editable + " X: " + x + " Y: " + y + " Z: " + z);
	    		} else {
	    			Note note = new Note(title, text, x, y, z, viewable, editable, authUser);
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
            	String title = json.findPath("title").getTextValue();
                String text = json.findPath("text").getTextValue();
                Integer viewable = json.findPath("viewable").getIntValue();
                Boolean editable = json.findPath("editable").getBooleanValue();
                if(title == null || text == null | viewable == null | editable == null) {
                    return badRequest("Missing parameter!");
                } else {
                    Note note = Note.get(id);
                    
                    if(!note.editable) {
                		return badRequest("Not authorized to edit note.");
                	}
                	else {                
	                    Note updatedNote = Note.update(note, title, text, viewable, editable);
	                    
	                    JSONSerializer noteSerializer = new JSONSerializer().include(
	                            "id",
	                            "title",
	                            "text",
	                            "user.email",
	                            "user.name").exclude("*");
	                    return ok(noteSerializer.serialize(updatedNote));
                	}
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
        	Note note = Note.get(id);       	
        	if(!note.editable) {
        		return badRequest("Not authorized to delete note.");
        	}
        	else {
        		Note.delete(id);
            	return ok();
        	}
    	}
    }
    
	public static Result destroyAllJson() {
    	// Check for header and match with valid user in db.
    	String token = request().getHeader("token");
		Session session = Session.authenticate(token); 
        if(session == null)
        	return unauthorized("Unauthorized");
        
        User authUser = Session.getAuthUser(token);
		Note.deleteAll(authUser);
		return ok();
	}
}
