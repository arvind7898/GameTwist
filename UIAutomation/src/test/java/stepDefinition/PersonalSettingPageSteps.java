package stepDefinition;

import java.io.IOException;

import org.junit.Assert;

import app.Factory.DriverFactory;
import app.Pages.PersonalSettingsPage;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class PersonalSettingPageSteps {
	
	String titlePersonalSettingsPage;
	String successMessageSecurityPage;
	private PersonalSettingsPage personalSettingsPage = new PersonalSettingsPage(DriverFactory.getDriver());
	
	@When("I click on Personal Data and Personal Setting page title should be {string}")
	public void i_click_on_personal_data_and_personal_setting_page_title_should_be(String expectedPersonalSettingsPageTitle) throws InterruptedException {
		personalSettingsPage.clickOnPersonalData();
		titlePersonalSettingsPage=personalSettingsPage.getPersonalSettingsPageTitle();
		System.out.println("Personal Settings Page title is: " +titlePersonalSettingsPage);
		Assert.assertTrue(titlePersonalSettingsPage.contains(expectedPersonalSettingsPageTitle));
	}
	@When("I click on Change Security Questions and Change Security Questions window should be displayed")
	public void i_click_on_change_security_questions_and_change_security_questions_window_should_be_displayed() throws InterruptedException {
		personalSettingsPage.clickChangeSecurityQuestionButton();
		personalSettingsPage.isDisplayedChangeSecurityQuestionsForm();

	}
	@Then("Select Security Question and Answer enter password and click on Save Changes")
	public void select_security_question_and_answer_enter_password_and_click_on_save_changes() throws IOException, InterruptedException {
		successMessageSecurityPage = personalSettingsPage.changeAndSaveSecurityQuestions();

	}
	@Then("Successfull message should be displayed as {string}")
	public void successfull_message_should_be_displayed_as(String expectedSuccessMessage) {
		Assert.assertTrue(successMessageSecurityPage.contains(expectedSuccessMessage));

	}
	@When("Go back and click on Change Newsletter button and header should be {string}")
	public void go_back_and_click_on_change_newsletter_button_and_header_should_be(String expectedHeaderMessage) throws IOException, InterruptedException {
		String HeaderMessage = personalSettingsPage.clickChangeNewsletterButton();
		Assert.assertTrue(HeaderMessage.contains(expectedHeaderMessage));
		System.out.println("HeaderMessage" + HeaderMessage);

	}
	@Then("click on Yes Radio button and click on Confirm button")
	public void click_on_yes_radio_button_and_click_on_confirm_button() throws InterruptedException {
		personalSettingsPage.changeAndSaveNewsletter();
	}



}
