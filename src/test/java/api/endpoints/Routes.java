package api.endpoints;
/*
Swagger URI --> https://petstore.swagger.io
Create user (POST) : https://petstore.swagger.io/v2/user
Get user (GET) : https://petstore.swagger.io/v2/user/{username}
Update user (PUT) : https://petstore.swagger.io/v2/user/{username}
Delete user (Delete) : https://petstore.swagger.io/v2/user/{username}

 */
public class Routes {

	public static String base_url ="https://petstore.swagger.io/v2";
	
	//User module
	public static String user_post_url =base_url+"/user";
	public static String user_get_url = base_url+"/user/{username}";
	public static String user_update_url = base_url+"/user/{username}";
	public static String user_delete_url = base_url+"/user/{username}";
	
	//Store Module
	public static String store_inventory_get_url = base_url+"/store/inventory";
	public static String store_post_url = base_url+"/store/order";
	public static String store_get_url = base_url+"/store/order/{orderId}";
	public static String store_delete_url = base_url+"/store/order/{orderId}";
	
	
	//Pet Module
	public static String pet_post_url = base_url+"/pet";
	public static String pet_put_url = base_url+"/pet";
	public static String pet_get_url = base_url+"/pet/{petId}";
	public static String pet_delete_url = base_url+"/pet/{petId}";
	
	
	
}
