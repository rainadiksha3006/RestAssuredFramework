package api.test;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import java.util.Date;

import api.endpoints.StoreEndpoints;
import api.payload.Store;
import io.restassured.response.Response;

public class StoreTests {


	Store storePayload;
	public Logger logger;
	
	@BeforeClass
	public void setUp()
	{
		
		storePayload = new Store();
		
		storePayload.setId(1);
		storePayload.setPetId(2);
		storePayload.setQuantity(10);
		storePayload.setShipDate(new Date());
		storePayload.setStatus("approved");
		storePayload.setComplete(true);
		logger = LogManager.getLogger(this.getClass());
		
		
	}
	
	@Test(priority=1)
	public void placePetOrder()
	{
		logger.info("**********************Placing an order**********************");
		Response response =	StoreEndpoints.createOrder(storePayload);
		response.then().log().all();
		
		Assert.assertEquals(response.getStatusCode(), 200);
		Assert.assertEquals(response.jsonPath().getInt("id"), storePayload.getId());
		Assert.assertEquals(response.jsonPath().getInt("petId"), storePayload.getPetId());
		logger.info("**********************order placed for purchasing the pet**********************");
	}
	
	@Test(priority=2)
	public void getPetOrder()
	{
		logger.info("**********************Find purchase order by id**********************");
		Response response = StoreEndpoints.getPurchaseOrder(storePayload.getId());
		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(), 200);
		Assert.assertEquals(response.jsonPath().getInt("id"), storePayload.getId());
		Assert.assertEquals(response.jsonPath().getInt("petId"), storePayload.getPetId());
		logger.info("**********************Purchase order details retrieved successfully**********************");
	}
	
	@Test(priority=3)
	public void deletePetOrder()
	{
		logger.info("**********************Delete purchase order by id**********************");
		Response response = StoreEndpoints.deletePurchaseOrder(storePayload.getId());
		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(), 200);
		logger.info("**********************Purchase order deleted successfully**********************");
	}
}
