package app.Pages;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

import app.Factory.DriverFactory;
import app.Util.ElementUtil;
import app.Util.Log;
import app.Util.TestDataGenerator;

public class RegisterPage {
	
	private WebDriver driver;
	String emailId,nickName;
	ElementUtil util = new ElementUtil(DriverFactory.getDriver());
	TestDataGenerator testDataGenerator = new TestDataGenerator();
	// By Locators
	private By email = By.xpath("//input[@name='email']");
	private By nickname = By.xpath("//input[@name='nickname']");
	private By password = By.xpath("//input[@name='password']");
	private By dobDay = By.xpath("//select[@name='day']");
	private By dobMonth = By.xpath("//select[@name='month']");
	private By dobYear = By.xpath("//select[@name='year']");
	private By recaptcha = By.cssSelector(".recaptcha-checkbox");
	private By recaptchaFrame = By.xpath("//iframe[@title='reCAPTCHA']");
	private By terms = By.xpath("//*[@id=\"termsAccept\"]");
	private By beginAdventure = By.xpath("//button[contains(text(),'Begin adventure')]");
	private By confirmEmailAddress = By.xpath("//div[@class='c-modal__headline']");
	private By resendEmail = By.xpath("//button[contains(text(),'Resend e-mail')]");

	public RegisterPage (WebDriver driver) {
		this.driver=driver;
	}	
	// Page Actions 
	public String getRegisterPageTitle() {
		return driver.getTitle();
	}
	
	public void enterUniqueEmail() {
		Log.info("Generating Unique Email Ids");
		emailId = testDataGenerator.generateUniqueEmail();
		util.doSendKeys(email,emailId);
		System.out.println("unique Email ID is: " + emailId);
	}
	public String enterUniqueNickname() {
		Log.info("Generating Unique Nicknames Ids");
		nickName= testDataGenerator.generateUniqueNickName();
		util.doSendKeys(nickname,nickName);
		System.out.println("unique Nickname ID is: " + nickName);
		return nickName;
	}
	public void enterPassword(String pwd) {
		util.doSendKeys(password,pwd);
	}
	public void enterDOB(int day, int month, int year) {
		Select select = new Select(driver.findElement(dobDay));
		select.selectByValue(Integer.toString(day));
		
		select = new Select(driver.findElement(dobMonth));
		select.selectByValue(Integer.toString(month));
		
		select = new Select(driver.findElement(dobYear));
		select.selectByValue(Integer.toString(year));
	}
	public void checkRecaptcha() throws InterruptedException {

		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0, 1000);");
		// Switch to captcha frame
		driver.switchTo().frame(driver.findElement(recaptchaFrame));
		//Click on recaptcha checkbox
		util.waitForElementToBeClickable(recaptcha, 10);
		TimeUnit.SECONDS.sleep(5);
		util.doClick(recaptcha);
		driver.switchTo().defaultContent();
	}
	public void checkTerms() throws InterruptedException {
		util.waitForElementToBeClickable(terms, 10);
		util.doClick(terms);
	}
	public void clickOnSubmit() {
		Log.info("Registering new player with all data !!");
		util.doClick(beginAdventure);
		util.waitForElementToBeDisappear(beginAdventure,5);
	}
	public String getRegisterConfirmationPageTitle() {
		return driver.getTitle();
	}
	public String getConfirmEmailAddressMessageIsDisplayed() {
		return util.doGetText(confirmEmailAddress);
	}
	public Boolean getResendEmailButtonIsDisplayed() {
		return driver.findElement(resendEmail).isDisplayed();
	}
	
	
	
	
	
	
	

}
