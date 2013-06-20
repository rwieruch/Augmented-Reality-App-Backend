package controllers;

import models.Room;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.node.ObjectNode;

/**
 * RoomsController.
 *
 * @desc
 * 	{"x": float, "y": float, "width": float, "height": float, "name": String}.
 * 
 * @author: Richard Hähne
 */
public class RoomsController extends Controller {

    /**
     * Send all objects as JSON.
     */
    public static Result indexJson() {
    	return ok(Json.toJson(Room.all())); // Get a list of all Accesspoints and convert them to JSON.
    }

	/**
	 * Create a new Room entry.
	 */
    public static Result createJson() {
    	JsonNode json = request().body().asJson();
    	if(json == null) {
    		return badRequest("Expecting Json data");
    	} else {
    		double xD = json.findPath("x").getDoubleValue(); // No getFloatValue, getNumberValue doesnt work.
    		double yD = json.findPath("y").getDoubleValue();
    		double widthD = json.findPath("width").getDoubleValue();
    		double heightD = json.findPath("height").getDoubleValue();
    		Float x = new Float(xD); // Use Java datatype for Nullcheck below.
    		Float y = new Float(yD);
    		Float width = new Float(widthD);
    		Float height = new Float(heightD);
    		String name = json.findPath("name").getTextValue();
    		if (x == null || y == null || width == null || height == null || name == null) {
    			return badRequest("Missing parameter!");
    		} else {
    			Room room = new Room(x, y, width, height, name);
				Room.create(room);
    			return ok(Json.toJson(room));
    		}
    	}
    }

    /**
     * Get.
     */
    public static Result getJson(Long id) {
    	Room room = Room.get(id);
    	if (room == null) {
    		return badRequest("Room " + id + " missing.");
    	} else {
    		return ok(Json.toJson(room));
    	}
     }

    /**
     * Update SSID.
     */
    public static Result updateJson(Long id) {
    	JsonNode json = request().body().asJson();
    	if(json == null) {
    		return badRequest("Expecting Json data");
    	} else {
    		double xD = json.findPath("x").getDoubleValue(); // No getFloatValue, getNumberValue doesnt work either.
    		double yD = json.findPath("y").getDoubleValue();
    		double widthD = json.findPath("width").getDoubleValue();
    		double heightD = json.findPath("height").getDoubleValue();
    		Float x = new Float(xD); // Use Java datatype for Nullcheck below.
    		Float y = new Float(yD);
    		Float width = new Float(widthD);
    		Float height = new Float(heightD);
    		String name = json.findPath("name").getTextValue();
    		if(x == null || y == null || width == null || height == null || name == null) {
    			return badRequest("Missing parameter!");
    		} else {
    			Room room = Room.get(id);
    			Room updatedRoom = Room.update(room, x, y, width, height, name);
				return ok(Json.toJson(updatedRoom));
    		}
    	}
    }

    public static Result destroyJson(Long id) {
    	Room.delete(id);
    	return ok();
    }
    
	public static Result destroyAllJson() {
		Room.deleteAll();
		return ok();
	}
}
