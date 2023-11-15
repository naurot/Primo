/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package my.contacteditor;

import java.sql.Date;

/**
 *
 * @author 14107
 */
public class UserType {

    String name;
    String password;
    int role;
    Date lastLoggedIn;

    public UserType() {
    }

    public UserType(String n, String p, int r) {
        name = n;
        password = p;
        role = r;
    }

    public UserType(String n, String p, int r ,Date l){
        name = n;
        password = p;
        role = r;
        lastLoggedIn = l;
    }
}
