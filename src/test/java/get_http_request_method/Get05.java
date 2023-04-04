package get_http_request_method;

import base_urls.HerOkuAppBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.*;
import static org.junit.Assert.assertTrue;

public class Get05 extends HerOkuAppBaseUrl {
/*
    Given
         "https://restful-booker.herokuapp.com/booking"
    When
          User send  a GET Request to the URL
    Then
          Status code is 200
    And
         Among the data there should be someone whose first name is "Jim" and last name is "Brown"

*/

    @Test
    public void get05(){
        //1.step: set the url
        spec.pathParam("first","booking").queryParams("firstname","Jim","lastname", "Brown");

        //2.step: set the expected data

        //3.step: send the request get data
        Response response=given().spec(spec).when().get("/{first}");
        response.prettyPrint();

        //4.step: do assertion
        response.then().assertThat().statusCode(200);

        assertTrue(response.asString().contains("bookingid"));
    }



}
