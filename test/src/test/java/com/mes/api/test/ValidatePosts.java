package com.mes.api.test;

import org.json.simple.JSONObject;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import java.util.logging.Logger;

import static org.testng.Assert.assertEquals;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;

import com.mes.api.common.ApiHelper;
import com.mes.api.common.RestAssuredUtility;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class ValidatePosts {

	public static final Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
//	A better approach will be to store it in .properties file rather than hard coding in every test
	String baseURI = "http://qainterview.merchante-solutions.com:3030";
	private Response response = null;
	private JsonPath jasonPath = null;
	private String respBody = null;
	HashMap<String, Object> params = new HashMap<String, Object>();

	@BeforeTest
	public void setUp() {
		response = RestAssuredUtility.getRequest(baseURI, "/posts");
	}

	@Test(priority = 1)
	public void verifyStatusCode() {
		ApiHelper.verifyStatusCode(response, 200);
	}

	@Test
	public void getDetails() {
		jasonPath = RestAssuredUtility.getJasonPath(response);
		ArrayList postsList = jasonPath.get("title");
		for (int i = 0; i < postsList.size() - 1; i++) {
			LOGGER.info("The titles are " + postsList.get(i));
		}
	}

	@Test
	public void updatePostForId() {
		RestAssured.baseURI = "http://qainterview.merchante-solutions.com:3030";
		RestAssured.basePath = "/posts";
		params.putAll(PayloadsGenerator.generatePayloadforPosts());
		Object postId = params.get("id");
		String endPoint = RestAssured.baseURI + RestAssured.basePath + "/" + postId;
		RequestSpecification httpRequest = RestAssured.given();
		JSONObject requestBody = new JSONObject();
		requestBody.putAll(params);

		httpRequest.header("Content-Type", "application/json");
		httpRequest.body(requestBody.toJSONString());

		response = httpRequest.request(Method.PUT, endPoint);
		respBody = response.getBody().asString();
		jasonPath = RestAssuredUtility.getJasonPath(response);
		ApiHelper.verifyStatusCode(response, 200);

//		Verify if the input parameters got updated successfully
		  for(Map.Entry<String, Object> entry : params.entrySet()) {
		  assertEquals(jasonPath.get(entry.getKey()).toString().replaceAll("[\\[\\]]",""),entry.getValue().toString(), "Assertion failed for update"); }
		 
	}

	@AfterTest
	public void teardown() {
		RestAssuredUtility.resetBaseUri(baseURI);
	}

}
