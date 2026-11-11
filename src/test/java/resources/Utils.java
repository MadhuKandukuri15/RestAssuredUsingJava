package resources;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

public class Utils {

	public RequestSpecification specBuilder() throws FileNotFoundException {
		PrintStream stream = new PrintStream(new File("logs.txt"));
		RestAssured.baseURI = "https://rahulshettyacademy.com";
		RequestSpecification req = new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com")
				.addFilter(RequestLoggingFilter.logRequestTo(stream))
				.addFilter(ResponseLoggingFilter.logResponseTo(stream))
				.addQueryParam("key", "qaclick123").setContentType(ContentType.JSON).build();
		return req;

	}

}
