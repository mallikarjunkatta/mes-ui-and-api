package com.mes.api.test;

import static org.testng.Assert.assertEquals;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

import org.json.simple.JSONObject;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.mes.api.common.ApiHelper;
import com.mes.api.common.RestAssuredUtility;
import com.mes.api.test.PayloadsGenerator;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class ValidateComments {
	
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
		RestAssured.basePath = "/comments";
		endPoint = RestAssured.baseURI+RestAssured.basePath;
		httpRequest = RestAssured.given();
		JSONObject requestBody = new JSONObject();
		requestBody.putAll(PayloadsGenerator.generatePayloadforComments());

		httpRequest.header("Content-Type", "application/json");
		httpRequest.body(requestBody.toJSONString());
		  
		response = httpRequest.request(Method.POST, endPoint);
		respBody = response.getBody().asString();
	}

	@Test(priority = 1)
	public void verifyStatusCode() {
		ApiHelper.verifyStatusCode(response, 201);
	}
	
	@Test
	public void verifyAddedComment() {
		params.putAll(PayloadsGenerator.generatePayloadforComments());
		Object commentId = params.get("id");
		Response response = httpRequest.request(Method.GET,endPoint +"/?id="+commentId);
		respBody = response.getBody().asString();
		jasonPath = RestAssuredUtility.getJasonPath(response);
		
//		Verify if the input parameters got added successfully
		for(Map.Entry<String, Object> entry : params.entrySet()) {
			assertEquals(jasonPath.get(entry.getKey()).toString().replaceAll("[\\[\\]]", ""),entry.getValue().toString(), "Assertion failed for post");
		}
	}
	
	@AfterTest
	public void teardown() {
		RestAssuredUtility.resetBaseUri(baseURI);
	}
}
