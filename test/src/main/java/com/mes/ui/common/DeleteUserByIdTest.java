package com.mes.ui.common;

import static org.testng.Assert.assertEquals;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.mes.ui.pages.UserPage;

public class DeleteUserByIdTest extends WebdriverBase {
	
WebDriver driver;
UserPage userPage = PageFactory.initElements(driver, UserPage.class);


	
	@BeforeTest
	public void launchFireFoxBrowser() {
		driver = getDriverInstance();
		driver.get("http://qainterview.merchante-solutions.com/admin");
		driver.manage().timeouts().pageLoadTimeout(60,TimeUnit.SECONDS);
	}
	
	@Test(dataProvider="UserId", dataProviderClass=TestData.class)
	public void deleteUserByID(String id) {
		userPage.userLink.click();
		driver.manage().timeouts().pageLoadTimeout(60,TimeUnit.SECONDS);
		driver.findElement(By.id("batch_action_item_"+id)).click();
		driver.manage().timeouts().pageLoadTimeout(60,TimeUnit.SECONDS);
		driver.findElement(By.linkText("Batch Actions")).click();
		driver.findElement(By.linkText("Delete Selected")).click();
		driver.findElement(By.xpath("//button[@type='button'][contains(.,'OK')]")).click();
		String DeletMessage = driver.findElement(By.xpath("//div[@class='flash flash_notice']")).getText();
		assertEquals("Successfully destroyed 1 user", DeletMessage, "Deleting user failed");
	}
	
	@AfterTest
	public void closeBrowser() {
		driver.close();
	}

}
