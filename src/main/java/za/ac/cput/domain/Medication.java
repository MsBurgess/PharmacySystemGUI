/*Medication.java
 * Domain class for Medication
 * Author: Ilyaas Davids (219466242)
 * Date: 11 Oct 2022
 */
package za.ac.cput.domain;


import java.io.Serializable;
import java.util.Objects;


public class Medication implements Serializable {

    private String medId;
    private String medName;
    private String medManufacturer;
    private String suppId;

    //Getter
    public String getMedId() {
        return medId;
    }

    public String getMedName() {
        return medName;
    }

    public String getMedManufacturer() {
        return medManufacturer;
    }

    public String getSuppId() {
        return suppId;
    }

    @Override
    public String toString() {
        return "Medication{" +
                "medId='" + medId + '\'' +
                ", medName='" + medName + '\'' +
                ", medManufacturer='" + medManufacturer + '\'' +
                ", suppId='" + suppId + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Medication that = (Medication) o;
        return medId.equals(that.medId) && medName.equals(that.medName) && medManufacturer.equals(that.medManufacturer) && suppId.equals(that.suppId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(medId, medName, medManufacturer, suppId);
    }

    //Builder Constructor
    protected Medication(){}

    private  Medication (Medication.Builder builder){
        this.medId = builder.medId;
        this.medName = builder.medName;
        this.medManufacturer = builder.medManufacturer;
        this.suppId = builder.suppId;
    }
    //Builder Classes
    public static class Builder {
        private String medId;
        private String medName;
        private String medManufacturer;
        private String suppId;

        public Medication.Builder setMedId(String medId) {
            this.medId = medId;
            return this;
        }

        public Medication.Builder setMedName(String medName) {
            this.medName = medName;
            return this;
        }

        public Medication.Builder setMedManufacturer(String medManufacturer) {
            this.medManufacturer = medManufacturer;
            return this;
        }

        public Medication.Builder setSuppId(String suppId) {
            this.suppId = suppId;
            return this;
        }

        public Medication.Builder copy(Medication medication)
        {
            this.medId = medication.medId;
            this.medName = medication.medName;
            this.medManufacturer = medication.medManufacturer;
            this.suppId = medication.suppId;
            return this;
        }
        public Medication build(){ return new Medication(this);}

    }
}

