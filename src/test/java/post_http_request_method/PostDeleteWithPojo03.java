package post_http_request_method;

import base_urls.JsonPlaceHolderBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;
import pojos.JsonPlaceHolderPojo;

import javax.management.ObjectName;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertTrue;

public class PostDeleteWithPojo03 extends JsonPlaceHolderBaseUrl {
 /*

     Given
            "https://jsonplaceholder.typicode.com/todos"

     {
        "userId":55,
        "title": "Tidy your room",
        "completed": false
       }

       When
            I send POST Request to the URL
            I send Delete Request to the Url
       Then
            Status code is 201
       And
            response body should be like { }
  */
    Integer id;
 @Test
 public void postWithPojo01() {

     //Note : when you are doing post , don't forget to delete while you are testing

     //1.step:set the url
     spec.pathParam("first", "todos");

     //2.step: set the request body
     JsonPlaceHolderPojo requestBody = new JsonPlaceHolderPojo(55, "Tidy your room", false);

     //3.step: send the request ,get the response
     Response response = given().spec(spec).contentType(ContentType.JSON).body(requestBody).when().post("/{first}");
     response.prettyPrint();

     //Get the id of newly created data
     JsonPath json=response.jsonPath();
     id=json.getInt("id");

     spec.pathParams("first", "todos","second",id);

     Response response2=given().spec(spec).when().delete("/{first}/{second}");

     Map<String, Object> actualData=response2.as(HashMap.class);

     assertTrue(actualData.size()==0);


 }
}
