package stepDefinations;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import resources.TestDataBuild;
import resources.Utils;

public class StepDefination extends Utils{
	RequestSpecification res;
	ResponseSpecification rs;
	Response response;
	TestDataBuild tb = new TestDataBuild();
	@Given("Add place payload")
	public void add_place_payload() {
		// Write code here that turns the phrase above into concrete actions
		

		rs = new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();
		res = given().spec(specBuilder()).body(tb.addPlace());
		System.out.println("build got succesfull");
		

	}

	@When("user calls {string} with Post request")
	public void user_calls_with_post_request(String string) {
		response = res.when().post("/maps/api/place/add/json").then().spec(rs).extract().response();
		// Write code here that turns the phrase above into concrete actions

	}

	@Then("The API call got success with status code {int}")
	public void the_api_call_got_success_with_status_code(Integer int1) {
		// Write code here that turns the phrase above into concrete actions
		assertEquals(response.getStatusCode(), 200);
		System.out.println("Status code verified");

	}

	@Then("{string} in response body is {string}")
	public void is_response_body_is(String string, String string2) {
		String s = response.asString();
		JsonPath js = new JsonPath(s);
		String r = js.get(string);
		System.out.println(r);

		assertEquals(r, string2);

	}

	@Then("{string} in response body {string}")
	public void in_response_body(String string, String string2) {
		String s = response.asString();
		JsonPath js = new JsonPath(s);
		String r = js.get(string);
		assertEquals(r, string2);

	}

}
