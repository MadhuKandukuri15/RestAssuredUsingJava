package resources;

public enum ResourceTest {
	addplaceAPI("/maps/api/place/add/json"), getPlaceAPI("/maps/api/place/get/json"),
	deletePlaceAPI("/maps/api/place/delete/json");

	private String resource;

	ResourceTest(String resource) {
		this.resource = resource;
	}

	public String getResource() {
		return resource;
	}

}
