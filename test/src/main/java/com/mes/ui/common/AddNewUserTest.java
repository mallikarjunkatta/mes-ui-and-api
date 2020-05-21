package com.mes.ui.common;

import static org.testng.Assert.assertEquals;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Reporter;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.mes.ui.pages.UserPage;


public class AddNewUserTest extends WebdriverBase {
	WebDriver driver;
	UserPage userPage = new UserPage();
	

	
	@BeforeTest
	public void launchFireFoxBrowser() {
		driver = getDriverInstance();
		userPage = PageFactory.initElements(driver, UserPage.class);
		driver.get("http://qainterview.merchante-solutions.com/admin");
		driver.manage().timeouts().pageLoadTimeout(60,TimeUnit.SECONDS);
	}
	
	@Test(dataProvider="NewUserDetails", dataProviderClass=TestData.class)
	public void AddNewUser(String username,String password, String email) {
		userPage.userLink.click();
		userPage.newUserLink.click();
		userPage.userName.sendKeys(username);
		userPage.password.sendKeys(password);
		userPage.email.sendKeys(email);
		userPage.createUser.click();
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		Reporter.log("Added new user");
		String successMessage = driver.findElement(By.xpath("//div[@class='flash flash_notice']")).getText();
		assertEquals("User was successfully created.", successMessage, "Adding new user fialed");
	}
	

	@AfterTest
	public void closeBrowser() {
		driver.close();
	}
	
	
}
