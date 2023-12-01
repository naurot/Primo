/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package my.contacteditor;

import java.math.BigDecimal;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;

/**
 *
 * @author 14107
 */
public class TableType {

    int location, shelvingUnit, shelf, ingID, ingBrandID, po, dishID;//dishID used only for deleteing from inv
    String ingBrandName, name;
    Date date, expDate;
    BigDecimal quantity, cost;
    SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");

    public TableType() {

    }

    public Object[] toTbl() {
        Object[] retVal = new Object[6];
        retVal[0] = this.location + "," + this.shelvingUnit + "," + this.shelf;
        retVal[1] = (this.ingBrandID == 0) ? this.name : this.ingBrandName + this.name;
        retVal[2] = this.po;
        retVal[3] = this.quantity;
        retVal[4] = sdf.format(this.date);
        retVal[5] = sdf.format(this.expDate);
        return retVal;
    }

    public String logValues() {
        return location + ","
                + shelvingUnit + ","
                + shelf + ","
                + ingID + ","
                + ingBrandID + ",'"
                + ingBrandName + "',"
                + po + ",'"
                + date + "','"
                + expDate + "',"
                + cost;
    }

    public Date getDate() {
        return this.date;
    }

}
