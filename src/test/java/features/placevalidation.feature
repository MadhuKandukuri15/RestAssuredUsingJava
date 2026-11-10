Feature: Add place api
Scenario: verify place being succesfullly added using addPlaceAPI
   
     Given Add place payload 
     When user calls "AddPlaceAPI" with Post request
     Then The API call got success with status code 200
     And "status" in response body is "OK"
     And "scope" in response body "APP"