/*Supplier.java
 * Domain class for Supplier
 * Author: Ilyaas Davids (219466242)
 * Date: 11 Oct 2022
 */
package za.ac.cput.domain;


import java.io.Serializable;
import java.util.Objects;


public class Supplier implements Serializable {

    private String suppId;
    private String suppName;


   //Getters
    public String getSuppId() {
        return suppId;
    }

    public String getSuppName() {
        return suppName;
    }

    @Override
    public String toString() {
        return "Supplier{" +
                "suppId='" + suppId + '\'' +
                ", suppName='" + suppName + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Supplier supplier = (Supplier) o;
        return suppId.equals(supplier.suppId) && suppName.equals(supplier.suppName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(suppId, suppName);
    }

    //Builder Constructor
    protected Supplier(){}

    private  Supplier (Supplier.Builder builder){
        this.suppId = builder.suppId;
        this.suppName = builder.suppName;
    }

    //Builder Classes
   public static class Builder{
        private String suppId;
        private String suppName;

        public Supplier.Builder setSuppId(String suppId) {
            this.suppId=suppId;
            return this;
        }

        public Supplier.Builder setSuppName(String suppName) {
            this.suppName=suppName;
            return this;
        }

        public Supplier.Builder copy(Supplier supplier)
        {
            this.suppId = supplier.suppId;
            this.suppName = supplier.suppName;
            return this;
        }
        public Supplier build(){ return new Supplier(this);}


    }

}
