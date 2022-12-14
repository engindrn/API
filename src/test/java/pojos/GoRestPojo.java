package pojos;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)//bu anatation ile json datayi pojo class a cevirirken pojo
                                          // class ta ayni seviyedeki karsiligi olmayan json veri isleme alinmaz


public class GoRestPojo {

    private Object meta;                //degeri null oldugu icin objecct yaptik
    private GoRestDataPojo data;       //olusturdugumuz inner pojo class data type oldu

    public GoRestPojo(Object meta, GoRestDataPojo data) {
        this.meta = meta;
        this.data = data;
    }

    public GoRestPojo() {
    }

    public Object getMeta() {
        return meta;
    }

    public void setMeta(Object meta) {
        this.meta = meta;
    }

    public GoRestDataPojo getData() {
        return data;
    }

    public void setData(GoRestDataPojo data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "GoRestPojo{" +
                "meta=" + meta +
                ", data=" + data +
                '}';
    }
}

// {
//     "meta": null,
//         "data": {
//               "id": 2508,
//               "name": "Sharmila Deshpande VM",
//              "email": "deshpande_sharmila_vm@becker.name",
//              "gender": "female",
//              "status": "active"
// }
// }