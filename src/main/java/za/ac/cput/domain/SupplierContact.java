/*SupplierContact.java
 * Domain class for SupplierContact
 * Author: Ilyaas Davids (219466242)
 * Date: 11 Oct 2022
 */
package za.ac.cput.domain;


import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class SupplierContact implements Serializable {
    @Id
    private String suppId;
    
    private Contact contact;

    //Getters
    public String getSuppId() {
        return suppId;
    }

    public Contact getContact() {
        return contact;
    }
    
    

    @Override
    public String toString() {
        return "SupplierContact{" +
                "suppId='" + suppId + '\'' +
                ", contact=" + contact +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SupplierContact that = (SupplierContact) o;
        return suppId.equals(that.suppId) && contact.equals(that.contact);
    }

    @Override
    public int hashCode() {
        return Objects.hash(suppId, contact);
    }

    //Builder Constructor
    protected SupplierContact(){}

    private  SupplierContact (SupplierContact.Builder builder){
        this.suppId = builder.suppId;
        this.contact = builder.contact;
    }

    //Builder classes
    public static class Builder {
        private String suppId;
        private Contact contact;

        public SupplierContact.Builder setSuppId(String suppId) {
            this.suppId=suppId;
            return this;
        }

        public SupplierContact.Builder setContact(Contact contact) {
            this.contact=contact;
            return this;
        }

        public SupplierContact.Builder copy(SupplierContact supplierContact)
        {
            this.suppId = supplierContact.suppId;
            this.contact = supplierContact.contact;
            return this;
        }
        public SupplierContact build(){ return new SupplierContact(this);}
    }
}
