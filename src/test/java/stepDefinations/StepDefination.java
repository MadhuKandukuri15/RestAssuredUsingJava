package stepDefinations;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

import java.io.FileNotFoundException;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import resources.TestDataBuild;
import resources.Utils;

public class StepDefination extends Utils {
	RequestSpecification res;
	ResponseSpecification rs;
	Response response;
	TestDataBuild tb = new TestDataBuild();

	@Given("Add place payload")
	public void add_place_payload() throws FileNotFoundException {
		// Write code here that turns the phrase above into concrete actions

		res = given().spec(specBuilder()).body(tb.addPlace());

	}

	@When("user calls {string} with Post request")
	public void user_calls_with_post_request(String string) {
		rs = new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();

		response = res.when().post("/maps/api/place/add/json").then().spec(rs).extract().response();
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
		JsonPath js = new JsonPath(s);
		String r = js.get(scope);
		assertEquals(r, value);

		System.out.println("Scope verified succesfully");
	}

}
