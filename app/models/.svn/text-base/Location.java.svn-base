package models;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;

import play.data.validation.Constraints.Required;
import play.db.ebean.Model;

@Entity
public class Location extends Model {

	@Id
	public Long id;
	
	// CNWIFI Code: start
	
	public int floor;
	public int x;
	public int y;
	public String name;

	/**
	 * Constructor.
	 */
	public Location(int floor, int x, int y, String name) {
		this.floor = floor;
		this.x = x;
		this.y = y;
		this.name = name;
	}

	// Helper for database operations.
	public static Finder<Long,Location> find = new Finder(
			Long.class, Location.class
			);
	
	public static List<Location> all() {
		return find.all();
	}
	  
	public static void create(Location location) {
		location.save();
	}
	  
	public static void delete(Long id) {
		find.ref(id).delete();
	}
	
	public static Location update(Location location, int floor, int x, int y, String name) {
		location.floor = floor;
		location.x = x;
		location.y = y;
		location.name = name;
		location.update();
		return location;
	}
	
	public static Location get(Long id) {
		return find.ref(id);
	}
}
