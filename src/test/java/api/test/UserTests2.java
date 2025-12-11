package api.test;
import api.payload.User;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import com.github.javafaker.Faker;
import api.endpoints.UserEndpoints2;

import io.restassured.response.Response;

public class UserTests2 {

	Faker faker;
	User userPayload;
	public Logger logger;
	
	@BeforeClass
	public void setUp()
	{
		faker = new Faker();
		userPayload = new User();
		
		userPayload.setId(faker.idNumber().hashCode());
		userPayload.setUsername(faker.name().username());
		userPayload.setFirstName(faker.name().firstName());
		userPayload.setLastName(faker.name().lastName());
		userPayload.setEmail(faker.internet().safeEmailAddress());
		userPayload.setPassword(faker.internet().password(5,10));
		userPayload.setPhone(faker.phoneNumber().cellPhone());
		
		//logs
		logger  =LogManager.getLogger(this.getClass());
	}
	
	@Test(priority=1)
	public void testPostUser()
	{
		logger.info("******************Creating User******************");
		Response response = UserEndpoints2.createUser(userPayload);
		response.then().log().all();
		
		//validating status code
		Assert.assertEquals(response.getStatusCode(), 200);
		logger.info("******************User is created******************");

	}
	
	@Test(priority=2)
	public void testGetUserByName()
	{
		logger.info("******************Reading User Info******************");

		Response response = UserEndpoints2.getUser(this.userPayload.getUsername());
        response.then().log().all();
		
		Assert.assertEquals(response.getStatusCode(), 200);
		logger.info("******************User Info Displayed******************");

	}
	
	@Test(priority=3)
	public void testUpdateUser()
	{
		logger.info("******************Updating User Info******************");

		//update data using payload
		userPayload.setFirstName(faker.name().firstName());
		userPayload.setLastName(faker.name().lastName());
		userPayload.setEmail(faker.internet().safeEmailAddress());
		
		Response response = UserEndpoints2.updateUser(this.userPayload.getUsername(),userPayload);		
		Assert.assertEquals(response.getStatusCode(), 200);

		
		//checking data after update
		Response responseAfterUpdate = UserEndpoints2.getUser(this.userPayload.getUsername());
		responseAfterUpdate.then().log().body();
		Assert.assertEquals(responseAfterUpdate.getStatusCode(), 200);
		logger.info("******************User is successfully updated******************");

		
	}
	
	@Test(priority=4)
	public void testdeleteUser()
	{
		logger.info("******************Deleting User******************");

		Response response = UserEndpoints2.deleteUser(this.userPayload.getUsername());
		response.then().log().body();
		
		Assert.assertEquals(response.getStatusCode(), 200);
		Assert.assertEquals(response.body().jsonPath().getString("message"), this.userPayload.getUsername());
		logger.info("******************User is successfully deleted******************");

	}
	
}
