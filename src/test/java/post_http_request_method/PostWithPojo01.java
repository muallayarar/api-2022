package post_http_request_method;

import base_urls.JsonPlaceHolderBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;
import pojos.JsonPlaceHolderPojo;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertEquals;

public class PostWithPojo01 extends JsonPlaceHolderBaseUrl {

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
    public void postWithPojo01(){



        //1.step:set the url
        spec.pathParam("first","todos");

        //2.step: set the request body
        JsonPlaceHolderPojo requestBody=new JsonPlaceHolderPojo(55,"Tidy your room",false);
        System.out.println(requestBody);

        //3.step: send the request ,get the response
        Response response=given().spec(spec).contentType(ContentType.JSON).body(requestBody).when().post("/{first}");
        response.prettyPrint();

        //4.step: do assertion
        //1.way
        response.then().assertThat().statusCode(201).body("userId",equalTo(requestBody.getUserId()),
                "title",equalTo(requestBody.getTitle()),
                "completed",equalTo(requestBody.getCompleted()));

        //2.way:de-serialization
        JsonPlaceHolderPojo actualData=response.as(JsonPlaceHolderPojo.class);
        System.out.println(actualData);

        assertEquals(requestBody.getUserId(),actualData.getUserId());
        assertEquals(requestBody.getTitle(),actualData.getTitle());
        assertEquals(requestBody.getCompleted(),actualData.getCompleted());




   }
}
