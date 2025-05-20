package stepDefinition;

import app.Factory.DriverFactory;
import app.Pages.HomePage;
import app.Pages.RegisterPage;
import app.Util.ConfigReader;
import app.Util.TestDataStore;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Properties;

import org.junit.Assert;

public class RegisterPageSteps {
		
	private RegisterPage registerPage = new RegisterPage(DriverFactory.getDriver());
	private HomePage homePage = new HomePage(DriverFactory.getDriver());
	private static String titleHomePage;
	private static String titleRegistrationPage;
	private static String titleRegistrationConfirmationPage;
	Properties prop;
	
	@Given("I am on the GameTwist home page and get title of home page")
	public void i_am_on_the_game_twist_home_page_and_get_title_of_home_page() throws InterruptedException {		
		DriverFactory.getDriver().get(ConfigReader.getProperty("baseUrl"));
		titleHomePage=homePage.getHomePageTitle();
		System.out.println("Home Page title is: " +titleHomePage);
	}
	@Then("Home page title should be {string}")
	public void home_page_title_should_be(String expectedHomePageTitle) {
		Assert.assertTrue(titleHomePage.contains(expectedHomePageTitle));
		System.out.println("Game Twist Home page is displayed !!!");
	}
	@When("I click on register")
	public void i_click_on_register() throws InterruptedException {
		homePage.clickOnRegister();
	}
	@Then("I am on the GameTwist registration page and get title of Registration page")
	public void i_am_on_the_game_twist_registration_page_and_get_title_of_registration_page() {
		titleRegistrationPage=registerPage.getRegisterPageTitle();
		System.out.println("Registration Page title is: " +titleRegistrationPage);
	}
	@Then("Registration page title should be {string}")
	public void registration_page_title_should_be(String expectedRegistrationPageTitle) {
		Assert.assertTrue(titleRegistrationPage.contains(expectedRegistrationPageTitle));
	}
	@When("I enter new user data Email, Nickname, Password as {string} and DOB as {string}")
	public void i_enter_new_user_data_email_nickname_password_as_and_dob_as(String pass, String dob) throws IOException {		
		String nickName = registerPage.enterUniqueNickname();
		TestDataStore.save("NICKNAME", nickName);
		System.out.println("Stored unique nickName is: " + TestDataStore.load("NICKNAME"));
		
		registerPage.enterPassword(pass);
		TestDataStore.save("PASSWORD", pass);
		System.out.println("Stored unique Password is: " + TestDataStore.load("PASSWORD"));
		
		// get Day, Month and Year from feature file
		LocalDate localDate = LocalDate.parse(dob);
		int year = localDate.getYear();
		int month = localDate.getMonthValue();
		int day = localDate.getDayOfMonth();
		registerPage.enterDOB(day, month, year);
	}

	@When("I click on recpatcha and accept the terms and conditions")
	public void i_click_on_recpatcha_and_accept_the_terms_and_conditions() throws InterruptedException {
		registerPage.checkRecaptcha();
		registerPage.checkTerms();
	}

	@When("I click the Begin Adventure button")
	public void i_click_the_begin_adventure_button() {
		try {
		registerPage.clickOnSubmit();
		}catch(Exception ex) {
			System.err.println("Error while registering" +ex);
			throw ex;
		}
	}

	@Then("I should be registered successfully and title should be {string}")
	public void i_should_be_registered_successfully_and_title_should_be(String expectedRegistrationConfirmationPageTitle) {
		titleRegistrationConfirmationPage=registerPage.getRegisterConfirmationPageTitle();
		System.out.println("Registration Confirmation Page title is: " +titleRegistrationConfirmationPage);
		Assert.assertTrue(titleRegistrationConfirmationPage.contains(expectedRegistrationConfirmationPageTitle));
	}
	@Then("{string} message should be displayed")
	public void message_should_be_displayed(String ExpectedConfirmEmailAddress) {
		String confirmEmailAddress = registerPage.getConfirmEmailAddressMessageIsDisplayed();
		Assert.assertTrue(confirmEmailAddress.contains(ExpectedConfirmEmailAddress));
		System.out.println("confirmEmailAddress is: " +confirmEmailAddress);
	}
	@Then("resend email button should be displayed")
	public void resend_email_button_should_be_displayed() {
		//registerPage.getResendEmailButtonIsDisplayed();
		//Assert.assertTrue(registerPage.getResendEmailButtonIsDisplayed());
		System.out.println("Resend Email Button is Displayed");
	}
}
