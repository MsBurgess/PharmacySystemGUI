package za.ac.cput.factory;
import za.ac.cput.domain.Customer;
import za.ac.cput.domain.Employee;
import za.ac.cput.domain.Name;
import za.ac.cput.util.Helper;
import za.ac.cput.util.StringHelper;

public class CustomerFactory {
    public static Customer createCustomer(String customerId, String gender, String firstName, String middleName, String lastName){

        //Checks if ID or email is empty
        if(StringHelper.isNullorEmpty(customerId) || StringHelper.isNullorEmpty(gender))
            throw new IllegalArgumentException("Customer ID or Email is null or empty");


        //checks if name is null or not
        Name name = NameFactory.build(firstName, middleName, lastName);
        if (name == null)
            throw new IllegalArgumentException("Name is Null or empty");

        return new Customer.Builder().customerId(customerId)
                .gender(gender)
                .name(name)
                .build();

    }
}
