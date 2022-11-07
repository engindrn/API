package get_request;

import base_url.GoRestBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;
import pojos.GoRestDataPojo;
import pojos.GoRestPojo;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class Get13Pojo extends GoRestBaseUrl {
    /*
        Given
            https://gorest.co.in/public/v1/users/2508
        When
            User send GET Request to the URL
        Then
            Status Code should be 200
        And
            Response body should be like
          {
            "meta": null,
            "data": {
                "id": 2508,
                "name": "Sharmila Deshpande VM",
                "email": "deshpande_sharmila_vm@becker.name",
                "gender": "female",
                "status": "active"
                 }
          }
    */

    @Test
    public void test01() {
        //set the url
        spec.pathParams("first","users","second",2508);

        //set the expected data
        //önce inner
        GoRestDataPojo goRestDataPojo= new GoRestDataPojo(2508,"Sharmila Deshpande VM","deshpande_sharmila_vm@becker.name","female","active");
        //sonra outor yani tamami
        GoRestPojo expectedData =new GoRestPojo(null,goRestDataPojo); //bi ustteki inner parametreli const. ismi
        System.out.println("expectedData = " + expectedData);

        //Send the request get the responce
      Response response =given().spec(spec).when().get("/{first}/{second}");
      response.prettyPrint();

      //Do Assertion
       GoRestPojo actualData= response.as(GoRestPojo.class);  //response u olusturdugumuz butun datalari girfdigimiz pojo class a cevirdik
        System.out.println("actualData = " + actualData);

        assertEquals(200,response.statusCode());
        assertEquals(expectedData.getMeta(),actualData.getMeta());
        assertEquals(goRestDataPojo.getId(),actualData.getData().getId());    //dataya gecip icerisindeki veriye ulatik
        assertEquals(goRestDataPojo.getName(),actualData.getData().getName());
        assertEquals(goRestDataPojo.getEmail(),actualData.getData().getEmail());
        assertEquals(goRestDataPojo.getGender(), actualData.getData().getGender());
        assertEquals(goRestDataPojo.getStatus(), actualData.getData().getStatus());


    }
    //data bolumundeki bilgileri expectedData dan almak yerine goRestDataPojo dan aldik ulasmasi daha kolay
}
