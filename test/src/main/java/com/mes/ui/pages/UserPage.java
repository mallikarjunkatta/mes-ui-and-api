package com.mes.ui.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class UserPage {
	
	
	@FindBy(how=How.LINK_TEXT, using="Users")
	public WebElement userLink;
	
	@FindBy(how=How.LINK_TEXT, using="New User")
	public WebElement newUserLink;
	
	@FindBy(how=How.ID, using="user_username")
	public WebElement userName;
	
	@FindBy(how=How.ID, using="user_password")
	public WebElement password;
	
	@FindBy(how=How.ID, using="user_email")
	public WebElement email;
	
	@FindBy(how=How.XPATH, using="//input[@value='Create User']")
	public WebElement createUser;
	
	@FindBy(how=How.XPATH, using="//div[@class='flash flash_notice']")
	public WebElement flashNotice;
	

}
