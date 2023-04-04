package delete_http_request_method;

import base_urls.JsonPlaceHolderBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;


import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.*;

public class Delete01 extends JsonPlaceHolderBaseUrl {

     /*
        Given
	         https://jsonplaceholder.typicode.com/todos/198

	    When
	         I send DELETE Request to the URL
	    Then
	         Status Code is 200
	    And
	         Response body is {}

*/

    @Test
    public void delete01(){

        spec.pathParams("first","todos","second",198);


        //in delete we don't need request body
        Map<String,Object> expectedMap=new HashMap<>();
        System.out.println(expectedMap);

        Response response=given().spec(spec).contentType(ContentType.JSON).when().delete("/{first}/{second}");
        response.prettyPrint();

        //GSON
        Map<String,Object> actualMap=response.as(HashMap.class);
        System.out.println(actualMap);

        response.then().assertThat().statusCode(200);
        assertEquals(expectedMap,actualMap);
        //or
        assertTrue(actualMap.size()==0);
        assertTrue(actualMap.isEmpty());








    }

}
