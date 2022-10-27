/** EmployeeContact.java
 * Entity class for EmployeeContact
 * Author: Daniella Burgess (219446482)
 * Date: 29 September 2022
 */

package za.ac.cput.domain;


import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;


@Entity
public class EmployeeContact implements Serializable {


    @Id
    private String  staffId;

    @Embedded
    private Contact contact;

    public Contact getContact()
    {
        return contact;
    }

    protected EmployeeContact() {}

    EmployeeContact(Builder builder){
        this.staffId = builder.staffId;
        this.contact = builder.contact;
    }

    public String getStaffId() {
        return staffId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EmployeeContact that = (EmployeeContact) o;
        return staffId.equals(that.staffId) && contact.equals(that.contact);
    }

    @Override
    public int hashCode() {
        return Objects.hash(staffId, contact);
    }

    @Override
    public String toString() {
        return "EmployeeContact{" + "staffId=" + staffId + ", contact=" + contact + '}';
    }


 
    public static class Builder {
        private String staffId;
        private Contact contact;

        public Builder setStaffId(String staffId) {
            this.staffId = staffId;
            return this;
        }

        public Builder setContact(Contact contact){
            this.contact = contact;
            return this;
        }


        public Builder copy(EmployeeContact employeecontact) {
            this.staffId = employeecontact.staffId;
            this.contact = employeecontact.contact;
            return this;
        }

        public EmployeeContact build() {
            return new EmployeeContact(this);
        }
    }
   }



