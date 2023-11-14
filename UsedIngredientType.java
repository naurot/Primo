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
    String units;
    
    public UsedIngredientType(){};
    
    public UsedIngredientType(IngredientType ing, String amount, String units){
        super(ing);
        this.amount = amount;
        this.units= units;
    }
    
    public String toString(){
        return amount + " " + units + " " + name.strip();
    }
}
