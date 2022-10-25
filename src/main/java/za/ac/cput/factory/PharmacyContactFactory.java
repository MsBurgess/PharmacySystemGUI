package za.ac.cput.factory;

/* PharmacyContactFactory.java
Factory for the PharmacyContact
Author: Waseem Dollie (216040566)
Date: 11 October 2022 */

import za.ac.cput.domain.Contact;
import za.ac.cput.domain.PharmacyContact;
import za.ac.cput.util.StringHelper;

public class PharmacyContactFactory {
    public static PharmacyContact build(String pharmId, String contactNumber, String email){

        //Checks if ID or email is empty
        if(StringHelper.isNullorEmpty(pharmId))
            throw new IllegalArgumentException("Pharm ID is null or empty");

        Contact contact = ContactFactory.build(contactNumber, email);
        if (contact == null)
            throw new IllegalArgumentException("Contact is null or empty");


        return new PharmacyContact.Builder().setPharmId(pharmId)
                .setContact(contact)
                .build();
    }
}
