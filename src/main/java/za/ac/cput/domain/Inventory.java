/**
 * Author: Siphosethu Feni
 * Student Number: 217237614
 *  *  Group: 10
 * Inventory.java
 */
package za.ac.cput.domain;



import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;


@Entity
public class Inventory implements Serializable {

    @Id
    private String inventoryID;
    private String tableStockAmount;
    private String medicineStockAmount;


    protected Inventory(){}

    private Inventory(Builder builder) {
        this.inventoryID = builder.inventoryID;
        this.tableStockAmount = builder.tableStockAmount;
        this.medicineStockAmount = builder.medicineStockAmount;
    }

    public String getInventoryID() {
        return inventoryID;
    }

    public String getTableStockAmount() {
        return tableStockAmount;
    }

    public String getMedicineStockAmount() {
        return medicineStockAmount;
    }


    @Override
    public String toString() {
        return "Inventory{" +
                "inventoryID=" + inventoryID +
                ", tableStockAmount=" + tableStockAmount +
                ", medicineStockAmount=" + medicineStockAmount +
                '}';
    }

    public static class Builder{
        private String inventoryID, tableStockAmount, medicineStockAmount;

        public Builder inventoryID(String inventoryID){
            this.inventoryID = inventoryID;
            return this;
        }

        public Builder tableStockAmount(String tableStockAmount){
            this.tableStockAmount = tableStockAmount;
            return this;
        }

        public Builder medicineStockAmount(String medicineStockAmount){
            this.medicineStockAmount = medicineStockAmount;
            return this;
        }


        public Inventory build(){
            return new Inventory(this);
        }
    }}
