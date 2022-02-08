package com.firebase.pages.loginpages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.firebase.base.BasePage;

public class LoginPage extends BasePage{
	public LoginPage(WebDriver driver){
		super(driver);

    }
	
	@FindBy(id="email_field") WebElement username;
	@FindBy(id="password_field") WebElement password;
	@FindBy(tagName ="button") WebElement btnLogin;
	
	public void enterIntoUsername(String usrName) {
		enterText(username, usrName);
		
	}
	public void enterIntoPassword(String passwrd) {
		enterText(password, passwrd);
	}
	public void clcikLoginButton() {
		clickElement(btnLogin);
	}
	public void handleAlert() {
		AcceptAlert();
	}

}
