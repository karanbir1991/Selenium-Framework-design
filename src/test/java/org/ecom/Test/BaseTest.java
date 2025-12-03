package org.ecom.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.ecom.pageobjects.Landing;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import io.github.bonigarcia.wdm.WebDriverManager;
import tools.jackson.core.type.TypeReference;
import tools.jackson.databind.ObjectMapper;

public class BaseTest {
	
	public WebDriver driver;
	public Landing landing;
	public ExtentReports extent;
	
	public WebDriver intializedriver() throws IOException {
		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream(System.getProperty("user.dir") + "/src/main/java/org/ecom/resources/GlobalData.properties");
		prop.load(fis);
		String browser = System.getProperty("browser")!=null ? System.getProperty("browser"):prop.getProperty("browser");
		
		if (browser.contains("chrome"))
		{
			  Map<String, Object> chromePrefs = new HashMap<>();
	          // Disable password leak detection
	          chromePrefs.put("profile.password_manager_leak_detection", false);
	          // Optionally, disable credentials saving service as well
	          chromePrefs.put("credentials_enable_service", false);

	          // Create ChromeOptions and add the preferences
	          ChromeOptions options = new ChromeOptions();
	          options.setExperimentalOption("prefs", chromePrefs);
	          if (browser.contains("headless")) {
	        	  options.addArguments("--headless=new");           // or just "--headless" if old is fine
	        	  options.addArguments("--no-sandbox");
	        	  options.addArguments("--disable-dev-shm-usage");
	        	  options.addArguments("--disable-gpu");
	        	  options.addArguments("--remote-debugging-port=9222");
	          }
			  driver = new ChromeDriver(options);
			  driver.manage().window().setSize(new Dimension(1920,1080));
			  System.out.println(driver.manage().window().getSize());
		}
		else if(browser.equalsIgnoreCase("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
		}
		else if(browser.equalsIgnoreCase("edge")) {
			driver = new EdgeDriver();
		}
		else {
			System.out.println("Browser not opening");
		}
		if (!browser.contains("headless")) {
		driver.manage().window().maximize();
		}
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		return driver;
	}
	
	public List<HashMap<String, String>> getJsonDataToMap(String filepath) throws Exception {

	    // Read JSON file into a String
	    String jsonContent = FileUtils.readFileToString(new File(filepath),StandardCharsets.UTF_8);

	    // Convert JSON String → List of HashMaps
	    ObjectMapper mapper = new ObjectMapper();

	    List<HashMap<String, String>> data =
	            mapper.readValue(jsonContent, new TypeReference<List<HashMap<String, String>>>() {});

	    return data;
	}
	
	@BeforeMethod(alwaysRun = true)
	public Landing launchbrowser() throws IOException {
		driver=intializedriver();
		driver.get("https://rahulshettyacademy.com/client");
		landing= new Landing(driver);
		return landing;	
	}
	@AfterMethod(alwaysRun = true)
	public void closedriver() throws Exception {
		Thread.sleep(1000);
		driver.quit();
	}
	
	public String getScreenshot(String testCaseName, WebDriver driver) throws IOException {

	    TakesScreenshot ts = (TakesScreenshot) driver;
	    File source = ts.getScreenshotAs(OutputType.FILE);

	    String filePath = System.getProperty("user.dir") + "//reports//" + testCaseName + ".png";
	    File destination = new File(filePath);

	    FileUtils.copyFile(source, destination);

	    return filePath;   // Return the full path for ExtentReports attachment
	}

	
	public void config() {
		String path = System.getProperty("user.dir")+"/Report/report.html";
		ExtentSparkReporter reporter = new ExtentSparkReporter(path);
		reporter.config().setReportName("Web Automation Report");
		reporter.config().setDocumentTitle("Test Results");
		
		extent = new ExtentReports();
		extent.attachReporter(reporter);
		extent.setSystemInfo("Tester", "Ecom");
		
	}

}
