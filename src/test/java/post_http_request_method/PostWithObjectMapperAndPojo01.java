package post_http_request_method;

import base_urls.JsonPlaceHolderBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;
import pojos.BookingPojo;
import pojos.JsonPlaceHolderPojo;
import test_data.JsonPlaceHolderTestData;
import utils.JsonUtil;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class PostWithObjectMapperAndPojo01 extends JsonPlaceHolderBaseUrl {
     /*

     Given
         1)   "https://jsonplaceholder.typicode.com/todos"

         2)       {
                    "userId":55,
                    "title": "Tidy your room",
                    "completed": false
                   }
       When

            I send POST Request to the URL
       Then
            Status code is 201
       And
            response body should be like {
                                            "userId":55,
                                            "title": "Tidy your room",
                                            "completed": false
                                            "id":201
                                                }

     */
    @Test
    public void getWithObjectMapperAndPojo01(){

        //1.step: set the url
        spec.pathParam("first","todos");


        //2.step:
        JsonPlaceHolderTestData expected=new JsonPlaceHolderTestData();
        String expectedData= expected.expectedDataInString(55,"Tidy your room",false);

        JsonPlaceHolderPojo expectedDataPojo= JsonUtil.convertJsonToJava(expectedData, JsonPlaceHolderPojo.class);

        //3.step:
        Response response= given().spec(spec).contentType(ContentType.JSON).body(expectedDataPojo).when().post("/{first}");
        response.prettyPrint();

       JsonPlaceHolderPojo actualDataPojo= JsonUtil.convertJsonToJava(response.asString(),JsonPlaceHolderPojo.class);
        System.out.println(actualDataPojo);

        //4.step: assertion

        assertEquals(201,response.getStatusCode());
        assertEquals(expectedDataPojo.getUserId(),actualDataPojo.getUserId());
        assertEquals(expectedDataPojo.getTitle(),actualDataPojo.getTitle());
        assertEquals(expectedDataPojo.getCompleted(),actualDataPojo.getCompleted());



    }
}
