package configreader;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigReader {
	
	private static Properties properties;
	
	static {
		loadProperties();
	}
	
	private static void loadProperties() {
		
		String env = System.getProperty("env") !=null ? System.getProperty("env") : "qa";	// default environment
		
		String fileName = "config/" + env + ".properties";
		
		properties = new Properties();
		
		try (InputStream input = ConfigReader.class.getClassLoader().getResourceAsStream(fileName)) {
			
			if (input == null) {
				throw new RuntimeException("Config file not found: " + fileName);
			}
			
			properties.load(input);
		
		} catch (IOException e) {
			throw new RuntimeException("Failed to load config file: " + fileName);
		}
		
	}
	
	public static String get(String key) {
		return properties.getProperty(key);
	}
}
