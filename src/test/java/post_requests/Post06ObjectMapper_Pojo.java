package post_requests;

import base_url.DummyRestApiBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;
import pojos.DummyRestApiDataPojo;
import pojos.DummyRestApiResponseBodyPojo;
import utils.ObjectMapperUtils;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class Post06ObjectMapper_Pojo extends DummyRestApiBaseUrl {

    /*
      URL: https://dummy.restapiexample.com/api/v1/create
      HTTP Request Method: POST Request
      Request body:
                    {
                       "employee_name": "Tom Hanks",
                       "employee_salary": 111111,
                       "employee_age": 23,
                       "profile_image": "Perfect image",
                       "id": 4891
                    }

      Test Case: Type by using Gherkin Language
      Assert:

               i) Status code is 200
               ii) Response body should be like the following
                   {
                       "status": "success",
                       "data": {
                           "employee_name": "Tom Hanks",
                           "employee_salary": 111111,
                           "employee_age": 23,
                           "profile_image": "Perfect image",
                           "id": 4891
                       },
                       "message": "Successfully! Record has been added."
                   }
    */
    //*********************************************************************************************
    /*
    Given
        https://dummy.restapiexample.com/api/v1/create

    And    {
            "employee_name": "Tom Hanks",              //bunu gonderecez asagidaki gibi bir data donecek
            "employee_salary": 111111,
            "employee_age": 23,
            "profile_image": "Perfect image",
            "id": 4891                                   //postta id yi girmeye gerek yok sistem veriyor
          }
     When
        User sends POST request

     Then
        Status code is 200
     And
        Response body should be like the following
        {
            "status": "success",                    //karsidan gelcek json data tipi bu yuzden buna gore pojo
            "data": {                               //bir class daha olusturacaz responsemuzu bu class data tipi yapacaz
                "employee_name": "Tom Hanks",       //response nin kalibi buna uygun
                "employee_salary": 111111,
                "employee_age": 23,
                "profile_image": "Perfect image",
                "id": 4891
            },
            "message": "Successfully! Record has been added."
        }


     */
    @Test
    public void post06(){
        //Set the Url
        spec.pathParam("first","create");

        //Set the Expected Data
        DummyRestApiDataPojo expectedData = new DummyRestApiDataPojo("Tom Hanks",111111,23,"Perfect image");

        System.out.println("expectedData = " + expectedData);

        //Send the POST Request and Get the Response
        Response response = given().spec(spec).contentType(ContentType.JSON).body(expectedData).when().post("/{first}");
        response.prettyPrint();

      //  do assertion
        DummyRestApiResponseBodyPojo actualData= ObjectMapperUtils.convertJsonToJava(response.asString(),DummyRestApiResponseBodyPojo.class);
        System.out.println("actualData = " + actualData);

        assertEquals(200,response.statusCode());
        assertEquals(expectedData.getEmployee_name(),actualData.getData().getEmployee_name());
        assertEquals(expectedData.getEmployee_salary(),actualData.getData().getEmployee_salary());
        assertEquals(expectedData.getEmployee_age(),actualData.getData().getEmployee_age());
        assertEquals(expectedData.getProfile_image(),actualData.getData().getProfile_image());
        // assertEquals("Successfully! Record has been added.",actualData.getMessage()); ==> Hard Codding

    }
}