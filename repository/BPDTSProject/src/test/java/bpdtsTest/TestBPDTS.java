package bpdtsTest;

import org.testng.annotations.Test;
import io.restassured.http.ContentType;
import static org.hamcrest.Matchers.*;

import org.hamcrest.Matchers;

import static io.restassured.RestAssured.*;


public class TestBPDTS {

	
	@Test
	public void testResponseHeaderData_IsCorrect() {
		given().
	    when().
	        get("https://bpdts-test-app-v2.herokuapp.com/users").
	    then().
	        assertThat().
	        statusCode(200).
	    and().
	        contentType(ContentType.JSON).
	    and().
	        header("Content-Length",equalTo("175719"));
		
		given().
	    when().
	        get("https://bpdts-test-app-v2.herokuapp.com/city/Manchester/users").
	    then().
	        assertThat().
	        statusCode(200).
	    and().
	        contentType(ContentType.JSON).
	    and().
	        header("Content-Length",equalTo("3"));
		
		given().
	    when().
	        get("https://bpdts-test-app-v2.herokuapp.com/instructions").
	    then().
	        assertThat().
	        statusCode(200).
	    and().
	        contentType(ContentType.JSON).
	    and().
	        header("Content-Length",equalTo("281"));
		
	}
	
	@Test
	public void getCity_whenUsePathParam_thenOK_checkRsponseheadersifOk() {
		
		   given()
		  .pathParam("city", "Manchester")
	      .when()
	      .get("https://bpdts-test-app-v2.herokuapp.com/city/{city}/users")
	      .then()
	      .assertThat()
	      .body("isEmpty()", Matchers.is(true))
	      .and()
	      .statusCode(200);
	
		   given().
		    when().
		        get("https://bpdts-test-app-v2.herokuapp.com/city/Manchester/users").
		    then().
		        assertThat().
		        statusCode(200).
		    and().
		        contentType(ContentType.JSON).
		    and().
		        header("Content-Length",equalTo("3"));
	}
	@Test
	public void getInstructions_checkResponseIsOk_contentLenght_equal281() {
	given()
		
		.when()
		.get("https://bpdts-test-app-v2.herokuapp.com/instructions")
		
		.then()
		.assertThat()
		.body("todo",containsString("Create a short automated test for this API. Check that the data returned by the API is valid, and that ensure that each valid operation can be successfully called for each endpoint. Once you've built the tests, push the answer to Github or Gitlab, and send us a link. "))
		.and()
		.statusCode(200)
		.and()
		.contentType(ContentType.JSON)
		.and()
		.header("Content-Length",equalTo("281"));
	}
	
	@Test
	public void getUserid_ifidparathisok() {
		given()
		.when()
		.get("https://bpdts-test-app-v2.herokuapp.com/user/1")
		.then()
		.assertThat()
		.body("user", not(emptyArray()))
		.and()
		.statusCode(200)
		.and()
		.contentType(ContentType.JSON)
		.header("Content-Length",equalTo("192"));
		
		
		given()
		.when()
		.get("https://bpdts-test-app-v2.herokuapp.com/user/0")
		.then()
		.assertThat()
		.body("message", containsString("Id 0 doesn't exist. You have requested this URI [/user/0] but did you mean /user/<string:id> or /users ?"))
		.and()
		.statusCode(404);
	}
	
	@Test
	public void getAllUsers_CheckSize_isEqualsto1000() {
		given()
		.when()
		.get("https://bpdts-test-app-v2.herokuapp.com/users")
		.then()
		.assertThat()
		.body("users", hasSize(1000))
		.and()
		.statusCode(200)
		.and()
		.contentType(ContentType.JSON)
		.header("Content-Length", equalTo("175719"));
	
	}
}
