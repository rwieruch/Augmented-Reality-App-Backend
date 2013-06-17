package models;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import play.data.validation.Constraints.Required;
import play.db.ebean.Model;

@Entity
public class Note extends Model {
	
	@Id
	public Long id;
	  
	@Required
	public String text;
	
	@ManyToOne
    public User user;
	
	public Note(String text, User user) {
		this.user = user;
		this.text = text;
	}
	
	// Helper for database operations.
	public static Finder<Long,Note> find = new Finder(
			Long.class, Note.class
			);
	  
	public static List<Note> all() {
		return find.all();
	}
	  
	public static void create(Note note) {
		//User user = note.user;
		//user.notes.add(note);
		//user.save();
		note.save();
	}
	  
	public static void delete(Long id) {
		find.ref(id).delete();
	}

	public static Note update(Note note, String text) {
		note.text = text;
		note.update();
		return note;
	}
	
	public static Note get(Long id) {
		return find.ref(id);
	}
	
}
