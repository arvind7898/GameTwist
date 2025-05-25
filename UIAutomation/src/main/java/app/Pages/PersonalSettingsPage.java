package app.Pages;

import java.io.IOException;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import app.Factory.DriverFactory;
import app.Util.ElementUtil;
import app.Util.Log;
import app.Util.TestDataStore;

public class PersonalSettingsPage {
	
	private WebDriver driver;
	ElementUtil util = new ElementUtil(DriverFactory.getDriver());
	
	private By tabPersonalData = By.xpath("//a[contains(text(),'Personal data')]");
	private By btnChangeSecurityQuestion = By.xpath("//button[contains(text(),'Change security question')]");
	private By formChangeSecurityQuestion = By.xpath("//div[contains(text(),'Change security question')]");
	private By drpDownSecurityQuestion = By.xpath("//select[contains(@name,'security-question')]");
	private By txtSecurityAnswer = By.xpath("//input[contains(@name,'security-answer')]");
	private By txtPassword = By.xpath("//input[contains(@name,'new-password')]");
	private By btnSaveChanges = By.xpath("//button[contains(text(),'Save changes')]");
	private By msgSecurityQuestionChangedSuccessful = By.
			xpath("//div[contains(text(),'The security question and answer have been changed.')]");
	private By wndCloseSecurityQuestion = By.xpath("//a[@class='c-btn c-btn--ghost']");
	private By btnChangeNewsletter = By.xpath("//table[@id='table-gdpr-status']//tr[td[3]]//button[1]");
	private By lblChangeNewsLetter = By.xpath("//div[contains(text(),'Change newsletter settings')]");
	private By radioBtnReceiveEmail = By.xpath("//input[contains(@id,'receiveEmailYes') and @value = 'true']");
	private By btnConfirm = By.xpath("//button[contains(text(), 'Confirm') and @type='submit']");
		
	public PersonalSettingsPage(WebDriver driver) {
		this.driver = driver;
	}
	public String getPersonalSettingsPageTitle() throws InterruptedException {
		TimeUnit.SECONDS.sleep(5);
		return driver.getTitle();
	}
	// Click on Personal Data tab
	public void clickOnPersonalData() {		
		util.waitForElementToBeVisible(tabPersonalData, 10);
		util.doClick(tabPersonalData);
	}
	public void clickChangeSecurityQuestionButton() throws InterruptedException {
		Log.info("Open Security Question Window");
		util.waitForElementToBeClickable(btnChangeSecurityQuestion, 10);
		util.doClick(btnChangeSecurityQuestion);
	}
	
	public void isDisplayedChangeSecurityQuestionsForm() {
		util.waitForElementToBeVisible(formChangeSecurityQuestion, 10);
		util.doIsDisplayed(formChangeSecurityQuestion);
		System.out.println("Security Question window is displayed");
	}
	public String changeAndSaveSecurityQuestions() throws IOException, InterruptedException {
		Log.info("Change and Save Security Questions");
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		WebElement overlay = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".has-overlay")));
		
		// Select security questions from Drop Down		
		util.waitForElementToBeClickable(drpDownSecurityQuestion, 10);
		TimeUnit.SECONDS.sleep(5);
		util.selectdropDownOptionByIndex(overlay.findElement(drpDownSecurityQuestion),2);
		System.out.println("Select value from Drop down");
		
		// Enter Security Answer in the textbox
		TestDataStore.save("SECURITYANSWER", "Honda");
		util.waitForElementToBeClickable(txtSecurityAnswer, 10);
		overlay.findElement(txtSecurityAnswer).sendKeys(TestDataStore.load("SECURITYANSWER"));
		System.out.println("Enter security answer value ");
		
		// Enter User Password
		util.waitForElementToBeClickable(txtPassword, 10);
		overlay.findElement(txtPassword).sendKeys(TestDataStore.load("PASSWORD"));
		
		// Click on save Changes Button
		util.waitForElementToBeClickable(btnSaveChanges, 10);
		overlay.findElement(btnSaveChanges).click();
		util.waitForElementToBeVisible(msgSecurityQuestionChangedSuccessful,20);
		String successMessage = overlay.findElement(msgSecurityQuestionChangedSuccessful).getText();
		return successMessage;
	}
	public String clickChangeNewsletterButton() throws InterruptedException {
		
		util.waitForElementToBeClickable(wndCloseSecurityQuestion, 10);
		util.doClick(wndCloseSecurityQuestion);
		System.out.println("Security Question window is closed ");

		// Scroll to change Newsletter button
		util.scrollToTheElement(btnChangeNewsletter);
		util.waitForElementToBeClickable(btnChangeNewsletter, 10);
		util.doClick(btnChangeNewsletter);
		util.waitForElementToBeDisappear(btnChangeNewsletter, 10);
		System.out.println("Change Newsletter button clicked ");
		return util.doGetText(lblChangeNewsLetter);
	}
	public void changeAndSaveNewsletter() throws InterruptedException {
		Log.info("Change and Save Newsletter options");
		// Click on Yes Radio Button
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		WebElement overlay = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".has-overlay")));
		TimeUnit.SECONDS.sleep(3);
		overlay.findElement(radioBtnReceiveEmail).click();
	
		// Click on Confirm Button
		overlay.findElement(btnConfirm).click();
		util.waitForElementToBeDisappear(btnConfirm, 10);
		System.out.println("Newsletter changes saved and Confirm button clicked");
	}
}
