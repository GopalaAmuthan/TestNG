package toDoMVCScripts;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import Utils.CoreUtils;
import Utils.JsonUtils;
import Utils.ThreadLocalFactory;

public class VerifyMarkAsCompleteToDoTask {

private static Logger logger = LogManager.getLogger(VerifyMarkAsCompleteToDoTask.class.getName());
	
	@BeforeClass
	//loading properties file
	public static void loadFiles() throws Exception {
		VerifyAddedToDoList.loadFiles();
	}
	
	
	@Test
	//to mark added item as complete and validate if added item is marked as complete
	public void completeaddedItem() {
		logger.info("Entering into validate the added item is marked as completed item in List");
		Reporter.log("Entering into validate the added item is marked as completed item in List");
		CoreUtils.markAsComplete((String) JsonUtils.jsonObject.get("Task"));
		boolean complete = CoreUtils.validateMarkAsComplete((String) JsonUtils.jsonObject.get("Task"));
		try {
			Assert.assertEquals(complete, true);
			logger.info("Added item is marked as completed item in List");
			Reporter.log("Added item is marked as completed item in List");
		} catch (Exception e) {
			logger.info("Added item is not marked as completed item in List");
			Reporter.log("Added item is not marked as completed item in List");	
		}		
		logger.info("Completed validating the added item is marked as completed item in List");
		Reporter.log("Completed validating the added item is marked as completed item in List");
	}
	
	@Test 
	//to verify footer count after marking as complete
	public void validateFooterCount() {
		logger.info("Entering into validate the footer after marking an item as completed item in List");
		Reporter.log("Entering into validate the footer after marking an item as completed item in List");
		boolean complete = CoreUtils.verifyFooterCount("0");
		try {
			Assert.assertEquals(complete, true);
			logger.info("Footer count of is displayed as 0 after marking an item as completed item in List");
			Reporter.log("Footer count of is displayed as 0 after marking an item as completed item in List");
		} catch (Exception e) {
			logger.info("Footer count of is not displayed as 0 after marking an item as completed item in List");
			Reporter.log("Footer count of is not displayed as 0 after marking an item as completed item in List");		
		}		
		logger.info("Completed validating the footer after marking an item as completed item in List");
		Reporter.log("Completed validating the footer after marking an item as completed item in List");
	}
	
	
	@AfterClass
	public void shutDownDriver() {
		if(ThreadLocalFactory.getDriver()!=null) {
			ThreadLocalFactory.getDriver().close();
			}
	}
	 
}
