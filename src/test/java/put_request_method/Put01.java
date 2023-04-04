package put_request_method;

import base_urls.JsonPlaceHolderBaseUrl;
import com.google.gson.Gson;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;
import test_data.JsonPlaceHolderTestData;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertEquals;

public class Put01 extends JsonPlaceHolderBaseUrl {
    /*
        Given
	        1) https://jsonplaceholder.typicode.com/todos/198
	        2) {
                 "userId": 21,
                 "title": "Wash the dishes",
                 "completed": false
               }
        When
	 		I send PUT Request to the Url
	    Then
	   	   Status code is 200
	   	   And response body is like   {
									    "userId": 21,
									    "title": "Wash the dishes",
									    "completed": false
									   }
     */
    @Test
    public void put01(){

        //1.set the url
        spec.pathParams("first","todos","second","198");

        //2.step: set the payload (expected data)
        JsonPlaceHolderTestData obj=new JsonPlaceHolderTestData();
        Map<String,Object> expectedData= obj.expectedDataSetUpWithAllKeys(21, "Wash the dishes",false);

        //3.step
        Response response= given().spec(spec).contentType(ContentType.JSON).body(expectedData).when().put("/{first}/{second}");
        response.prettyPrint();


        //4.step: do assertion
        //1.way:
        response.then().
                assertThat().
                statusCode(200).
                body("userId", equalTo(expectedData.get("userId")),
                "title", equalTo(expectedData.get("title")),
                "completed", equalTo(expectedData.get("completed")));



        //2.way:Use GSON: de-serialization: converting Json Data to Java Object
        Map<String,Object> actualData= response.as(HashMap.class);

        expectedData.put("Status Code",200);
        assertEquals(expectedData.get("userId"), actualData.get("userId"));
        assertEquals(expectedData.get("title"), actualData.get("title"));
        assertEquals(expectedData.get("completed"), actualData.get("completed"));

        //How to use GSON in Serialization: Java Object ==> Json Data
        Map<String,Integer>ages=new HashMap<>();
        ages.put("Ali Can",13);
        ages.put("Veli Han",15);
        ages.put("Ayse Kan",21);
        ages.put("Mary Star",65);
        System.out.println(ages);

        //Convert ages map tp json data
        //1.step: create gson object
        Gson gson=new Gson();
        //2.step: by using "gson" object select method to convert Java Object to Json Data
        String jsonFromMap=gson.toJson(ages);
        System.out.println(jsonFromMap);




    }
}
