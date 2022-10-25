package za.ac.cput.factory;

import za.ac.cput.domain.Contact;
import za.ac.cput.domain.EmployeeContact;
import za.ac.cput.util.StringHelper;

public class EmployeeContactFactory {
    public static EmployeeContact build(String staffId, String contactNumber, String email)
    {
        if (StringHelper.isNullorEmpty(staffId))
            throw new IllegalArgumentException("staff id is null or empty");

       Contact contact = ContactFactory.build(contactNumber, email);
       if (contact == null)
           throw new IllegalArgumentException("Contact is null or empty");

        return new EmployeeContact.Builder().setStaffId(staffId)
                .setContact(contact)
                .build();
    }
}

