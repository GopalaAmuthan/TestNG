package Utils;

import java.io.File;
import java.io.FileReader;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class JsonUtils {
 
	public static JSONObject jsonObject;

	public static void loadJson(String FileName) throws Exception {
		File directory = new File(".");
		String configFilepath = directory.getCanonicalPath() +"\\src\\test\\resources\\DataFiles";
		JSONParser parser = new JSONParser();
		Object obj = parser.parse(new FileReader(configFilepath+"\\"+FileName+".json"));		
		jsonObject = (JSONObject) obj;
		ThreadLocalFactory.setJsonObj(jsonObject);			
	}
}
