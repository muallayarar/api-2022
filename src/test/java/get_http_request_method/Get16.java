package get_http_request_method;

import base_urls.DummyRestApiBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.Collections;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertEquals;

public class Get16 extends DummyRestApiBaseUrl {
    /*
           URL: https://dummy.restapiexample.com/api/v1/employees
           HTTP Request Method: GET Request
           Test Case: Type by using Gherkin Language
           Assert:  i) Status code is 200
                   ii) There are 24 employees
                  iii) "Tiger Nixon" and "Garrett Winters" are among the employees
                   iv) The greatest age is 66
                    v) The name of the lowest age is "Tatyana Fitzpatrick"
                   vi) Total salary of all employees is 6,644,770
    */
    /*
     Given
           https://dummy.restapiexample.com/api/v1/employees
     When
           user sends GET Request to the url
     Then
            Status code is 200
     And
            There are 24 employees
     And
            "Tiger Nixon" and "Garrett Winters" are among the employees
     And
             The greatest age is 66
     And
             The name of the lowest age is "Tatyana Fitzpatrick"
     And
             Total salary of all employees is 6,644,770

     */

    @Test
    public void get01(){
        spec.pathParam("first","employees");

        Response response=given().spec(spec).when().get("/{first}");
        response.prettyPrint();


        response.then().assertThat().statusCode(200).body("data.id",hasSize(24),
                "data.employee_name", hasItems( "Tiger Nixon" , "Garrett Winters"));

        JsonPath json=response.jsonPath();
       List<Integer> ageList= json.getList("data.findAll{it.id}.employee_age");
        System.out.println(ageList);
        Collections.sort(ageList);
        System.out.println(ageList.get(ageList.size()-1));
        assertEquals(66,(int)ageList.get(ageList.size()-1));

       String nameOfLowestAge= json.getString("data.findAll{it.employee_age=="+ageList.get(0)+"}.employee_name");
       System.out.println(nameOfLowestAge);
       assertEquals("[Tatyana Fitzpatrick]",nameOfLowestAge);

       List<Integer> salaryList=json.getList("data.findAll{it.id}.employee_salary");
        System.out.println(salaryList);
        //1.way
        int sum=0;
        for (int w:salaryList) {
            sum+=w;
        }
        System.out.println(sum);

        //2.way:
        int sum2= salaryList.stream().reduce(0,(t,u)->(t+u));
        System.out.println(sum2);

        //3.way:
        int sum3=salaryList.stream().reduce(0,Math::addExact);
        System.out.println(sum3);

        assertEquals(6644770,sum3);


    }

}
