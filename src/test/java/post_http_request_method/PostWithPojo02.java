package post_http_request_method;

import base_urls.HerOkuAppBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;
import pojos.BookingDatesPojo;
import pojos.BookingPojo;
import pojos.HerOkuAppPostResponseBodyPojo;

import static io.restassured.RestAssured.*;
import static org.junit.Assert.*;

public class PostWithPojo02 extends HerOkuAppBaseUrl {
    /*

     Given
            "https://restful-booker.herokuapp.com/booking"
{
    "firstname": "Mualla",
    "lastname": "Yarar",
    "totalprice": 444,
    "depositpaid": true,
    "bookingdates": {
        "checkin": "2021-09-21",
        "checkout": "2022-09-26"
    },
    "additionalneeds": "Breakfast with white tea, dragon juice "
}
     When
            I send POST Request to the url
     Then
           Status Code is 200
      And
            Response body should be like that;

{
    "firstname": "Mary",
    "lastname": "Smith",
    "totalprice": 647,
    "depositpaid": false,
    "bookingdates": {
        "checkin": "2016-02-05",
        "checkout": "2021-01-16"
    },
    "additionalneeds": "Breakfast"
}

     */
    @Test
    public void postWithPojo02(){
        spec.pathParam("first","booking");

        //2.set the request body
        BookingDatesPojo bookingDates=new BookingDatesPojo("2021-09-21","2022-09-26");
        BookingPojo requestBody= new BookingPojo("Mualla","Yarar",444,true,bookingDates,"Breakfast with white tea, Dragon juice");

       //3.step: sent the request and get the response
       Response response=given().spec(spec).contentType(ContentType.JSON).body(requestBody).post("/{first}");
       response.prettyPrint();

        HerOkuAppPostResponseBodyPojo actualData=response.as(HerOkuAppPostResponseBodyPojo.class);
        System.out.println(actualData);

        //4.step:
        assertEquals(200, response.getStatusCode());
        assertEquals(requestBody.getFirstname(),actualData.getBooking().getFirstname());
        assertEquals(requestBody.getLastname(),actualData.getBooking().getLastname());
        assertEquals(requestBody.getTotalprice(),actualData.getBooking().getTotalprice());
        assertEquals(requestBody.getDepositpaid(),actualData.getBooking().getDepositpaid());
        assertEquals(requestBody.getBookingdates().getCheckin(),actualData.getBooking().getBookingdates().getCheckin());
        assertEquals(requestBody.getBookingdates().getCheckout(),actualData.getBooking().getBookingdates().getCheckout());
        assertEquals(requestBody.getAdditionalneeds(),actualData.getBooking().getAdditionalneeds());

    }

}
