package get_http_request_method;

import base_urls.GoRestBaseUrl;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.List;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.assertTrue;


public class Get11 extends GoRestBaseUrl {
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
            The number of users should  be 10
        And
            We have at least one "active" status
        And
           "Bhudeva Tandon", "Chandrabhan Kakkar", " Girja Pandey" are among the users
        And
            The female users are more than male users
     */
      @Test
      public void get01() {
          spec.pathParam("first", "users");

          //2.ste:expected data

          //3.step: response
          Response response = given().spec(spec).when().get("/{first}");
          response.prettyPrint();

          //4.step:do assertion
          response.
                  then().
                  assertThat().
                  statusCode(200).
                  body("meta.pagination.limit",equalTo(10),
                          "meta.pagination.links.current",equalTo("https://gorest.co.in/public/v1/users?page=1"),
                          "data.id",hasSize(10), "data.status", hasItem("active"),
                          "data.name", hasItems("Akroor Khanna", "Prathamesh Varman Sr.", "Anasuya Mahajan") );


          //The female users are more than male users
          //I will compare number of female and male user in 2 ways
          //1.way:i will get all genders then i will count the females then i will calculate males and i will check which one is more
          JsonPath json=response.jsonPath();
          List<String >genders= json.getList("data.gender");
          System.out.println(genders);

          int numOfFemales=0;
          for (String w:genders) {
              if(w.equals("female")){
                  numOfFemales++;
              }
          }
          System.out.println(numOfFemales);

          assertTrue(numOfFemales >=genders.size()-numOfFemales);

          //2.way:i will get all females by using Groovy, i will get all males by Groovy then compare them
          List<String>femaleList= json.getList("data.findAll{it.gender== 'female'}.gender");//this  wat is recommended
          System.out.println("femaleList"+femaleList);

          List<String> maleList = json.getList("data.findAll{it.gender== 'male'}.gender");
          System.out.println("maleList"+ maleList);

          assertTrue(femaleList.size()>maleList.size());
























      }







}
