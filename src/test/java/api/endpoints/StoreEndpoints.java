package api.endpoints;
import static io.restassured.RestAssured.*;
import api.payload.Store;
import io.restassured.http.ContentType;
import io.restassured.response.Response;


public class StoreEndpoints {

	public static Response createOrder(Store payload)
	{
	Response response =	given()
		                 .accept(ContentType.JSON)
		                 .contentType(ContentType.JSON)
		                 .body(payload)
		
		                .when()
		                  .post(Routes.store_post_url);
		
	return response;
		
	}
	
	public static Response getPurchaseOrder(int orderId)
	{
		Response response =	given()
                .accept(ContentType.JSON)
                .pathParam("orderId", orderId)

               .when()
                 .get(Routes.store_get_url);
		
		return response;
	}
	
	public static Response deletePurchaseOrder(int orderId)
	{
		Response response =	given()
                .accept(ContentType.JSON)
                .pathParam("orderId", orderId)

               .when()
                 .delete(Routes.store_delete_url);
		
		return response;
	}
}
