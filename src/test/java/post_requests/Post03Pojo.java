package post_requests;

import base_url.JsonPlaceHolderBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;
import pojos.JsonPlaceHolderPojo;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class Post03Pojo extends JsonPlaceHolderBaseUrl {
    /*
         Given
            https://jsonplaceholder.typicode.com/todos
            {
            "userId": 55,                                   //post islemi oldugu inic id verilmez sistem veriir
            "title": "Tidy your room",
            "completed": false
            }
        When
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
    public void test01() {
        //set the url
        spec.pathParam("first","todos");

        //set the expecdet data
        JsonPlaceHolderPojo expectedData= new JsonPlaceHolderPojo(155,"Tidy your room",false);
        System.out.println("expectedData = " + expectedData);

        //send the request get the response
        Response response=given().spec(spec).contentType(ContentType.JSON).body(expectedData).when().post("/{first}");
        response.prettyPrint();

        //do Assertion
       JsonPlaceHolderPojo actualData= response.as(JsonPlaceHolderPojo.class);    //jsoni pojo classa donecek as() methodu ile
        System.out.println("actualData = " + actualData);

        assertEquals(201,response.statusCode());
        assertEquals(expectedData.getUserId(),actualData.getUserId());
        assertEquals(expectedData.getTitle(),actualData.getTitle());
        assertEquals(expectedData.getCompleted(),actualData.getCompleted());


    }
}
//expected data ile actula data tipi ayni olamasi gerekir assertion islemi yapabilmek icin