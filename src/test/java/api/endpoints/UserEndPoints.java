package api.endpoints;

import static io.restassured.RestAssured.*;
import api.payload.User;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

//Created to perform Create, Read, Update, Delete operations on user api.

public class UserEndPoints {
	
	public static Response postUser(User payload) {
		Response response = 
		given()
			.contentType(ContentType.JSON)
			.accept(ContentType.JSON)
			.body(payload)
		.when()
			.post(Routes.post_url);
		return response;
	}
	
	
	public static Response getUser(String username) {
		Response response =
		given()
			.pathParam("username", username)
		.when()
			.get(Routes.get_url);
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
			.put(Routes.put_url);
		return response;
	}
	
	public static Response deleteUser(String username) {
		Response response =
		given()
			.pathParam("username", username)
		.when()
			.delete(Routes.delete_url);
		return response;
	}

}
