/**ContactFactory.java
 * Factory class for Contact domain
 * Author: Daniella Burgess (219446482)
 * Date: 29 September 2022
 */

package za.ac.cput.factory;

import za.ac.cput.domain.Contact;
import za.ac.cput.util.Helper;
import za.ac.cput.util.StringHelper;

public class ContactFactory {
        public static Contact build(String contactNumber, String email) {

            if (StringHelper.isNullorEmpty(contactNumber) || StringHelper.isNullorEmpty(email))
                throw new IllegalArgumentException("Contact number or email is null or empty");

//            if (Helper.isValidEmail(email))
//                throw new IllegalArgumentException("This is a valid email");

            return  new Contact.Builder().setContactNumber(contactNumber)
                    .setEmail(email)
                    .build();
        }


}
