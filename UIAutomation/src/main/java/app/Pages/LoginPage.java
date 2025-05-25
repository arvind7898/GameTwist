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
	
	private By txtNickName = By.id("username");
	private By txtPassWord = By.id("password");
	private By btnLogin = By.xpath("//button[contains(text(),'Log in')]");
	private By popupWheelOfFortune = By.xpath("//div[@class='c-wheel__cta']");
	private By btnCollectBonus = By.xpath("//div[@class='c-streak__btn c-btn c-btn--primary c-btn--auto']");
	private By btnCollect = By.xpath("//a[@class='c-btn c-btn--primary c-dialog__cta']");
	private By radioBtnRemindMeLater = By.xpath("//input[@type='radio' and @name='receiveEmail' and @value='false']");
	private By radioBtnMarketingConsentNo = By.xpath("//input[@type='radio' and @name='marketingConsent' and @value='false']");
	private By btnConfirm = By.xpath("//button[@type='submit']");
	private By lblUserProfile = By.xpath("//div[contains(text(),'Nick')]");
	private By lblUserMenu = By.xpath("//span[contains(normalize-space(text()), 'Hello')]");
	private By btnLogOut = By.xpath("//a[contains(text(),'Log out')]");
	
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
		util.waitForElementToBeVisible(txtNickName, 15);
		util.doSendKeys(txtNickName, username);
		// Enter Password
   		util.waitForElementToBeVisible(txtPassWord, 15);
		util.doSendKeys(txtPassWord, pass);
		// Click on Login Button
		util.waitForElementToBeClickable(btnLogin, 15);
		util.doClick(btnLogin);
		util.waitForElementToBeDisappear(btnLogin, 10);
		
		// Click on Wheel of Fortune
		util.waitForElementToBeVisible(popupWheelOfFortune,35);
		util.doClick(popupWheelOfFortune);
		TimeUnit.SECONDS.sleep(5);
		System.out.println("Wheel of Fortune");
		
		// Click on Bonus button
		util.waitForElementToBeVisible(btnCollectBonus,35);
		util.doClick(btnCollectBonus);
		TimeUnit.SECONDS.sleep(5);
		System.out.println("collectBonusButton");
		
		// Click on Collect button
		util.waitForElementToBeVisible(btnCollect,25);
		util.doClick(btnCollect);
		TimeUnit.SECONDS.sleep(5);
		System.out.println("collectButton");
		
		// Wait for overlay to appear
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		WebElement overlay = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".has-overlay")));
		
		// Click "receiveEmailNot now, remind me later." radio button
		overlay.findElement(radioBtnRemindMeLater).click();
		System.out.println("remindMeLaterRadioButton");
		
		// Click "No, I do not want to accept offers." radio button
		TimeUnit.SECONDS.sleep(5);
		overlay.findElement(radioBtnMarketingConsentNo).click();
		System.out.println("marketingConsentNoRadioButton");

		// Click on Confirm Button
		TimeUnit.SECONDS.sleep(5);
		overlay.findElement(btnConfirm).click();
		System.out.println("confirmButton");
		
	}
	// Click on User Profile at top right corner
	public String clickOnProfile() {
		Log.info("Click on User Profile at top right corner");
		util.waitForElementToBeVisible(lblUserProfile, 20);
		util.doClick(lblUserProfile);
		System.out.println("userProfile");
		
		util.waitForElementToBeVisible(lblUserMenu, 10);
		String helloMessage = util.doGetText(lblUserMenu);
		System.out.println("Message in User Menu ---" +helloMessage);
		return helloMessage;
		
	}
	
	public void clickOnLogout() {
		Log.info("Player Logging Out !!!");
		clickOnProfile();
		util.waitForElementToBeVisible(btnLogOut, 10);
		util.doClick(btnLogOut);
		System.out.println("User logged out successfully !!!!");
	}
}
