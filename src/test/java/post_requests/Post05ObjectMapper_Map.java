package post_requests;

import base_url.JsonPlaceHolderBaseUrl;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.codehaus.jackson.map.ObjectMapper;
import org.junit.Test;
import test_data.JsonPlaceHolderTestData;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class Post05ObjectMapper_Map extends JsonPlaceHolderBaseUrl {
     /*
         Given
           1) https://jsonplaceholder.typicode.com/todos
           2) {
                 "userId": 55,
                 "title": "Tidy your room",
                 "completed": false
               }


            I send POST Request to the Url
        Then
            Status code is 201
        And
            response body is like {
                                    "userId": 55,
                                    "title": "Tidy your room",
                                    "completed": false,
                                    "id": 201
                                    }
     */

    @Test
    public void post05ObjectMapper() throws IOException {
        //Set the Url
        spec.pathParam("first","todos");

        //Set the Expected Data

    //   String jsonInString = "{\n" +
    //           "                                    \"userId\": 55,\n" +
    //           "                                    \"title\": \"Tidy your room\",\n" +
    //           "                                    \"completed\": false,\n" +
    //           "                                    \"id\": 201\n" +
    //           "                                    }";

        JsonPlaceHolderTestData obj=new JsonPlaceHolderTestData();
       String jsonInString= obj.expectedDataInString(55,"Tidy your room",false); //bu bize string bir data donecek

        HashMap expectedData =   new ObjectMapper().readValue(jsonInString, HashMap.class); //strıng datayi istedigimiz fortmata cevirir readValue()
        System.out.println("expectedData = " + expectedData);                            //her defa excption olasin ve goruntu olarak burda olmasin diye utils da bir method ile yapacaz

        //Send the Request and Get the Response
        Response response= given().spec(spec).contentType(ContentType.JSON).body(expectedData).when().post("/{first}");
        response.prettyPrint();

        //do assertion
        HashMap actualData= new ObjectMapper().readValue(response.asString(), HashMap.class); //objeMapper olusturup response string olarak alip map e attik
        System.out.println("actualData = " + actualData);

        assertEquals(201,response.statusCode());
        assertEquals(expectedData.get("completed"),expectedData.get("completed"));
        assertEquals(expectedData.get("title"),expectedData.get("title"));
        assertEquals(expectedData.get("userId"),expectedData.get("userId"));





    }
}