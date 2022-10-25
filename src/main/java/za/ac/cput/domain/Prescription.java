/* Prescription.java
 Domain for the Prescription
 Author: Zaeem Petersen (219010145)
 Date: 08 October 2022
*/
package za.ac.cput.domain;


import java.io.Serializable;


public class Prescription implements Serializable {


    private String prescID;
    private String prescType;
    private String prescDoses;

    protected Prescription(){}

    //builder constructor
    private Prescription(Builder builder){
        this.prescID = builder.prescID;
        this.prescType = builder.prescType;
        this.prescDoses = builder.prescDoses;
    }

    //getters
    public String getPrescID() {
        return prescID;
    }
    public String getPrescType() {
        return prescType;
    }
    public String getPrescDoses() {
        return prescDoses;
    }

    @Override
    public String toString() {
        return "Prescription{" +
                "prescID=" + prescID +
                ", prescType='" + prescType + '\'' +
                ", prescDoses=" + prescDoses +
                '}';
    }

    public static class Builder{
        private String prescID;
        private String prescType;
        private String prescDoses;

        public Builder prescID(String prescID) {
            this.prescID = prescID;
            return this;
        }
        public Builder prescType(String prescType) {
            this.prescType = prescType;
            return this;
        }
        public Builder prescDoses(String prescDoses) {
            this.prescDoses = prescDoses;
            return this;
        }

        public Builder copy(Prescription prescription){
            this.prescID = prescription.prescID;
            this.prescType = prescription.prescType;
            this.prescDoses = prescription.prescDoses;
            return this;
        }

        public Prescription build(){
            return new Prescription(this);
        }
    }
}
