package put_request;

import base_url.JsonPlaceHolderBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;
import test_data.JsonPlaceHolderTestData;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class Put01 extends JsonPlaceHolderBaseUrl {

    /*
    Given
     1) https://jsonplaceholder.typicode.com/todos/198
     2) {
             "userId": 21,
             "title": "Wash the dishes",
             "completed": false
           }
    When
      I send PUT Request to the Url
   Then
      Status code is 200
      And response body is like
       {
             "userId": 21,
             "title": "Wash the dishes",
             "completed": false
           }
 */

    @Test
    public void test01() {
        //set the url
        spec.pathParams("first", "todos", "second", 198);

        //Set the expected data
        JsonPlaceHolderTestData obj = new JsonPlaceHolderTestData();
        Map<String, Object> expectedData = obj.expectedDataMethod(21, "Wash the dishes", false);
                                           //olusturup map e koyduk
        System.out.println("expectedData = " + expectedData);

        //send the request get the response
        Response response = given().spec(spec).contentType(ContentType.JSON).body(expectedData).when().put("/{first}/{second}");
                            //geleni response koyduk
        response.prettyPrint();

        //do assertion
        Map <String,Object> actualData= response.as(HashMap.class);
                                        //json responsumuzu de-serilization yaptik map e cevirdik
        System.out.println("actualData = " + actualData);

        assertEquals(expectedData.get("completed"),actualData.get("completed"));
        assertEquals(expectedData.get("title"),actualData.get("title"));
        assertEquals(expectedData.get("userId"),actualData.get("userId"));

    }
}