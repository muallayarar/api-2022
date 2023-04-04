package post_http_request_method;

import base_urls.HerOkuAppBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;
import test_data.HerOkuAppTestData;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.junit.Assert.assertEquals;

public class Post01 extends HerOkuAppBaseUrl {

    /*
      Given
            "https://restful-booker.herokuapp.com/booking"

     {
        "firstname": "Selim",
        "lastname":"Ak",
        "totalprice": 11111,
        "depositpaid": true,
        "bookingdates": {
            "checkin":"2020-09-09",
            "checkout":"2020-09-21"
            }
       }
       When
            I send POST Request to the URL
       Then
            Status code is 200
       And
            Response body should be like
       {

         "bookingid":11,
         "booking": {
         "firstname": "Selim",
        "lastname":"Ak",
        "totalprice": 11111,
        "depositpaid": true,
        "bookingdates": {
            "checkin":"2020-09-09",
            "checkout":"2020-09-21"
            }
 */
    @Test
    public void post01(){

        //1.step:set the url
        spec.pathParam("first","booking");

        //2.step: set expected data
        HerOkuAppTestData herOkuAppTestData =new HerOkuAppTestData();
        Map<String,String> bookingDatesMap= herOkuAppTestData.bookingDatesSetUp("2020-09-09","2020-09-21");
        Map<String,Object> expectedData= herOkuAppTestData.expectedDataSetUp("Selim","Ak",11111,true, bookingDatesMap);


        //3.step: send the post request and get the response
        Response response= given().spec(spec).contentType(ContentType.JSON).body(expectedData).when().post("/{first}");
        response.prettyPrint();

        //4.step: do assertions
        Map<String ,Object> actualData=response.as(HashMap.class);
        System.out.println(actualData);

        assertEquals(200, response.getStatusCode());
        assertEquals(expectedData.get("firstname"),((Map)actualData.get("booking")).get("firstname"));
        assertEquals(expectedData.get("lastname"),((Map)actualData.get("booking")).get("lastname"));
        assertEquals(expectedData.get("totalprice"),((Map)actualData.get("booking")).get("totalprice"));
        assertEquals(expectedData.get("depositpaid"),((Map)actualData.get("booking")).get("depositpaid"));

        assertEquals(bookingDatesMap.get("checkin"),((Map)((Map)actualData.get("booking")).get("bookingdates")).get("checkin"));
        assertEquals(bookingDatesMap.get("checkout"),((Map)((Map)actualData.get("booking")).get("bookingdates")).get("checkout"));
    }
}
