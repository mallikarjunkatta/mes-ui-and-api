package com.mes.api.test;

import org.testng.annotations.DataProvider;

public class DataProviderClass {
	

	@DataProvider(name = "PostId")
	public Object[][] idForPosts() {
		return new Object[][] 
		{
			{"id_post", 2}
		};
		
	}
}