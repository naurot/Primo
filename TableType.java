/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package my.contacteditor;

import java.math.BigDecimal;
import java.sql.Date;

/**
 *
 * @author 14107
 */
public class TableType {
    int id, brand_id, type, po, vendor_id, quantity, size, units, sloc, sunit, sshelf;
    String name, brand_name;
    Date date;
    BigDecimal cost;
    
    public TableType(){
    
    }
    public TableType(int id, String name, int brandID, String brandName, int type, Date date, int po, int vendorID, int sQuantity, int size, int sUnits, BigDecimal cost){
        
    }
}
