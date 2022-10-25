/**
 * Author: Siphosethu Feni
 * Student Number: 217237614
 *  *  Group: 10
 * CustomerContact.java
 */

package za.ac.cput.domain;

import java.io.Serializable;



public class CustomerContact implements Serializable {


    private String customerId;

    private Contact contact;

   public Contact getContact() {return contact;}

    public CustomerContact(){}

    public CustomerContact(Builder builder){
        this.customerId = builder.customerId;
        this.contact = builder.contact;
    }

    public String getCustomerId(){return customerId;}

    public static class Builder{
        private String customerId;
        private Contact contact;

        public CustomerContact.Builder setCustomerId(String customerId){
            this.customerId = customerId;
            return this;
        }

        public CustomerContact.Builder setContact(Contact contact){
            this.contact = contact;
            return this;
        }

        public CustomerContact.Builder copy(CustomerContact customerContact){
            this.customerId = customerContact.customerId;
            this.contact = customerContact.contact;
            return this;
        }

        public CustomerContact build(){
            return new CustomerContact(this);
        }
    }

    @Override
    public String toString() {
        return "CustomerContact{" +
                ", customerId='" + customerId + '\'' +
                ", contactId='" + contact + '\'' +
                '}';
    }
}
