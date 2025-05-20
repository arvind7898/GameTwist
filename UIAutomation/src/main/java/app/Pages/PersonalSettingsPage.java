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
	
	private By personalDataTab = By.xpath("//a[contains(text(),'Personal data')]");
	private By changeSecurityQuestionButton = By.xpath("//button[contains(text(),'Change security question')]");
	private By changeSecurityQuestionForm = By.xpath("//div[contains(text(),'Change security question')]");
	private By securityQuestionDropdown = By.xpath("//select[contains(@name,'security-question')]");
	private By securityAnswerText = By.xpath("//input[contains(@name,'security-answer')]");
	private By passwordInputText = By.xpath("//input[contains(@name,'new-password')]");
	private By saveChangesButton = By.xpath("//button[contains(text(),'Save changes')]");
	private By securityQuestionChangedSuccessfulMessage = By.
			xpath("//div[contains(text(),'The security question and answer have been changed.')]");
	private By closeSecurityQuestionWindow = By.xpath("//a[@class='c-btn c-btn--ghost']");
	private By changeNewsletterButton = By.xpath("//table[@id='table-gdpr-status']//tr[td[3]]//button[1]");
	
	// News Letter Settings form
	private By changeNewsLetterFormHeader = By.xpath("//div[contains(text(),'Change newsletter settings')]");
	private By receiveEmailRadioButton = By.xpath("//input[contains(@id,'receiveEmailYes') and @value = 'true']");
	private By confirmButton = By.xpath("//button[contains(text(), 'Confirm') and @type='submit']");
		
	public PersonalSettingsPage(WebDriver driver) {
		this.driver = driver;
	}
	public String getPersonalSettingsPageTitle() throws InterruptedException {
		TimeUnit.SECONDS.sleep(5);
		return driver.getTitle();
	}
	// Click on Personal Data tab
	public void clickOnPersonalData() {		
		util.waitForElementToBeVisible(personalDataTab, 10);
		util.doClick(personalDataTab);
	}
	public void clickChangeSecurityQuestionButton() throws InterruptedException {
		Log.info("Open Security Question Window");
		util.waitForElementToBeClickable(changeSecurityQuestionButton, 10);
		util.doClick(changeSecurityQuestionButton);
	}
	
	public void isDisplayedChangeSecurityQuestionsForm() {
		util.waitForElementToBeVisible(changeSecurityQuestionForm, 10);
		util.doIsDisplayed(changeSecurityQuestionForm);
		System.out.println("Security Question window is displayed");
	}
	public String changeAndSaveSecurityQuestions() throws IOException, InterruptedException {
		Log.info("Change and Save Security Questions");
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		WebElement overlay = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".has-overlay")));
		
		// Select security questions from Drop Down		
		util.waitForElementToBeClickable(securityQuestionDropdown, 10);
		TimeUnit.SECONDS.sleep(5);
		util.selectdropDownOptionByIndex(overlay.findElement(securityQuestionDropdown),2);
		System.out.println("Select value from Drop down");
		
		// Enter Security Answer in the textbox
		TestDataStore.save("SECURITYANSWER", "Honda");
		util.waitForElementToBeClickable(securityAnswerText, 10);
		overlay.findElement(securityAnswerText).sendKeys(TestDataStore.load("SECURITYANSWER"));
		System.out.println("Enter security answer value ");
		
		// Enter User Password
		util.waitForElementToBeClickable(passwordInputText, 10);
		overlay.findElement(passwordInputText).sendKeys(TestDataStore.load("PASSWORD"));
		
		// Click on save Changes Button
		util.waitForElementToBeClickable(saveChangesButton, 10);
		overlay.findElement(saveChangesButton).click();
		util.waitForElementToBeVisible(securityQuestionChangedSuccessfulMessage,20);
		String successMessage = overlay.findElement(securityQuestionChangedSuccessfulMessage).getText();
		return successMessage;
	}
	public String clickChangeNewsletterButton() throws InterruptedException {
		
		util.waitForElementToBeClickable(closeSecurityQuestionWindow, 10);
		util.doClick(closeSecurityQuestionWindow);
		System.out.println("Security Question window is closed ");

		// Scroll to change Newsletter button
		util.scrollToTheElement(changeNewsletterButton);
		util.waitForElementToBeClickable(changeNewsletterButton, 10);
		util.doClick(changeNewsletterButton);
		util.waitForElementToBeDisappear(changeNewsletterButton, 10);
		System.out.println("Change Newsletter button clicked ");
		return util.doGetText(changeNewsLetterFormHeader);
	}
	public void changeAndSaveNewsletter() throws InterruptedException {
		Log.info("Change and Save Newsletter options");
		// Click on Yes Radio Button
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		WebElement overlay = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".has-overlay")));
		TimeUnit.SECONDS.sleep(5);
		overlay.findElement(receiveEmailRadioButton).click();
	
		// Click on Confirm Button
		overlay.findElement(confirmButton).click();
		util.waitForElementToBeDisappear(confirmButton, 10);
		System.out.println("Newsletter changes saved and Confirm button clicked");
	}
}
