package app.Pages;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import app.Factory.DriverFactory;
import app.Util.ElementUtil;
import app.Util.Log;

public class HomePage {
	private WebDriver driver;
	ElementUtil util = new ElementUtil(DriverFactory.getDriver());

	private By register = By.xpath("//a[text()='Register']");
	private By login = By.xpath("//a[text()='Login']");
	private By acceptAllCookies = By.id("onetrust-accept-btn-handler");

	public HomePage(WebDriver driver) {
		this.driver = driver;
	}
	// Page Actions
	public String getHomePageTitle() throws InterruptedException {
		TimeUnit.SECONDS.sleep(3);
		return driver.getTitle();
	}
	public void clickOnRegister() throws InterruptedException {
		Log.info("Click on Register button on Home page");
		//Accept all cookies
		util.waitForElementToBeClickable(acceptAllCookies, 10);
		util.doClick(acceptAllCookies);
		util.waitForElementToBeClickable(register, 10);
		util.doClick(register);
	}
	public void clickOnLogin() throws InterruptedException{
		Log.info("Click on Login button on Home page");
		//Accept all cookies
		util.waitForElementToBeClickable(acceptAllCookies, 10);
		util.doClick(acceptAllCookies);
		util.waitForElementToBeClickable(login, 10);
		util.doClick(login);
	}
}
