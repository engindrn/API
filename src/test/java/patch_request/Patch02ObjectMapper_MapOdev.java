package patch_request;

import base_url.ReqresBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;
import test_data.ReqresTestData;
import utils.ObjectMapperUtils;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class Patch02ObjectMapper_MapOdev extends ReqresBaseUrl {

    //4: Map ile  Object Mapper kullanarak yapınız.
  /*
       Given
           1) https://reqres.in/api/users/2
           2) {
                "name": "neo"
               }
       When
            I send PATCH Request to the Url
       Then
             Status code is 200
             And response body is like   {
                                                "name": "neo",
                                                "updatedAt": "2022-10-02T12:53:21.675Z"
                                         }
    */

    @Test
    public void test01() {
        //set the url
        spec.pathParams("first","users","second",2);
        
        //set the expected data
        ReqresTestData obj =new ReqresTestData();
        Map<String,String> ecpectedData= obj.expectedDataMethod("ali",null);
        System.out.println("ecpectedData = " + ecpectedData);

        //send the request get the response
         Response response=given().spec(spec).contentType(ContentType.JSON).body(ecpectedData).when().patch("/{first}/{second}");
        response.prettyPrint();

        //do assertion
        Map actulData=ObjectMapperUtils.convertJsonToJava(response.asString(),Map.class);
        System.out.println("actualDta = " + actulData);

        assertEquals(200,response.getStatusCode()) ;
        assertEquals(ecpectedData.get("name"),actulData.get("name"));

    }
}
