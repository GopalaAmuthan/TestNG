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

public class VerifyRemoveToDoTask {

private static Logger logger = LogManager.getLogger(VerifyRemoveToDoTask.class.getName());
	
	@BeforeClass
	//loading properties file
	public static void loadFiles() throws Exception {
		VerifyFilteringToDoStatus.loadFiles();
	}
	
	@Test
	//to remove added task and verify if it is removed
	public void removeAddedTask() {
		logger.info("Entering into validate the removed item is not present in List");
		Reporter.log("Entering into validate the removed item is not present in List");
		CoreUtils.removeAddedToDOTask((String) JsonUtils.jsonObject.get("Task1"));
		boolean statusName = CoreUtils.verifyStringinList("eleToDOlabel", (String) JsonUtils.jsonObject.get("Task1"));
		try {
			Assert.assertEquals(statusName, false);
			logger.info("Removed item is not present in List");
			Reporter.log("Removed item is not present in List");
		} catch (Exception e) {
			logger.info("Removed item is present in List");
			Reporter.log("Removed item is present in List");	
		}		
		logger.info("Completed validating the removed item is not present in List");
		Reporter.log("Completed validating the removed item is not present in List");
	}
	
		@AfterClass
	public void shutDownDriver() {
		if(ThreadLocalFactory.getDriver()!=null) {
			ThreadLocalFactory.getDriver().close();
			}
	}
}
