package com.flexipill;


import io.restassured.http.ContentType;

import static io.restassured.RestAssured.*;
import io.restassured.response.Response;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.util.List;

import org.json.simple.JSONObject;
import org.testng.annotations.Test;

public class RestAssured {
    //"token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOjk4NSwiYXV0aF90eXBlIjoiZXh0ZXJuYWxfYXV0aCIsImlhdCI6MTcxNzQyNDYxMiwiZXhwIjoxNzE3NDI1MjEyfQ.ilw80FXFJM6TFV5qTfgrE9ZynmuMJCjrwVajnxltCV4"
	//String jwtToken = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOjk4NSwiYXV0aF90eXBlIjoiZXh0ZXJuYWxfYXV0aCIsImlhdCI6MTcxNzQ5NDExNCwiZXhwIjoxNzE3NDk0NzE0fQ.t13yHwkFCS7fr6sy2iSciUQmd7aykf_GFO57OaE8YYs";
	String accesToken = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOjk4NSwiYXV0aF90eXBlIjoiZXh0ZXJuYWxfYXV0aCIsImlhdCI6MTcxNzUxMTYwNCwiZXhwIjoxNzE3NTEyMjA0fQ.ozHUf9gNEyipxhXYF09kGIj5jciJUYzXn31RkSqvY8Y";
	
	
	@Test(enabled = true)
	public void login() {
		JSONObject js = new JSONObject();
		//{"phone_number":"1111111111","otp_code":"1111"}'
		js.put("phone_number","1111111111");
		js.put("otp_code","1111");
		given().header("Content-Type","application/JSON").contentType(ContentType.JSON).
    	body(js.toJSONString()).when().post("https://backendstaging.platinumrx.in/auth/login").then().log().all();
	}
	@Test(enabled = true)
	public void Add_To_Cart(){
		JSONObject js2 = new JSONObject();
		js2.put("increaseQuantityBy", "2");
		js2.put("drugCode", 1110806);
		
		given().header("Authorization", "Bearer"+accesToken)
		.contentType(ContentType.JSON)
		.body(js2.toJSONString()).when()
		.post("https://backendstaging.platinumrx.in/cart/addItem").then().log().all();
		
	}
	
	@Test(enabled = true)
	public void CreateOrder() {
		JSONObject js3 = new JSONObject();
		js3.put("paymentType", "COD");
		js3.put("orderType", "SEARCH");
		js3.put("patientName", "test");
		js3.put("patientAddress", "test-block test-city test-state 577201");
		js3.put("patientMobileNumber", "9876543219");
		js3.put("patientAge", "23");
		js3.put("patientGender", "male");
		js3.put("pincode", 577201);
		js3.put("city", "test-city");
		js3.put("state", "test-state");
		given().header("Authorization","Bearer" +accesToken)
		.contentType(ContentType.JSON)
    	.body(js3.toJSONString()).when()
    	.post("https://backendstaging.platinumrx.in/orders/initiateOrder").then().log().all();
	}
}
