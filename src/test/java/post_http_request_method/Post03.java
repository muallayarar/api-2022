package post_http_request_method;

import base_urls.AgroMonitoringBApiBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;
import test_data.AgroMonitoringApiTestData;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.junit.Assert.*;

public class Post03 extends AgroMonitoringBApiBaseUrl {

    /*
        Given
            1) "http://api.agromonitoring.com/agro/1.0/polygons?appid=f4ffe3b2ef1fcb3600ab1d7fbc88c2f0"
            2) {
                "name":"Polygon Sample",
                "geo_json":{
                   "type":"Feature",
                   "properties":{},
                   "geometry":{
                      "type":"Polygon",
                      "coordinates":[
                                     [
                                        [-121.1958,37.6683],
                                        [-121.1779,37.6687],
                                        [-121.1773,37.6792],
                                        [-121.1958,37.6792],
                                        [-121.1958,37.6683]
                                    ]
                                  ]
                              }
                           }
                 }
	    When
		    I send POST Request to the Url
	    Then
		    Assert Status Code (201)
		    And Response Body should be like {
                                                "id": "5fd8c383714b523b2ce1f154",
                                                "geo_json": {
                                                    "geometry": {
                                                        "coordinates": [
                                                            [
                                                                [
                                                                    -121.1958,
                                                                    37.6683
                                                                ],
                                                                [
                                                                    -121.1779,
                                                                    37.6687
                                                                ],
                                                                [
                                                                    -121.1773,
                                                                    37.6792
                                                                ],
                                                                [
                                                                    -121.1958,
                                                                    37.6792
                                                                ],
                                                                [
                                                                    -121.1958,
                                                                    37.6683
                                                                ]
                                                            ]
                                                        ],
                                                        "type": "Polygon"
                                                    },
                                                    "type": "Feature",
                                                    "properties": {
                                                                  }
                                                },
                                                "name": "Polygon Sample",
                                                "center": [
                                                    -121.1867,
                                                    37.67385
                                                ],
                                                "area": 190.9484,
                                                "user_id": "5fd8c02a3da20c000759e0f8",
                                                "created_at": 1608041347
                                            }
     */
    @Test
    public void post03(){

        //1.Step: Set the Url
        spec.pathParams("first", "agro", "second", 1.0, "third", "polygons").
                queryParams("appid", "f4ffe3b2ef1fcb3600ab1d7fbc88c2f0");

        //2.Step: Set the Expected Data
        AgroMonitoringApiTestData requestBody = new AgroMonitoringApiTestData();
        Map<String, Object> requestBodyMap = requestBody.requestBodySetUp();

        //3.Step: Send the POST Request and get the response
        Response response = given().spec(spec).contentType(ContentType.JSON).body(requestBodyMap).when().post("/{first}/{second}/{third}");
        response.prettyPrint();

        //Add more key-values into the request body
        requestBodyMap.put("area",190.9484);

        //4.Step: Do Assertions
        //1.Way:use GSON to convert response to a Map
        Map<String, Object> responseBody = response.as(HashMap.class);
        System.out.println(responseBody);

        assertEquals(requestBodyMap.get("area"), responseBody.get("area"));
        assertEquals(requestBodyMap.get("name"), responseBody.get("name"));
        assertEquals(requestBody.geometrySetUp().get("type"), ((Map)((Map) responseBody.get("geo_json")).get("geometry")).get("type"));
        assertEquals(String.valueOf(requestBody.coordinates[0][1][0]), ((Map)((Map) responseBody.get("geo_json")).get("geometry")).get("coordinates").toString().substring(25, 33));

        //2.Way:
       // JsonPath json = response.jsonPath();

        //assertTrue(json.getFloat("geo_json.geometry.coordinates[0][0][0]")==requestBody.coordinates[0][0][0]);
        //assertTrue(json.getString("geo_json.geometry.type").equals(requestBody.geometrySetUp().get("type")));
        //assertTrue(json.getString("geo_json.type").equals(requestBody.geo_json_SetUp().get("type")));
        //assertTrue(json.getJsonObject("geo_json.properties").equals(requestBody.geo_json_SetUp().get("properties")));
        //assertTrue(json.getString("name").equals(requestBodyMap.get("name")));
        //assertTrue(json.getFloat("center[0]")==requestBody.center[0]);
       // assertTrue(json.getFloat("center[1]")==requestBody.center[1]);

        //To assert "area" value you can use both of the followings
        //assertTrue(json.get("area").toString().equals(requestBodyMap.get("area").toString()));
        //assertTrue(json.getDouble("area")==(Double)requestBodyMap.get("area"));

    }

}
