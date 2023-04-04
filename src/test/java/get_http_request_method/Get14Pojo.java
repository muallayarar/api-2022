package get_http_request_method;

import base_urls.HerOkuAppBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;
import pojos.BookingDatesPojo;
import pojos.BookingPojo;
import pojos.HerOkuAppPostResponseBodyPojo;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

public class Get14Pojo extends HerOkuAppBaseUrl {
    /*
        Given
            https://restful-booker.herokuapp.com/booking/9
        When
 		    I send GET Request to the URL
 	    Then
 		    Status code is 200
 		And
 		    Response body is like {
                                        "firstname": "Jim",
                                        "lastname": "Ericsson",
                                        "totalprice": 961,
                                        "depositpaid": true,
                                        "bookingdates": {
                                            "checkin": "2018-11-13",
                                            "checkout": "2020-04-26"
                                        },
                                        "additionalneeds": "Breakfast"
                                    }
     */
    @Test
    public void  get14Pojo(){

        spec.pathParams("first","booking","second",9);

        BookingDatesPojo bookingDates=new BookingDatesPojo("2018-11-13","2020-04-26");
        BookingPojo expectedBody =new BookingPojo("Jim","Ericsson",961, true, bookingDates,"Breakfast");
        System.out.println(expectedBody);

        Response response=given().spec(spec).contentType(ContentType.JSON).body(expectedBody).get("/{first}/{second}");
        response.prettyPrint();

        BookingPojo actualData=response.as(BookingPojo.class);
        System.out.println(actualData);

        assertEquals(200,response.getStatusCode());

        assertEquals(expectedBody.getFirstname(),actualData.getFirstname());
        assertEquals(expectedBody.getLastname(),actualData.getLastname());
        assertEquals(expectedBody.getTotalprice(),actualData.getTotalprice());
        assertEquals(expectedBody.getDepositpaid(),actualData.getDepositpaid());
        assertEquals(expectedBody.getAdditionalneeds(),actualData.getAdditionalneeds());

        assertEquals(expectedBody.getBookingdates().getCheckout(),actualData.getBookingdates().getCheckout());
        assertEquals(expectedBody.getBookingdates().getCheckin(),actualData.getBookingdates().getCheckin());





    }

}
