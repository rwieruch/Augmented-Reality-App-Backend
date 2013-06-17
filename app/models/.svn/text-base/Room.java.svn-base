package models;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;

import play.data.validation.Constraints.Required;
import play.db.ebean.Model;

/**
 * A room for login app.
 *
 * @author: Richard Hähne
 */
@Entity
public class Room extends Model {

	@Id
	public Long id;
	
	@Required
	public int x;
	public int y;
	public int width;
	public int height;
	public String name;

	/**
	 * Constructor.
	 */
	public Room(int x, int y, int width, int height, String name) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.name = name;
	}

	/**
	 * Static methods.
	 */

	/**
	 * Helper for database operations, Ebean.
	 */
	public static Finder<Long,Room> find = new Finder(Long.class, Room.class);

	/**
	 * Get all stored objects.
	 */
	public static List<Room> all() {
		return find.all();
	}

	/**
	 * Store a new object in Ebean.
	 */
	public static void create(Room room) {
		room.save();
	}

	/**
	 * Delete a single object. Instead of using Ebean Model delete().
	 */
	public static void delete(Long id) {
		find.ref(id).delete();
	}

	/**
	 * Update. @return updated Room
	 */
	public static Room update(Room room, int x, int y, int width, int height, String name) {
		room.x = x;
		room.y = y;
		room.width = width;
		room.height = height;
		room.name = name;
		room.update();
		return room;
	}
	
	public static Room get(Long id) {
		return find.ref(id);
	}
}
