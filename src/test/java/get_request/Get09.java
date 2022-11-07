package get_request;

import base_url.RestfulBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class Get09 extends RestfulBaseUrl {
    /*
    Given
        https://restful-booker.herokuapp.com/booking/91
    When
        I send GET Request to the url
    Then
        Response body should be like that;
           {
    "firstname": "Howard",
    "lastname": "Liu",
    "totalprice": 111,
    "depositpaid": true,
    "bookingdates": {
  a-      "checkin": "2018-01-01",
  b-      "checkout": "2019-01-01"
    },
    "additionalneeds": "Breakfast"
}
 */  //json icinde farkli json data var bizde map icinde inner map yapacagiz expected data olrak
    //iki farkli map olusturmaliyiz bookkingdates in degerleri a ve b olacak sekilde bir daha map olmali


    @Test
    public void get09(){

//Set the Url
        spec.pathParams("first", "booking","second", 91);

//Set The Expected Data
        /*ilk olarak innerdan baslamak gerekir daha sonra bu inner map i diger map imize
        bookingdates in value si olarak koyacaz bu yuzden diger olusturacagimiz map in data type inin value kismini object yaptik*/
        Map<String,String> bookingdatesMap = new HashMap<>();      //key ve value Stringlerden olustugu icinp<String,String>
        bookingdatesMap.put("checkin","2018-01-01");
        bookingdatesMap.put("checkout","2019-01-01");

        Map<String,Object> expectedData = new HashMap<>();

        expectedData.put("firstname","Howard");          //value lar farkli data turlerinde bu yuzden object yaptik
        expectedData.put("lastname","Liu");
        expectedData.put("totalprice",111);
        expectedData.put("depositpaid",true);
        expectedData.put("bookingdates",bookingdatesMap);   //burda value olrak yukardaki inner map imizi koyduk
        expectedData.put("additionalneeds","Breakfast");
        System.out.println(expectedData);

//Send The Request and Get The Response
        Response response = given().spec(spec).when().get("/{first}/{second}");
        response.prettyPrint();

//Do Assertion
        /* assertion islemini yapabilmemiy icin json data type inda aldigimiz response mizi yukarda olusturdugumuz
         map icerindeki expected datamizla karsilastirabilmek icin DE-SERILIZATION YAPTIK
         Json datayi java objesine cevirdik*/
        Map<String,Object> actualData = response.as(HashMap.class);
        assertEquals(expectedData.get("firstname"),actualData.get("firstname"));
        assertEquals(expectedData.get("lastname"),actualData.get("lastname"));
        assertEquals(expectedData.get("totalprice"),actualData.get("totalprice"));
        assertEquals(expectedData.get("depositpaid"),actualData.get("depositpaid"));
        assertEquals(bookingdatesMap.get("checkin"), ((Map)(actualData.get("bookingdates"))).get("checkin"));
        //Key-Value ikilileri String-Object şeklinde olduğundan, Bookingdata value kısmını casting ile Map yaptık.
        assertEquals(bookingdatesMap.get("checkout"), ((Map)(actualData.get("bookingdates"))).get("checkout"));


    }
}

/*normalde get methodu icin boyle yapmaya gerek yok body() methodu veya JsonPath ile biz bu bilgileri assertion
yapabiliriz. asil bu sekilde post ve put islemlerde ihtiyac olacak
 */