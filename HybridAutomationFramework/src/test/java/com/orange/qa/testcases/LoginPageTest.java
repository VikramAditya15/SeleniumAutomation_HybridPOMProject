package com.orange.qa.testcases;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.AssertJUnit;
import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.orange.qa.base.TestBase;
import com.orange.qa.pages.HomePage;
import com.orange.qa.pages.LoginPage;

public class LoginPageTest extends TestBase {

	LoginPage loginPage;
	HomePage homePage;

	public LoginPageTest() throws IOException {
		super();
		// TODO Auto-generated constructor stub
	}

	@BeforeMethod
	public void setUp() throws IOException {
		initialization();

		loginPage = new LoginPage();
	}

	@Test(priority = 1)
	public void validateLoginPageTitleTest() {
		String loginTitle = loginPage.validateLoginPageTitle();
		Assert.assertEquals(loginTitle, "OrangeHRM");
	}

	@Test(priority = 2)
	public void validateLoginPageHeaderTest() {
		boolean loginHeader = loginPage.validateLoginHeader();
		Assert.assertTrue(loginHeader);
	}

	@Test(priority = 3)
	public void validateLoginTest() throws IOException {
		homePage = loginPage.login(prop.getProperty("username"), prop.getProperty("password"));
	}

	@AfterMethod
	public static void tearDown() {
		driver.quit();
	}

}
