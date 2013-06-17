package api;

import static org.fest.assertions.Assertions.assertThat;
import static play.test.Helpers.GET;
import static play.test.Helpers.contentAsString;
import static play.test.Helpers.fakeRequest;
import static play.test.Helpers.routeAndCall;
import static play.test.Helpers.status;

import java.util.HashMap;
import java.util.Map;

import models.Note;

import org.codehaus.jackson.JsonNode;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import play.libs.Json;
import play.mvc.Result;
import util.BaseModelTest;

/**
 * Test for notes API.
 * @author Robin Wieruch
 *
 */

public class NotesApiTest extends BaseModelTest {
	
	 static String email = "noteUser@gmx.de";
	 static String name = "noteUser";
	 static String password = "geheim";
	 static String token;
	 
	 static Note noteOne;
	 static Note noteTwo;
	 static Note noteThree;
	 
	 @BeforeClass
	 public static void beforeClass() {
	    // Registere a user which can create, update, get and delete notes.
		Map registerMap = new HashMap();
		registerMap.put("email", email);
		registerMap.put("name", name);
		registerMap.put("password", password);
		JsonNode registerNode = Json.toJson(registerMap);
		Result registerResult = routeAndCall(fakeRequest("POST","/register.json").withJsonBody(registerNode));
		assertThat(status(registerResult)).isEqualTo(200);
		
		// Authenticate a user which can create, update, get and delete notes.
		Map loginMap = new HashMap();
		loginMap.put("email", email);
		loginMap.put("password", password);
		JsonNode loginNode = Json.toJson(loginMap);
		Result loginResult = routeAndCall(fakeRequest("POST","/login.json").withJsonBody(loginNode));
		assertThat(status(loginResult)).isEqualTo(200);
		assertThat(contentAsString(loginResult)).contains("token");
		
		// Extract token.
		token = contentAsString(loginResult);
		String[] result = token.split(":");
		result = result[2].split(",");
		token = result[0].substring(1, result[0].length() - 1);
        System.out.println("###NotesApiTest: Extracted token: " + token);
		
		// Create first note.
		
		noteOne = new Note("note one", null);	
			
		Map map1 = new HashMap();
		map1.put("text", noteOne.text);
		JsonNode node1 = Json.toJson(map1);
		Result result1 = routeAndCall(fakeRequest("POST","/notes.json")
				  .withJsonBody(node1)
				  .withHeader("token", token));
		assertThat(status(result1)).isEqualTo(200);
		//assertThat(contentType(result)).isEqualTo("application/json");
		  
		noteOne.id = (long) 1;
		  
		System.out.println("###NotesApiTest CreateResult: " + contentAsString(result1));
		assertThat(contentAsString(result1)).contains("id").contains("text").contains("user").contains("email").contains("name"); // keys
		assertThat(contentAsString(result1)).contains("1").contains(noteOne.text).contains(email).contains(name); // values
		
		// Create second note.
		
		noteTwo = new Note("note two", null);	
			
		Map map2 = new HashMap();
		map2.put("text", noteTwo.text);
		JsonNode node2 = Json.toJson(map2);
		Result result2 = routeAndCall(fakeRequest("POST","/notes.json")
				  .withJsonBody(node2)
				  .withHeader("token", token));
		assertThat(status(result2)).isEqualTo(200);
		//assertThat(contentType(result)).isEqualTo("application/json");
		  
		noteTwo.id = (long) 2;
		  
		System.out.println("###NotesApiTest CreateResult: " + contentAsString(result2));
		assertThat(contentAsString(result2)).contains("id").contains("text").contains("user").contains("email").contains("name"); // keys
		assertThat(contentAsString(result2)).contains("2").contains(noteTwo.text).contains(email).contains(name); // values
		
		// Create third note.
		
		noteThree = new Note("note three", null);	
			
		Map map3 = new HashMap();
		map3.put("text", noteThree.text);
		JsonNode node3 = Json.toJson(map3);
		Result result3 = routeAndCall(fakeRequest("POST","/notes.json")
				  .withJsonBody(node3)
				  .withHeader("token", token));
		assertThat(status(result3)).isEqualTo(200);
		//assertThat(contentType(result)).isEqualTo("application/json");
		  
		noteThree.id = (long) 3;
		  
		System.out.println("###NotesApiTest CreateResult: " + contentAsString(result3));
		assertThat(contentAsString(result3)).contains("id").contains("text").contains("user").contains("email").contains("name"); // keys
		assertThat(contentAsString(result3)).contains("3").contains(noteThree.text).contains(email).contains(name); // values
	 }
	 
	 @AfterClass
	 public static void after() {
	      Result result1 = routeAndCall(fakeRequest("DELETE","/notes/1.json")
				  .withHeader("token", token));
		  assertThat(status(result1)).isEqualTo(200);
		  
	      Result result2 = routeAndCall(fakeRequest("DELETE","/notes/2.json")
				  .withHeader("token", token));
		  assertThat(status(result2)).isEqualTo(200);
	 }
		
	// Erroneous tests (400, 401, ...)
	
	@Test
	public void unAuthIndexJson() {
	  Result result = routeAndCall(fakeRequest(GET, "/notes.json"));
	  assertThat(status(result)).isEqualTo(401);
	}
	
