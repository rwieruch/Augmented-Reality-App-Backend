package models;

import static org.junit.Assert.*;

import org.junit.Test;

import util.BaseModelTest;

public class UserTest extends BaseModelTest {

	@Test
    public void postAndGetUser() {
        new User("alice@gmx.de", "Alice", "geheimnis", null, null, null, null, null, null, null).save();
        User user = User.find.where().eq("email", "alice@gmx.de").findUnique();
        assertNotNull(user);
        assertEquals("Alice", user.name);
    }
	
	@Test
    public void authenticateUser() {
        new User("wuppi@gmx.de", "Wuppi", "geheimnis", null, null, null, null, null, null, null).save();
        
        assertNotNull(User.authenticate("wuppi@gmx.de", "geheimnis"));
        assertNull(User.authenticate("wuppi@gmx.de", "badpassword"));
        assertNull(User.authenticate("wuppi@gmail.com", "geheimnis"));
    }
	
}
