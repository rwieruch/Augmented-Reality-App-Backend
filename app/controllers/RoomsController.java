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
 * 	{"x": int, "y": int, "width": int, "height": int, "name": String}.
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
    		Double x = json.findPath("x").getDoubleValue();
    		Double y = json.findPath("y").getDoubleValue();
    		Double width = json.findPath("width").getDoubleValue();
    		Double height = json.findPath("height").getDoubleValue();
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
    		Integer x = json.findPath("x").getIntValue();
    		Integer y = json.findPath("y").getIntValue();
    		Integer width = json.findPath("width").getIntValue();
    		Integer height = json.findPath("height").getIntValue();
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
}
