

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import files.ReusableMethods;
import files.payLoad;

import static io.restassured.RestAssured.*;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

public class ONE_DynamicJson 
{
	@Test(dataProvider="BooksData")
	public void addBook(String isbn, String aisle)
	{
		RestAssured.basePath = "http://216.10.245.166";
		
		String response = given().header("Content-Type", "application/json")
			.body(payLoad.AddBook(isbn, aisle))
			.when()
			.post("/Library/AddBook.php")
			.then().log().all().assertThat().statusCode(200)
			.extract().response().asString();
		
		JsonPath js = ReusableMethods.rawToJson(response);
		String id = js.getString("ID");
		
		System.out.println(id);
	}
	
	@DataProvider(name="BooksData")
	public Object[][] getData()
	{
		return new Object[][] {{"asass", "2345"}, {"ppkh", "9976"}, {"eggz", "6754"}};
	}
}
