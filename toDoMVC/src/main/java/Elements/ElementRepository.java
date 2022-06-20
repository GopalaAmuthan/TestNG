package Elements;

import java.util.HashMap;

import org.openqa.selenium.By;

public class ElementRepository {
	private static HashMap<String, By> hashMap = new HashMap<String, By>();
	
	static {
		hashMap.put("edeaddToDO", By.xpath("//*[@class='ng-scope']//header//form//input"));
		hashMap.put("eleToDOCount", By.xpath("//*[@class='ng-scope']//section[@class='main']//ul//li"));
		hashMap.put("eleToDOlabel", By.xpath("//*[@class='ng-scope']//section[@class='main']//ul//li//label"));
		hashMap.put("eleMarkToDOComplete", By.xpath("//*[@class='ng-scope']//section[@class='main']//ul//li//input"));
		hashMap.put("eleToDOCountFooter", By.xpath("//footer//span//strong"));
		hashMap.put("eleToDOActiveButton", By.xpath("//footer//ul//li[2]//a"));
		hashMap.put("eleToDOCompleteButton", By.xpath("//footer//ul//li[3]//a"));
		hashMap.put("eleToDORemove", By.xpath("//*[@class='ng-scope']//section[@class='main']//ul//li//button"));
	}
	public static By getObject(String xpath) {
		By element = null;
		if(hashMap.containsKey(xpath)) {
			element = hashMap.get(xpath);
		}
		return element;
	}
}
