/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package my.contacteditor;

/**
 *
 * @author 14107
 */
import java.math.BigDecimal;
import java.util.Date;

public class OrderType extends IngredientType {

    int quantity;
    Object expDate;
    int poNum;

    private static String getMeasure(int type, BigDecimal size, int units) {
        return "cups";
    }

    private static Object getDate(int expDateNum) {
        return expDateNum;
    }

    public OrderType() {
    }

    public OrderType(int q, int d, int p) {
        quantity = q;
        expDate = getDate(d);
        poNum = p;
    }

    public Object[] toTable() {
        Object[] retVal = new Object[5];
        if (this.brandID == 0) {
            retVal[0] = this.name;
        } else {
            retVal[0] = this.brandName + " " + this.name;
        }
        retVal[1] = 0;
        retVal[2] = this.size + " " + getMeasure(this.type, this.size, this.units);
        retVal[3] = this.cost;
        retVal[4] = getDate(this.expDateNum);
        return retVal;
    }
}
