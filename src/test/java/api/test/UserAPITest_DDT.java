package api.test;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;

import api.endpoints.UserEndPoints;
import api.payload.User;
import api.utilities.DataProviderClass;
import io.restassured.response.Response;

public class UserAPITest_DDT {
	public Logger logger;
	
	@Test(priority = 1, dataProvider = "getUserData", dataProviderClass = DataProviderClass.class )
	public void testCreateUser(String Id,	String Username, String Firstname, String Lastname, String Email, String Password, String Phone, String	Userstatus) {
		User payload = new User();
		logger = LogManager.getLogger(this.getClass());
		
		payload.setId(Integer.parseInt(Id));
		payload.setUsername(Username);
		payload.setFirstName(Firstname);
		payload.setLastName(Lastname);
		payload.setEmail(Email);
		payload.setPassword(Password);
		payload.setPhone(Phone);
		payload.setUserStatus(Integer.parseInt(Userstatus));
		
		logger.info("----------TestCreateUser----------");
		Response response = UserEndPoints.postUser(payload);
		logger.info("Validated status code.");
		Assert.assertEquals(response.getStatusCode(), 200);
		logger.info("Received response body.");
		logger.info(response.getBody().asString());
	}
	
	@Test(priority = 2, dataProvider = "getUsername", dataProviderClass = DataProviderClass.class)
	public void testReadUserByName(String Username) {
		logger.info("----------TestReadUserByName----------");
		Response response = UserEndPoints.getUser(Username);
		logger.info("Validated status code.");
		Assert.assertEquals(response.getStatusCode(), 200);
		logger.info("Received response body.");
		logger.info(response.getBody().asString());
	}
	
	@Test(priority = 3, dataProvider = "getUsername", dataProviderClass = DataProviderClass.class)
	public void testDeleteUserByName(String Username) {
		logger.info("----------TestDeleteUserByName----------");
		Response response = UserEndPoints.deleteUser(Username);
		logger.info("Validated status code.");
		Assert.assertEquals(response.getStatusCode(), 200);
		logger.info("Received response body.");
		logger.info(response.getBody().asString());
	}
	

}
