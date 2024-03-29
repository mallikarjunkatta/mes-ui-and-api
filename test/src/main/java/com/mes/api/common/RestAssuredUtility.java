package com.mes.api.common;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class RestAssuredUtility {
	
	public static void setBaseUri(String baseUri) {
		RestAssured.baseURI = baseUri;
	}
	
	public static void resetBaseUri(String baseUri) {
		RestAssured.baseURI = null;
	}
	
	public static Response getRequest(String baseUri, String pathParameter) {
		RestAssured.baseURI = baseUri;
		RestAssured.basePath = pathParameter;
		RequestSpecification requestSpecification = RestAssured.given();
		return requestSpecification.request(Method.GET, RestAssured.baseURI+RestAssured.basePath);	
	}
	
	public static JsonPath getJasonPath(Response response) {
				return new JsonPath(response.asString());
	}
	
	
	
	
	

}
