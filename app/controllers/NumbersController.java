package controllers;

import models.Number;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.node.ObjectNode;

/**
 * NumbersController.
 *
 * @desc
 * 	{"number": float}.
 * 
 * @author: Richard Hähne
 */
public class NumbersController extends Controller {

    /**
     * Send all objects as JSON.
     */
    public static Result indexJson() {
    	return ok(Json.toJson(Number.all()));
    }

	/**
	 * Create a new item.
	 */
    public static Result createJson() {
    	JsonNode json = request().body().asJson();
    	if(json == null) {
    		return badRequest("Expecting Json data");
    	} else {
    		Double valueD = json.findPath("number").getDoubleValue(); // No getFloatValue, getNumberValue doesnt work.
    		Float value = new Float(valueD); // Use Java datatype for Nullcheck below.
    		if (value == null) {
    			return badRequest("Missing parameter \"number\"!");
    		} else {
    			Number number = new Number(value);
				Number.create(number);
    			return ok(Json.toJson(number));
    		}
    	}
    }

    /**
     * Get.
     */
    public static Result getJson(Long id) {
    	Number number = Number.get(id);
    	if (number == null) {
    		return badRequest("Number " + id + " missing.");
    	} else {
    		return ok(Json.toJson(number));
    	}
     }

    /**
     * Update certain item.
     */
    public static Result updateJson(Long id) {
    	JsonNode json = request().body().asJson();
    	if(json == null) {
    		return badRequest("Expecting Json data");
    	} else {
    		Double valueD = json.findPath("number").getDoubleValue(); // No getFloatValue, getNumberValue doesnt work.
    		Float value = new Float(valueD); // Use Java datatype for Nullcheck below.
    		if (value == null) {
    			return badRequest("Missing parameter \"number\"!");
    		} else {
    			Number number = Number.get(id);
    			Number updatedNumber = Number.update(number, value);
				return ok(Json.toJson(updatedNumber));
    		}
    	}
    }

    public static Result destroyJson(Long id) {
    	Number.delete(id);
    	return ok();
    }
    
	public static Result destroyAllJson() {
		Number.deleteAll();
		return ok();
	}
}
