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

	private By btnRegister = By.xpath("//a[text()='Register']");
	private By btnLogin = By.xpath("//a[text()='Login']");
	private By btnAcceptAllCookies = By.id("onetrust-accept-btn-handler");

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
		util.waitForElementToBeClickable(btnAcceptAllCookies, 10);
		util.doClick(btnAcceptAllCookies);
		util.waitForElementToBeClickable(btnRegister, 10);
		util.doClick(btnRegister);
	}
	public void clickOnLogin() throws InterruptedException{
		Log.info("Click on Login button on Home page");
		//Accept all cookies
		util.waitForElementToBeClickable(btnAcceptAllCookies, 10);
		util.doClick(btnAcceptAllCookies);
		util.waitForElementToBeClickable(btnLogin, 10);
		util.doClick(btnLogin);
	}
}
