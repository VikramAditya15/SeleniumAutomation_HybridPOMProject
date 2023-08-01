package com.orange.qa.testcases;

import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.orange.qa.base.TestBase;
import com.orange.qa.pages.AdminManagementPage;
import com.orange.qa.pages.HomePage;
import com.orange.qa.pages.LoginPage;
import com.orange.qa.util.RetryAnalyser;
import com.orange.qa.util.TestUtil;

public class AdminManagementTest extends TestBase{
	
	LoginPage loginPage;
	HomePage homePage;
	AdminManagementPage adminPage;
	
	String testDataFilePath=System.getProperty("user.dir")+ "/src/test/resources/TestData/AdminData.json";

	public AdminManagementTest() throws IOException {
		super();
		// TODO Auto-generated constructor stub
	}
	
	@DataProvider(name="getAdminData")
	public Object[][] getTestData(Method method) throws IOException
	{
		List<Map<String,String>> maps=new ArrayList<>();
		
		ObjectMapper objectMapper=new ObjectMapper();
		FileInputStream fis=new FileInputStream(testDataFilePath);
		JsonNode node=  objectMapper.readTree(fis);
		System.out.println("Node Value : "+node.toPrettyString());
		node=node.path(method.getName());
		System.out.println("Node Value : "+node.toPrettyString());
		maps=objectMapper.readValue(node.toString(), new TypeReference<List<Map<String,String>>>() {
		});
		System.out.println("Data Size : "+maps.size());
		
		return maps.stream().map(object -> new Map[]{object}).toArray(Map[][]::new);
		
	}
	
	@BeforeMethod
	public void setUp() throws IOException
	{
		initialization();
		loginPage=new LoginPage();
		homePage=new HomePage();
		adminPage=new AdminManagementPage();
	}
	
	  @Test(dataProvider= "getAdminData",priority=1,enabled=true) public void
	  validateUserSearch(HashMap<String,String> data) throws IOException {
	  homePage=loginPage.login(prop.getProperty("username"),
	  prop.getProperty("password")); String employeeName=homePage.getUserName();
	  adminPage=homePage.navigatetoAdminPage();
	  adminPage.searchUser(data.get("userName"), data.get("userRole"),
	  employeeName, data.get("status")); }
	 
	
	/*
	 * @Test(dataProvider= "getAdminData",priority=1,enabled=true) public void
	 * validateSuccessCase(HashMap<String,String> data) throws IOException {
	 * Assert.assertTrue(false); }
	 * 
	 * @Test(dataProvider= "getAdminData",priority=2,enabled=true) public void
	 * validateFailureCase(HashMap<String,String> data) throws IOException {
	 * Assert.assertTrue(false); }
	 */
	
	@AfterMethod
	public void tearDown()
	{
		driver.quit();
	}

}
