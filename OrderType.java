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
import static my.contacteditor.Inventory.unit;
import static my.contacteditor.Inventory.getMeasure;

public class OrderType extends IngredientType {
//    static final String[][] unit = {{"each", "dozen"},
//    {"dash", "tsp", "Tbs", "ounce", "cup", "lb"},
//    {"splash", "tsp", "Tbs", "ounce", "cup", "pint", "quart", "gal"}};
    int quantity;
    Object expDate;
    int poNum;

//    public static String getMeasure(BigDecimal quantity, int units, int type) {
//        return unit[type][units] + (quantity.compareTo(BigDecimal.valueOf(1)) == 1 && units != 2 && type != 0 ? "s" : "");
//    }

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
        retVal[2] = this.size + " " + getMeasure(this.size, this.units, this.type);
        retVal[3] = this.cost;
        retVal[4] = getDate(this.expDateNum);
        return retVal;
    }
}
