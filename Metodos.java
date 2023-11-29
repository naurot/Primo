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
import java.util.Calendar;
import javax.swing.*;
import static my.contacteditor.Inventory.bigZero;

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
    static SimpleDateFormat sqf = new SimpleDateFormat("yyyy-MM-dd");
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
                tmp.amount = rs.getString("IngredientQuantity");
                tmp.units = rs.getInt("IngredientUnits");
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
                        //                        + u.type + ","
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
                        //                        + u.type + ","
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

    public ArrayList<TableType> popInvTable() {
        ArrayList<TableType> retVal = new ArrayList<>();
        String query;
        conn = db.getConnection();
        TableType tmp;
        try {
            stmt = conn.createStatement();
//            SELECT * FROM ((inventory natural join stocks)  right join suppliedby on purchaseorder = po and vendor_id = sb_vendor_id);
//            query = "SELECT * FROM ingredient left join (SELECT id, name, brand_id, brand_name, SUM(quantity) as Total from inventory group by id, brand_id, brand_name) on ingredient.id = inventory.ing_id)";
            query = "select * from ingredient i left join purchase p on i.id=p.ingid and i.brand_id=p.ingbrandid and i.brand_name=p.ingbrandname";
            System.out.println("query: " + query);
            rs = stmt.executeQuery(query);
            while (rs.next()) {
                tmp = new TableType();
                tmp.id = rs.getInt("id");
                tmp.name = rs.getString("name");
                tmp.brand_id = rs.getInt("brand_id");
                tmp.brand_name = rs.getString("brand_name");
                tmp.type = rs.getInt("type");
                tmp.units = rs.getInt("units");
                tmp.expDate = rs.getDate("expDate");
//                tmp.expDateNum = rs.getInt("expDate");
//                tmp.vendor_id = rs.getInt("sb_vendor_id");
                tmp.quantity = rs.getInt("quantity");
                tmp.size = rs.getBigDecimal("size");
                tmp.units = rs.getInt("units");
                tmp.cost = rs.getBigDecimal("cost");
                System.out.println("name" + tmp.name);
                retVal.add(tmp);
            }
        } catch (SQLException sqle) {
            System.out.println("SQLException: " + sqle);
        }
        return retVal;
    }

    public ArrayList<OrderType> popOrderTable() {
        ArrayList<OrderType> retVal = new ArrayList<>();
        String query;
        conn = db.getConnection();
        OrderType tmp;
        try {
            stmt = conn.createStatement();
            query = "SELECT * FROM ingredient order by id,brand_ID";
            System.out.println("query: " + query);
            rs = stmt.executeQuery(query);
            while (rs.next()) {
                tmp = new OrderType();
                tmp.id = rs.getInt("id");
                tmp.name = rs.getString("name");
                tmp.brandID = rs.getInt("brand_id");
                tmp.brandName = rs.getString("brand_name");
                tmp.type = rs.getInt("type");
                tmp.expDateNum = rs.getInt("expDateNum");
                tmp.size = rs.getBigDecimal("size");
                tmp.units = rs.getInt("units");
                tmp.cost = rs.getBigDecimal("cost");
                tmp.quantity = bigZero;
                System.out.println("name" + tmp.name);
                retVal.add(tmp);
            }
        } catch (SQLException sqle) {
            System.out.println("SQLException: " + sqle);
        }
        return retVal;
    }

    public void submitOrder(ArrayList<OrderType> po) {
        String query;
        conn = db.getConnection();
        String date = sqf.format(new Date());
        OrderType tmp;
        System.out.println("In submit order");
        if (po.isEmpty()) {
            return;
        }
        try {
            query = "INSERT INTO po (date) VALUES ('" + date + "')";
            System.out.println("query: " + query);
            PreparedStatement prepStmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            int affectedRows = prepStmt.executeUpdate();
            if (affectedRows > 0) {
                rs = prepStmt.getGeneratedKeys();
                if (rs.next()) {
                    int poNum = rs.getInt(1);
                    System.out.println("last id: " + poNum);
                    for (OrderType item : po) {
                        query = "INSERT INTO purchase VALUES("
                                + item.id + ","
                                + item.brandID + ",'"
                                + item.brandName + "',"
                                + poNum + ","
                                + item.quantity + ","
                                + "'" + getExpDate(item.expDateNum)
                                + "')";
                        System.out.println("query: " + query);
                        prepStmt = conn.prepareStatement(query);
                        prepStmt.executeUpdate();
                    }
                    //insert into inventory
                    for (OrderType item : po) {
                        query = "INSERT INTO inventory VALUES("
                                + null +","
                                + 1 + ","
                                + 1 + ","
                                + item.id + ","
                                + item.brandID + ",'"
                                + item.brandName + "',"
                                + poNum + ","
                                + item.quantity.multiply(item.size) + ")";
                        System.out.println("query: " + query);
                        prepStmt = conn.prepareStatement(query);
                        prepStmt.executeUpdate();
                    }
                }
            } else {
                System.out.println("ERROR: submit order, but auto-incremented key not returned");
            }
        } catch (SQLException sqle) {
            System.out.println("SQLException: " + sqle);
        }
    }

    public String getExpDate(int num) {
        Date date = new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DATE, num);
        return sqf.format(cal.getTime());
    }

    public Object[][] popUsedTable() {
        Object[][] retVal = null;
        String query;
        conn = db.getConnection();
        try {
            stmt = conn.createStatement();
            query = "Select ingredient_ID, brand_id, brand_Name, sum(ingredientQuantity * dishQuantity) as Total from has natural join consists_of where date='2023-11-21' group by ingredient_id, brand_ID, brand_Name";
            System.out.println("query: " + query);
            stmt.executeQuery(query);
            int i = 0;
            while (rs.next()) {
                System.out.println("name: " + rs.getString("brand_name"));
                retVal[i] = new Object[4];
                retVal[i][0] = rs.getInt("ingredient_ID");
                retVal[i][1] = rs.getInt("brand_ID");
                retVal[i][2] = rs.getString("brand_Name");
                retVal[i++][3] = rs.getBigDecimal("Total");
            }
        } catch (SQLException sqle) {
            System.out.println("SQLException: " + sqle);
        }
        return retVal;
    }
}
