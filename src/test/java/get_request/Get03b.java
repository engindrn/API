package get_request;

import base_url.ReqresBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertEquals;

public class Get03b extends ReqresBaseUrl {
    /*
   Given
       https://reqres.in/api/users/2
   When
       User send GET Request to the URL
   Then
       HTTP Status Code should be 200
   And
       Response format should be "application/json"
   And
       "email" is "janet.weaver@reqres.in",               **** bu aşamadan sonra body nın içine girnek zorundayız***
   And
       "first_name" is "Janet"
   And
       "last_name" is "Weaver"
   And
       "text" is "To keep ReqRes free, contributions towards server costs are appreciated!"
*/

    @Test
    public void test01() {
        //Set the Url
        spec.pathParams("first","users","second",2);

        //Set The Expected Data

        //Send The Request and Get The Response
        Response response=given().spec(spec).when().get("/{first}/{second}");

        //Do Assertion
        response.then().
                statusCode(200).
                contentType(ContentType.JSON).
                body("data.email",equalTo("janet.weaver@reqres.in"),       //body() bu metgod içerdeki bilgileri verir
                        "data.first_name",equalTo("Janet"),
                        "data.last_name",equalTo("Weaver"),
                "support.text",equalTo("To keep ReqRes free, contributions towards server costs are appreciated!"));

/*bu soruda json data formatında bady içerisinde iki ana data var biri data digeri support.
biz data bady sının içerisindeki verilere ulaşmak istediğimiz için onun adıyla başladık
 */
       //     {
       //     "data": {
       //               "id": 2,
       //               "email": "janet.weaver@reqres.in",
       //               "first_name": "Janet",
       //               "last_name": "Weaver",
       //               "avatar": "https://reqres.in/img/faces/2-image.jpg"
       //    },
       //       "support": {
       //               "url": "https://reqres.in/#support-heading",
       //               "text": "To keep ReqRes free, contributions towards server costs are appreciated!"
       //    }
       //    }

    }
}
