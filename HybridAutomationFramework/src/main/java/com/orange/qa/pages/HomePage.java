package com.orange.qa.pages;

import java.io.IOException;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import com.orange.qa.base.TestBase;

public class HomePage extends TestBase {

	public HomePage() throws IOException {
		super();
		PageFactory.initElements(driver, this);
		// TODO Auto-generated constructor stub
	}

	// Page Factory - OR
	@FindBy(xpath = "//h6[text()='Dashboard']")
	WebElement dashboardHeader;

	@FindBy(xpath = "//span[text()='Admin']")
	WebElement adminTab;

	@FindBy(xpath = "//p[@class='oxd-userdropdown-name']")
	WebElement userName;

	// Defining Page Actions
	public String validateDashboardHeader() {
		return dashboardHeader.getText();
	}

	public AdminManagementPage navigatetoAdminPage() throws IOException {
		adminTab.click();
		return new AdminManagementPage();
	}

	public String getUserName() {
		return userName.getText();
	}

}
