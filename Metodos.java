package my.contacteditor;

import java.sql.Connection;
import java.util.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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

    static SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
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

    public ArrayList<MenuDishType> fillDataList(MenuDishType t, String query) {
        ArrayList<MenuDishType> list = new ArrayList<>();
        try {
            System.out.println("MenuDishType");
            MenuDishType tmp;
            conn = db.getConnection();
            stmt = conn.createStatement();
            rs = stmt.executeQuery(query);
            while (rs.next()) {
                tmp = new MenuDishType();
                tmp.id = rs.getInt("ID");
                tmp.name = rs.getString("Name");
                tmp.mealType = rs.getInt("MealType");
                tmp.dishType = rs.getInt("DishType");
                tmp.numServings = rs.getInt("NumServings");
                tmp.date = rs.getDate("Date");
                tmp.meal = rs.getInt("Meal");
                tmp.quantity = rs.getInt("DishQuantity");
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
                tmp.type = rs.getInt("Type");
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
            System.out.println("UsedIngredient Type");
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
                tmp.type = rs.getInt("Type");
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

    public void createRecipe(DishType d, ArrayList<UsedIngredientType> i) {
        // create dish (in Dish table), then...
        // create entry in consists_of table

        try {
            System.out.println("create Recipe (new dish)");
            String query;
            String quote = "'";
            conn = db.getConnection();
            stmt = conn.createStatement();
            query = "INSERT INTO dish VALUES (null,"
                    + quote + d.name + quote + ","
                    + d.mealType + ","
                    + d.dishType + ","
                    + d.numServings + ")";
//            System.out.println("query: " + query);
//            stmt.executeUpdate(query);
//            rs = stmt.executeQuery("select last_insert_id() as last_id");
            PreparedStatement pstmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            pstmt.executeUpdate();
            ResultSet keys = pstmt.getGeneratedKeys();
            keys.next();
            int lastID = keys.getInt(1);
//            String lastID = rs.getString("last_id");
            // use last_insert_id() to get dish_id
            System.out.println("id = " + lastID);
            for (UsedIngredientType u : i) {
                query = "INSERT INTO consists_of VALUES ("
                        + lastID + ","
                        + u.id + ","
                        + u.brandID + ","
                        + quote + u.brandName + quote + ","
                        + u.type + ","
                        + u.amount + ","
                        + u.units + ")";
                System.out.println("query: " + query);
                stmt.executeUpdate(query);
            }
        } catch (SQLException sqle) {
            System.out.println(sqle);
        }
    }

    public void updateRecipe(DishType d, ArrayList<UsedIngredientType> i) {
        // delete dish_id (in consists_of table), then...
        // create entry in consists_of table
        //TODO: allow dish to be update by dish parameters too
        try {
            System.out.println("Update Recipe");
            String query;
            String quote = "'";
            conn = db.getConnection();
            stmt = conn.createStatement();
            query = "DELETE FROM consists_of where dish_id = " + d.id;
            stmt.executeUpdate(query);
            for (UsedIngredientType u : i) {
                query = "INSERT INTO consists_of VALUES ("
                        + d.id + ","
                        + u.id + ","
                        + u.brandID + ","
                        + quote + u.brandName + quote + ","
                        + u.type + ","
                        + u.amount + ","
                        + u.units + ")";
                System.out.println("update query: " + query);
                stmt.executeUpdate(query);
            }
        } catch (SQLException sqle) {
            System.out.println(sqle);
        }
    }

    public void deleteDish(int dishID) {
        //check to see if dish in menu?
        try {
            String query;
            System.out.println("Delete Recipe: ID# " + dishID);
            conn = db.getConnection();
            stmt = conn.createStatement();
            query = "DELETE  FROM consists_of WHERE Dish_ID = " + dishID;
            stmt.executeUpdate(query);
            query = "DELETE FROM dish WHERE ID = " + dishID;
            stmt.executeUpdate(query);
        } catch (SQLException sqle) {
            System.out.println(sqle);
        }
    }

    public void addMenu(String d, int meal, ArrayList<MenuDishType> menu) {
        //add date and meal to menu table
        //loop through menu and...
        //add date, meal, dishID, and quantity to has table
        String query;

        try {
            java.util.Date dateStr = sdf.parse(d);
            java.sql.Date dateDB = new java.sql.Date(dateStr.getTime());

            conn = db.getConnection();
            try {
                stmt = conn.createStatement();
                try {
                    query = "DELETE FROM has WHERE date='" + dateDB + "' and meal=" + meal;
                    System.out.println("query: " + query);
                    stmt.executeUpdate(query);
                } catch (SQLException f) {
                    System.out.println("SQLError: " + f);
                }
                try {
                    query = "DELETE FROM menu WHERE date='" + dateDB + "' and meal=" + meal;
                    System.out.println("query: " + query);
                    stmt.executeUpdate(query);
                } catch (SQLException f) {
                    System.out.println("SQLException: " + f);
                }
                if (!menu.isEmpty()) {
                    try {
                        query = "INSERT INTO menu VALUES ("
                                + "'" + dateDB + "'" + ","
                                + meal + ")";
                        System.out.println("addMenu, query1: " + query);
                        stmt.executeUpdate(query);
                    } catch (SQLException f) {
                        System.out.println("SQLException: " + f);
                    }
                    try {
                        for (MenuDishType dish : menu) {
                            query = "INSERT INTO has VALUES ("
                                    + "'" + dateDB + "'" + ","
                                    + meal + ","
                                    + dish.id + ","
                                    + dish.quantity + ")";
                            System.out.println("addMenu, query2: " + query);
                            stmt.executeUpdate(query);
                        }
                    } catch (SQLException f) {
                        System.out.println("SQLException: " + f);
                    }
                }
            } catch (SQLException sqle) {
                System.out.println("Error: " + sqle);
            }
        } catch (ParseException e) {
            System.out.println(e);
        }
    }

    public String[][] popAdminTable() {
        String[][] retValue = new String[100][4];
        int index = 0;
        String query;
        conn = db.getConnection();
        try {
            stmt = conn.createStatement();
            query = "SELECT * FROM password";
            rs = stmt.executeQuery(query);
            while (rs.next()) {
                retValue[index][0] = rs.getString("name");
                retValue[index][1] = rs.getString("password");
                retValue[index][2] = rs.getString("role");
                retValue[index++][3] = rs.getDate("last").toString();
            }
        } catch (SQLException sqle) {
            System.out.println("Error: " + sqle);
        }
        return retValue;
    }

    public void adminUI(String query) {
        System.out.println("AdminUI query: " + query);
        conn = db.getConnection();
        try {
            stmt = conn.createStatement();
            stmt.executeUpdate(query);
        } catch (SQLException sqle) {
            System.out.println("Error: " + sqle);
        }
    }

    public ArrayList<TableType> popITable() {
        ArrayList<TableType> retVal = new ArrayList<>();
        String query;
        conn = db.getConnection();
        TableType tmp;
        try {
            stmt = conn.createStatement();
//            SELECT * FROM ((inventory natural join stocks)  right join suppliedby on purchaseorder = po and vendor_id = sb_vendor_id);
            query = "SELECT * FROM ingredient left join (SELECT id, name, brand_id, brand_name, SUM(quantity) as Total from inventory group by id, brand_id, brand_name) on ingredient.id = inventory.ing_id)";
            query = "select i.id,name,i.brand_id,i.brand_name,type,date,po,sb_vendor_id, suppliedquantity,size,suppliedunits,cost from ingredient i left join suppliedby s on i.id=s.id and i.brand_id=s.brand_id and i.brand_name=s.brand_name";
            rs = stmt.executeQuery(query);
            while (rs.next()) {
                tmp = new TableType();
                tmp.id = rs.getInt("id");
                tmp.name = rs.getString("name");
                tmp.brand_id = rs.getInt("brand_id");
                tmp.brand_name = rs.getString("brand_name");
                tmp.type = rs.getInt("type");
                tmp.date = rs.getDate("date");
                tmp.po = rs.getInt("po");
                tmp.vendor_id = rs.getInt("sb_vendor_id");
                tmp.quantity = rs.getInt("suppliedQuantity");
                tmp.size = rs.getInt("size");
                tmp.units = rs.getInt("suppliedUnits");
                tmp.cost = rs.getBigDecimal("cost");
                System.out.println("name" + tmp.name);
                retVal.add(tmp);
            }
        } catch (SQLException sqle) {
            System.out.println("SQLException: " + sqle);
        }
        return retVal;
    }

    public ArrayList<Object> popVTable() {
        return new ArrayList<>(1);
    }

    public ArrayList<Object> popIngTable() {
        return new ArrayList<>(1);
    }
}
