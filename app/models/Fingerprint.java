package models;

import java.util.List;

import models.AccessPoint;
import models.APFingerprint;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import play.data.validation.Constraints.Required;
import play.db.ebean.Model;

/**
 * Fingerprint.
 *
 * @author: Jan Schmalfuß
 */
@Entity
public class Fingerprint extends Model {

	@Id
	public Long id;

	@Required
	public int x;
	public int y;
	
	@OneToMany(cascade = CascadeType.ALL)
	public List<APFingerprint> accesspoints;	

	/**
	 * Constructor.
	 */
	public Fingerprint(int x, int y, String macAdress, int intensity) {		
		this.x = x;
		this.y = y;
		accesspoints.add(new APFingerprint(macAdress,intensity));
	}
	
	/**
	 * Static methods.
	 */

	/**
	 * Helper for database operations, Ebean.
	 */
	public static Finder<Long,Fingerprint> find = new Finder(Long.class, Fingerprint.class);

	/**
	 * Get all stored objects.
	 */
	public static List<Fingerprint> all() {
		return find.all();
	}

	/**
	 * Store a new object in Ebean.
	 */
	public static void create(Fingerprint fp) {
		fp.save();
	}

	/**
	 * Delete a single object.
	 */
	public static void delete(Long id) {
		find.ref(id).delete();
	}

	/**
	 * Update. @return updated Fingerprint
	 */
	public static Fingerprint update(Fingerprint fp, int x, int y, String macAdress, int intensity) {
		APFingerprint apf = new APFingerprint(macAdress,intensity);
		fp.accesspoints.add(apf);
		fp.x = x;
		fp.y = y;
		fp.update();
		return fp;
	}
	
	public static Fingerprint get(Long id) {
		return find.ref(id);
	}
}
