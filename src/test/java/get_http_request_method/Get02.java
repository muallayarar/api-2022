package get_http_request_method;

import base_urls.HerOkuAppBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;
import static io.restassured.RestAssured.*;
import static org.testng.AssertJUnit.*;


public class Get02 extends HerOkuAppBaseUrl {
    /*
    Given
          "https://restful-booker.herokuapp.com/booking/1"
    When
          User send a GET to the url
    Then
          HTTP Status code should be 404
    And
         Status Line should be HTTP/1.1 404 NOT FOUND
    And
         Response body contains "Not Found"
    And
         Response body does not contain "Techproed"
    And
         Server is "Cowboy"
*/

    //Note: Path Parameters are used to make resource smaller
    @Test
    public void get02(){
        //1.step: set the url
        String url= "https://restful-booker.herokuapp.com/booking/1"; // ==> not recommended
        // spec.pathParams("first","booking","second",1);

        //2.step: set the excepted date

        //3.step: send the request and get the response
        Response response= given().when().get(url);
        response.prettyPrint();//NOt found


        //4.step: Assertion
        response.then().assertThat().statusCode(404).statusLine("HTTP/1.1 404 Not Found");

        //assertTrue(true)==> green tick        assertTrue(false)==> red cross
       assertTrue(response.asString().contains("Not Found")); //If response.asString().contains("Not Found") returns true, you will get green tick

        //assertFalse(false)==> Green tick          assertFalse(true)==> red cross
        assertFalse(response.asString().contains("TecProEd"));//If response.asString().contains("Not TecProEd") returns false, you will get green tick

        //Excepted Data comes from test case, actual data comes from API
        //assertEquals() returns true(test passes) if the arguments match
        assertEquals("Cowboy" ,response.getHeader("Server"));
    }
}
