package com.inventory;

import java.io.*;
import java.sql.*;

public class database {

    Connection connection = null;
    Statement statement = null;
    ResultSet resultSet = null;

    public void getConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost/inventoryManagement", "root", "student");
            System.out.println("Connection made");
        } catch(ClassNotFoundException classNotFound) {
            classNotFound.printStackTrace();
        } catch(SQLException sqlException) {
            sqlException.printStackTrace();
        }
    }


}
