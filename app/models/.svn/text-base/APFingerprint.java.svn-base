package models;

import javax.persistence.Entity;
import javax.persistence.Id;

import play.data.validation.Constraints.Required;
import play.db.ebean.Model;

/**
 * AccessPoint.
 *
 * Helperclass to save the Accesspoints and their intensity
 *   
 *
 * @author: Jan Schmalfuß
 */
@Entity
public class APFingerprint extends Model {

	@Id
	public Long id;
	
	@Required
	public String macAddress;
	public int intensity;

	/**
	 * Constructor.
	 */
	public APFingerprint(String macAddress, int intensity) {
		this.macAddress = macAddress;
		this.intensity = intensity;
	}
}
