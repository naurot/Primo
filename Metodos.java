package my.contacteditor;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.*;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author 14107
 * @param <T>
 */
public class Metodos<T> {

    Database db = new Database();
    Connection conn = null;
    Statement stmt = null;
    ResultSet rs = null;

    public ArrayList<DishType> fillDataList(DishType t, String query) {
        ArrayList<DishType> list = new ArrayList<>();
        try {
            System.out.println("DishType");
            DishType tmp;
            conn = db.getConnection();
            stmt = conn.createStatement();
            rs = stmt.executeQuery(query);
            while (rs.next()) {
                tmp = new DishType();
                tmp.id = rs.getInt("ID");
                tmp.name = rs.getString("Name");
                tmp.mealType = rs.getInt("MealType");
                tmp.dishType = rs.getInt("DishType");
                tmp.numServings = rs.getInt("NumServings");
                list.add(tmp);
            }
        } catch (SQLException sqle) {
            System.out.println(sqle);
        }
        return list;
    }

    public ArrayList<IngredientType> fillDataList(IngredientType t, String query) {
        ArrayList<IngredientType> list = new ArrayList<>();
        try {
            System.out.println("Ingredient Type");
            IngredientType tmp;
            conn = db.getConnection();
            stmt = conn.createStatement();
            rs = stmt.executeQuery(query);
            while (rs.next()) {
                tmp = new IngredientType();
                tmp.id = rs.getInt("ID");
                tmp.name = rs.getString("Name");
                tmp.brandID = rs.getInt("Brand_ID");
                tmp.brandName = rs.getString("Brand_Name");
                list.add(tmp);
            }
        } catch (SQLException sqle) {
            System.out.println(sqle);
        }
        return list;
    }

    public ArrayList<UsedIngredientType> fillDataList(UsedIngredientType t, String query) {
        ArrayList<UsedIngredientType> list = new ArrayList<>();
        try {
            System.out.println("Ingredient Type");
            UsedIngredientType tmp;
            conn = db.getConnection();
            stmt = conn.createStatement();
            rs = stmt.executeQuery(query);
            while (rs.next()) {
                tmp = new UsedIngredientType();
                tmp.id = rs.getInt("ID");
                tmp.name = rs.getString("Name");
                tmp.brandID = rs.getInt("Brand_ID");
                tmp.brandName = rs.getString("Brand_Name");
                tmp.amount = rs.getString("Quantity");
                tmp.units = rs.getString("Units");
                System.out.println("Units: " + tmp.units);
                list.add(tmp);
            }
        } catch (SQLException sqle) {
            System.out.println(sqle);
        }
        return list;
    }

}
