/* Employee.java
 Entity for the Employee
 Author: Zaeem Petersen (219010145)
 Date: 08 October 2022
*/

package za.ac.cput.domain;


import java.io.Serializable;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Employee implements Serializable {

    @Id
    private String staffId;
    private String gender;
    @Embedded
    private Name name;
    protected Employee(){}

    //builder constructor
    private  Employee (Builder builder){
        this.staffId = builder.staffId;
        this.gender = builder.gender;
        this.name = builder.name;
    }

    //getters
    public String getStaffId(){
        return staffId;
    }
    public String getGender(){
        return gender;
    }
    public Name getName(){
        return name;
    }

  public String toString() {
        return "Employee{" +
                "staffId='" + staffId + '\'' +
                ", gender='" + gender + '\'' +
                ", name=" + name +
                '}';
  }

    //builder class
    public static class Builder {
        private String staffId;
        private String gender;
        private Name name;

        public Builder staffId(String staffId) {
            this.staffId=staffId;
            return this;
        }

        public Builder gender(String gender) {
            this.gender=gender;
            return this;
        }

        public Builder name(Name name){
            this.name = name;
            return this;
        }

        public Builder copy(Employee employee)
        {
            this.staffId = employee.staffId;
            this.gender = employee.gender;
            this.name = employee.name;
            return this;
        }
        public Employee build(){ return new Employee(this);}
    }


}
