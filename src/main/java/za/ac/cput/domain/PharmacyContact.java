package za.ac.cput.domain;

/* Pharmacy.java
Domain for the Pharmacy
Author: Waseem Dollie (216040566)
Date: 10 October 2022 */




import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class PharmacyContact implements Serializable {


    @Id
    private String  pharmId;

    @Embedded
    private Contact contact;

    public Contact getContact()
    {
        return contact;
    }

    protected PharmacyContact() {}

    PharmacyContact(Builder builder){
        this.pharmId = builder.pharmId;
        this.contact = builder.contact;
    }

    public String getPharmId() {
        return pharmId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PharmacyContact that = (PharmacyContact) o;
        return pharmId.equals(that.pharmId) && contact.equals(that.contact);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pharmId, contact);
    }

    @Override
    public String toString() {
        return "PharmacyContact{" +
                "pharmId='" + pharmId + '\'' +
                ", contact=" + contact +
                '}';
    }

    public static class Builder {
        private String pharmId;
        private Contact contact;

        public PharmacyContact.Builder setPharmId(String pharmId) {
            this.pharmId = pharmId;
            return this;
        }

        public PharmacyContact.Builder setContact(Contact contact){
            this.contact = contact;
            return this;
        }


        public PharmacyContact.Builder copy(PharmacyContact pharmacyContact) {
            this.pharmId = pharmacyContact.pharmId;
            this.contact = pharmacyContact.contact;
            return this;
        }

        public PharmacyContact build() {return new PharmacyContact(this);}


    }


}

