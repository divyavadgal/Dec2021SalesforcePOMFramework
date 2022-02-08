package com.firebase.tests.Login;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.firebase.Base.BaseTest;
import com.firebase.utility.CommmonUtilities;

public class LoginTest extends BaseTest{
	
	@Test
	public void invalidLoginTest() {
		//new line
		System.out.println("entering to logintest @test");
		String username=CommmonUtilities.getApplicationProperty("username");
		loginPage.enterIntoUsername(username);
		loginPage.enterIntoPassword("123");
		loginPage.clcikLoginButton();
		loginPage.handleAlert();
		
		
	}
	
	@Test
	public void validLoginTest() {
		String username=CommmonUtilities.getApplicationProperty("username");
		loginPage.enterIntoUsername(username);
		String password=CommmonUtilities.getApplicationProperty("password");
		loginPage.enterIntoPassword(password);
		loginPage.clcikLoginButton();
		String title=homePage.getTitleOfTheHomePage();
		Assert.assertEquals(title,"Selenium");
		
	}

}

/*
 * driver.get(CommmonUtilities.getApplicationProperty("url"));
		String username=CommmonUtilities.getApplicationProperty("username");
		String password=CommmonUtilities.getApplicationProperty("password");
		login(username,password);
		System.out.println("end of before method");
 */

