package app.Util;

import java.util.Random;

import com.github.javafaker.Faker;

public class TestDataGenerator {
	Faker faker = new Faker();
	    public String generateUniqueEmail() {
	    	String uniqueEmail = faker.internet().emailAddress();
	    	System.out.println("uniqueEmail is: " + uniqueEmail);
	    	return uniqueEmail;
	    }
	    public String generateUniqueNickName() {
	    	Random rand = new Random();
	    	int randomNumber= 100000 + rand.nextInt(900000);
	        String uniqueNickName = "Nick" + randomNumber ;
	    	System.out.println("uniqueNickName is: " + uniqueNickName);
	        return uniqueNickName; 
	    }
	


}
