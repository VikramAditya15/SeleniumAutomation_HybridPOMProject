package com.orange.qa.pages;

import java.io.IOException;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.orange.qa.base.TestBase;

public class LoginPage extends TestBase {

	public LoginPage() throws IOException {
		super();
		PageFactory.initElements(driver, this);
		// TODO Auto-generated constructor stub
	}

	// Page Factory - OR
	@FindBy(name = "username")
	WebElement username;

	@FindBy(name = "password")
	WebElement password;

	@FindBy(xpath = "//button[@type='submit']")
	WebElement loginBtn;

	@FindBy(xpath = "//div[@class='orangehrm-login-branding']")
	WebElement loginTxt;

	// Defining Page Actions
	public String validateLoginPageTitle() {
		return driver.getTitle();
	}

	public boolean validateLoginHeader() {
		return loginTxt.isDisplayed();
	}

	public HomePage login(String uname, String pwd) throws IOException {
		username.sendKeys(uname);
		password.sendKeys(pwd);
		loginBtn.click();

		return new HomePage();

	}
	

}
