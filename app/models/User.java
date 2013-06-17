package models;

import java.util.List;
import java.util.Map;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.codehaus.jackson.annotate.JsonIgnore;

import play.db.ebean.Model;

@Entity
@Table(name="augmenteduser")
public class User extends Model {
	
	@Id
    public String email;
	
    @JsonIgnore
    public String password;
    
    // For later use: convert password and email with bas64 to a unique token with timestamp.
    //public String token;
	
	// UserEntity
    public String name;
    public String type;
    public String realname;
    public String image;
    public String info;
    
    // EntityData
    //public String entity_type;
    //public Map<String, String> properties;
    public String x;
    public String y;
    public String z;    
    
    @OneToMany(cascade = CascadeType.ALL)
    public List<Note> notes;
    
    public User(String email, String name, String password, String type, String realname, String image, String info, String x, String y, String z) {
        this.email = email;
        this.name = name;
        this.password = password;
        this.type = type;
        this.realname = realname;
        this.image = image;
        this.info = info;
        this.x = x;
        this.y = y;
        this.z = z;
    }
    
    public static Finder<String,User> find = new Finder<String,User>(
        String.class, User.class
    ); 
    
	public static User create(User user) {
		User existingUser = find.where().eq("email", user.email).findUnique();
		if(existingUser == null) {       		
			user.save();
			return user;
		} else {
			return null;
		}
	}
	
	public static User update(String email, User user) {
		User oldUser = find.ref(email);
		user.notes = oldUser.notes;
		user.update();
		return user;
	}
	
	public static User get(String email) {
		return find.ref(email);
	}
	
    public static User authenticate(String email, String password) {
        return find.where().eq("email", email).eq("password", password).findUnique();
    }
}
