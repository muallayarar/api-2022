package get_http_request_method;

import base_urls.DummyRestApiBaseUrl;
import org.junit.Test;
import pojos.DummyDataInnerPojo;
import pojos.DummyRestApiPojo;

import static io.restassured.RestAssured.given;

public class Get17 extends DummyRestApiBaseUrl {
    /*
       URL: https://dummy.restapiexample.com/api/v1/employee/1
       HTTP Request Method: GET Request
       Test Case: Type by using Gherkin Language
       Assert: 	i) Status code is 200
               ii) "employee_name" is "Tiger Nixon"
              iii) "employee_salary" is 320800
               iv)  "employee_age" is 61
                v) "status" is "success"
               vi)  "message" is "Successfully! Record has been fetched."
     */

    /*
    Given
          https://dummy.restapiexample.com/api/v1/employee/1
    when
          User sends GET Request
    Then
          Status code is 200
    And
          "employee_name" is "Tiger Nixon"
    And
         "employee_salary" is 320800
    And
         "employee_age" is 61
    And
          "status" is "success"
    And
           "message" is "Successfully! Record has been fetched."

     */
    @Test
    public void get01(){
        spec.pathParams("first","employee","second",1);

        //we will use expected data bcz task wants all datas

        DummyDataInnerPojo dataPojo =new DummyDataInnerPojo(1,"Tiger Nixon",320800,61,"");
        System.out.println(dataPojo);
        //DummyRestApiPojo expectedData=new DummyRestApiPojo("success", dataPojo, "Successfully! Record has been fetched.");
        //System.out.println(expectedData);


        //Response response=given().spec(spec).contentType(ContentType.JSON).when().get("/{first}/{second}");
       // response.prettyPrint();



    }
}
