/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package my.contacteditor;

/**
 *
 * @author 14107
 */


    
public class UsedIngredientType extends IngredientType{
    String amount;
    int units;
        final String[][] unit = {{"each", "dozen"},{"dash", "tsp", "Tbs", "ounce", "cup", "lb"},{"splash", "tsp", "Tbs", "ounce", "cup", "pint", "quart", "gal"}};
            
    public UsedIngredientType(){};
    
    public UsedIngredientType(IngredientType ing, String amount, int units){
        super(ing);
        this.amount = amount;
        this.units= units;
    }
    
    public String toString(){
        return amount + " " + unit[this.type][units] + " " + name.strip();
    }
}
