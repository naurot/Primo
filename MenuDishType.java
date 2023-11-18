/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package my.contacteditor;

import java.util.Date;

/**
 *
 * @author 14107
 */
public class MenuDishType extends DishType {

    Date date;
    int meal;
    int quantity;
    private int totalServings;

    public MenuDishType() {
    }
    
   public MenuDishType(Date date, int meal, DishType dish, int quantity) {
        super(dish);
        this.date = date;
        this.meal = meal;
        this.quantity = quantity;
    }

    public String toString() {
        return quantity + " " + name;
    }

    public int getNumServings() {
        return numServings;
    }

    public int getTotalServings() {
        return quantity * numServings;
    }

    public void setQuantity(int quantity) {
//        this.quantity = quantity;
    }
}
