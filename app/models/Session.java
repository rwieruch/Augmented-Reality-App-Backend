package models;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import org.apache.commons.codec.binary.Base64;

import play.db.ebean.Model;
import play.db.ebean.Model.Finder;

@Entity
public class Session extends Model {
	
	@Id
    public Long id;
	
	public String token;
	public Date timestamp;
	
	@ManyToOne
	public User user;
	
	public Session(String email, String password, User user) {
		this.user = user;
		
	    Date date = new Date();
	    this.timestamp = date;
		
		String orig = email+":"+password+":"+date;
        byte[] encoded = Base64.encodeBase64(orig.getBytes());     
        this.token = new String(encoded);
        
        System.out.println("Base64 Encoded : " + token);   
        System.out.println("Timestamp : " + timestamp);   
	}
	
	public static Finder<Long,Session> find = new Finder<Long,Session>(
        Long.class, Session.class
	); 
	
	public static void create(Session session) {
		//Session existingSession = find.where().eq("email", session.user.email).findUnique();
		// Kill existing but expired (by timestamp) sessions
	    
		session.save();
	}
	
	public static void delete(Long id) {
		find.ref(id).delete();
	}
	
	public static Session authenticate(String token) {
		return find.where().eq("token", token).findUnique();
	}
	
	public static User getAuthUser(String token) {
		Session session = find.where().eq("token", token).findUnique();
		return session.user;
	}

}
