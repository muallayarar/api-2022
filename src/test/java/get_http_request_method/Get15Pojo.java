package get_http_request_method;

import base_urls.GoRestBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;
import pojos.GoRestDataPojo;
import pojos.GoRestResponseBodyPojo;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class Get15Pojo extends GoRestBaseUrl {
    /*
    Given
            https://gorest.co.in/public/v1/users/25
        When
            User send GET Request to the URL
        Then
            Status Code should be 200
        And
            Response body should be like
                                             {
                                        "meta": null,
                                        "data": {
                                            "id": 25,
                                            "name": "Chandini Kaur",
                                            "email": "chandini_kaur@wehner.org",
                                            "gender": "male",
                                            "status": "inactive"
                                        }
                                    }
     */

    @Test
    public void get15Pojo(){

        spec.pathParams("first","users","second",25);

        GoRestDataPojo goRestData=new GoRestDataPojo("Chandini Kaur","chandini_kaur@wehner.org","male","inactive");
        GoRestResponseBodyPojo expectedData=new GoRestResponseBodyPojo(null,goRestData);
        System.out.println(expectedData);

        Response response=given().spec(spec).contentType(ContentType.JSON).get("/{first}/{second}");
        response.prettyPrint();


        GoRestResponseBodyPojo actualData=response.as(GoRestResponseBodyPojo.class);

        assertEquals(200,response.getStatusCode());
        assertEquals(expectedData.getMeta(),actualData.getMeta());

        assertEquals(expectedData.getData().getName(),actualData.getData().getName());
        assertEquals(expectedData.getData().getEmail(),actualData.getData().getEmail());
        assertEquals(expectedData.getData().getGender(),actualData.getData().getGender());
        assertEquals(expectedData.getData().getStatus(),actualData.getData().getStatus());



    }
}
