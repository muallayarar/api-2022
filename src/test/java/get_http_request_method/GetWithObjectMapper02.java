package get_http_request_method;

import base_urls.HerOkuAppBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;
import utils.JsonUtil;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.junit.Assert.assertEquals;

public class GetWithObjectMapper02 extends HerOkuAppBaseUrl {
    /*

     Given
            "https://restful-booker.herokuapp.com/booking/19"
     When
            I send GET Request to the url
     Then
           Status Code is 200
      And
            Response body should be like that;

                                                   {
                                                        "firstname": "Leonardo",
                                                        "lastname": "Balderas",
                                                        "totalprice": 832,
                                                        "depositpaid": true,
                                                        "bookingdates": {
                                                            "checkin": "2022-08-10",
                                                            "checkout": "2022-08-16"
                                                        },
                                                        "additionalneeds": "Breakfast"
                                                    }
     */
    @Test
    public void getWithObjectMapper02() {

        //1.step: set the url
        spec.pathParams("first", "booking", "second", 19);

        //2.step: expected data
        String expectedData = "{\n" +
                "\"firstname\": \"Jack\",\n" +
                "\"lastname\": \"Macdonald\", \n" +
                "\"totalprice\": 514, \n" +
                "\"depositpaid\": true,\n" +
                "\"bookingdates\": {\n" +
                                    "\"checkin\": \"2022-08-11\", \n" +
                                    "\"checkout\": \"2022-08-24\"\n" +
                "},\n" +
                "\"additionalneeds\": \"Extra Pillow\"\n"+
                "}";

        HashMap<String,Object> expectedDataMap= JsonUtil.convertJsonToJava(expectedData, HashMap.class);
        //3.step:send the request and get the response
        Response response = given().spec(spec).when().get("/{first}/{second}");
        response.prettyPrint();

        HashMap<String,Object>actualDataMap= JsonUtil.convertJsonToJava(response.asString(),HashMap.class);

        //4.step:assertion
        assertEquals(200, response.getStatusCode());

        assertEquals(expectedDataMap.get("firstname"),actualDataMap.get("firstname"));
        assertEquals(expectedDataMap.get("lastname"),actualDataMap.get("lastname"));
        assertEquals(expectedDataMap.get("totalprice"),actualDataMap.get("totalprice"));
        assertEquals(expectedDataMap.get("depositpaid"),actualDataMap.get("depositpaid"));
        assertEquals(expectedDataMap.get("totalprice"),actualDataMap.get("totalprice"));
        assertEquals(((Map)expectedDataMap.get("bookingdates")).get("checkin"),((Map)actualDataMap.get("bookingdates")).get("checkin"));
        assertEquals(((Map)expectedDataMap.get("bookingdates")).get("checkout"),((Map)actualDataMap.get("bookingdates")).get("checkout"));


    }
}
