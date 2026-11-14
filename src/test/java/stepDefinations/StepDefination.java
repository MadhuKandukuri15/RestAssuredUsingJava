package stepDefinations;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.sql.SQLException;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import resources.ResourceTest;
import resources.TestDataBuild;
import resources.Utils;

public class StepDefination extends Utils {
	RequestSpecification res;
	ResponseSpecification rs;
	Response response;
	TestDataBuild tb = new TestDataBuild();
	JsonPath js;
	static String place_id;
	ResourceTest rsTest;
	
	@Given("Add place payload with {string} {string} {string}")
	public void add_place_payload(String name, String language, String address) throws IOException, SQLException, ClassNotFoundException {
		res = given().spec(specBuilder()).body(tb.addPlace(name, language, address));
	}

	@When("user calls {string} with {string} request")
	public void user_calls_with_request(String resource, String httpMethod) {
		rs = new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();

		 rsTest = ResourceTest.valueOf(resource);
		String resourceFromEnum = rsTest.getResource();
		if(httpMethod.equalsIgnoreCase("POST")) {
		response = res.when().post(resourceFromEnum).then().spec(rs).extract().response();
		} else if(httpMethod.equalsIgnoreCase("get")) {
			response = res.when().get(resourceFromEnum).then().spec(rs).extract().response();
		}
		// Write code here that turns the phrase above into concrete actions

	}

	@Then("The API call got success with status code {int}")
	public void the_api_call_got_success_with_status_code(Integer int1) {
		// Write code here that turns the phrase above into concrete actions
		assertEquals(response.getStatusCode(), 200);
	}

	@Then("{string} in response body is {string}")
	public void is_response_body_is(String status, String value) {
		String s = response.asString();
		JsonPath js = new JsonPath(s);
		String respValue = js.get(status);

		assertEquals(respValue, value);

	}

	@Then("{string} in response body {string}")
	public void in_response_body(String scope, String value) {
		String s = response.asString();
		 js = new JsonPath(s);
		String r = js.get(scope);
		assertEquals(r, value);

		System.out.println("Scope verified succesfully");
	}
	@Then("verify place id created maps to {string} using {string}")
	public void verify_place_id_created_maps_to_using(String string, String resourceName) throws ClassNotFoundException, IOException, SQLException {
	   
	 place_id=getJsonKey(response, "place_id");
	res = given().spec(specBuilder()).queryParam("place_id",place_id);
	user_calls_with_request(resourceName, "get");
	String name=getJsonKey(response, "name");
	assertEquals(string,name);
	}
	@Given("verify delete fucntionality")
	public void verify_delete_fucntionality() throws ClassNotFoundException, IOException, SQLException {
		
	res=	given().spec(specBuilder()).body(tb.deletePlaceusingPlaceid(place_id));
	}


}
