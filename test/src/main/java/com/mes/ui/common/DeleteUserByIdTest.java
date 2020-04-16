package com.mes.ui.common;

import static org.testng.Assert.assertEquals;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class DeleteUserByIdTest extends WebdriverBase {
	
WebDriver driver;

	
	@BeforeTest
	public void launchFireFoxBrowser() {
		driver = getDriverInstance();
		driver.get("http://qainterview.merchante-solutions.com/admin");
		driver.manage().timeouts().pageLoadTimeout(60,TimeUnit.SECONDS);
	}
	
	@Test(dataProvider="UserId", dataProviderClass=TestData.class)
	public void deleteUserByID(String id) {
		driver.findElement(By.linkText("Users")).click();
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
