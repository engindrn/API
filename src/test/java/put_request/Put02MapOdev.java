package put_request;

import base_url.ReqresBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;
import test_data.ReqresTestData;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class Put02MapOdev extends ReqresBaseUrl {
    
    /*
        Given
            1) https://reqres.in/api/users/2
            2) {
                "name": "morpheus",
                "job": "zion president"
                }
        When
            I send Put Request to the Url
        Then
            Status code is 200
            And response body should be like {
                                                "name": "morpheus",
                                                "job": "zion president",
                                                "updatedAt": "2022-10-02T11:35:05.693Z"
                                            }
*/ //3: Map ile Gson kullanarak yapınız.

    @Test
    public void test01() {
        //set the Url
        spec.pathParams("first","users","second",2);

        //Set the expected data
        ReqresTestData obj= new ReqresTestData();
        Map<String, String> expectedData = obj.expectedDataMethod("ali","zion president");
        System.out.println("expectedData = " + expectedData);

        //send the request get the response
       Response response= given().spec(spec).contentType(ContentType.JSON).body(expectedData).when().put("/{first}/{second}");
        response.prettyPrint();

        //do assertion
        Map <String,String> actualData=response.as(HashMap.class);    //Gson
        System.out.println("actualData = " + actualData);

        assertEquals(200,response.getStatusCode());
       assertEquals(expectedData.get("name"),actualData.get("name"));
       assertEquals(expectedData.get("job"),actualData.get("job"));

    }
}
