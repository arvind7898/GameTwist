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
	private By txtEmail = By.xpath("//input[@name='email']");
	private By txtNickname = By.xpath("//input[@name='nickname']");
	private By txtPassword = By.xpath("//input[@name='password']");
	private By drpdownDobDay = By.xpath("//select[@name='day']");
	private By drpdownDobMonth = By.xpath("//select[@name='month']");
	private By drpdownDobYear = By.xpath("//select[@name='year']");
	private By chkBoxRecaptcha = By.cssSelector(".recaptcha-checkbox");
	private By iframeRecaptchaFrame = By.xpath("//iframe[@title='reCAPTCHA']");
	private By chkBoxterms = By.xpath("//input[@id='termsAccept']");
	private By btnBeginAdventure = By.xpath("//button[contains(text(),'Begin adventure')]");
	private By lblConfirmEmailAddress = By.xpath("//div[@class='c-modal__headline']");
	private By btnResendEmail = By.xpath("//button[contains(text(),'Resend e-mail')]");
	
	public RegisterPage (WebDriver driver) {
		this.driver=DriverFactory.getDriver();
	}	
	// Page Actions 
	public String getRegisterPageTitle() {
		return driver.getTitle();
	}
	
	public void enterUniqueEmail() {
		Log.info("Generating Unique Email Ids");
		emailId = testDataGenerator.generateUniqueEmail();
		util.doSendKeys(txtEmail,emailId);
		System.out.println("unique Email ID is: " + emailId);
	}
	public String enterUniqueNickname() {
		Log.info("Generating Unique Nicknames Ids");
		nickName= testDataGenerator.generateUniqueNickName();
		util.doSendKeys(txtNickname,nickName);
		System.out.println("unique Nickname ID is: " + nickName);
		return nickName;
	}
	public void enterPassword(String pwd) {
		util.doSendKeys(txtPassword,pwd);
	}
	public void enterDOB(int day, int month, int year) {
		Select select = new Select(driver.findElement(drpdownDobDay));
		select.selectByValue(Integer.toString(day));
		
		select = new Select(driver.findElement(drpdownDobMonth));
		select.selectByValue(Integer.toString(month));
		
		select = new Select(driver.findElement(drpdownDobYear));
		select.selectByValue(Integer.toString(year));
	}
	public void checkRecaptcha() throws InterruptedException {

		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0, 1000);");
		// Switch to captcha frame
		driver.switchTo().frame(driver.findElement(iframeRecaptchaFrame));
		//Click on recaptcha checkbox
		util.waitForElementToBeClickable(chkBoxRecaptcha, 20);
		util.doClick(chkBoxRecaptcha);
		driver.switchTo().defaultContent();
		TimeUnit.SECONDS.sleep(5);
	}
	public void checkTerms() throws InterruptedException {
		//util.waitForElementToBeVisible(terms, 20);
		TimeUnit.SECONDS.sleep(5);
		util.doClick(chkBoxterms);
	}
	public void clickOnSubmit() throws InterruptedException {
		Log.info("Registering new player with all data !!");
		util.doClick(btnBeginAdventure);
		TimeUnit.SECONDS.sleep(5);
		//util.waitForElementToBeDisappear(beginAdventure,5);
	}
	public String getRegisterConfirmationPageTitle() {
		return driver.getTitle();
	}
	public String getConfirmEmailAddressMessageIsDisplayed() {
		return util.doGetText(lblConfirmEmailAddress);
	}
	public Boolean getResendEmailButtonIsDisplayed() {
		return driver.findElement(btnResendEmail).isDisplayed();
	}

}
