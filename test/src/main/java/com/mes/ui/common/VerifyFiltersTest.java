package com.mes.ui.common;

import static org.testng.Assert.assertTrue;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class VerifyFiltersTest extends WebdriverBase {
	
WebDriver driver;

	
	@BeforeTest
	public void launchFireFoxBrowser() {
		driver = getDriverInstance();
		driver.get("http://qainterview.merchante-solutions.com/admin");
		driver.manage().timeouts().pageLoadTimeout(60,TimeUnit.SECONDS);
	}
	
	
	/*
	 * This test can be improvised as below Write one method which filters by the
	 * input filter criteria and filter value , but for the demo purpose I am
	 * writing one scenario. The filter value can be fed from dataprovider. 
	 */
	@Test
	public void filterUserName() {
		String fitlerText = "Jo";
		driver.findElement(By.linkText("Users")).click();
		driver.manage().timeouts().pageLoadTimeout(90,TimeUnit.SECONDS);
		Select nameFilter = new Select(driver.findElement(By.xpath("//div[@id='q_username_input']/select")));
		nameFilter.selectByVisibleText("Starts with");
		driver.findElement(By.id("q_username")).sendKeys(fitlerText);
		driver.findElement(By.cssSelector(".buttons > input:nth-child(1)")).click();
		
//		get all the fiter results
		List<WebElement> names = driver.findElements(By.xpath("//tr[starts-with(@id,'user_')]/td[@class='col col-username']"));
		for(int i =0; i < names.size(); i++) {
			String name = names.get(i).getText();
			System.out.println(name.toLowerCase());
//			assertTrue(name.toLowerCase().startsWith("jo"),"Name does not start with "+fitlerText);
			assertTrue(name.matches("^[JO|Jo|jo].*"), "Name does not start with "+fitlerText);
		}
	}
	
	@AfterTest
	public void closeBrowser() {
		driver.close();
	}

	
}
