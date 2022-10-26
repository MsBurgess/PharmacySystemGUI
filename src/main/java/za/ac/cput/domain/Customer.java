package za.ac.cput.domain;

import java.io.Serializable;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Customer implements Serializable {


   @Id
    private String customerId;
    private String gender;
    @Embedded
    private Name name;

    protected Customer(){}

    //builder constructor
    private  Customer (Builder builder){
        this.customerId = builder.customerId;
        this.gender = builder.gender;
        this.name = builder.name;
    }

    //getters
    public String getCustomerId(){
        return customerId;
    }
    public String getGender(){
        return gender;
    }
    public Name getName(){
        return name;
    }

    @Override
    public String toString(){
        return "Customer{" +
                "customerId='" + customerId + '\'' +
                ", gender='" + gender + '\'' + '}';
    }

    //builder class
    public static class Builder {
        private String customerId;
        private String gender;
        private Name name;

        public Builder customerId(String customerId) {
            this.customerId=customerId;
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

        public Builder copy(Customer customer)
        {
            this.customerId = customer.customerId;
            this.gender = customer.gender;
            this.name = customer.name;
            return this;
        }
        public Customer build(){ return new Customer(this);}
    }


}



