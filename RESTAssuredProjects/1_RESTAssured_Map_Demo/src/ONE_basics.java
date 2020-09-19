import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import org.testng.Assert;

import files.ReusableMethods;
import files.payload;

public class ONE_basics {

	public static void main(String[] args) 
	{
		// TODO Auto-generated method stub

		// Given - all input details
		// When - submit the API request - resource + HTTP method e.g. POST, GET etc
		// Then - validate the response
		
		RestAssured.baseURI = "https://rahulshettyacademy.com";
		
		// ADD PLACE
		String response = given().log().all().queryParam("key", "qaclick123")
			.header("Content-Type", "application/json")
			.body(payload.AddPlace())
			.when().post("/maps/api/place/add/json")
			.then().log().all().assertThat().statusCode(200)
			.body("scope", equalTo("APP"))
			.header("Server", "Apache/2.4.18 (Ubuntu)")
			.extract().response().asString();
		
		JsonPath js = new JsonPath(response);
		String placeId = js.get("place_id");
		
		////
		
		// UPDATE Place
		String newAddress = "Summer Walk, Africa";
		
		given().log().all().queryParam("key", "qaclick123")
			.header("Content-Type", "application/json")
			.body("{\r\n" + 
					"    \"place_id\":\"" + placeId + "\",\r\n" + 
					"    \"address\":\"" + newAddress + "\",\r\n" + 
					"    \"key\":\"qaclick123\"\r\n" + 
					"}")
			.when().put("/maps/api/place/update/json")
			.then().assertThat().log().all().statusCode(200)
			.body("msg", equalTo("Address successfully updated"));
		
		//////
		
		// GET Place
		String getPlaceResponse = given().log().all()
			.queryParam("key",  "qaclick123")
			.queryParam("place_id", placeId)
			.when().get("/maps/api/place/get/json")
			.then().assertThat().log().all().statusCode(200)
			.extract().response().asString();
		
		JsonPath js1 = ReusableMethods.rawToJson(getPlaceResponse);
		String actualAddress = js1.getString(("address"));
		
		Assert.assertEquals(actualAddress, newAddress);
	}

}
