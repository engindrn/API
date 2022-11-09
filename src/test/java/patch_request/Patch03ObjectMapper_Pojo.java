package patch_request;

import base_url.ReqresBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;
import pojos.ReqresNamePojo;
import pojos.ReqresPojo;
import utils.ObjectMapperUtils;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class Patch03ObjectMapper_Pojo extends ReqresBaseUrl {

    //4:  Pojo Class ile  Object Mapper kullanarak yapınız.
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
        ReqresNamePojo expectedData =new ReqresNamePojo("neo");
        System.out.println("expectedData = " + expectedData);

        //send the request get the response
        Response response=given().spec(spec).contentType(ContentType.JSON).body(expectedData).when().patch("/{first}/{second}");
        response.prettyPrint();

        //do assertion
        ReqresNamePojo actualData= ObjectMapperUtils.convertJsonToJava(response.asString(),ReqresNamePojo.class);

            assertEquals(200,response.getStatusCode());
            assertEquals(expectedData.getName(),actualData.getName());

    }

}
