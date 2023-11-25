/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package my.contacteditor;

import java.math.BigDecimal;

/**
 *
 * @author 14107
 */
public class IngredientType {

    int id;
    String name;
    int brandID;
    String brandName;
    int type;
    BigDecimal cost;
    BigDecimal size;
    int units;
    int expDateNum;

    public IngredientType() {
    }

    ;
    public IngredientType(IngredientType ing) {
        id = ing.id;
        name = ing.name;
        brandID = ing.brandID;
        brandName = ing.brandName;
        type = ing.type;
        cost = ing.cost;
        size = ing.size;
        units = ing.units;
        expDateNum = ing.expDateNum;
    }
}
