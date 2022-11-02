package get_request;

import base_url.ReqresBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.*;

public class Get02b extends ReqresBaseUrl {

     /*
   Given
       https://reqres.in/api/users/23
   When
       User send a GET Request to the url
   Then
       HTTP Status code should be 404
   And
       Status Line should be HTTP/1.1 404 Not Found
   And
       Server is "cloudflare"
   And
       Response body should be empty

*/

    @Test
    public void get01() {
       //Set the Url
        spec.pathParams("first","users","second",23);

        //Set The Expected Data

        //Send The Request and Get The Response
        Response response = given().spec(spec).when().get("/{first}/{second}");
        response.prettyPrint();//bos
        System.out.println(response.getStatusCode());//404

        //Do Assertion
        assertEquals(404,response.statusCode());    //getStatusCode() methoduyla status codunu aldık assertion yaptık
        assertEquals("HTTP/1.1 404 Not Found",response.statusLine());
        assertEquals("cloudflare",response.getHeader("Server")); //headerdaki sever a gittik aldik ismini
                                                                       //getheaders dersek oradaki butun bilgileri aliriz


     //   assertEquals(0,response.asString().length()); //kontrol ettik 8 karakter var bady bos degil
        assertEquals(2,response.asString().replaceAll("\\s","").length());
        //2 parantezler icin biraktik kalan bosluklari sildik




    }
}
/*response umuzu asString() methoduyla string olarak alıp string manipulationdan faydalanabiliriz.*/