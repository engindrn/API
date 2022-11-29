package get_request;

import base_url.RestfulBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;
import org.testng.asserts.SoftAssert;
import pojos.BookingDatesPojo;
import pojos.BookingPojo;
import utils.ObjectMapperUtils;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class Get15ObjectMapper_Pojo extends RestfulBaseUrl {
    /*
        Given
	            https://restful-booker.herokuapp.com/booking/22
        When
		 		I send GET Request to the URL
		Then
		 		Status code is 200
          {
                "firstname": "Guoqiang",
                "lastname": "Morante Briones",
                "totalprice": 111,
                "depositpaid": true,
                "bookingdates": {
                    "checkin": "2018-01-01",
                    "checkout": "2019-01-01"
                },
                "additionalneeds": "Breakfast"
       }
     */

    @Test
    public void test01() {
        //set the url
        spec.pathParams("first","booking","second",22);

        //set the expected data
        BookingDatesPojo bookingDatesPojo =new BookingDatesPojo ("2018-01-01","2019-01-01");
        BookingPojo expectedData= new BookingPojo("Guoqiang","Morante Briones",111,true,bookingDatesPojo,"Breakfast");
        System.out.println("expectedData = " + expectedData);
        
        //send the request get the response
       Response response= given().spec(spec).when().get("/{first}/{second}");
         response.prettyPrint();

        //Do Assertion
        BookingPojo actualData = ObjectMapperUtils.convertJsonToJava(response.asString(), BookingPojo.class);
        System.out.println("actualData = " + actualData);


        //soft Assertion
        //1. adim =softAssert objesi olustur
        SoftAssert softAssert=new SoftAssert();

        //2.adim:asserion yap
        softAssert.assertEquals(actualData.getFirstname(),expectedData.getFirstname(),"buraya mesaj yazilabilir");
        softAssert.assertEquals(actualData.getLastname(),expectedData.getLastname());
        softAssert.assertEquals(actualData.getDepositpaid(),expectedData.getDepositpaid());
        softAssert.assertEquals(actualData.getTotalprice(),expectedData.getTotalprice());
        softAssert.assertEquals(actualData.getAdditionalneeds(),expectedData.getAdditionalneeds());

        softAssert.assertEquals(actualData.getBookingdates().getCheckin(),bookingDatesPojo.getCheckin());
        softAssert.assertEquals(actualData.getBookingdates().getCheckout(),bookingDatesPojo.getCheckout());

        //3.adim: assertAll() methodunun kullan
        softAssert.assertAll();


        //hard Assertion//
        assertEquals(200,response.getStatusCode());
        assertEquals(expectedData.getFirstname(),actualData.getFirstname());
        assertEquals(expectedData.getLastname(),actualData.getLastname());
        assertEquals(expectedData.getTotalprice(),actualData.getTotalprice());
        assertEquals(expectedData.getAdditionalneeds(),actualData.getAdditionalneeds());

        assertEquals(bookingDatesPojo.getCheckin(),actualData.getBookingdates().getCheckin());
        assertEquals(bookingDatesPojo.getCheckout(),actualData.getBookingdates().getCheckout());



       
       
       
    }
}
