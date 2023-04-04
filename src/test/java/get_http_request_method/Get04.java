package get_http_request_method;

import base_urls.JsonPlaceHolderBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class Get04 extends JsonPlaceHolderBaseUrl {
    /*
        Given
            https://jsonplaceholder.typicode.com/todos
        When
            I send  a GET Request to the URL
        And
		    Accept type is  “application/json”
        Then
            HTTP Status Code should be 200
        And
		    Response format should be “application/json”
		And
		    There should be 200 todos”,
		And
		    “quis eius est sint explicabo” should be one of the todos
		And
		    2,7, and 9 should be among the userIds
     */

    @Test
    public  void get04(){

        //1.step: set the url
        spec.pathParam("first","todos");

        //2.step: set the expected data

        //3.step: send request get response
        Response response =given().spec(spec).accept(ContentType.JSON).when().get("/{first}");
        response.prettyPrint();

        //4.step: do assertion
        response.
                then().
                assertThat().
                statusCode(200).
                contentType(ContentType.JSON).
                body("id", hasSize(200)).
                body("title",hasItem("quis eius est sint explicabo")).
                body("userId", hasItems(2,7,9));
    }
}
