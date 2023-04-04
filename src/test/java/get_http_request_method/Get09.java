package get_http_request_method;

import base_urls.HerOkuAppBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.junit.Assert.*;


public class Get09 extends HerOkuAppBaseUrl {
    /*

     Given
            "https://restful-booker.herokuapp.com/booking/7"
     When
            I send GET Request to the url
     Then
            Response body should be like that;

       {
    "firstname": "Mary",
    "lastname": "Jackson",
    "totalprice": 121,
    "depositpaid": true,
    "bookingdates": {
        "checkin": "2015-10-07",
        "checkout": "2018-02-14"
    }
}

     */

    @Test
    public void get09() {

        spec.pathParams("first", "booking", "second", 7);

        Map<String,String> expectedbookingDates = new HashMap<>();
        expectedbookingDates.put("checkin", "2015-10-07");
        expectedbookingDates.put("checkout", "2018-02-14");

        Map<String, Object> expectedData = new HashMap<>();
        expectedData.put("firstname", "Mary");
        expectedData.put("lastname", "Jackson");
        expectedData.put("totalprice", 121);
        expectedData.put("depositpaid", true);
        expectedData.put("bookingdates", expectedbookingDates);
        System.out.println(expectedData);

        Response response = given().spec(spec).when().get("/{first}/{second}");
        response.prettyPrint();

        Map<String, Object> actualData = response.as(HashMap.class);
        System.out.println(actualData);

        assertEquals(expectedData.get("firstname"),actualData.get("firstname"));
        assertEquals(expectedData.get("lastname"),actualData.get("lastname"));
        assertEquals(expectedData.get("totalprice"),actualData.get("totalprice"));
        assertEquals(expectedData.get("depositpaid"),actualData.get("depositpaid"));
        assertEquals(expectedbookingDates.get("checkin"),((Map)actualData.get("bookingdates")).get("checkin"));
        assertEquals(expectedbookingDates.get("checkout"),((Map)actualData.get("bookingdates")).get("checkout"));
    }

}
