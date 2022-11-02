package get_request;

import base_url.JsonPlaceHolderBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;
import test_data.JsonPlaceHolderTestData;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.request;
import static org.junit.Assert.assertEquals;

public class Get08 extends JsonPlaceHolderBaseUrl {

    //De-Serialization: Json datayı Java objesine çevirme
    //Serialization: Java objesini Json formatına çevirme.
    //De-Serialization: Iki türlü yapacağız.
    //Gson: Google tarafından üretilmiştir.
    //Object Mapper: Daha popüler...



    /*
    Given
       https://jsonplaceholder.typicode.com/todos/2
   When
       I send GET Request to the URL
   Then
       Status code is 200
       And "completed" is false
       And "userId" is 1
       And "title" is "quis ut nam facilis et officia qui"
       And header "Via" is "1.1 vegur"
       And header "Server" is "cloudflare"
       {
           "userId": 1,                                  //KARŞIDAN GELEN DATANIN BUNA BENZEDİĞİNİ ASSERT ETMEMİZ
           "id": 2,                                         //İSTENİYOR BUNUN İÇİN BUNA BENZER DATA OLUŞTYRUP (SET THE EXPECTED DATA)
           "title": "quis ut nam facilis et officia qui",     //ASSERTİON YAPACAĞIZ
           "completed": false
       }
*/

    @Test
    public void test01() {
        //Set the Url
        spec.pathParams("first", "todos", "second", 2);

        //Set The Expected Data==>payload da denir

        Map<String, Object> expectedData = new HashMap<>(); //hashmap hizlidir sira onemli degildi-
        /*KEY LER HEP STRİNG OLD İÇİN STRİNG VALUE LER KARIŞIK DATA TYPE OLDUKLARI İÇİN OBJECT TAPTIK
        /*object her turlu datayi icine alir burda farkli datalar var value olrak ama kisitlidir yavastir manipulation zordur*/

        expectedData.put("userId", 1);
        expectedData.put("id", 2);
        expectedData.put("title", "quis ut nam facilis et officia qui");
        expectedData.put("completed", false);
        System.out.println("ExpectedData " + expectedData);

        //Send The Request and Get The Response
        Response response = given().spec(spec).when().get("/{first}/{second}");
        response.prettyPrint();

        //Do Assertion
        Map<String, Object> actualData = response.as(HashMap.class); //De-Serialization  json i map olarak cevirdik (as ile) MAP E KOYDUK
        System.out.println("actualData " + actualData);                              //ASSRTİON YAPABİLMEK İÇİNDE GELEN JSON DATAYI MAP E ÇEVİRDK

        assertEquals(expectedData.get("userId"), actualData.get("userId"));
        assertEquals(expectedData.get("id"), actualData.get("id"));
        assertEquals(expectedData.get("title"), actualData.get("title"));
        assertEquals(expectedData.get("completed"), actualData.get("completed"));

        assertEquals("1.1 vegur", response.header("Via"));
        assertEquals("cloudflare", response.header("Server"));
        assertEquals(200, response.statusCode());


        /*bu sekilde hepsini ayni anda yapmadik cunki hangisi eslesmedi gormek icin*/
//for (int i = 0; i < actualData.size(); i++) {
//        assertEquals(expextedData.get(i),actualData.get(i));
//        }



        /*Alternatif ,yanlissa value dan anlayabiliyorsunuz*/
//    SoftAssert softAssert = new SoftAssert();
//for (int i = 0; i < actualData.size(); i++) {
//        softAssert.assertEquals(expextedData.get(i), actualData.get(i));
//        }
//        softAssert.assertAll();

    }





    //Dinamik yöntem
    @Test
    public void get08b() {

//Set the Url
        spec.pathParams("first", "todos", "second", 2);

//Set The Expected Data ==> Payload
        JsonPlaceHolderTestData objJsonPlcHldr = new JsonPlaceHolderTestData();   //TEST DATAMIZDAN BİR ONJE OLUŞTURDUK

        Map<String, Object> expectedData = objJsonPlcHldr.expectedDataMethod(1, "quis ut nam facilis et officia qui", false);
      /*TEST DATA PACKAGEDE BULUNAN JSONPLACAHOLDERTESTDTA CLASSINDAKİ METHODUMUZA ULAŞMAK İÇİN ÖNCE İKİ ÜST SATIRDA O CLASSDAN
      BİR OBJE OLUŞTURDUK SONRA OBJE İSMİMİZİN İSMİNİ YAZIP O CLASSDAKİ METHODUMUZA ULAŞTIK BİZE BİR MAP DÖNECEĞİ İÇİN
      BURDADA MAP E KOYDUK. ARTIK YA BİZE VERİLEN JSON DATA İLE ASSERTİON YAPARIZ YADA POST YAPARIZ
       */
        System.out.println(expectedData);


//Send The Request and Get The Response
        Response response = given().spec(spec).when().get("/{first}/{second}");
        response.prettyPrint();

//Do Assertion
        Map<String, Object> actualData = response.as(HashMap.class);//De-Serialization
        System.out.println("actualData = " + actualData);
        assertEquals(expectedData.get("userId"), actualData.get("userId"));
        assertEquals(expectedData.get("title"), actualData.get("title"));
        assertEquals(expectedData.get("completed"), actualData.get("completed"));

        assertEquals("1.1 vegur", response.header("Via"));
        assertEquals("cloudflare", response.header("Server"));
        assertEquals(200, response.statusCode());


    }
}