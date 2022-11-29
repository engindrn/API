package get_request;

import base_url.AutomationExercieseBaseUrl;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class XGetAutomationexerciseJsonPath extends AutomationExercieseBaseUrl {

     /*
        Given
            https://automationexercise.com/api/brandsList
        When
            User sends a GET Request to the url
        Then
            HTTP Status Code should be 200
        And
            Content Type should be "text/html; charset=utf-8"
        And
            Status Line should be HTTP/1.1 200 OK
        And
             Number of H&M brands must be equal to Polo(H&M marka sayısı Polo marka sayısına eşit olmalı)
*/

    @Test
    public void test01() {
        //set the url
        spec.pathParams("first", "brandsList");

        //set the expected data

        //send the request get the response
        Response response = given().spec(spec).when().get("/{first}"); //html dondugu icin prety print yapinca
                                                                         //hata veriyor

        //do assertion
        //assertEquals(200,response.getStatusCode());
        response.then().assertThat().statusCode(200)
                .contentType("text/html; charset=utf-8")
                .statusLine("HTTP/1.1 200 OK");


        //1.yol
        JsonPath jsonPath=response.jsonPath();
        List<String> HM=response.htmlPath().getList("brands.findAll{it.brand=='H&M'}.id");
        System.out.println("HM = " + HM);

        List <String> Polo=response.htmlPath().getList("brands.findAll{it.brand=='Polo'}");
        System.out.println("Polo = " + Polo);

        assertEquals(HM.size(),Polo.size());





        //2.yol
        List<String> brandlist = response.htmlPath().getList("brands.brand");
        int numOfHM = 0;
        int numOfPolo = 0;
        for (String w : brandlist) {
            if (w.equals("H&M")) {
                numOfHM++;
            }
            if (w.equals("Polo")) {
                numOfPolo++;

                assertEquals(numOfHM, numOfPolo);




                //3.yol
                long hmCount = response.htmlPath().getList("brands.brand").stream().filter(t -> t.toString().equals("H&M")).count();
                long poloCount=response.htmlPath().getList("brands.brand").stream().filter(t -> t.toString().equals("Polo")).count();
                assertEquals(hmCount,poloCount);

            }

        }
    }
}