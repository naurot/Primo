/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package my.contacteditor;

import java.math.BigDecimal;
import java.sql.Date;
import java.text.SimpleDateFormat;

/**
 *
 * @author 14107
 */
public class TableType {
//    int id, brand_id, type, po, vendor_id, quantity, units, sloc, sunit, sshelf, expDateNum;
//    String name, brand_name;
//Date expDate;
//    BigDecimal cost, size;
    int location, shelvingUnit,shelf,ingID,ingBrandID,po;
    String ingBrandName, name;
    Date date,expDate;
    BigDecimal quantity, cost;
    SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
    
    public TableType(){
    
    }
//  
//      public TableType(int id, String name, int brandID, String brandName, int type, int expDateNum, int po, int vendorID, int sQuantity, int size, int sUnits, BigDecimal cost, Date expDate){
//        
//    }
    public Object[] toTbl(){
        Object[] retVal = new Object[6];
        retVal[0] = this.location +","+this.shelvingUnit+","+this.shelf;
        retVal[1] = (this.ingBrandID == 0) ? this.name : this.ingBrandName + this.name;
        retVal[2] = this.po;
        retVal[3] = this.quantity;
        retVal[4] = sdf.format(this.date);
        retVal[5] = sdf.format(this.expDate);
        return retVal;
    }
}
