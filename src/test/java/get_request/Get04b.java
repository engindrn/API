package get_request;

import base_url.RestfulBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.*;

public class Get04b extends RestfulBaseUrl {

       /*
    Given
        https://restful-booker.herokuapp.com/booking?firstname=Blanca&lastname=Nieves
    When
        User sends get request to the URL
    Then
        Status code is 200
    And
        Among the data there should be someone whose firstname is "Blanca" and lastname is "Nieves"

 */

    @Test
    public void test01() {
        //Set the Url
        spec.pathParam("first","booking").queryParams("firstname","Blanca","lastname","Nieves");
        /**/


        //Set The Expected Data

        //Send The Request and Get The Response
        Response response=given().spec(spec).when().get("/{first}");
        response.prettyPrint();

        //Do Assertion
        assertEquals(200,response.getStatusCode());
        assertTrue(response.asString().contains("bookingid"));      //dönen bady de bookingid olmasi aranan kisi hakkinda
    }                                                              //bilgi oldugunu yani kisinin  var oldugunu gosterir
}
