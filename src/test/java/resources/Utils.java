package resources;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.sql.SQLException;
import java.util.Properties;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class Utils {

	public static RequestSpecification req;

	public RequestSpecification specBuilder() throws IOException, SQLException, ClassNotFoundException {
		if (req == null) {

			PrintStream stream = new PrintStream(new FileOutputStream("logs.txt"));

			RequestSpecification req = new RequestSpecBuilder().setBaseUri(getBaseURI("baseURI"))
					.addFilter(RequestLoggingFilter.logRequestTo(stream))
					.addFilter(ResponseLoggingFilter.logResponseTo(stream)).addQueryParam("key", "qaclick123")
					.setContentType(ContentType.JSON).build();
			return req;
		}
		return req;
	}

	public static String getBaseURI(String key) throws IOException {
		Properties prop = new Properties();
		FileInputStream fs = new FileInputStream(
				"C:\\Users\\kmadh\\eclipse-workspace\\ApiFramework\\src\\test\\java\\resources\\config.properties");
		prop.load(fs);
		return prop.getProperty(key);

	}

	String url = "jdbc:mysql://localhost:3306/testdb"; // DB URL
	String user = "root"; // DB username
	String password = "Madhusql965251"; // DB password

//	public String getBaseURI(String key) throws SQLException, ClassNotFoundException {
//		Class.forName("com.mysql.cj.jdbc.Driver");
//
//		Connection c = DriverManager.getConnection(url, user, password);
//		Statement st = c.createStatement();
//		ResultSet rs = st.executeQuery("select firstname from employee where empid=101;");
//		String s = "";
//		while (rs.next()) {
//			s = rs.getString("firstname");
//		}
//		return s;
//
//	}

	public String getJsonKey(Response response, String key) {

		JsonPath js = new JsonPath(response.asString());
		return js.get(key);

	}

	
}
