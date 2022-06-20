package Utils;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WaitUtils {

	public static void commonwait() {
		int timeOut = Integer.parseInt(PropertyFileUtils.prop.getProperty("CommonWait"));
		ThreadLocalFactory.getDriver().manage().timeouts().implicitlyWait(timeOut, TimeUnit.SECONDS);
		
	}
	
}
