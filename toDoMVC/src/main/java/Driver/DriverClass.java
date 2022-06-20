package Driver;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import Utils.PropertyFileUtils;
import Utils.ThreadLocalFactory;

public class DriverClass {
	public static WebDriver driver = null;
	private static Logger logger = LogManager.getLogger(DriverClass.class.getName());
	
	//to initialize webdriver
	public static WebDriver initializeWebDriver() throws Exception {
		String browser = PropertyFileUtils.prop.getProperty("browser");
		String driverPath = PropertyFileUtils.prop.getProperty("driverPath");
		switch(browser) {
		  case "Chrome":
			  System.setProperty("webdriver.chrome.driver", driverPath + "\\chromedriver.exe");
			  driver = new ChromeDriver();
			  ThreadLocalFactory.setDriver(driver);
			  logger.info("Initializing webDriver");			  
			  ThreadLocalFactory.getDriver().manage().window().maximize();
		    break;
		  
		  default:
		   logger.error("No matching browsers identified for setting up driver");
		}
		return driver;
	}
	
	

}
