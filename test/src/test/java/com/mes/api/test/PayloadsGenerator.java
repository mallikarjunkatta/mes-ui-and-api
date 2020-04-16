package com.mes.api.test;

import java.util.HashMap;


import org.json.simple.JSONObject;

public class PayloadsGenerator {
	
	public static HashMap<String,Object> generatePayloadforComments() {
		HashMap<String,Object> requestParamsComment = new HashMap<String,Object>();
		requestParamsComment.put("id", "25480");
		requestParamsComment.put("userId", "88899");
		requestParamsComment.put("body","mes automation test");
		requestParamsComment.put("completed" , "false");
		return requestParamsComment;
	}
	
	public static HashMap<String,Object> generatePayloadforPosts() {
		HashMap<String,Object> UpdatePosts = new HashMap<String,Object>();
		UpdatePosts.put("userId", "2");
		UpdatePosts.put("id", "5");
		UpdatePosts.put("title","updated on Wedensday");
		UpdatePosts.put("body","updated body on Wedensday");
		return UpdatePosts;
	}
	
	
}
