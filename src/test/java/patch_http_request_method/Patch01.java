package patch_http_request_method;

import base_urls.JsonPlaceHolderBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;
import test_data.JsonPlaceHolderTestData;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class Patch01 extends JsonPlaceHolderBaseUrl {
     /*
        Given
	        1) https://jsonplaceholder.typicode.com/todos/198
	        2) {
                 "title": "Wash the dishes",
               }
        When
	 		I send PATCH Request to the Url
	    Then
	   	   Status code is 200
	   	   And response body is like   {
									    "userId": 10,
									    "title": "Wash the dishes",
									    "completed": true
									    "id":198
									   }
     */


    @Test
    public void patch01(){

        spec.pathParams("first","todos","second",198);

        //2.step: set the request body data
        JsonPlaceHolderTestData requestBody=new JsonPlaceHolderTestData();
        Map<String,Object> requestBodyMap=requestBody.expectedDataSetUpWithMissingKeys(null,"Wash the dishes",null);
        System.out.println(requestBodyMap);

        //3.step: send the request and get the response
        Response response=given().spec(spec).contentType(ContentType.JSON).body(requestBodyMap).when().patch("/{first}/{second}");
        response.prettyPrint();

        //4.step: do assertion
        //1.LOGİC :No need to verify data which you did not touch
        response.then().
                assertThat().
                statusCode(200).
                body("title",equalTo(requestBodyMap.get("title")));
        //2.LOGIC:
        Map<String,Object> expectedDataMap=requestBody.expectedDataSetUpWithAllKeys(10,"Wash the dishes",true);
        response.then().assertThat().statusCode(200).body("userId",equalTo(expectedDataMap.get("userId")),
                "title",equalTo(expectedDataMap.get("title")),
                "completed",equalTo(expectedDataMap.get("completed")));

        //GSON to do assertion. Homework!
        Map<String, Object> actualData = response.as(HashMap.class);


    }
}
