package app.Factory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.safari.SafariDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DriverFactory {
	
	public WebDriver driver;
	public static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<>();
	/**
	 * 
	 * @param browser
	 * @return
	 */
	public WebDriver init_driver(String browser) {

		System.out.println("browser value is: " + browser);

		if (browser.equals("chrome")) {
			
			ChromeOptions options = new ChromeOptions();
			// Disable notifications and Pop up
			options.addArguments("--disable-notifications");
			options.addArguments("--disable-popup-blocking");
			WebDriverManager.chromedriver().setup();
			tlDriver.set(new ChromeDriver(options));
			
		} else if (browser.equals("firefox")) {
			FirefoxOptions profile = new FirefoxOptions();
			// Disable notifications and popup blocking
			profile.setCapability("dom.webnotifications.enabled", false);
			profile.setCapability("dom.disable_open_during_load", false);
			WebDriverManager.firefoxdriver().setup();
			tlDriver.set(new FirefoxDriver(profile));
		} else if (browser.equals("safari")) {
			tlDriver.set(new SafariDriver());
		} else {
			System.out.println("Please pass the correct browser value: " + browser);
		}

		getDriver().manage().deleteAllCookies();
		getDriver().manage().window().maximize();
		return getDriver();

	}
	
	// this is used to get the driver with ThreadLocal
	public static synchronized WebDriver getDriver() {
		return tlDriver.get();
	}
	

}
