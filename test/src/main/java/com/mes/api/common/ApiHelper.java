package com.mes.api.common;

import static org.testng.Assert.assertEquals;

import io.restassured.response.Response;

public class ApiHelper {
	
	public static void verifyStatusCode(Response response, int code) {
		assertEquals(response.getStatusCode(), code, "The status code assertion failed");
	}
	
	
	public static void verifyStatusLine(Response response, String statusDescription) {
		assertEquals(response.getStatusLine(), statusDescription, "The Status description assertion failed :");
	}
	
	

}
