package get_http_request_method;

import base_urls.HerOkuAppBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;
import pojos.BookingDatesPojo;
import pojos.BookingPojo;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class GetWithPojo01 extends HerOkuAppBaseUrl {
    /*

     Given
            "https://restful-booker.herokuapp.com/booking/9"
     When
            I send GET Request to the url
     Then
           Status Code is 200
      And
            Response body should be like that;

       {
    "firstname": "James",
    "lastname": "Brown",
    "totalprice": 111,
    "depositpaid": true,
    "bookingdates": {
        "checkin": "2018-01-01",
        "checkout": "2019-01-01"
    }
     "additionalneeds": "Breakfast"
}
}

     */

    @Test
    public void getWithPojo01(){

        //1.step: set the URL
       spec.pathParams("first","booking","second",9);

       //2.step: set the expected data
        BookingDatesPojo bookingDates=new BookingDatesPojo("2018-01-01","2019-01-01");
        BookingPojo expectedData =new BookingPojo( "James","Brown",111,true, bookingDates,"Breakfast" );


        //3.step:
        Response response= given().spec(spec).when().get("/{first}/{second}");
        response.prettyPrint();

        //4.step: do assertions
        BookingPojo actualData= response.as(BookingPojo.class);
        System.out.println(actualData);

        assertEquals(200, response.getStatusCode());
        assertEquals("First names are not matching " +expectedData.getFirstname(),actualData.getFirstname());
        assertEquals("Last names are not matching "+expectedData.getLastname(),actualData.getLastname());
        assertEquals("Total prices are not matching "+expectedData.getTotalprice(),actualData.getTotalprice());
        assertEquals("Deposit paid is not matching "+expectedData.getDepositpaid(),actualData.getDepositpaid());
        assertEquals("Check in dates are not matching "+expectedData.getBookingdates().getCheckin(),actualData.getBookingdates().getCheckin());//***IMPORTANT
        assertEquals("Check out dates are not matching " +expectedData.getBookingdates().getCheckout(),actualData.getBookingdates().getCheckout());//***IMPORTANT
        assertEquals(expectedData.getAdditionalneeds(),actualData.getAdditionalneeds());

    }
}
