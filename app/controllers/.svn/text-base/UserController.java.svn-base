package controllers;

import models.Note;
import models.Session;
import models.User;

import org.codehaus.jackson.JsonNode;

import flexjson.JSONSerializer;

import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;

/**
 * Controller for authentication and CRUD operations.
 * @author Robin Wieruch
 *
 */
public class UserController extends Controller {
	
	/**
	 * Create a new user by registration. Expect email, name and password.
	 */
    public static Result register() {
    	JsonNode json = request().body().asJson();
    	if(json == null) {
    		return badRequest("Expecting Json data");
    	} else {
    		
    		// Retrieve required values.
    		String email = json.findPath("email").getTextValue();
    		String name = json.findPath("name").getTextValue();
    		String password = json.findPath("password").getTextValue();
    		
    		// Retrieve additional values.
    		String type = json.findPath("type").getTextValue();
    		String realname = json.findPath("realname").getTextValue();
    		String image = json.findPath("image").getTextValue();
    		String info = json.findPath("info").getTextValue();
    		String x = json.findPath("x").getTextValue();
    		String y = json.findPath("y").getTextValue();
    		String z = json.findPath("z").getTextValue();
    		
    		// Check for required values.
    		if(email == null || name == null || password == null) {
    			return badRequest("Missing parameter!");
    		} else {
    			User user = new User(email, name, password, type, realname, image, info, x, y, z);
    			
    			// Check if user already exists, then save to DB.
    			User createdUser = User.create(user);
    			if(createdUser != null)
    				return ok(Json.toJson(createdUser));
    			else
    				return badRequest("User already exists.");
    		}
    	}
    }
    
	/**
	 * Get myself.
	 */
    public static Result getAuthUser() {
        // Check for header and match with valid user in db.
    	String token = request().getHeader("token");
        System.out.println("###UserController Token: " + token);
        User authUser = Session.getAuthUser(token);
        if(authUser == null) {
            return unauthorized("Unauthorized");
        } else {   
			return ok(new JSONSerializer().include("notes").exclude("password", "class", "notes.class").serialize(authUser));
		}
    	
    }
    
    /**
	 * Update a user. Expect email and password.
	 */
    public static Result updateJson() {
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
            	
            	// Retrieve possible updated values.
            	String name = json.findPath("name").getTextValue();
            	String type = json.findPath("type").getTextValue();
        		String realname = json.findPath("realname").getTextValue();
        		String image = json.findPath("image").getTextValue();
        		String info = json.findPath("info").getTextValue();
        		String x = json.findPath("x").getTextValue();
        		String y = json.findPath("y").getTextValue();
        		String z = json.findPath("z").getTextValue();

                User user = new User(authUser.email, name, authUser.password, type, realname, image, info, x, y, z);
                User updatedUser = User.update(authUser.email, user);

                return ok(Json.toJson(updatedUser));
            }
        }
    }
}
