package get_request;

import base_url.GoRestBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.List;

import static io.restassured.RestAssured.given;
import static junit.framework.TestCase.assertTrue;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.core.IsEqual.equalTo;


public class Get11 extends GoRestBaseUrl {

    /*
    Given
        https://gorest.co.in/public/v1/users
    When
        User send GET Request    //Kullanıcı GET İsteği gönderir
    Then
        The value of "pagination limit" is 10  //"Sayfalandırma sınırı" değeri 10'dur
    And
        The "current link" should be "https://gorest.co.in/public/v1/users?page=1"  //"Geçerli bağlantı", "https://gorest.co.in/public/v1/users?page=1" olmalıdır
    And
        The number of users should  be 10  //Kullanıcı sayısı 10 olmalıdır
    And
        We have at least one "active" status  // En az bir "etkin" durumumuz vardır
    And
       "Swami Asan", "Shresthi Kapoor" ,"Bandhu Guha" are among the users   //Kullanicilar arasında  Niranjan Gupta, Samir Namboothiri ve Gandharva Kaul olmali
    And
        The female users are less than or equals to male users   //Kadın kullanıcılar erkek kullanıcılara eşit veya daha az olmali
 */

    @Test
    public void test01() {
        //1. Set The URL
        spec.pathParam("first","users");

        // 2. Set The Expected Data ( put, post, patch)

        // 3. Send The Request And Get The Response
        Response response=given().spec(spec).when().get("/{first}");
        response.prettyPrint();

        // 4. Do Assertion
        response.then().assertThat()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("meta.pagination.limit",equalTo(10),
                         "meta.pagination.links.current",equalTo("https://gorest.co.in/public/v1/users?page=1"),
                        "data",hasSize(10),"data.status",hasItem("active"),
                        "data.name",hasItems("Swami Asan", "Shresthi Kapoor" ,"Bandhu Guha"));

        /*The female users are less than or equals to male users*/
        //burda dataya ulasmak icin jsonpath kullanmamiz gerekir
        //1.yol
       /* JsonPath json =response.jsonPath(); *///buna gerek kalmadan da response. diyip jsonpath ile islem yapabiliriz
        List<String> genders =response.jsonPath().getList("data.gender"); //önce cinsiyetleri aldik direk liste koyabiliriz

       int numFemale=0;
        for (String w:genders){
            if (w.equalsIgnoreCase("female")){
                numFemale++;
            }
        }
        assertTrue(numFemale<=genders.size()-numFemale);

        //2:YOL
        //kadin ve erkek sayilarini Groovy ile bulalim

       List<String>femaleNames= response.jsonPath().getList("data.findAll{it.gender=='female'}.name");  //string data groovy de tek tirnak olr
        System.out.println("femaleNames = " + femaleNames);

        List <String>maleNames= response.jsonPath().getList("data.findAll{it.gender=='male'}.name");
        System.out.println("maleNames = " + maleNames);

        assertTrue(femaleNames.size()<=maleNames.size());
    }
}




 //   JsonPath json=response.jsonPath();
//Assert.assertTrue(json.getList("data.findAll{it.gender='female'}").size()<=json.getList("data.findAll{it.gender='male'}").size());

/*for (String w:genders) {
        if(w.equals("female")){
            female++;
        }else male++;

    }
    System.out.println("female = " + female);
    System.out.println("male = " + male);


}*/

/*List<String> femaleNumbers= response.jsonPath().getList("data.findAll{it.gender=='female'}.gender"); //list icindeki gender= female olanları al dedik
System.out.println("femaleNumbers = " + femaleNumbers);

List<String> femaleNames= response.jsonPath().getList("data.findAll{it.gender=='female'}.name"); //list icindeki gender= female olanların isimlerini al dedik
System.out.println("femaleNames = " + femaleNames);

List<String> maleNames= response.jsonPath().getList("data.findAll{it.gender=='male'}.name"); //list icindeki gender= male olanların isimlerini al dedik
System.out.println("maleNames = " + maleNames);

List<String> maleNumbers= response.jsonPath().getList("data.findAll{it.gender=='male'}.gender"); //list icindeki gender= male olanları al dedik
System.out.println("maleNumbers = " + maleNumbers);*/