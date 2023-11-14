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
public class DishType {
    String name;
    int id;
    int mealType;
    int dishType;
    int numServings;
    BigDecimal costPerServing;
    
    public DishType(){}
    public DishType(DishType dish){
        name = dish.name;
        id = dish.id;
        mealType = dish.mealType;
        dishType = dish.dishType;
        numServings = dish.numServings;
    }
}