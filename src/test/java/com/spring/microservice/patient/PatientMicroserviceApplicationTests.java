package com.spring.microservice.patient;

import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import io.restassured.RestAssured;
import static io.restassured.RestAssured.*;
@SpringBootTest
class PatientMicroserviceApplicationTests {

	@Test
	void contextLoads() {
	}

	 @Test
	 public void testAuthentication(){
	 	String jsonBody="{\"email_ID\":\"dipankar.dutta@gmail.com\",\"password\":\"dip@dutta\"}";
	 	String token=given()
	 			.header("Content-Type", "application/json").contentType(ContentType.JSON).accept(ContentType.JSON)
	 			.body(jsonBody)
	 			.when()
	 			.post("http://localhost:8002/api/v1/patient/authenticate")
	 			.then()
	 			.assertThat().statusCode(200)
	 			.extract().response().asString();

	 }

	 @Test
	 public void testGetPatient() {
	 	// GET TOKEN
	 	String jsonBody="{\"email_ID\":\"dipankar.dutta@gmail.com\",\"password\":\"dip@dutta\"}";
	 	String token = given()
	 			.header("Content-Type", "application/json").contentType(ContentType.JSON).accept(ContentType.JSON)
	 			.body(jsonBody)
	 			.when()
	 			.post("http://localhost:8002/api/v1/patient/authenticate")
	 			.then()
	 			.assertThat().statusCode(200)
	 			.extract().response().asString();

	 	// GET DOCTOR DETAILS
	 	String data = given()
	 			.header("Authorization", "Bearer " + token).contentType(ContentType.JSON).accept(ContentType.JSON)
	 			.when()
	 			.get("http://localhost:8002/api/v1/patient/get/dipankar.dutta@gmail.com")
	 			.then().assertThat().statusCode(200).extract().response().asString();
	 	System.out.println(data);

	 }

	 @Test
	 public void testGetPatientById(){
	 	String data = given()
	 			.header("Content-Type", "application/json").contentType(ContentType.JSON).accept(ContentType.JSON)
	 			.when()
	 			.get("http://localhost:8002/api/v1/patient/get/id/9de6e805-f38a-4488-8f01-87712c2f1e6c")
	 			.then().assertThat().statusCode(200).extract().response().asString();
	 	System.out.println(data);
	 }


	 @Test
	 public void testValidateToken(){
	 	String data = given()
	 			.header("Content-Type", "application/json").contentType(ContentType.JSON).accept(ContentType.JSON)
	 			.when()
	 			.get("http://localhost:8002/api/v1/patient/validade-token/eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJkaXBhbmthci5kdXR0YUBnbWFpbC5jb20iLCJleHAiOjE2NzQ1NDkxOTgsImlhdCI6MTY3NDM3NjM5OH0.FxRubsd5eKyP9qPWmUcz9vcBlH6NBdLefytHk34OzsJReaOFHyf98X-Guif2VYUbBr8ZxhIJWhOYSC8R6Fw0gQ")
	 			.then().assertThat().statusCode(200).extract().response().asString();
	 	System.out.println(data);
	 }

}
