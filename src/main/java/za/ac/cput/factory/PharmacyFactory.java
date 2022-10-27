package za.ac.cput.factory;

/* PharmacyFactory.java
Factory for the Pharmacy
Author: Waseem Dollie (216040566)
Date: 11 October 2022 */

import za.ac.cput.domain.Pharmacy;
import za.ac.cput.domain.Prescription;
import za.ac.cput.util.StringHelper;


public class PharmacyFactory {
    public static Pharmacy build(String pharmId, String pharmName, String medicationId, String inventoryId) {

        if(StringHelper.isNullorEmpty(pharmId) || StringHelper.isNullorEmpty(pharmName) || StringHelper.isNullorEmpty(medicationId) || StringHelper.isNullorEmpty(inventoryId)){
            throw new IllegalArgumentException("pharmId or pharmName is null or empty");
        }

        return new Pharmacy.Builder().pharmId(pharmId)
                .pharmName(pharmName)
                .medicationId(medicationId)
                .inventoryId(inventoryId)
                .build();
    }
}