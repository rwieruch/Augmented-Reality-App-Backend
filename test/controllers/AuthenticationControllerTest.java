package controllers;

import java.util.HashMap;
import java.util.Map;

import org.junit.*;

import play.test.*;
import play.mvc.*;
import play.mvc.Http.*;

import models.*;

import play.test.Helpers.*;

import util.BaseModelTest;

public class AuthenticationControllerTest extends BaseModelTest {
	
	@Test
    public void registerUser() {
	    Map<String, String> registerUserParams = new HashMap<String, String>();
	    registerUserParams.put("email", "schnupp@gmx.de");
	    registerUserParams.put("name", "Schnupp");
	    registerUserParams.put("password", "admin");
	    //callAction(controllers.routes.ref.AuthenticationController.register(), fakeRequest().withHeader("Content-Type", "application/json").withJsonBody(json));
	    
	    // Check new user created
	    // Check if response is without password
	    // check if user with same email|password cant get registeres
    }
	
	
	
}
