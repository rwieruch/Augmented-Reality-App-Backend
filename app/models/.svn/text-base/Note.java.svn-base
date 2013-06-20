package models;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Version;

import play.data.validation.Constraints.Required;
import play.db.ebean.Model;

@Entity
public class Note extends Model {
	
	@Id
	public Long id;
	  
	@Required
	public String text;

	public String x;
    public String y;
    public String z; 

    // Can be public, proteced, private
    public String viewable;

    public boolean editable;
	
	@ManyToOne
    public User user;
	
	public Note(String text, String x, String y, String z, String viewable, String editable, User user) {
		this.user = user;
		this.text = text;
		this.x = x;
        this.y = y;
        this.z = z;
        this.viewable = viewable;
        if(editable.equals("true"))
        	this.editable = true;
        else
        	this.editable = false;
	}
	
	// Helper for database operations.
	public static Finder<Long,Note> find = new Finder(Long.class, Note.class);
	  
	public static List<Note> all(User authUser) {
		//return find.all();
		List<Note> publiclist = find.where().eq("viewable", "public").findList();
		List<Note> protecedlist = find.where().eq("viewable", "protected").findList();
		List<Note> myprivatelist = find.where().eq("viewable", "private").eq("user.email", authUser.email).findList();
		
		List<Note> finallist = new ArrayList<Note>();
		finallist.addAll(publiclist);
		finallist.addAll(protecedlist);
		finallist.addAll(myprivatelist);
		
		return finallist;
	}
	
	public static List<Note> allPublic() {
		return find.where().eq("viewable", "public").findList();
	}
	  
	public static void create(Note note) {
		note.save();
	}
	  
	public static void delete(Long id) {
		find.ref(id).delete();
	}

	public static Note update(Note note, String text, String viewable, String editable) {
		note.text = text;
		note.viewable = viewable;
		if(editable.equals("true"))
        	note.editable = true;
        else
        	note.editable = false;
		note.update();
		return note;
	}
	
	public static Note get(Long id) {
		return find.ref(id);
	}
	
}
