///*
// * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
// * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
// */
package my.contacteditor;
//
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.SQLException;
//
///**
// *
// * @author 14107
// */
//public class TestDB {
//    public static void main(String[] args) {
//            String url = "jdbc:mysql://localhost:3306/world";
//    String user = "root";
//    String passwd = "root";
//
//        try {
//            Connection connection = DriverManager.getConnection(url, user, passwd);
//
//            System.out.println("Connected: " + url);
//        } catch (SQLException e) {
//            System.out.println("ERROR");
//            System.out.println(e);
//            e.printStackTrace();
//        }
//    }
//}
//import com.mysql.jdbc.Driver;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;
import java.util.Scanner;
//import java.sql.Statement;
public class TestDB{
    public static void main(String[] args) {
//                Statement stmt = null;
        ResultSet rs = null;
        try {
            // Step 1: Load the JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");
            // Step 2: Establish a connection using the JDBC URL
            String url = "jdbc:mysql://localhost:3306/primo";
            String username = "root";
            String password = "root";
            Connection conn = DriverManager.getConnection(url, username, password);
            // Step 3: Perform database operations
            // ...
//            stmt = conn.createStatement();
            rs = conn.createStatement().executeQuery("SELECT * FROM Dish");
            while (rs.next()){
                System.out.println(rs.getString(2));
            }
            // Step 4: Close the connection
            conn.close();
        } catch (ClassNotFoundException e) {
            System.out.println("JDBC driver not found!");
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println("Error connecting to the database!");
            e.printStackTrace();
        }
    }
}