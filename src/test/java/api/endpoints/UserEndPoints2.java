package api.endpoints;

import static io.restassured.RestAssured.*;

import java.util.ResourceBundle;

import api.payload.User;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

//Created to perform Create, Read, Update, Delete operations on user api.

public class UserEndPoints2 {
	
	//Getting URL from properties file
	public static ResourceBundle getURL() {
		//Load properties file
		ResourceBundle routes = ResourceBundle.getBundle("routes");
		return routes;
	}
	
	public static Response postUser(User payload) {
		Response response = 
		given()
			.contentType(ContentType.JSON)
			.accept(ContentType.JSON)
			.body(payload)
		.when()
			.post(getURL().getString("user_post_url"));
		return response;
	}
	
	
	public static Response getUser(String username) {
		Response response =
		given()
			.pathParam("username", username)
		.when()
			.get(getURL().getString("user_get_url"));
		return response;
	}
	
	public static Response putUser(String username, User payload) {
		Response response = 
		given()
			.contentType(ContentType.JSON)
			.accept(ContentType.JSON)
			.pathParam("username", username)
			.body(payload)
		.when()
			.put(getURL().getString("user_put_url"));
		return response;
	}
	
	public static Response deleteUser(String username) {
		Response response =
		given()
			.pathParam("username", username)
		.when()
			.delete(getURL().getString("user_delete_url"));
		return response;
	}

}
