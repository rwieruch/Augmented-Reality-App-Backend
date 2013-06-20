/**
 * FingerprintController.
 *
 *@desc
 * 	{"x": float, "y": float, "AccessPoints": [{"macAdress": "String", "intensity": int},{"macAdress": "String", "intensity": int},...] }
 *
 * @author: Jan Schmalfuß, Richard
 */
package controllers;

import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;
import models.Fingerprint;
import models.APFingerprint;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.node.ObjectNode;

public class FingerprintController extends Controller {
	/**
	 * Send all objects as JSON.
	 */
	public static Result indexJson() {
		return ok(Json.toJson(Fingerprint.all())); // Get a list of all Fingerprints and convert them
								// to JSON.
	}

	/**
	 * Create a new Fingerprint Object. Expect macAdress and ssid.
	 */
	public static Result createJson() {
		JsonNode json = request().body().asJson();
		if (json == null) {
			return badRequest("Expecting Json data");
		} else {
			double xD = json.findPath("x").getDoubleValue(); // No getFloatValue, getNumberValue doesnt work either.
			double yD = json.findPath("y").getDoubleValue();
    		Float x = new Float(xD); // Use Java datatype for Nullcheck below.
    		Float y = new Float(yD);
			Iterator<JsonNode> it = json.findPath("AccessPoints").getElements();
			if (x == null || y == null || it == null) { //ToDo
				return badRequest("Missing parameter!");
			} else {
				Fingerprint fingerprint = new Fingerprint(x, y);
					while(it.hasNext()) {
						JsonNode j = it.next();
						String macAdress = j.findPath("macAddress").getTextValue();
						int intensity = j.findPath("intensity").getIntValue();
						fingerprint.accesspoints.add(new APFingerprint(macAdress,intensity));
					}				
				Fingerprint.create(fingerprint);
				
				return ok(Json.toJson(fingerprint));
			}
		}
	}

	/**
	 * Get.
	 */
	public static Result getJson(Long id) {
		Fingerprint fingerprint = Fingerprint.get(id);
		if (fingerprint == null) {
			return badRequest("Fingerprint " + id + " missing.");
		} else {
			return ok(Json.toJson(fingerprint));
		}
	}

	/**
	 * Update Fingerprint
	 */
	public static Result updateJson(Long id) {
		JsonNode json = request().body().asJson();
		if (json == null) {
			return badRequest("Expecting Json data");
		} else {
			double xD = json.findPath("x").getDoubleValue(); // No getFloatValue, getNumberValue doesnt work either.
			double yD = json.findPath("y").getDoubleValue();
    		Float x = new Float(xD); // Use Java datatype for Nullcheck below.
    		Float y = new Float(yD);
			Iterator<JsonNode> it = json.findPath("AccessPoints").getElements();
			
			List<APFingerprint> aps = new ArrayList<APFingerprint>();
			while(it.hasNext()) {
				JsonNode j = it.next();
				String macAdress = j.findPath("macAddress").getTextValue();
				int intensity = j.findPath("intensity").getIntValue();
				aps.add(new APFingerprint(macAdress,intensity));
			}			
			
			if (x == null || y == null || it == null) {
				return badRequest("Missing parameter!");
			} else {
				Fingerprint fingerprint = Fingerprint.get(id);
				Fingerprint updatedFingerprint = Fingerprint.update(
						fingerprint, x, y, aps);
				return ok(Json.toJson(updatedFingerprint));
			}
		}
	}

	public static Result destroyJson(Long id) {
		Fingerprint.delete(id);
		return ok();
	}

	public static Result destroyAllJson() {
		Fingerprint.deleteAll();
		return ok();
	}
}