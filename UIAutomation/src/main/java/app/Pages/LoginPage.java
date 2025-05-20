package app.Pages;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import app.Factory.DriverFactory;
import app.Util.ElementUtil;
import app.Util.Log;

public class LoginPage {
	
	private WebDriver driver;
	ElementUtil util = new ElementUtil(DriverFactory.getDriver());
	
	private By nickNameInputField = By.id("username");
	private By passWordInputField = By.id("password");
	private By loginButton = By.xpath("//button[contains(text(),'Log in')]");
	private By wheelOfFortunePopUp = By.xpath("//div[@class='c-wheel__cta']");
	private By collectBonusButton = By.xpath("//div[@class='c-streak__btn c-btn c-btn--primary c-btn--auto']");
	private By collectButton = By.xpath("//a[@class='c-btn c-btn--primary c-dialog__cta']");
	private By remindMeLaterRadioButton = By.xpath("//input[@type='radio' and @name='receiveEmail' and @value='false']");
	private By marketingConsentNoRadioButton = By.xpath("//input[@type='radio' and @name='marketingConsent' and @value='false']");
	private By confirmButton = By.xpath("//button[@type='submit']");
	private By userProfile = By.xpath("//div[contains(text(),'Nick')]");
	private By userMenu = By.xpath("//span[contains(normalize-space(text()), 'Hello')]");
	private By logOutButton = By.xpath("//a[contains(text(),'Log out')]");
	
	public LoginPage(WebDriver driver) {
		this.driver = driver;
	}
	
	// Page Actions
	public String getLoginPageTitle() throws InterruptedException {
		TimeUnit.SECONDS.sleep(3);
		return driver.getTitle();
	}
	public void firstLogin(String username, String pass ) throws InterruptedException{
		Log.info("Logging first time with newly registered player !!!");
		// Enter Nickname
		util.waitForElementToBeVisible(nickNameInputField, 15);
		util.doSendKeys(nickNameInputField, username);
		// Enter Password
   		util.waitForElementToBeVisible(passWordInputField, 15);
		util.doSendKeys(passWordInputField, pass);
		// Click on Login Button
		util.waitForElementToBeClickable(loginButton, 15);
		util.doClick(loginButton);
		util.waitForElementToBeDisappear(loginButton, 10);
		
		// Click on Wheel of Fortune
		util.waitForElementToBeVisible(wheelOfFortunePopUp,35);
		util.doClick(wheelOfFortunePopUp);
		TimeUnit.SECONDS.sleep(5);
		System.out.println("Wheel of Fortune");
		
		// Click on Bonus button
		util.waitForElementToBeVisible(collectBonusButton,35);
		util.doClick(collectBonusButton);
		TimeUnit.SECONDS.sleep(5);
		System.out.println("collectBonusButton");
		
		// Click on Collect button
		util.waitForElementToBeVisible(collectButton,25);
		util.doClick(collectButton);
		TimeUnit.SECONDS.sleep(5);
		System.out.println("collectButton");
		
		// Wait for overlay to appear
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		WebElement overlay = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".has-overlay")));
		
		// Click "receiveEmailNot now, remind me later." radio button
		overlay.findElement(remindMeLaterRadioButton).click();
		System.out.println("remindMeLaterRadioButton");
		
		// Click "No, I do not want to accept offers." radio button
		TimeUnit.SECONDS.sleep(5);
		overlay.findElement(marketingConsentNoRadioButton).click();
		System.out.println("marketingConsentNoRadioButton");

		// Click on Confirm Button
		TimeUnit.SECONDS.sleep(5);
		overlay.findElement(confirmButton).click();
		System.out.println("confirmButton");
		
	}
	// Click on User Profile at top right corner
	public String clickOnProfile() {
		Log.info("Click on User Profile at top right corner");
		util.waitForElementToBeVisible(userProfile, 20);
		util.doClick(userProfile);
		System.out.println("userProfile");
		
		util.waitForElementToBeVisible(userMenu, 10);
		String helloMessage = util.doGetText(userMenu);
		System.out.println("Message in User Menu ---" +helloMessage);
		return helloMessage;
		
	}
	
	public void clickOnLogout() {
		Log.info("Player Logging Out !!!");
		clickOnProfile();
		util.waitForElementToBeVisible(logOutButton, 10);
		util.doClick(logOutButton);
		System.out.println("User logged out successfully !!!!");
	}
}
