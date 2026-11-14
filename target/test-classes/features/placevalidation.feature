Feature: Add place api
Scenario: verify place being succesfullly added using addPlaceAPI
     Given Add place payload with "<name>" "<language>" "<address>"
     When user calls "addplaceAPI" with "Post" request
     Then The API call got success with status code 200
     And "status" in response body is "OK"
     And "scope" in response body "APP"
     And verify place id created maps to "<name>" using "getPlaceAPI"
     
Examples:
		| name | language | address            |
		|madhu | English  | World cross center |
		#| bhargav | English | Vizag |
		
Scenario:
		Given verify delete fucntionality
		 When user calls "deletePlaceAPI" with "Post" request
		 Then "status" in response body is "OK"