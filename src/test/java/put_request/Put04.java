package put_request;

import base_url.DummyRestApiBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;
import pojos.DummyRestApiDataPojo;
import pojos.DummyRestApiResponseBodyPojo;
import utils.ObjectMapperUtils;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class Put04 extends DummyRestApiBaseUrl {
    /*
        URL: https://dummy.restapiexample.com/api/v1/update/21
       HTTP Request Method: PUT Request
       Request body: {
                        "employee_name": "Ali Can",
                        "employee_salary": 111111,
                        "employee_age": 23,
                        "profile_image": "Perfect image"
                     }
       Test Case: Type by using Gherkin Language
       Assert:
                i) Status code is 200
                ii) Response body should be like the following
                    {
                        "status": "success",
                        "data": {
                            "employee_name": "Ali Can",
                            "employee_salary": 111111,
                            "employee_age": 23,
                            "profile_image": "Perfect image"
                        },
                        "message": "Successfully! Record has been updated."
                    }
     */
 /*Given
    URL: https://dummy.restapiexample.com/api/v1/update/21
    Request body: {
                    "employee_name": "Ali Can",           //sorgulamak icin boyle bi bady olusturmamiz gerekir
                    "employee_salary": 111111,
                    "employee_age": 23,
                    "profile_image": "Perfect image"
                  }
When
    PUT Request

Then
    i) Status code is 200
And
    ii) Response body should be like the following
                {
                    "status": "success",                      //karsidan donecek olan response bady e ugun boyle bir
                    "data": {                               //kalip outor bady olusturmamiz gerekir. burdaki bilgiler
                        "employee_name": "Ali Can",         //asset edilmeyecekse sadece outor bady i olusturdugumuz pojo
                        "employee_salary": 111111,          //class  response data tipi ve variable data type olarak kullanilir
                        "employee_age": 23,
                        "profile_image": "Perfect image"
                    },
                    "message": "Successfully! Record has been updated."
                }   */

    @Test
    public void test() {
        spec.pathParams("first","update","second",2);

        DummyRestApiDataPojo dummyRestApiDataPojo=new DummyRestApiDataPojo("Ali Can",111111,23,"Perfect image");
        DummyRestApiResponseBodyPojo expectedData =new DummyRestApiResponseBodyPojo("success",dummyRestApiDataPojo,"Successfully! Record has been updated.") ;
    //bunu gndermeye gerek yok bu outor badyi raspose data type olarak kullanacaz bide assert etmek icin gonderilebilir
        System.out.println("dummyRestApiDataPojo = " + expectedData);

        Response response = given().spec(spec).contentType(ContentType.JSON).body(dummyRestApiDataPojo).when().put("/{first}/{second}");
        response.prettyPrint();

        DummyRestApiResponseBodyPojo actualData = ObjectMapperUtils.convertJsonToJava(response.asString(),DummyRestApiResponseBodyPojo.class);
        System.out.println("actualData = " + actualData);

        assertEquals(200,response.statusCode());
        assertEquals(expectedData.getStatus(),actualData.getStatus());
        assertEquals(expectedData.getMessage(),actualData.getMessage());

        assertEquals(expectedData.getData().getEmployee_name(),actualData.getData().getEmployee_name());
        assertEquals(expectedData.getData().getEmployee_salary(),actualData.getData().getEmployee_salary());
        assertEquals(expectedData.getData().getEmployee_age(),actualData.getData().getEmployee_age());
        assertEquals(expectedData.getData().getProfile_image(),actualData.getData().getProfile_image());




    }
}
