package get_request;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class Get01b {

    /*
   Given
       https://reqres.in/api/users/3               //bu base url i ve end pointleri swagger den aliriz(kullanma klavuzu)
   When
       User sends a GET Request to the url
   Then
       HTTP Status Code should be 200
   And
       Content Type should be JSON
   And
       Status Line should be HTTP/1.1 200 OK
*/

//junitten bir test anotationu olustururuz. method oluşturup testimizi yazarız
    @Test
    public void get01() {

        //ilk olarak request i gondermemiz gerekir
        //First Step:Set the Url
        String url="https://reqres.in/api/users/3";


        //Second Step:Set The Expected Data


        //Third Step:Send The Request and Get The Response
       Response response= given().when().get(url);    //bu bize json data formatında bir response dönecek bunu bir responce variable assigne ettik.
         response.prettyPrint();                     //response i yazirmamiza yarar


        //Fourt Step:Do Assertion

       // HTTP Status Code should be 200
       // Content Type should be JSON
       // Status Line should be HTTP/1.1 200 OK
       response.then().assertThat().                      //then ile assertionumuzu yaptik
               statusCode(200).
               contentType(ContentType.JSON).
               statusLine("HTTP/1.1 200 OK");



    }
}
//First Step:Set the Url
//Second Step:Set The Expected Data
//Third Step:Send The Request and Get The Response
//Fourt Step:Do Assertion