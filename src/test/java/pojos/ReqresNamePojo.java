package pojos;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)     //bu anatation ile json datayi pojo class a cevirirken pojo
                                               // class ta ayni seviyedeki karsiligi olmayan json veri isleme alinmaz


public class ReqresNamePojo {

    private String name;

    public ReqresNamePojo(String name) {
        this.name = name;
    }

    public ReqresNamePojo() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "ReqresNamePojo{" +
                "name='" + name + '\'' +
                '}';
    }
}
