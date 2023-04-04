package post_http_request_method;

import base_urls.HerOkuAppBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;
import pojos.BookingDatesPojo;
import pojos.BookingPojo;
import pojos.HerOkuAppPostResponseBodyPojo;
import utils.JsonUtil;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class PostGetWithObjectMapperAndPojo01 extends HerOkuAppBaseUrl {
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
       And
            I send GET Request to the URL
       Then
            Status code is 200
       And
            Response body should be like
       {

         "bookingid":43605,
         "booking": {
         "firstname": "Selim",
        "lastname":"Ak",
        "totalprice": 11111,
        "depositpaid": true,
        "bookingdates": {
            "checkin":"2020-09-09",
            "checkout":"2020-09-21"
            },
            "additionalneeds": "Breakfast"
            }
 */

    @Test
    public void PostGetWithObjectMapperAndPojo01(){
                   //TO SEND POST REQUEST WE DID 1. ,2. , 3.
        //1
        spec.pathParam("first","booking");

        //2.
        BookingDatesPojo bookingDates=new BookingDatesPojo("2020-09-09", "2020-09-21");
        BookingPojo requestBody=new BookingPojo("Selim","Ak",11111, true,bookingDates,"Breakfast");
        System.out.println(requestBody);

        //3.
        Response response=given().spec(spec).contentType(ContentType.JSON).body(requestBody).when().post("{first}");
        response.prettyPrint();

                 //CONVERT POST RESPONSE BODY TO JAVA OBJECT BY USİNG OBJECT MAPPER
        HerOkuAppPostResponseBodyPojo responseBody= JsonUtil.convertJsonToJava(response.asString(),HerOkuAppPostResponseBodyPojo.class);
        System.out.println(responseBody);

                 //FROM responseBody We got THE VALUE OF bookingId by using GETTER OF bookingId
        Integer bookingId=responseBody.getBookingid();

                //BY USING bookingId I WILL SEND GET REQUEST IN 1.step,2.step,3.step
        //1.step: set the url for GET Request
        spec.pathParams("first","booking","second",bookingId);
        //2.step: set the expected data
        //no need to create data bcz the data which is sent in the POST Request will be expected data

        //3.step: send the GET request and GET the response
        Response response2=given().spec(spec).when().get("/{first}/{second}");
        response2.prettyPrint();

                //I CONVERT GET RESPONSE BODY TO JAVA OBJECT BY USING OBJECT MAPPER
        BookingPojo getResponseBodyInPojo = JsonUtil.convertJsonToJava(response2.asString(),BookingPojo.class);


               //BY USİNG requestBody and ResponseBody, I DID ASSERTİON

        //4.
        assertEquals(200,response2.getStatusCode());
        assertEquals(requestBody.getFirstname(),getResponseBodyInPojo.getFirstname());
        assertEquals(requestBody.getLastname(),getResponseBodyInPojo.getLastname());
        assertEquals(requestBody.getTotalprice(),getResponseBodyInPojo.getTotalprice());
        assertEquals(requestBody.getDepositpaid(),getResponseBodyInPojo.getDepositpaid());
        assertEquals(requestBody.getBookingdates().getCheckin(),getResponseBodyInPojo.getBookingdates().getCheckin());
        assertEquals(requestBody.getBookingdates().getCheckout(),getResponseBodyInPojo.getBookingdates().getCheckout());
        assertEquals(requestBody.getAdditionalneeds(),getResponseBodyInPojo.getAdditionalneeds());


    }
}
