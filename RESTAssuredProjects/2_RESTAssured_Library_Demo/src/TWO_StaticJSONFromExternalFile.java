import static io.restassured.RestAssured.given;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.testng.annotations.Test;

import files.ReusableMethods;
import files.payLoad;

import static io.restassured.RestAssured.*;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

public class TWO_StaticJSONFromExternalFile 
{
	@Test
	public void addBook() throws IOException
	{
		RestAssured.basePath = "http://216.10.245.166";
		
		String response = given().header("Content-Type", "application/json")
			.body(new String(Files.readAllBytes(Paths.get("C:\\Users\\Andy\\Documents\\software\\Test Automation\\RESTAssured\\RESTAssuredProjects\\2_RESTAssured_Library_Demo\\src\\files\\AddBook.json"))))
			.when()
			.post("/Library/AddBook.php")
			.then().log().all().assertThat().statusCode(200)
			.extract().response().asString();
		
		JsonPath js = ReusableMethods.rawToJson(response);
		String id = js.getString("ID");
		
		System.out.println(id);
	}
}
