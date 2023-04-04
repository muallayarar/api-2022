package get_http_request_method;

import base_urls.GoRestBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class Get13 extends GoRestBaseUrl {
    /*

    Given
           https://gorest.co.in/public/v1/users

    When
          User send GET Request
    Then
          The value of "pagination limit" is 10
    And
         The "current link" should be "https://gorest.co.in/public/v1/users?page=1"
    And
         The number of users should be 11
    And
         we have at least one "active" status
    And
         "Ujjwal Kocchar","Arnesh Abbott","Vasudeva Sinha" are among the users

    And
          The female users are less than male users
     */

    @Test
    public void get001(){
        spec.pathParam("first","users");

        Response response=given().spec(spec).contentType(ContentType.JSON).when().get("/{first}");
        response.prettyPrint();

        //1.way
        response.then().assertThat().statusCode(200).body("meta.pagination.limit",equalTo(10),
                "meta.pagination.links.current",equalTo("https://gorest.co.in/public/v1/users?page=1"),
                "data.name",hasSize(10),
                "data.status",  hasItem("active"),
                "data.name", hasItems( "Bharadwaj Kaur","Vedanshi Dubashi","Subhasini Varma V"));

        //2.way
        JsonPath json=response.jsonPath();
       assertEquals(10,json.getInt("meta.pagination.limit"));
       assertEquals("https://gorest.co.in/public/v1/users?page=1",json.getString("meta.pagination.links.current"));
       assertEquals(10,json.getList("data.name").size());
       assertTrue(json.getList("data.status").contains("active"));

       List<String> expectedNamesList= Arrays.asList("Bharadwaj Kaur","Vedanshi Dubashi","Subhasini Varma V");
       assertTrue(json.getList("data.name").containsAll(expectedNamesList));

       List<String> genderList=json.getList("data.gender");
        System.out.println(genderList);

        //1.way
        int femaleCounter =0;
        for (String w: genderList){
            if (w.equals("female")){
                femaleCounter++;
            }

        }

        assertTrue(femaleCounter > genderList.size()- femaleCounter);

        //2.way: use groovy
        List<String> femaleList = json.getList("data.findAll{it.gender='female'}.gender");
        List<String> maleList = json.getList("data.findAll{it.gender='female'}.gender");
        assertTrue(femaleList.size()>maleList.size());


        List<String> statusList=json.getList("data.status");
        System.out.println(statusList );

        //1.way
        int statusCounter=0;
        for (String w:statusList) {
         if (w.equals("active")){
             statusCounter++;
         }
        }

        assertTrue(statusList.size()>5);

        //2.way: use groovy
        List<String> listOfActiveStatus =json.getList("data.findAll{it.status='active'}.status");
        System.out.println(listOfActiveStatus);
        assertTrue(listOfActiveStatus.size()>5);
    }
}
