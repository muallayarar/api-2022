package get_http_request_method;

import base_urls.JsonPlaceHolderBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;
import pojos.JsonPlaceHolderPojo;
import test_data.JsonPlaceHolderTestData;
import utils.JsonUtil;

import java.util.HashMap;

import static io.restassured.RestAssured.*;
import static org.junit.Assert.*;


public class GetWithObjectMapper01 extends JsonPlaceHolderBaseUrl {

         /*

     Given
            "https://jsonplaceholder.typicode.com/todos/198"

       When
            I send GET Request to the URL
       Then
            Status code is 200
       And
            response body should be like {
                                            "userId":10,
                                             "id":198,
                                            "title": "quis eius est sint explicabo",
                                            "completed": true

                                                }
*/

    @Test
    public void getWithObjectMapper01(){
        //1.step:set the url
        spec.pathParams("first","todos","second",198);

        //2.step: set the expected data
        JsonPlaceHolderTestData expect=new JsonPlaceHolderTestData();
        String expectedData= expect.expectedDataInString(10, "quis eius est sint explicabo",true);
        HashMap<String,Object> expectedDataMap= JsonUtil.convertJsonToJava(expectedData, HashMap.class);

        //3.step: send the request and get the response
        Response response= given().spec(spec).when().get("/{first}/{second}");
        response.prettyPrint();

        HashMap<String,Object> actualDataMap =JsonUtil.convertJsonToJava(response.asString(),HashMap.class);

        //4.step: do assertion
        assertEquals(200,response.getStatusCode());

        assertEquals(expectedDataMap.get("userId"),actualDataMap.get("userId"));
        assertEquals(expectedDataMap.get("title"),actualDataMap.get("title"));
        assertEquals(expectedDataMap.get("completed"),actualDataMap.get("completed"));
    }
    @Test //This is the best
    public void get02ObjectMapper(){

        //1.Step: Set the URL
        spec.pathParams("first", "todos", "second", 198);

        //2.Step: Set the Expected Data
        JsonPlaceHolderTestData jsonPlaceHolderTestData = new JsonPlaceHolderTestData();
        String expectedData = jsonPlaceHolderTestData.expectedDataInString(10, "quis eius est sint explicabo", true);
        JsonPlaceHolderPojo expectedDataPojo = JsonUtil.convertJsonToJava(expectedData, JsonPlaceHolderPojo.class);

        //3.Step:
        Response response = given().spec(spec).when().get("/{first}/{second}");

        //4.Do Assertions
        JsonPlaceHolderPojo actualDataPojo = JsonUtil.convertJsonToJava(response.asString(), JsonPlaceHolderPojo.class);

        assertEquals(200, response.getStatusCode());

        assertEquals(expectedDataPojo.getUserId(), actualDataPojo.getUserId());
        assertEquals(expectedDataPojo.getTitle(), actualDataPojo.getTitle());
        assertEquals(expectedDataPojo.getCompleted(), actualDataPojo.getCompleted());

    }

}
