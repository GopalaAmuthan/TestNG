package Utils;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import Driver.DriverClass;
import Elements.ElementRepository;

public class CoreUtils extends DriverClass {
	private static Logger logger = LogManager.getLogger(CoreUtils.class.getName());
	
	//common method to perform actions on webelements
	public static void actionOnElement(String element, String value) {
		String elementType = element.substring(0, 3);
		WebElement ele;
		switch (elementType.toUpperCase()) {
		//to click an object
		case "ELE":
			ele = ThreadLocalFactory.getDriver().findElement(ElementRepository.getObject(element));
			ele.click();
			WaitUtils.commonwait();
			logger.info("Clicked on object "+element);
			break;
		
		//to enter values in edit field and tab out
		case "EDT":
			ele = ThreadLocalFactory.getDriver().findElement(ElementRepository.getObject(element));
			ele.click();
			ele.sendKeys(Keys.chord(Keys.CONTROL, "a"));
			ele.sendKeys(value);
			ele.sendKeys(Keys.TAB);
			WaitUtils.commonwait();
			logger.info("Entered value "+value+" for object "+element);
			break;
			
		//to enter values in edit field and enter
		case "EDE":
			ele = ThreadLocalFactory.getDriver().findElement(ElementRepository.getObject(element));
			ele.click();
			ele.sendKeys(Keys.chord(Keys.CONTROL, "a"));
			ele.sendKeys(value);
			ele.sendKeys(Keys.ENTER);
			WaitUtils.commonwait();
			logger.info("Entered value "+value+" for object "+element);
			break;
		}
	}
	
	//to verify size of an WebElement
	public static boolean verifyCount(String element, int num) {
		boolean countValue = false;		
		int count = ThreadLocalFactory.getDriver().findElements(ElementRepository.getObject(element)).size();
		if(count == num) {
			countValue = true;
		}
		return countValue;
	}
	
	//to verify display text of an WebElement
	public static boolean verifyStringinList(String element, String name) {
		boolean display = false;
		List<WebElement> toDoList = ThreadLocalFactory.getDriver().findElements(ElementRepository.getObject(element));
		for (WebElement ele : toDoList) {
			String displayValue = ele.getText();
			if(displayValue.equalsIgnoreCase(name)) {
				display = true;
				break;
			}				
		}
		return display;
	}
	
	//to mark a toDO task as complete
	public static void markAsComplete(String name) {
		int display = 0;
		List<WebElement> toDoListName = ThreadLocalFactory.getDriver().findElements(ElementRepository.getObject("eleToDOlabel"));				
		for (WebElement ele : toDoListName) {
			display++;
			String displayValue = ele.getText();
			if(displayValue.equalsIgnoreCase(name)) {				
				break;
			}				
		}
		List<WebElement> toDoListAction = ThreadLocalFactory.getDriver().findElements(ElementRepository.getObject("eleMarkToDOComplete"));
		toDoListAction.get(display-1).click();
		WaitUtils.commonwait();
		logger.info("Marked as Complete for the task "+name);		
	}
	
	//to verify marked toDO task is complete
	public static boolean validateMarkAsComplete(String name) {
		boolean complete = false;
		int display = 0;
		List<WebElement> toDoListName = ThreadLocalFactory.getDriver().findElements(ElementRepository.getObject("eleToDOlabel"));		
		for (WebElement ele : toDoListName) {
			display++;
			String displayValue = ele.getText();
			if(displayValue.equalsIgnoreCase(name)) {				
				break;
			}				
		}
		List<WebElement> toDoListAction = ThreadLocalFactory.getDriver().findElements(ElementRepository.getObject("eleToDOCount"));
		String classValue = toDoListAction.get(display-1).getAttribute("class");
		if(classValue.contains("complete")) {
			complete = true;
		}
		WaitUtils.commonwait();
		logger.info("Completed validating the Marked as Complete for the task "+name);
		return complete;
	}
	//to verify footer count of tabs
	public static boolean verifyFooterCount(String num) {
		boolean countValue = false;		
		String count = ThreadLocalFactory.getDriver().findElement(ElementRepository.getObject("eleToDOCountFooter")).getText();
		if(count.equals(num)) {
			countValue = true;
		}
		return countValue;
	}
	
	//to remove a toDO task
	
	public static void removeAddedToDOTask(String name) {
		int display = 0;
		List<WebElement> toDoListName = ThreadLocalFactory.getDriver().findElements(ElementRepository.getObject("eleToDOlabel"));				
		for (WebElement ele : toDoListName) {
			display++;
			String displayValue = ele.getText();
			if(displayValue.equalsIgnoreCase(name)) {				
				break;
			}				
		}
		Actions action = new Actions(ThreadLocalFactory.getDriver());
		List<WebElement> toDoListAction = ThreadLocalFactory.getDriver().findElements(ElementRepository.getObject("eleToDOCount"));
		action.moveToElement(toDoListAction.get(display-1)).build().perform();
		List<WebElement> removeAction = ThreadLocalFactory.getDriver().findElements(ElementRepository.getObject("eleToDORemove"));
		removeAction.get(display-1).click();
		WaitUtils.commonwait();
		logger.info("Removed task "+name+" from To DO List");		
	}

}
