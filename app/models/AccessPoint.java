package models;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;

import play.data.validation.Constraints.Required;
import play.db.ebean.Model;

/**
 * AccessPoint.
 *
 * @author: Richard Hähne
 */
@Entity
public class AccessPoint extends Model {

	@Id
	public Long id;
	
	@Required
	public String macAdress;
	public String ssid;

	/**
	 * Constructor.
	 */
	public AccessPoint(String macAdress, String ssid) {
		this.macAdress = macAdress;
		this.ssid = ssid;
	}

	/**
	 * Static methods.
	 */

	/**
	 * Helper for database operations, Ebean.
	 */
	public static Finder<Long,AccessPoint> find = new Finder(Long.class, AccessPoint.class);

	/**
	 * Get all stored objects.
	 */
	public static List<AccessPoint> all() {
		return find.all();
	}

	/**
	 * Store a new object in Ebean.
	 */
	public static void create(AccessPoint accesspoint) {
		accesspoint.save();
	}

	/**
	 * Delete a single object. Instead of using Ebean Model delete().
	 */
	public static void delete(Long id) {
		find.ref(id).delete();
	}

	/**
	 * Update. @return updated AccessPoint
	 */
	public static AccessPoint update(AccessPoint accesspoint, String ssid) {
		accesspoint.ssid = ssid;
		accesspoint.update();
		return accesspoint;
	}
	
	public static AccessPoint get(Long id) {
		return find.ref(id);
	}
	
	/**
	 * Get a single object by its macAdress property.
	 */
	public static AccessPoint getbyMacAdress(String macAdress) {
		return find.where().eq("macAdress", macAdress).findUnique();
	}
}
