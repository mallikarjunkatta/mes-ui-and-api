package com.mes.ui.common;

import org.testng.annotations.DataProvider;

public class TestData {
	
	@DataProvider(name="NewUserDetails")
	public Object[][] getDataForUser(){
		return new Object[][] {
			{"john123", "email","john@jhon.com" },
		};
	}
	
	@DataProvider(name="UserId")
	public Object[][] getUserId(){
		return new Object[][] {
			{"334" }
		};
	}
}
