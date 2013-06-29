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
/**
 * Entity for storing Notes.
 * @author Robin
 *
 */
@Entity
public class Note extends Model implements ViewableType {
	
	@Id
	public Long id;
	  
	@Required
	public String title;
	public String text;

	public float x;
    public float y;
    public float z; 

    // Can be public, proteced, private. @see ViewableType.
    public int viewable;

    public boolean editable;
	
	@ManyToOne
    public User user;
	
	public Note(String title, String text, float x, float y, float z, int viewable, boolean editable, User user) {
		this.user = user;
		this.title = title;
		this.text = text;
		this.x = x;
        this.y = y;
        this.z = z;
        this.viewable = viewable;
       	this.editable = editable;
	}
	
	// Helper for database operations.
	public static Finder<Long,Note> find = new Finder(Long.class, Note.class);
	  
	public static List<Note> all(User authUser) {
		//return find.all();
		List<Note> publiclist = find.where().eq("viewable", PUBLIC).findList();
		List<Note> protecedlist = find.where().eq("viewable", PROTECTED).findList();
		List<Note> myprivatelist = find.where().eq("viewable", PRIVATE).eq("user.email", authUser.email).findList();
		
		List<Note> finallist = new ArrayList<Note>();
		finallist.addAll(publiclist);
		finallist.addAll(protecedlist);
		finallist.addAll(myprivatelist);
		
		return finallist;
	}
	
	public static List<Note> allPublic() {
		return find.where().eq("viewable", PUBLIC).findList();
	}
	  
	public static void create(Note note) {
		note.save();
	}
	  
	public static void delete(Long id) {
		find.ref(id).delete();
	}
	
	public static void deleteAll(User authUser) {
		List<Note> list = all(authUser);
		for (Note item: list) {
			Note.delete(item.id);
		}
	}

	public static Note update(Note note, String title, String text, int viewable, boolean editable) {
		note.title = title;
		note.text = text;
		note.viewable = viewable;
		note.editable = editable;
		note.update();
		return note;
	}
	
	public static Note get(Long id) {
		return find.ref(id);
	}
	
}
