package Utils;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

public class PropertyFileUtils {
	public static Properties prop = new Properties();
	
	public static void loadconfig() throws Exception {
		File directory = new File(".");
		String configFilepath = directory.getCanonicalPath() +"\\src\\main\\resources";
		FileInputStream file = new FileInputStream(configFilepath +"\\application.properties");
		prop.load(file);					
	}

}
