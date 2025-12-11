package api.endpoints;
import static io.restassured.RestAssured.*;

import java.util.ResourceBundle;

import api.payload.User;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class UserEndpoints2 {

	//method created for getting URL's from properties file
	static ResourceBundle getURL()
	{
		ResourceBundle routes = ResourceBundle.getBundle("routes"); //path is not required because this method picks properties file from src/test/resources folder
	    return routes;
	}
	
	public static Response createUser(User payload)
	{
			
		Response response = given()
		 .contentType(ContentType.JSON)
		 .accept(ContentType.JSON)
		 .body(payload)
		 
		.when()
		  .post(getURL().getString("user_post_url"));  // we can access user_post_url directly using class name since it is static 
		
		  return response;
		
	}
	
	
	public static Response getUser(String userName)
	{
		Response response = given()
		 .accept(ContentType.JSON)
		 .pathParam("username", userName)
		 
		.when()
		  .get(getURL().getString("user_get_url"));   
		
		  return response;
		
	}
	
	public static Response updateUser(String userName, User payload)
	{
		Response response = given()
		 .contentType(ContentType.JSON)
		 .accept(ContentType.JSON)
		 .pathParam("username", userName)
		 .body(payload)
		 
		.when()
		  .put(getURL().getString("user_update_url"));   
		
		  return response;
		
	}
	
	public static Response deleteUser(String userName)
	{
		Response response = given()
		 .accept(ContentType.JSON)
		 .pathParam("username", userName)
		 
		.when()
		  .delete(getURL().getString("user_delete_url"));   
		
		  return response;
		
	}
	
}
