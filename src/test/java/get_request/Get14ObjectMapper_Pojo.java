package get_request;

import base_url.JsonPlaceHolderBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;
import pojos.JsonPlaceHolderPojo;
import utils.ObjectMapperUtils;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class Get14ObjectMapper_Pojo extends JsonPlaceHolderBaseUrl {

    /*
        Given
	        https://jsonplaceholder.typicode.com/todos/198
        When
	 		I send GET Request to the URL
	 	Then
	 		Status code is 200
	 		And response body is like {
									    "userId": 10,
									    "id": 198,
									    "title": "quis eius est sint explicabo",
									    "completed": true
									  }
     */

//objectMapper + pojo = en iyi yontem

    @Test
    public void test01() {

        //set the url
        spec.pathParams("first","todos","second",198);

        //set the expected data
        JsonPlaceHolderPojo expectedData = new JsonPlaceHolderPojo(10,"quis eius est sint explicabo",true);



        //send the request and get the response
        Response response= given().spec(spec).when().get("/{first}/{second}");


        //do assertion
       JsonPlaceHolderPojo actulaData= ObjectMapperUtils.convertJsonToJava(response.asString(),JsonPlaceHolderPojo.class);

        assertEquals(200, response.statusCode());
        assertEquals(expectedData.getUserId(),actulaData.getUserId());
        assertEquals(expectedData.getTitle(),actulaData.getTitle());
        assertEquals(expectedData.getCompleted(),actulaData.getCompleted());

    }
}
