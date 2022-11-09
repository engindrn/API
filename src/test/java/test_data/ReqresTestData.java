package test_data;

import java.util.HashMap;
import java.util.Map;

public class ReqresTestData {

    public Map<String,String> expectedDataMethod (String name,String job){

        Map<String,String> expectedData = new HashMap<>();

        if(name!=null){
            expectedData.put("name",name);
        }
        if (job!=null){
            expectedData.put("job",job);
        }

        return expectedData;

    }

}



     //   {
     //    "name": "morpheus",
     //    "job": "leader"
     //    }