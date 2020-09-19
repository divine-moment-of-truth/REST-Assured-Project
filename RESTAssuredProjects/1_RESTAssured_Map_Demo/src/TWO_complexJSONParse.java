import org.testng.Assert;

import files.payload;
import io.restassured.path.json.JsonPath;

public class TWO_complexJSONParse 
{
	public static void main(String[] args) 
	{
		JsonPath js = new JsonPath(payload.CoursePricesComplexJson());
		
		// 1. Total number of courses
		int courseCount = js.getInt("courses.size()");
		
		// 2. Get 'PurchaseAmount' from complex JSON object
		int purchaseAmount = js.getInt("dashboard.purchaseAmount");
		
		// 3. Print title of first course
		String titleOfFirstCourse = js.getString("courses[0].title");
		
		// 4. Print All course titles and their respective Prices
		for(int i = 0; i < courseCount; i++)
		{
			System.out.println("Course Name: " + js.getString("courses[" + i + "].title") + ", Price: " + js.getInt("courses[" + i + "].price"));
		}
		
		System.out.println("CHIPS AND BEANS");
		
		// 5. Print no of copies sold by RPA Course
		int cypressNumSold = js.getInt("courses[2].copies");
		System.out.println(cypressNumSold);
		
		for(int i = 0; i < courseCount; i++)
		{
			String courseTitle = js.getString("courses[" + i + "].title");
			
			if (courseTitle.equalsIgnoreCase("RPA"))
			{
				int copies = js.getInt("courses[" + i + "].copies");
				System.out.println("Number of RSA Courses: " + copies);
				break;
			}
		}
		
		// 6. Verify if Sum of all Course prices matches with Purchase Amount
		int sumOfAllCourses = 0;
		int totalPerCourse = 0;
		for(int i = 0; i < courseCount; i++)
		{
			totalPerCourse = js.getInt("courses[" + i + "].price") * js.getInt("courses[" + i + "].copies");
			sumOfAllCourses += totalPerCourse;
		}
		System.out.println("Sum of all courses: " + sumOfAllCourses);
		System.out.println("Purchase amount: " + purchaseAmount);
		Assert.assertEquals(purchaseAmount, sumOfAllCourses);
	}
}
