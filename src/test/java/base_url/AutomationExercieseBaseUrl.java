package base_url;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import org.junit.Before;

public class AutomationExercieseBaseUrl {

    protected RequestSpecification spec;      //instance olarak bir variable olusturup prorected yaptik
                                                //atamasini asgida yaptik
                                            //java oop concept kullanip (inheritance) bu class i test classimiza
                                            //extend edip burdan base url imizi alcagiz#
    @Before                //junutten @before anatationnu nu kullandik
    public void setup(){
        spec= new RequestSpecBuilder().setBaseUri("https://automationexercise.com").build();
    }
}