	@Test
	public void createJsonWithoutJson() {
      Result result = routeAndCall(fakeRequest("POST","/notes.json"));
	  assertThat(status(result)).isEqualTo(400);
	}
	
	@Test
	public void unAuthCreateJson() {
      Map map = new HashMap();
      map.put("text", "note one");
      JsonNode node = Json.toJson(map);
	  Result result = routeAndCall(fakeRequest("POST","/notes.json").withJsonBody(node));
	  assertThat(status(result)).isEqualTo(401);
	}
	
	@Test
	public void unAuthGetJson() {
      Result result = routeAndCall(fakeRequest("GET","/notes/1.json"));
	  assertThat(status(result)).isEqualTo(401);
	}
	
	@Test
	public void updateJsonWithoutJson() {
      Result result = routeAndCall(fakeRequest("PUT","/notes/1.json"));
	  assertThat(status(result)).isEqualTo(400);
	}
	
	/*@Test
	public void unAuthUpdateJson() {
	  Map map = new HashMap();
	  map.put("text", "note two");
	  JsonNode node = Json.toJson(map);
	  Result result = routeAndCall(fakeRequest("PUT","/notes/1.json").withJsonBody(node));
	  assertThat(status(result)).isEqualTo(400);
	}*/
	
	@Test
	public void unAuthDeleteJson() {
      Result result = routeAndCall(fakeRequest("DELETE","/notes/1.json"));
	  assertThat(status(result)).isEqualTo(401);
	}
	
	// Successful tests (200, 203, ...)
	
	// Check whether it is possible to get a note.
	@Test
	public void authGetJson() {
      Result result = routeAndCall(fakeRequest("GET","/notes/1.json")
			  .withHeader("token", token));
	  assertThat(status(result)).isEqualTo(200);
	 //assertThat(contentType(result)).isEqualTo("application/json");
	  
	  System.out.println("###NotesApiTest GetResult: " + contentAsString(result));
	  assertThat(contentAsString(result)).contains("id").contains("text");//.contains("user").contains("email").contains("name"); // keys
	  assertThat(contentAsString(result)).contains("1").contains(noteOne.text);//.contains(email).contains(name); // values for note 1
	  containsPW(result); // in BaseModelTest, check for notContains: password:value
	}

	@Test
	public void authIndexUpdateDeleteIndexJson() {  
		
      // Index.
		
	  Result result1 = routeAndCall(fakeRequest("GET","/notes.json")
			  .withHeader("token", token));
	  assertThat(status(result1)).isEqualTo(200);
	  //assertThat(contentType(result)).isEqualTo("application/json");
	  
	  System.out.println("###NotesApiTest IndexResult: " + contentAsString(result1));
	  assertThat(contentAsString(result1)).contains("id").contains("text");//.contains("user").contains("email").contains("name"); // keys
	  assertThat(contentAsString(result1)).contains("1").contains(noteOne.text);//.contains(email).contains(name); // values for note 1
	  assertThat(contentAsString(result1)).contains("2").contains(noteTwo.text);//.contains(email).contains(name); // values for note 2
	  assertThat(contentAsString(result1)).contains("3").contains(noteThree.text);//.contains(email).contains(name); // values for note 2
	  containsPW(result1); // in BaseModelTest, check for notContains: password:value
	  
	  // Update.
	  
	  /*noteTwo.text = "note updated";
	  
	  Map map2 = new HashMap();
	  map2.put("text", noteTwo.text);
	  JsonNode node2 = Json.toJson(map2);
	  Result result = routeAndCall(fakeRequest("PUT","/notes/2.json")
			  .withHeader("email", email)
			  .withHeader("password", password)
			  .withJsonBody(node2));

	  assertThat(status(result)).isEqualTo(200);
	  //assertThat(contentType(result)).isEqualTo("application/json");

	  System.out.println("###NotesApiTest UpdateResult: " + contentAsString(result));
	  assertThat(contentAsString(result)).contains("id").contains("text").contains("user").contains("email").contains("name"); // keys
	  assertThat(contentAsString(result)).contains("2").contains(noteTwo.text).contains(email).contains(name); // values
	  containsPW(result); // in BaseModelTest, check for notContains: password:value*/
	  
	  // Delete.
	  
      Result result3 = routeAndCall(fakeRequest("DELETE","/notes/3.json")
			  .withHeader("token", token));
	  assertThat(status(result3)).isEqualTo(200);
	  
      // Index.
		
	  Result result4 = routeAndCall(fakeRequest("GET","/notes.json")
			  .withHeader("token", token));
	  assertThat(status(result4)).isEqualTo(200);
	  //assertThat(contentType(result)).isEqualTo("application/json");
	  
	  System.out.println("###NotesApiTest IndexResult: " + contentAsString(result1));
	  assertThat(contentAsString(result4)).contains("id").contains("text");//.contains("user").contains("email").contains("name"); // keys
	  assertThat(contentAsString(result4)).contains("1").contains(noteOne.text);//.contains(email).contains(name); // values for note 1
	  assertThat(contentAsString(result4)).contains("2").contains(noteTwo.text);//.contains(email).contains(name); // values for note 2
	  assertThat(contentAsString(result4)).doesNotContain("3").doesNotContain(noteThree.text);//.contains(email).contains(name); // values for note 3
	  containsPW(result4); // in BaseModelTest, check for notContains: password:value
	}		  
}
