package Utils;

import org.json.simple.JSONObject;
import org.openqa.selenium.WebDriver;

public class ThreadLocalFactory {
	
	private static ThreadLocal <WebDriver> driver = new ThreadLocal<>();
	private static ThreadLocal <JSONObject> jsonObj = new ThreadLocal<>();

	public static void setDriver(WebDriver dr) {
		driver.set(dr);
	}
	
	public static WebDriver getDriver() {
		return driver.get();
	}
	
	public static void unload() {
		driver.remove();
	}
	
	public static void setJsonObj(JSONObject obj) {
		jsonObj.set(obj);
	}
	
	public static JSONObject getJsonObj() {
		return jsonObj.get();
	}
}
