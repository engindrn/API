package pojos;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)//bu anatation ile json datayi pojo class a cevirirken pojo
                                          // class ta ayni seviyedeki karsiligi olmayan json veri isleme alinmaz

   /*     "employee_name": "Tom Hanks",
            "employee_salary": 111111,
            "employee_age": 23,
            "profile_image": "Perfect image",
            "id": 4891                                   //postta id yi girmeye gerek yok sistem veriyor
            */



public class DummyRestApiDataPojo {
    private String employee_name;
    private Integer employee_salary;
    private Integer employee_age;
    private String profile_image;


    public DummyRestApiDataPojo(String employeeName, Integer employeeSalary, Integer employeeAge, String profileImage) {
        this.employee_name = employeeName;
        this.employee_salary = employeeSalary;
        this.employee_age = employeeAge;
        this.profile_image = profileImage;
    }

    public DummyRestApiDataPojo() {
    }

    public String getEmployee_name() {
        return employee_name;
    }
    public void setEmployee_name(String employee_name) {
        this.employee_name = employee_name;
    }
    public Integer getEmployee_salary() {
        return employee_salary;
    }
    public void setEmployee_salary(Integer employee_salary) {
        this.employee_salary = employee_salary;
    }
    public Integer getEmployee_age() {
        return employee_age;
    }
    public void setEmployee_age(Integer employee_age) {
        this.employee_age = employee_age;
    }
    public String getProfile_image() {
        return profile_image;
    }
    public void setProfile_image(String profile_image) {
        this.profile_image = profile_image;
    }


    @Override
    public String toString() {
        return "DummyRestApiDataPojo{" +
                "employee_name='" + employee_name + '\'' +
                ", employee_salary=" + employee_salary +
                ", employee_age=" + employee_age +
                ", profile_image='" + profile_image + '\'' +
                '}';
    }
}


