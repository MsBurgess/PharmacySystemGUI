/**Contact.java
 * Domain class for Contact
 * Author: Daniella Burgess (219446482)
 * Date: 29 September 2022
 */

package za.ac.cput.domain;

import java.io.Serializable;
import java.util.Objects;


public class Contact implements Serializable {
    private String contactNumber;
    private String email;

    public Contact() {}

    private Contact(Builder builder) {
        this.contactNumber = builder.contactNumber;
        this.email = builder.email; }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Contact contact = (Contact) o;
        return contactNumber.equals(contact.contactNumber) && email.equals(contact.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(contactNumber, email);
    }

    @Override
    public String toString() {
        return "Contact{" +
                "contactNumber='" + contactNumber + '\'' +
                ", email='" + email + '\'' +
                '}';
    }

    public static class Builder {

        private String contactNumber;
        public String email;


        public Builder setContactNumber(String contactNumber) {
            this.contactNumber = contactNumber;
            return this;
        }

        public Builder setEmail(String email) {
            this.email = email;
            return this;
        }

        public Builder copy(Contact contact) {
            this.contactNumber = contact.contactNumber;
            this.email = contact.email;
            return this;
        }

        public Contact build() {
            return new Contact(this);
        }


    }
}
