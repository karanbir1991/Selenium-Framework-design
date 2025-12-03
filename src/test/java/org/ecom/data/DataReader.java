package org.ecom.data;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;
import java.nio.charset.StandardCharsets;
import org.apache.commons.io.FileUtils;
import org.ecom.pageobjects.Landing;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import tools.jackson.core.type.TypeReference;
import tools.jackson.databind.ObjectMapper;

public class DataReader {
	
	public List<HashMap<String, String>> getJsonDataToMap() throws Exception {

	    // Read JSON file into a String
	    String jsonContent = FileUtils.readFileToString(
	            new File(System.getProperty("user.dir") + "/src/main/java/org/ecom/data/DataFile.json"),
	            StandardCharsets.UTF_8);

	    // Convert JSON String → List of HashMaps
	    ObjectMapper mapper = new ObjectMapper();

	    List<HashMap<String, String>> data =
	            mapper.readValue(jsonContent, new TypeReference<List<HashMap<String, String>>>() {});

	    return data;
	}


}
