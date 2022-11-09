package put_request;

import base_url.ReqresBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;
import pojos.ReqresPojo;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class Put03PojoOdev extends ReqresBaseUrl {
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
*///3: Pojo Class ile  Gson kullanarak yapınız.


    @Test
    public void terst() {
        //set the url
        spec.pathParams("first","users","second",2);

        //set the expected data
        ReqresPojo expectedData =new ReqresPojo("morpheus","zion president");
        System.out.println("expectedData = " + expectedData);

        //send the request ghet the response
       Response response= given().spec(spec).contentType(ContentType.JSON).body(expectedData).when().put("/{first}/{second}");
        response.prettyPrint();

        //do assrtion
        ReqresPojo actualData=response.as(ReqresPojo.class);    //gson
        System.out.println("actualData = " + actualData);

        assertEquals(200,response.getStatusCode());
        assertEquals(expectedData.getName(),actualData.getName());
        assertEquals(expectedData.getJob(),actualData.getJob());
    }
}
