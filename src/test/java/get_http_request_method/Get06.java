package get_http_request_method;

import base_urls.HerOkuAppBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;
import org.testng.asserts.SoftAssert;


import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertEquals;

public class Get06 extends HerOkuAppBaseUrl {
    /*
     Given
         "https://restful-booker.herokuapp.com/booking/20"
    When
          User send  a GET Request to the URL
    Then
          HTTP Status Code should be 200
    And
         Response content type is "application/json"
    And
         Response body should be like ;
         {"firstname: "Jim",
         "lastname": Brown",
         "totalprice":111,
         "depositpaid":true,
         "bookingdates": {
         "checkin": "2018-01-01",
         "checkout": "2019-01-01"}
     */

    @Test
    public void get06(){
        //1.step: set the url
        spec.pathParams("first","booking", "second", 20);

        //2.step: set the expected data

        //3.step: send the request get the data
        Response response= given().spec(spec).when().get("/{first}/{second}");
        response.prettyPrint();


        //4.do assertion
        //1.way:
        response.
                then().
                assertThat().
                statusCode(200).contentType(ContentType.JSON).
                body("firstname", equalTo("Jim"),
                "lastname", equalTo("Brown"),
                "totalprice", equalTo(111),
                "depositpaid",equalTo(true),
                        "bookingdates.checkin", equalTo("2018-01-01"),
                        "bookingdates.checkout", equalTo("2019-01-01"),
                        "additionalneeds",equalTo("Breakfast"));

        //2.way: use json path.  JsonPath is a class and it has many useful methods to navigate inside the Json Data
        response.then().
                assertThat().
                statusCode(200).contentType(ContentType.JSON);
        //Create JsonPath object from response object
        JsonPath json= response.jsonPath();
        assertEquals("First name is not matching","Jim",json.getString("firstname"));
        assertEquals("Last name is not matching","Brown",json.getString("lastname"));
        assertEquals("Total price is not matching",111,json.getInt("totalprice"));
        assertEquals("Deposit paid is not matching",true,json.getBoolean("depositpaid"));
        assertEquals("Checkin date is not matching","2018-01-01",json.getString("bookingdates.checkin"));
        assertEquals("Checkout date is not matching","2019-01-01",json.getString("bookingdates.checkout"));

        //3.way: soft assertion
        //i)Create SoftAssert Object
        SoftAssert softAssert=new SoftAssert();
        //ii)by using softAssert object do assertions
        softAssert.assertEquals(json.getString("firstname"),"Jim","First name is not matching");
        softAssert.assertEquals(json.getString("lastname"),"Brown","Last name is not matching");
        softAssert.assertEquals(json.getInt("totalprice"),111,"Total price is not matching");
        softAssert.assertEquals(json.getBoolean("depositpaid"),true,"Deposit paid is not matching");
        softAssert.assertEquals(json.getString("bookingdates.checkin"),"2018-01-01","Checkin date is not matching");
        softAssert.assertEquals(json.getString("bookingdates.checkout"),"2019-01-01","Checkout date is not matching");

        //İii)Do not forget to use assertAll.if you don't use assertAll() you will get green everytime but it is not meaningful
        softAssert.assertAll();
    }
}
