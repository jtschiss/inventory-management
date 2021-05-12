package com.inventory;

import java.io.*;
import java.sql.*;

public class Database {

    Connection connection = null;
    Statement statement = null;
    ResultSet resultSet = null;

    public void getConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/inventoryManagement", "root", "student");
        } catch(ClassNotFoundException classNotFound) {
            classNotFound.printStackTrace();
        } catch(SQLException SQLe) {
            SQLe.printStackTrace();
        }
    }

    // adds item
    public String buildSQL(String name, String price, int quantity) {
        String sql = "INSERT INTO items (name, price, quantity) VALUES ('" + name + "', '" + price + "', " + quantity
                + ")";
        return sql;
    }

    // adds location
    public String buildSQL(String section, String shelf) {
        String sql = "INSERT INTO locations (section, shelf) VALUES ('" + section + "', '" + shelf + "')";
        return sql;
    }

    public int runSql(String sql) {
        int rowsAffected = 0;

        getConnection();

        try {
            Statement statement = connection.createStatement();

            rowsAffected = statement.executeUpdate(sql);
        } catch (SQLException SQLe) {
            SQLe.printStackTrace();
        }
        return rowsAffected;
    }


}
