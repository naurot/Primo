/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package my.contacteditor;

/**
 *
 * @author 14107
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
public class Main {
    public static void main(String[] args) {
        try {
            // Step 1: Load the JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");
            // Step 2: Establish a connection using the JDBC URL
            String url = "jdbc:mysql://localhost:3306/world";
            String username = "root";
            String password = "root";
            Connection connection = DriverManager.getConnection(url, username, password);
            // Step 3: Perform database operations
            // ...
            // Step 4: Close the connection
            connection.close();
        } catch (ClassNotFoundException e) {
            System.out.println("JDBC driver not found!");
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println("Error connecting to the database!");
            e.printStackTrace();
        }
    }
}