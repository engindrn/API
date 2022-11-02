package get_request;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class xxxx {
    /*
    Given
            https://restful-booker.herokuapp.com/booking/101
        When
            User sends a GET Request to the url
        Then
            HTTP Status Code should be 200
        And
            Content Type should be JSON
        And
            Status Line should be HTTP/1.1 200 OK
     */


    @Test
    public void test01() {

        //set the url
        String url= "https://restful-booker.herokuapp.com/booking/101";

        //Set the expected data

        //Send The Request and Get The Response
        Response response= given().when().get(url);
        response.prettyPrint();

        //do assertion
        response.then().assertThat().statusCode(200).contentType(ContentType.JSON).statusLine("HTTP/1.1 200 OK");


    }
}
/*
Given
    https://automationexercise.com/api/productsList
When
    User sends a GET Request to the url
Then
    HTTP Status Code should be 200
And
    Content Type should be "text/html; charset=utf-8"
And
    Status Line should be HTTP/1.1 200 OK
And
     There must be 12 Women, 9 Men, 13 Kids usertype in products
 jsonpath kullanınız */