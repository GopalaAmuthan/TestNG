package toDoMVCScripts;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import Driver.DriverClass;
import Utils.CoreUtils;
import Utils.JsonUtils;
import Utils.PropertyFileUtils;
import Utils.ThreadLocalFactory;
import Utils.WaitUtils;

public class VerifyFilteringToDoStatus {

private static Logger logger = LogManager.getLogger(VerifyFilteringToDoStatus.class.getName());
	
	@BeforeClass
	//loading properties file
	public static void loadFiles() throws Exception {
		PropertyFileUtils.loadconfig();
		JsonUtils.loadJson("ToDoDetails");
		DriverClass.initializeWebDriver();
		logger.info("Launching URL");
		ThreadLocalFactory.getDriver().get(PropertyFileUtils.prop.getProperty("URL"));
		WaitUtils.commonwait();	
		CoreUtils.actionOnElement("edeaddToDO", (String) JsonUtils.jsonObject.get("Task1"));
		CoreUtils.actionOnElement("edeaddToDO", (String) JsonUtils.jsonObject.get("Task2"));
		CoreUtils.markAsComplete((String) JsonUtils.jsonObject.get("Task1"));
	}
	
	@Test
	//to verify footer count in complete tab
	public void validateFooterForCompleteaddedItem() {
		logger.info("Entering into validate the footer for completed item in List");
		Reporter.log("Entering into validate the footer for completed item in List");
		CoreUtils.actionOnElement("eleToDOCompleteButton", "");
		boolean complete = CoreUtils.verifyFooterCount("1");
		try {
			Assert.assertEquals(complete, true);
			logger.info("Footer count of completed item is diplayed as 1");
			Reporter.log("Footer count of completed item is diplayed as 1");
		} catch (Exception e) {
			logger.error("Footer count of completed item is not diplayed as 1");
			Reporter.log("Footer count of completed item is not diplayed as 1");		
		}					
		logger.info("Completed validating the footer for completed item in List");
		Reporter.log("Completed validating the footer for completed item in List");
	}
	
	@Test
	//to verify footer count in Active tab
	public void validateFooterForActiveaddedItem() {
		logger.info("Entering into validate the footer for Active item in List");
		Reporter.log("Entering into validate the footer for Active item in List");
		CoreUtils.actionOnElement("eleToDOActiveButton", "");
		boolean active = CoreUtils.verifyFooterCount("1");
		try {
			Assert.assertEquals(active, true);
			logger.info("Footer count of Active item is diplayed as 1");
			Reporter.log("Footer count of Active item is diplayed as 1");
		} catch (Exception e) {
			logger.error("Footer count of Active item is not diplayed as 1");
			Reporter.log("Footer count of Active item is not diplayed as 1");		
		}
		logger.info("Completed validating the footer for Active item in List");
		Reporter.log("Completed validating the footer for Active item in List");
	}
	
	@AfterClass
	public void shutDownDriver() {
		if(ThreadLocalFactory.getDriver()!=null) {
			ThreadLocalFactory.getDriver().close();
			}
	}
}
