package get_http_request_method;

import base_urls.GoRestBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;
import pojos.GoRestDataPojo;
import pojos.GoRestResponseBodyPojo;
import utils.JsonUtil;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class Get12 extends GoRestBaseUrl {

    /*
    Given
            https://gorest.co.in/public/v1/users/13
    When
            User send GET Request to the URL
    Then
            Status Code should be 200
    And
            Response body should be like
                                            {
                                    "meta": null,
                                    "data": {
                                        "id": 13,
                                        "name": "Tushar Panicker II",
                                        "email": "tushar_panicker_ii@green.io",
                                        "gender": "female",
                                        "status": "inactive"
                                    }
                                  }
     */

    @Test
    public void get01(){

        //1.step
        spec.pathParams("first","users","second",13);

        //2.step:
        GoRestDataPojo dataPojo=new GoRestDataPojo("Tushar Panicker II","tushar_panicker_ii@green.io", "female","inactive");
        GoRestResponseBodyPojo expectedDataPojo= new GoRestResponseBodyPojo(null,dataPojo);
        System.out.println(expectedDataPojo);

        //3.step:

        Response response=given().spec(spec).when().get("/{first}/{second}");
        response.prettyPrint();

        GoRestResponseBodyPojo actualDataPojo= JsonUtil.convertJsonToJava(response.asString(), GoRestResponseBodyPojo.class);
        System.out.println(actualDataPojo);

        //4.step
        //1.way
        assertEquals(200,response.getStatusCode());
        assertEquals(expectedDataPojo.getMeta(), actualDataPojo.getMeta());
        assertEquals(expectedDataPojo.getData().getName(), actualDataPojo.getData().getName());
        assertEquals(expectedDataPojo.getData().getEmail(), actualDataPojo.getData().getEmail());
        assertEquals(expectedDataPojo.getData().getGender(), actualDataPojo.getData().getGender());
        assertEquals(expectedDataPojo.getData().getStatus(), actualDataPojo.getData().getStatus());

        assertEquals(200,response.getStatusCode());
        assertEquals(dataPojo.getName(), actualDataPojo.getMeta());
        assertEquals(dataPojo.getEmail(), actualDataPojo.getData().getName());
        assertEquals(dataPojo.getGender(), actualDataPojo.getData().getEmail());
        assertEquals(dataPojo.getStatus(), actualDataPojo.getData().getGender());




    }
}
