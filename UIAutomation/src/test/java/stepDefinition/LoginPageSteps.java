package stepDefinition;

import java.io.IOException;

import org.junit.Assert;

import app.Factory.DriverFactory;
import app.Pages.HomePage;
import app.Pages.LoginPage;
import app.Util.TestDataStore;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class LoginPageSteps {
	
	String titleLoginPage;
	private LoginPage loginPage = new LoginPage(DriverFactory.getDriver());
	private HomePage homePage = new HomePage(DriverFactory.getDriver());
	
	@When("I click on login")
	public void i_click_on_login() throws InterruptedException {
		homePage.clickOnLogin();
	}
	@Given("I am on the GameTwist Login page and get title of Login page")
	public void i_am_on_the_game_twist_login_page_and_get_title_of_login_page() throws InterruptedException {
		titleLoginPage=loginPage.getLoginPageTitle();
		System.out.println("Login Page title is: " +titleLoginPage);
	}
	@Then("Login Page title should be {string}")
	public void login_page_title_should_be(String expectedLoginPageTitle) { 
		Assert.assertTrue(titleLoginPage.contains(expectedLoginPageTitle));
	}
	@Then("I login with the newly registered player")
	public void i_login_with_the_newly_registered_player() throws IOException, InterruptedException {
		// Get Registered Player Nickname
		String username = TestDataStore.load("NICKNAME");
		//String username ="Nick768879";
        System.out.println("Retrieved Nickname is: " + username);
		
		String pass = TestDataStore.load("PASSWORD");
		//String pass="Password12345";
        System.out.println("Retrieved Password is: " + pass);
		loginPage.firstLogin(username, pass);
	}
	
	@When("I click on player name on top right corner menu should be displayed and message contains {string}")
	public void i_click_on_player_name_on_top_right_corner_menu_should_be_displayed_and_message_contains(String expectedMenuMessage) {
		String menuMessage = loginPage.clickOnProfile();
		Assert.assertTrue(menuMessage.contains(expectedMenuMessage));

	}
	@When("Click on menu and click on Logout")
	public void click_on_menu_and_click_on_logout() {
		loginPage.clickOnLogout();

	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
