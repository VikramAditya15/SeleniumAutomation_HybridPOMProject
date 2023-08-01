package com.orange.qa.pages;

import java.io.IOException;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import com.orange.qa.base.TestBase;

public class AdminManagementPage extends TestBase{

	
	Select sel;
	public AdminManagementPage() throws IOException {
		super();
		PageFactory.initElements(driver,this);
		// TODO Auto-generated constructor stub
	}
	
	@FindBy(xpath="//div[@class='oxd-form-row']//input[@class='oxd-input oxd-input--active']")
	@CacheLookup
	WebElement userName;
	
	@FindBy(xpath="//label[contains(text(),'User Role')]/parent::div[@class='oxd-input-group__label-wrapper']/following-sibling::div//div[@class='oxd-select-text oxd-select-text--active']")
	@CacheLookup
	WebElement userRoleDropDown;
	
	@FindBy(xpath="//input[@placeholder='Type for hints...']")
	@CacheLookup
	WebElement employeeName;
	
	@FindBy(xpath="//label[contains(text(),'Status')]/parent::div[@class='oxd-input-group__label-wrapper']/following-sibling::div//div[@class='oxd-select-text oxd-select-text--active']")
	@CacheLookup
	WebElement statusDropDown;
	
	@FindBy(xpath="//button[@type='submit' and text()=' Search ']")
	@CacheLookup
	WebElement searchBtn;
	
	
	public void searchUser(String uname,String userRole,String empName,String status)
	{
		
		userName.click();
		userName.clear();
		userName.sendKeys(uname);
		
		employeeName.click();
		employeeName.clear();
		employeeName.sendKeys(empName);
		
		sel=new Select(userRoleDropDown);
		sel.selectByValue(userRole);
		
		sel=new Select(userRoleDropDown);
		sel.selectByValue(userRole);
		
		searchBtn.click();	
		
	}

}
