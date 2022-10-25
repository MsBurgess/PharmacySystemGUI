/* EmployeeFactory.java
 Factory for the Employee
 Author: Zaeem Petersen (219010145)
 Date: 09 October 2022
*/
package za.ac.cput.factory;

import za.ac.cput.domain.Employee;
import za.ac.cput.domain.Name;
import za.ac.cput.util.Helper;
import za.ac.cput.util.StringHelper;

public class EmployeeFactory {
    public static Employee createEmployee(String staffId, String gender, String firstName, String middleName, String lastName){

        //Checks if ID or email is empty
        if(StringHelper.isNullorEmpty(staffId) || StringHelper.isNullorEmpty(gender))
            throw new IllegalArgumentException("Staff ID or Email is null or empty");


        //checks if name is null or not
        Name name = NameFactory.build(firstName, middleName, lastName);
        if (name == null)
            throw new IllegalArgumentException("Name is Null or empty");

        return new Employee.Builder().staffId(staffId)
                .gender(gender)
                .name(name)
                .build();

    }
}
