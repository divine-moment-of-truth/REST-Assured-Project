package files;

public class payLoad 
{
	public static String AddBook(String aisle, String isbn)
	{
		String payload = "{\r\n" + 
				"    \"name\":\"Learn Appium Automation with Java\",\r\n" + 
				"    \"isbn\":\"" + isbn + "\",\r\n" + 
				"    \"aisle\":\"" + aisle + "\",\r\n" + 
				"    \"author\":\"John foe\"\r\n" + 
				"}\r\n" + 
				"";
		return payload;
	}
}
