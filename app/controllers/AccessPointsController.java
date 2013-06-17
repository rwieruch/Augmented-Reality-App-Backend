package controllers;

import models.AccessPoint;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.node.ObjectNode;

/**
 * AccessPointsController.
 *
 * @desc
 * 	{"macAdress": String, "ssid": String}.
 * 
 * @author: Richard Hähne
 */
public class AccessPointsController extends Controller {

    /**
     * Send all objects as JSON.
     */
    public static Result indexJson() {
    	return ok(Json.toJson(AccessPoint.all())); // Get a list of all Accesspoints and convert them to JSON.
    }

	/**
	 * Create a new AccessPoint Object. Expect macAdress and ssid.
	 */
    public static Result createJson() {
    	JsonNode json = request().body().asJson();
    	if(json == null) {
    		return badRequest("Expecting Json data");
    	} else {
    		String macAdress = json.findPath("macAdress").getTextValue();
    		String ssid = json.findPath("ssid").getTextValue();
    		if(macAdress == null || ssid == null) {
    			return badRequest("Missing parameter!");
    		} else {
    			AccessPoint accesspoint = new AccessPoint(macAdress, ssid);
				AccessPoint.create(accesspoint);
    			return ok(Json.toJson(accesspoint));
    		}
    	}
    }

    /**
     * Get.
     */
    public static Result getJson(Long id) {
    	AccessPoint accesspoint = AccessPoint.get(id);
    	if (accesspoint == null) {
    		return badRequest("AccessPoint " + id + " missing.");
    	} else {
    		return ok(Json.toJson(accesspoint));
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
    		String ssid = json.findPath("ssid").getTextValue();
    		if(ssid == null) {
    			return badRequest("Missing parameter [ssid]!");
    		} else {
    			AccessPoint accesspoint = AccessPoint.get(id);
    			AccessPoint updatedAccesspoint = AccessPoint.update(accesspoint, ssid);
				return ok(Json.toJson(updatedAccesspoint));
    			// return ok("Updated " + macAdress + " to " + ssid + ".");
    		}
    	}
    }

    public static Result destroyJson(Long id) {
    	AccessPoint.delete(id);
    	return ok();
    }

    /**
     * Retrieve single object by macAdress per POST and send as JSON.
     */
    /*
    public static Result getJson() {
    	JsonNode json = request().body().asJson();
    	if(json == null) {
    		return badRequest("Expecting Json data");
    	} else {
    		String macAdress = json.findPath("macAdress").getTextValue();
    		if(macAdress == null) {
    			return badRequest("Missing parameter [macAdress]!");
    		} else {
    			JsonNode result = Json.toJson(AccessPoint.getbyMacAdress(macAdress)); // Get an Accesspoint and convert it to JSON.
    			if (result == null) { // Dont return ok if object is not in db.
    				return badRequest("No Accesspoint " + macAdress + " known.");
    			}
    			return ok(result);
    		}
    	}
    }
    */

    /**
     * Destroy single object per POST.
     */
    /*public static Result deleteJson() {
    	JsonNode json = request().body().asJson();
    	if(json == null) {
    		return badRequest("Expecting Json data");
    	} else {
    		String macAdress = json.findPath("macAdress").getTextValue();
    		if(macAdress == null) {
    			return badRequest("Missing parameter [macAdress]!");
    		} else {
    			AccessPoint ap = AccessPoint.getbyMacAdress(macAdress); // Get an Accesspoint.
    			if (ap == null) { // Dont return ok if object is not in db.
    				return badRequest("No Accesspoint " + macAdress + " known.");
    			}
    			ap.delete();
    			return ok("Deleted " + macAdress + ".");
    		}
    	}
    }*/
}
