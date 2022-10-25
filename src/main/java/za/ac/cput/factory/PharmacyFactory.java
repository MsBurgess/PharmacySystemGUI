package za.ac.cput.factory;

/* PharmacyFactory.java
Factory for the Pharmacy
Author: Waseem Dollie (216040566)
Date: 11 October 2022 */

import za.ac.cput.domain.Pharmacy;
import za.ac.cput.util.StringHelper;


public class PharmacyFactory {
    public static Pharmacy build(String pharmId, String pharmName, String medicationId, String inventoryId) {

        //Checks if ID is empty
        if (StringHelper.isNullorEmpty(pharmId))
            throw new IllegalArgumentException("Pharm ID is null or empty");

        //Checks if ID or email is empty
        if (StringHelper.isNullorEmpty(pharmName))
            throw new IllegalArgumentException("Pharm Name is null or empty");

        //Checks if ID or email is empty
        if (StringHelper.isNullorEmpty(medicationId))
            throw new IllegalArgumentException("Medication ID is null or empty");

        //Checks if ID or email is empty
        if (StringHelper.isNullorEmpty(inventoryId))
            throw new IllegalArgumentException("Inventory ID is null or empty");



        return new Pharmacy.Builder().pharmId(pharmId)
                .build();

    }}