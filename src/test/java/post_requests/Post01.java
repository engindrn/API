package post_requests;

import base_url.JsonPlaceHolderBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;
import test_data.JsonPlaceHolderTestData;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class Post01 extends JsonPlaceHolderBaseUrl {
     /*
    Given
      1) https://jsonplaceholder.typicode.com/todos
      2)
   When
       I send POST Request to the Url
   Then
       Status code is 201
        {
            "userId": 55,
            "title": "Tidy your room",
            "completed": false
                         }
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

        //set the expected data
        JsonPlaceHolderTestData obj = new JsonPlaceHolderTestData(); //daha onceden olusturdugumuz testdata clasindaki methodumuzu kullanabilmek icin class isminde bir obje olusturduk
       //obj.expectedDataMethod(55,"Tidy your room",false); cagirdigimiz methodla map girmek istedigimiz parametrelerimizi girdik oda mape ekledi mapimizi olusturduk
        Map<String, Object> expectedData= obj.expectedDataMethod(55,"Tidy your room",false); //expexted data mizi kullanmak icin onu bir map e koyariz
        System.out.println("expectedData = " + expectedData);

        //send the request get the response
      Response response= given().spec(spec).contentType(ContentType.JSON).body(expectedData).post("/{first}");
      /*post isleminde contentype belirmek gerekir sonra bodye map olrk olusturdugumuz expected data yi koduk. posta parametremizi yazdik*/
      /*gelen datayi response koyduk*/
        response.prettyPrint();

        //do assertion
        /*bunun icin de-serialization yapmamz gerekir yani response olarak gelen datayi kullamabilmek icin yani map
         olark olusturdugumuz expected data ile assertion yapabilmek icin json olark donen response mizi map e cevirecez */
         Map <String,Object>actualData=  response.as(HashMap.class);   //mape koyduk
        System.out.println("actualData = " + actualData);

        assertEquals(expectedData.get("completed"),actualData.get("completed"));
        assertEquals(expectedData.get("title"),actualData.get("title"));
        assertEquals(expectedData.get("userId"),actualData.get("userId"));


    }


}
