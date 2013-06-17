package controllers;

import models.Location;
import models.Note;
import play.data.Form;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;
import org.codehaus.jackson.JsonNode;

/**
 * LocationsController.
 *
 * @desc
 * 	{"floor": int, "x": int, "y": int, "name": String}.
 * 
 * @author: Richard Hähne
 */
public class LocationsController extends Controller {

    /**
     * Send all objects as JSON.
     */
    public static Result indexJson() {
    	return ok(Json.toJson(Location.all()));
    }

	/**
	 * Create a new Location entry.
	 */
    public static Result createJson() {
    	JsonNode json = request().body().asJson();
    	if(json == null) {
    		return badRequest("Expecting Json data");
    	} else {
    		Integer floor = json.findPath("floor").getIntValue();
    		Integer x = json.findPath("x").getIntValue();
    		Integer y = json.findPath("y").getIntValue();
    		String name = json.findPath("name").getTextValue();
    		if(floor == null || x == null || y == null || name == null) {
    			return badRequest("Missing parameter!");
    		} else {
    			Location location = new Location(floor, x, y, name);
				Location.create(location);
    			return ok(Json.toJson(location));
    		}
    	}
    }

    /**
     * Get.
     */
    public static Result getJson(Long id) {
    	Location location = Location.get(id);
    	if (location == null) {
    		return badRequest("Location " + id + " missing.");
    	} else {
    		return ok(Json.toJson(location));
    	}
     }
    

    public static Result updateJson(Long id) {
    	JsonNode json = request().body().asJson();
    	if(json == null) {
    		return badRequest("Expecting Json data");
    	} else {
    		Integer floor = json.findPath("floor").getIntValue();
    		Integer x = json.findPath("x").getIntValue();
    		Integer y = json.findPath("y").getIntValue();
    		String name = json.findPath("name").getTextValue();
    		if(floor == null || x == null || y == null || name == null) {
    			return badRequest("Missing parameter!");
    		} else {
    			Location location = Location.get(id);
    			Location updatedLocation = Location.update(location, floor, x, y, name);
				return ok(Json.toJson(updatedLocation));
    			// return ok("Updated " + macAdress + " to " + ssid + ".");
    		}
    	}
    }
    
    // Destroy
  
    public static Result destroyJson(Long id) {
    	Location.delete(id);
    	return ok();
    }
	
}
