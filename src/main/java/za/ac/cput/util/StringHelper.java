/* StringHelper.java
 Helper class for the Customer and Prescription Entity and Factory classes
 Author: Zaeem Petersen (219010145)
 Date: 07 April 2022
*/
package za.ac.cput.util;

import java.util.UUID;

public class StringHelper {

    public static String generateId(){
        return UUID.randomUUID().toString();
    }

    public static boolean isNullorEmpty(String q){

        if (q==null || q.equals("") || q.isEmpty())
            return true;
        else
            return false;
    }
}
