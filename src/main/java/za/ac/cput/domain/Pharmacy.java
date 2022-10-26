package za.ac.cput.domain;

/* Pharmacy.java
Domain for the Pharmacy
Author: Waseem Dollie (216040566)
Date: 10 October 2022 */


import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;

/* @Email - Did not use javax validator as commons validator is more thorough when checking for email validation.
 * @NotNull/@NotBlank - Did not use as checks for nulls/blanks is done in the helper and Factory class.*/

@Entity
public class Pharmacy implements Serializable {

    @Id
    private String pharmId;
    private String pharmName;
    private String medicationId;
    private String inventoryId;

        private PharmacyContact pharmacyContact;

    protected Pharmacy(){

    }

    private Pharmacy(Builder builder){
        this.pharmId = builder.pharmId;
        this.pharmName = builder.pharmName;
        this.medicationId = builder.medicationId;
        this.inventoryId = builder.inventoryId;
        this.pharmacyContact = builder.pharmacyContact;
    }

    public String getPharmId() {return pharmId;}

    public String getPharmName() {return pharmName;}

    public String getMedicationId() {return medicationId;}

    public String getInventoryId() {return inventoryId;}

    public PharmacyContact getPharmacyContact() {return pharmacyContact;}

    @Override
    public String toString() {
        return "Pharmacy{" +
                "pharmId='" + pharmId + '\'' +
                ", pharmName='" + pharmName + '\'' +
                ", medicationId='" + medicationId + '\'' +
                ", inventoryId='" + inventoryId + '\'' +
                ", pharmacyContact=" + pharmacyContact +
                '}';
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pharmacy pharmacy = (Pharmacy) o;
        return pharmId.equals(pharmacy.pharmId) && pharmName.equals(pharmacy.pharmName) && medicationId.equals(pharmacy.medicationId) && inventoryId.equals(pharmacy.inventoryId) && pharmacyContact.equals(pharmacy.pharmacyContact);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pharmId, pharmName, medicationId, inventoryId, pharmacyContact);
    }

    public static class Builder{
        private String pharmId, pharmName,medicationId, inventoryId ;
        private PharmacyContact pharmacyContact;

        public Builder pharmId(String pharmId){
            this.pharmId = pharmId;
            return this;
        }

        public Builder pharmName(String pharmName){
            this.pharmName = pharmName;
            return this;
        }

        public Builder medicationId(String medicationId){
            this.medicationId = medicationId;
            return this;
        }

        public Builder inventoryId(String inventoryId){
            this.inventoryId = inventoryId;
            return this;
        }

        public Builder pharmacyContact(PharmacyContact pharmacyContact){
            this.pharmacyContact = pharmacyContact;
            return this;
        }

        public Pharmacy build(){
            return new Pharmacy(this);
        }
    }}

