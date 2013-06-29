package models;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;

import play.data.validation.Constraints.Required;
import play.db.ebean.Model;

/**
 * A single number for login app.
 *
 * @author: Richard Hähne
 */
@Entity
public class Number extends Model {

	@Id
	public Long id;
	
	@Required
	public float value;

	/**
	 * Constructor.
	 */
	public Number(float value) {
		this.value = value;
	}

	/**
	 * Static methods.
	 */

	/**
	 * Helper for database operations, Ebean.
	 */
	public static Finder<Long,Number> find = new Finder(Long.class, Number.class);

	/**
	 * Get all stored objects.
	 */
	public static List<Number> all() {
		return find.all();
	}

	/**
	 * Store a new object in Ebean.
	 */
	public static void create(Number number) {
		number.save();
	}

	/**
	 * Delete a single object. Instead of using Ebean Model delete().
	 */
	public static void delete(Long id) {
		find.ref(id).delete();
	}

	/**
	 * Delete all objects.
	 */
	public static void deleteAll() {
		List<Number> list = find.all();
		for (Number item: list) {
			Number.delete(item.id);
		}
	}
	
	/**
	 * Update. @return updated Number
	 */
	public static Number update(Number number, float value) {
		number.value = value;
		number.update();
		return number;
	}
	
	public static Number get(Long id) {
		return find.ref(id);
	}
}
