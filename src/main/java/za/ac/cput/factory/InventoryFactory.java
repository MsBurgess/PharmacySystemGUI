/**
 * Author: Siphosethu Feni
 * Student Number: 217237614
 *  *  Group: 10
 * InventoryFactory.java
 */

package za.ac.cput.factory;

import za.ac.cput.domain.Inventory;
import za.ac.cput.util.StringHelper;

public class InventoryFactory {
    public static Inventory createInventory(String inventoryID,String tableStockAmount, String medicineStockAmount) {
        if(StringHelper.isNullorEmpty(inventoryID) ||StringHelper.isNullorEmpty(tableStockAmount) || StringHelper.isNullorEmpty(medicineStockAmount))
            throw new IllegalArgumentException("TableStockAmount or MedicineStockAmount is null or empty");

        return new Inventory.Builder().inventoryID(inventoryID)
                .tableStockAmount(tableStockAmount)
                .medicineStockAmount(medicineStockAmount)
                .build();
    }
}
