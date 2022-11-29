package get_request;

import base_url.DummyRestApiBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertEquals;

public class Get16JsonPath_Matcher extends DummyRestApiBaseUrl {

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
        User sends Get Request

    Then
         i) Status code is 200
    And
         ii) There are 24 employees
    And
         iii) "Tiger Nixon" and "Garrett Winters" are among the employees
    And
         iv) The greatest age is 66
    And
         v) The name of the lowest age is "Tatyana Fitzpatrick"
    And
         vi) Total salary of all employees is 6,644,770

     */

    @Test
    public void get16(){
        spec.pathParam("first","employees");

        Response response = given().spec(spec).when().get("/{first}");
        response.prettyPrint();

        response.then().assertThat().statusCode(200).body("data",hasSize(24),
                "data.employee_name",hasItems("Tiger Nixon","Garrett Winters"));

        List <Integer>ages= response.jsonPath().getList("data.employee_age"); //yaslarin listesini aldik liste attik
        System.out.println("ages = " + ages);
        Collections.sort(ages);                                       //collections dan sort methodu ile siraladik
        System.out.println("sorted ages = " + ages);
        System.out.println(ages.get(ages.size()-1));

        assertEquals((Integer)66,ages.get(ages.size()- 1));

        //v) The name of the lowest age is "Tatyana Fitzpatrick"                             //dinamik
     String lowestAgedEmployee=response.jsonPath().getString("data.findAll{it.employee_age=="+ages.get(0)+"}.employee_name"); //listin ilk elemani

    response.jsonPath().getList("data.findAll{it.employee_age==19}.employee_name"); //hard

        assertEquals("[Tatyana Fitzpatrick]",lowestAgedEmployee);

        //vi) Total salary of all employees is 6,644,770  /calisanlarin toplam massi 6,644,770
       List <Integer>salaries= response.jsonPath().getList("data.employee_salary");
        System.out.println("salaries = " + salaries);
        //1.yol
        int sum=0;
        for (int w:salaries) {
            sum+=w;
        }
        System.out.println("sum = " + sum);

        assertEquals(6644770,sum);

        //2. Yol
        int sum2 = salaries.stream().reduce(0, Integer::sum);
        System.out.println("sum2 = " + sum2);
        assertEquals(6644770,sum2);

        //3. Yol
        int sum3 = salaries.stream().reduce(0, Math::addExact);
        System.out.println("sum3 = " + sum3);
        assertEquals(6644770,sum3);

        }
    }


