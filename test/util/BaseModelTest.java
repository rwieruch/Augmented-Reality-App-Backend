package util;
import static org.fest.assertions.Assertions.assertThat;
import static play.test.Helpers.contentAsString;
import static play.test.Helpers.fakeRequest;
import static play.test.Helpers.routeAndCall;
import static play.test.Helpers.status;

import java.util.HashMap;
import java.util.Map;

import org.codehaus.jackson.JsonNode;
import org.junit.AfterClass;
import org.junit.BeforeClass;

import play.libs.Json;
import play.mvc.Result;
import play.test.FakeApplication;
import play.test.Helpers;

/**
 * Abstract test for enabling database manipulation during test.
 * @author Robin Wieruch
 *
 */

public class BaseModelTest {
	
	  public static FakeApplication app;
	 
	  @BeforeClass
	  public static void startApp() {
	    app = Helpers.fakeApplication(Helpers.inMemoryDatabase());
	    Helpers.start(app);
	  }
	 
	  @AfterClass
	  public static void stopApp() {
	    Helpers.stop(app);
	  }
	  
	  public void containsPW(Result result) {
		  assertThat(contentAsString(result)).doesNotContain("password").doesNotContain("geheim").doesNotContain("token");
	  }
}