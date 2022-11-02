package get_request;

import base_url.ReqresBaseUrl;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.List;

import static io.restassured.RestAssured.given;
import static junit.framework.TestCase.assertEquals;

public class Get06b extends ReqresBaseUrl {
    /*
   Given
          https://reqres.in/api/unknown/
   When
        I send GET Request to the URL
   Then

        1)Status code is 200
        2)Print all pantone_values                              //çalışma amaçlı genelde böyle bitşey olmaz
        3)Print all ids greater than 3 on the console
          Assert that there are 3 ids greater than 3
        4)Print all names whose ids are less than 3 on the console
          Assert that the number of names whose ids are less than 3 is 2
*/

    @Test
    public void test01() {
        //Set the Url
        spec.pathParam("first","unknown");

        //Set The Expected Data

        //Send The Request and Get The Response
        Response response=given().spec(spec).when().get("/{first}");
        response.prettyPrint();

        //Do Assertion

        //1)Status code is 200
        assertEquals(200,response.getStatusCode());

        //2)Print all pantone_values
        JsonPath jsonPath=response.jsonPath();   //response u jsonpath e dönusturuyoruz boylece datalar jsonPath e geciyor

        jsonPath.getList("data.pantone_value");     //jsonPath ı liste dönüştürdükartık list olarak islem yapabiliriz.
                                                            //list methodlarını kullanabiliriz
        System.out.println(jsonPath.getList("data.pantone_value")); //bu isimdeki butun datalar
        System.out.println(jsonPath.getList("data.pantone_value").size());//kac tane ogrendik
        System.out.println(jsonPath.getList("data.pantone_value").get(0));//ilk elemani aldik


       // 3)Print all ids greater than 3 on the console
        jsonPath.getList("data.id");                               //bu tum id leri verir
        System.out.println(jsonPath.getList("data.id"));

         List<Integer>ids=jsonPath.getList("data.findAll{it.id>3}.id");   //grovy language ile listteki istedigimiz filtrelemeleri yaptik
        System.out.println(ids);                             //istedigimizi sona yazdik .id

        // Assert that there are 3 ids greater than 3
        assertEquals(3,ids.size());

       //4)Print all names whose ids are less than 3 on the console
        List<String>names=jsonPath.getList("data.findAll{it.id<3}.name");   //id leri 3 den kücük olanları namelerini aldık
        System.out.println(names);

       //Assert that the number of names whose ids are less than 3 is 2
        assertEquals(2,names.size());



    }
}


//    List<Integer> idler =jsonPath.getList("data.id");
//
// for (int i = 0; i < idler.size(); i++) {
//        if (idler.get(i)>3){
//        System.out.println(i+ ".id:" +idler.get(i));
//        }
//        }


//    List<Integer> list=json.getList("data.pantone_value");
//  for (int i = 0; i <list.size() ; i++) {
//        System.out.println((i+1)+". data : "+list.get(i));
//        }