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
    int id, brand_id, type, po, vendor_id, quantity, units, sloc, sunit, sshelf, expDateNum;
    String name, brand_name;
Date expDate;
    BigDecimal cost, size;
    
    public TableType(){
    
    }
//  
      public TableType(int id, String name, int brandID, String brandName, int type, int expDateNum, int po, int vendorID, int sQuantity, int size, int sUnits, BigDecimal cost, Date expDate){
        
    }
    public Object[] toTbl(){
        Object[] retVal = new Object[6];
        retVal[0] = 0;
        retVal[1] = name;
        retVal[2] = 0;
//        retVal[3] = quantity;
        retVal[3] = size;
        retVal[4] = 0;
        retVal[5] = expDate;
        return retVal;
    }
}
