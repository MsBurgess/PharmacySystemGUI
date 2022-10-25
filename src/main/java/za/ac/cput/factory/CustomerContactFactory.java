/**
 * Author: Siphosethu Feni
 * Student Number: 217237614
 *  *  Group: 10
 * CustomerContactFactory.java
 */

package za.ac.cput.factory;

import za.ac.cput.domain.Contact;
import za.ac.cput.domain.CustomerContact;
import za.ac.cput.util.StringHelper;

public class CustomerContactFactory {
    public static CustomerContact createCustomerContact(String customerId, String contactNumber, String email){

        if(StringHelper.isNullorEmpty(customerId))
            throw new IllegalArgumentException("Customer ID or Email is null or empty");

        Contact contact = ContactFactory.build(contactNumber, email);
        if (contact == null)
            throw new IllegalArgumentException("Contact is null or empty");

        return new CustomerContact.Builder().setCustomerId(customerId)
                .setContact(contact)
                .build();
    }
}
