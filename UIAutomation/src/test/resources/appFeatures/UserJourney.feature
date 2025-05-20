Feature: User Registration on GameTwist and then first time login and user update profile 

  As a new visitor to GameTwist
  I want to register a new player account
  So that I can log in and start playing games

  Scenario: Successful registration with valid data
  	Given I am on the GameTwist home page and get title of home page
  	Then Home page title should be "Play FREE Online Casino games | GameTwist Casino"
  	When I click on register
    Then I am on the GameTwist registration page and get title of Registration page
    Then Registration page title should be "Registration | GameTwist Casino"
    When I enter new user data Email, Nickname, Password as "Password12345" and DOB as "2001-10-20"
    And I click on recpatcha and accept the terms and conditions
    And I click the Begin Adventure button
    Then I should be registered successfully and title should be "Registration Confirmation | GameTwist Casino"
    And "Confirm your e-mail address" message should be displayed
    And resend email button should be displayed

    
	Scenario: Successful Login with registered player
	  Given I am on the GameTwist home page and get title of home page
  	Then Home page title should be "Play FREE Online Casino games | GameTwist Casino"
  	When I click on login
		Given I am on the GameTwist Login page and get title of Login page 
		Then Login Page title should be "Play FREE Online Casino games | GameTwist Casino"
		Then I login with the newly registered player
		When I click on player name on top right corner menu should be displayed and message contains "Hello Nick"
		When I click on Personal Data and Personal Setting page title should be "Personal settings | GameTwist Casino"
		When I click on Change Security Questions and Change Security Questions window should be displayed
		Then Select Security Question and Answer enter password and click on Save Changes
		Then Successfull message should be displayed as "The security question and answer have been changed."
		When Go back and click on Change Newsletter button and header should be "Change newsletter settings"
		Then click on Yes Radio button and click on Confirm button
		When Click on menu and click on Logout 
		Then Home page title should be "Play FREE Online Casino games | GameTwist Casino"
    
    
    
    
    