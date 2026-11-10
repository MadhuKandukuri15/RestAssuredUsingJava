package resources;

import java.util.ArrayList;
import java.util.List;

import pojo.AddPlace;
import pojo.Location;

public class TestDataBuild {
	
	public AddPlace addPlace() {
		AddPlace m = new AddPlace();
		m.setAccuracy(50);
		m.setAddress("29, side layout, cohen 09");
		m.setLanguage("French-IN");
		m.setName("Frontline house");
		m.setPhone_number("(+91) 983 893 3937");
		m.setWebsite("http://google.com");
		Location Lt = new Location();
		Lt.setLat(-38.383494);
		Lt.setLng(33.427362);

		List<String> l = new ArrayList<String>();
		l.add("shoe park");
		l.add("shop");
		m.setTypes(l);
		m.setLocation(Lt);
		return m;
	}

}
