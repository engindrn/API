package get_request;

import base_url.GoRestBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;
import test_data.GoRestTestData;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class Get10 extends GoRestBaseUrl {


    /*
   Given
       https://gorest.co.in/public/v1/users/2986
   When
       User send GET Request to the URL
   Then
       Status Code should be 200
   And
       Response body should be like
   {
    "meta": null,
    "data": {
        "id": 2986,
        "name": "Bhaumik Jha",
        "email": "jha_bhaumik@schinner-moore.info",
        "gender": "male",
        "status": "inactive"
    }
}
*/

    @Test
    public void get10() {
        spec.pathParams("first", "users", "second", 2986);

        GoRestTestData obj = new GoRestTestData();  //test data package deki classimizdan objemizi olusturduk. obj ile methoda ulastik parametreleri gonderdik
        Map<String, String> dataKeyMap = obj.dataKeyMap("Bhaumik Jha", "jha_bhaumik@schinner-moore.info", "male", "inactive");
        Map<String, Object> expectedData = obj.expectedDataMethod(null, dataKeyMap);
        System.out.println(expectedData);

        Response response = given().spec(spec).when().get("/{first}/{second}");
        response.prettyPrint();

        Map<String, Object> actualData = response.as(HashMap.class);  //json datayi map donusturduk
        System.out.println("actualData = " + actualData);

        assertEquals(expectedData.get("meta"), actualData.get("meta"));
        assertEquals(dataKeyMap.get("name"), ((Map) actualData.get("data")).get("name"));
        assertEquals(dataKeyMap.get("email"), ((Map) actualData.get("data")).get("email"));
        assertEquals(dataKeyMap.get("gender"), ((Map) actualData.get("data")).get("gender"));
        assertEquals(dataKeyMap.get("status"), ((Map) actualData.get("data")).get("status"));
        assertEquals(200, response.statusCode());
    }
}



  //  JsonPath jsonPath=response.jsonPath();

  //  assertEquals(null,jsonPath.getString("meta"));
  //      assertEquals(2986,jsonPath.getInt("data.id"));
  //      assertEquals("Navin Talwar",jsonPath.getString("data.name"));
  //      assertEquals("navin_talwar@mclaughlin.name",jsonPath.getString("data.email"));
  //      assertEquals("inactive",jsonPath.getString("data.status"));