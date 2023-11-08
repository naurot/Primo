package my.contacteditor;

import java.sql.Connection;
import java.sql.DriverManager;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author 14107
 */
public class Database {

    Connection conn = null;

    public Connection getConnection() {
        String url = "jdbc:mysql://localhost:3306/primo";
        String username = "root";
        String password = "root";
        try {
            //Load the JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");
            //Establish a connection using the JDBC URL
            conn = (Connection)DriverManager.getConnection(url, username, password);
        }catch(Exception e){
            System.out.println(e);
        }
        return conn;
    }
}
