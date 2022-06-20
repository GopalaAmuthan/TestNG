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

public class VerifyAddedToDoList {

private static Logger logger = LogManager.getLogger(VerifyAddedToDoList.class.getName());
	
	@BeforeClass
	//loading properties file
	public static void loadFiles() throws Exception {
		PropertyFileUtils.loadconfig();
		JsonUtils.loadJson("ToDoDetails");
		DriverClass.initializeWebDriver();
		logger.info("Launching URL");		
		ThreadLocalFactory.getDriver().get(PropertyFileUtils.prop.getProperty("URL"));
		WaitUtils.commonwait();	
		CoreUtils.actionOnElement("edeaddToDO", (String) JsonUtils.jsonObject.get("Task"));
	}
	
	@Test
	//to verify add item into cart
	public void verifyAddItem() throws Exception {
		logger.info("Entering into validate the added item in List");
		Reporter.log("Entering into validate the added item in List");
		boolean statusName = CoreUtils.verifyStringinList("eleToDOlabel", (String) JsonUtils.jsonObject.get("Task"));
		try {
			Assert.assertEquals(statusName, true);
			logger.info((String) JsonUtils.jsonObject.get("Task") + " is added in List");
			Reporter.log((String) JsonUtils.jsonObject.get("Task") + " is added in List");
		} catch (Exception e) {
			logger.error((String) JsonUtils.jsonObject.get("Task") + " is not added in List");
			Reporter.log((String) JsonUtils.jsonObject.get("Task") + " is not added in List");
		}
		logger.info("Completed validating the added item in List");
		Reporter.log("Completed validating the added item in List");
	}
	
	@AfterClass
	public void shutDownDriver() {
		if(ThreadLocalFactory.getDriver()!=null) {
			ThreadLocalFactory.getDriver().close();
			}
	}
}
