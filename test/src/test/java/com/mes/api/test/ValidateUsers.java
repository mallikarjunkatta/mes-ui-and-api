package com.mes.api.test;

import java.util.HashMap;
import java.util.logging.Logger;

import org.json.simple.JSONObject;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.mes.api.common.ApiHelper;
import com.mes.api.common.RestAssuredUtility;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class ValidateUsers {
	public static final Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
//	A better approach will be to store it in .properties file rather than hard coding in every test
	String baseURI = "http://qainterview.merchante-solutions.com:3030";
	private Response response = null;
	private JsonPath jasonPath = null;
	private String respBody= null;
	private RequestSpecification httpRequest =null;
	private String endPoint = null;
	HashMap<String,Object> params = new HashMap<String,Object>();
	
	@BeforeTest
	public void setUp() {
		RestAssured.baseURI = "http://qainterview.merchante-solutions.com:3030";
		RestAssured.basePath = "/users/1";
		endPoint = RestAssured.baseURI+RestAssured.basePath;
		httpRequest = RestAssured.given();
		JSONObject requestBody = new JSONObject();
		requestBody.putAll(PayloadsGenerator.generatePayloadforComments());

		httpRequest.header("Content-Type", "application/json");
		httpRequest.body(requestBody.toJSONString());
		  
		response = httpRequest.request(Method.GET, endPoint);
		respBody = response.getBody().asString();
	}
	
	@Test(priority = 1)
	public void verifyStatusCode() {
		ApiHelper.verifyStatusCode(response, 200);
	}
	
	@AfterTest
	public void teardown() {
		RestAssuredUtility.resetBaseUri(baseURI);
	}
}
