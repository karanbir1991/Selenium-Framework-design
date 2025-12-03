package cucumber;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
	    features = "src/test/java/cucumber",
	    glue = "org.ecom.stepDefinition",
	    plugin = {"pretty", "html:target/cucumber.html"}
	)
public class TestngTestrunner extends AbstractTestNGCucumberTests {

}
