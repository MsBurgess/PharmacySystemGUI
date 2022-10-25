/*SupplierContactFactory.java
 * Factory class for SupplierContact
 * Author: Ilyaas Davids (219466242)
 * Date: 12 Oct 2022
 */
package za.ac.cput.factory;

import za.ac.cput.domain.Contact;
import za.ac.cput.domain.PharmacyContact;
import za.ac.cput.domain.SupplierContact;
import za.ac.cput.util.StringHelper;

public class SupplierContactFactory {

    public static SupplierContact createSupplierContact(String suppId, String contactNumber, String email) {

        if (StringHelper.isNullorEmpty(suppId))
            throw new IllegalArgumentException("Supplier ID is null or empty");

        Contact contact = ContactFactory.build(contactNumber, email);
        if (contact == null)
            throw new IllegalArgumentException("Contact is null or empty");

        return new SupplierContact.Builder().setSuppId(suppId)
                .setContact(contact)
                .build();
    }
}
