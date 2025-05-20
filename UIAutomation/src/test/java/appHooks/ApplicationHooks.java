package appHooks;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import app.Factory.DriverFactory;
import app.Util.ConfigReader;
import app.Util.Log;
import app.Util.TestDataStore;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.qameta.allure.Allure;

public class ApplicationHooks {
	
	private DriverFactory driverFactory ;
	private WebDriver driver;
	private ConfigReader configReader;
	Properties prop;
	
	@Before(order = 0)
	public void getProperty() {
		configReader = new ConfigReader();
		prop = configReader.init_prop();
	}

	@Before(order = 1)
	public void launchBrowser(Scenario scenario) {
		Log.info("Starting Scenario: " + scenario.getName());
		String browserName = prop.getProperty("browser");
		driverFactory = new DriverFactory();
		driver = driverFactory.init_driver(browserName);
	}

	@After(order = 0)
	public void quitBrowser() throws IOException {
		driver.quit();
	}

	@After(order = 1)
	public void tearDown(Scenario scenario) {
		if (scenario.isFailed()) {
			// take screenshot:
			Log.info("Taking Screenshot if Scenario Failed !!!");
			String screenshotName = scenario.getName().replaceAll(" ", "_");
			byte[] screenShot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
			scenario.attach(screenShot, "image/png", screenshotName);
			Allure.addAttachment("Failed Screenshot", new ByteArrayInputStream(screenShot));
			Log.error("Scenario FAILED: " + scenario.getName());
		}
		else {
			Log.info("Scenario PASSED: " + scenario.getName());
		}
	}
	
	

}
