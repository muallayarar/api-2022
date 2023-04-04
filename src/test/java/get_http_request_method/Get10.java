package get_http_request_method;

import base_urls.GoRestBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;
import test_data.GoRestTestData;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class Get10 extends GoRestBaseUrl {
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
            "name": "Dandak Adiga",
            "email": "adiga_dandak@christiansen-schimmel.biz",
            "gender": "female",
            "status": "active"
        }
    }
     */
    @Test
    public void get10(){

        //1.step: set the url
        spec.pathParams("first","users","second",20);

        //2.step: set the expected data
        GoRestTestData goRestTestData=new GoRestTestData();
        Map<String,String> dataMap = goRestTestData.dataKeyMap("Satish Kapoor PhD","phd_kapoor_satish@hammes.org","female","inactive");
        Map<String,Object> expectedData= goRestTestData.expectedDataMap(null, dataMap);
        System.out.println(expectedData);


        //3.step:
        Response response=given().spec(spec).when().get("/{first}/{second}");
        response.prettyPrint();


        //4.step: do assertion
        Map<String,Object> actualData= response.as(HashMap.class);
        assertEquals(expectedData.get("meta"),actualData.get("meta"));
        assertEquals(dataMap.get("name"),((Map)actualData.get("data")).get("name"));
        assertEquals(dataMap.get("email"),((Map)actualData.get("data")).get("email"));
        assertEquals(dataMap.get("gender"),((Map)actualData.get("data")).get("gender"));
        assertEquals(dataMap.get("status"),((Map)actualData.get("data")).get("status"));
        assertEquals(200,response.getStatusCode());




    }
}
