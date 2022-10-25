/*MedicationFactory.java
 * Factory class for Medication
 * Author: Ilyaas Davids (219466242)
 * Date: 12 Oct 2022
 */
package za.ac.cput.factory;

import za.ac.cput.domain.Medication;
import za.ac.cput.util.StringHelper;

public class MedicationFactory {

    public static Medication createMedication(String medId, String medName, String medManufacturer, String suppId){

        if(StringHelper.isNullorEmpty(medId)) {
            throw new IllegalArgumentException("Medication ID is null or empty");
        }else if (StringHelper.isNullorEmpty(medName)){
            throw new IllegalArgumentException("Medication Name is null or empty");
        }else if (StringHelper.isNullorEmpty(medManufacturer)){
            throw new IllegalArgumentException("Medication Manufacturer is null or empty");
        } else if (StringHelper.isNullorEmpty(suppId)) {
            throw new IllegalArgumentException("Supplier ID is null or empty");
        }
        return new Medication.Builder()
                .setMedId(medId)
                .setMedName(medName)
                .setMedManufacturer(medManufacturer)
                .setSuppId(suppId)
                .build();
    }
}

