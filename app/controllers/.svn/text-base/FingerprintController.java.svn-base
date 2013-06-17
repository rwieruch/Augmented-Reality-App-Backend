/**
 * FingerprintController.
 *
 *@desc
 * 	{"x": Integer, "y": Integer, "macAdress": String, "intensity": Integer}.
 *
 * @author: Jan Schmalfuß
 */
package controllers;

import java.util.List;
import models.Fingerprint;
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
			Integer x = json.findPath("x").getIntValue();
			Integer y = json.findPath("y").getIntValue();
			String macAddress = json.findPath("macAddress").getTextValue();
			Integer intensity = json.findPath("intensity").getIntValue();
			if (x == null || y == null || macAddress == null
					|| intensity == null) {
				return badRequest("Missing parameter!");
			} else {
				Fingerprint fingerprint = new Fingerprint(x, y, macAddress,
						intensity);
				Fingerprint.create(fingerprint);
				return ok("Added " + fingerprint.id);
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
			Integer x = json.findPath("x").getIntValue();
			Integer y = json.findPath("y").getIntValue();
			String macAddress = json.findPath("macAddress").getTextValue();
			Integer intensity = json.findPath("intensity").getIntValue();
			if (x == null || y == null || macAddress == null
					|| intensity == null) {
				return badRequest("Missing parameter!");
			} else {
				Fingerprint fingerprint = Fingerprint.get(id);
				Fingerprint updatedFingerprint = Fingerprint.update(
						fingerprint, x, y, macAddress, intensity);
				return ok(Json.toJson(updatedFingerprint));
			}
		}
	}

	public static Result destroyJson(Long id) {
		Fingerprint.delete(id);
		return ok();
	}
}