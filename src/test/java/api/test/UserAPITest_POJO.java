package api.test;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import api.endpoints.UserEndPoints;
import api.payload.User;
import io.restassured.response.Response;

public class UserAPITest_POJO {
	
	Faker faker;
	User payload;
	public Logger logger;
	
	@BeforeClass
	void setup() {
		faker = new Faker();
		payload = new User();
		logger = LogManager.getLogger(this.getClass());
		
		int id = faker.number().hashCode();
		String firstName = faker.name().firstName();
		String lastName = faker.name().lastName();
		String username = firstName+"."+lastName;
		String password = username+"@"+id;
		String email = username+"@gmail.com";
		String phone = faker.phoneNumber().cellPhone();
		int userStatus = faker.number().numberBetween(0, 2);
		
		payload.setId(id);
		payload.setUsername(username);
		payload.setFirstName(firstName);
		payload.setLastName(lastName);
		payload.setEmail(email);
		payload.setPassword(password);
		payload.setPhone(phone);
		payload.setUserStatus(userStatus);
	}
	
	@Test(priority = 1)
	void testCreateUser() {
		logger.info("----------TestCreateUser----------");
		Response response = UserEndPoints.postUser(payload);
		
		logger.info("Validated status code.");
		Assert.assertEquals(response.getStatusCode(), 200);
		logger.info("Received response body.");
		logger.info(response.getBody().asString());
	}
	
	@Test(priority = 2)
	void testReadUser1() {
		logger.info("----------TestReadUser1----------");
		Response response = UserEndPoints.getUser(this.payload.getUsername());
		
		logger.info("Validated status code.");
		Assert.assertEquals(response.getStatusCode(), 200);
		logger.info("Received response body.");
		logger.info(response.getBody().asString());
	}
	
	@Test(priority = 3)
	void testUpdateUser() {
		payload.setPhone(faker.phoneNumber().cellPhone());
		payload.setUserStatus(faker.number().numberBetween(0, 2));
		
		logger.info("----------TestUpdateUser----------");
		Response response = UserEndPoints.putUser(this.payload.getUsername(), payload);
		
		logger.info("Validated status code.");
		Assert.assertEquals(response.getStatusCode(), 200);
		logger.info("Received response body.");
		logger.info(response.getBody().asString());
	}
	
	@Test(priority = 4)
	void testReadUser2() {
		logger.info("----------TestReadUser2----------");
		Response response = UserEndPoints.getUser(this.payload.getUsername());
		
		logger.info("Validated status code.");
		Assert.assertEquals(response.getStatusCode(), 200);
		logger.info("Received response body.");
		logger.info(response.getBody().asString());
	}

	@Test(priority = 5)
	void testDeleteUser() {
		logger.info("----------TestDeleteUser----------");
		Response response = UserEndPoints.deleteUser(this.payload.getUsername());
		
		logger.info("Validated status code.");
		Assert.assertEquals(response.getStatusCode(), 200);
		logger.info("Received response body.");
		logger.info(response.getBody().asString());
	}
	
	@Test(priority = 6)
	void testReadUser3() {
		logger.info("----------TestReadUser3----------");
		Response response = UserEndPoints.getUser(this.payload.getUsername());
		
		logger.info("Validated status code.");
		Assert.assertEquals(response.getStatusCode(), 404);
		logger.info("Received response body.");
		logger.info(response.getBody().asString());
	}
}







