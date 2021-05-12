package com.inventory;

import com.entity.*;

import java.io.*;
import java.sql.*;
import java.util.ArrayList;


public class Database {

    Connection connection = null;
    Statement statement = null;

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

    // adds, removes, or selects location
    public String buildSQL(String action, String section, String shelf) {
        String sql = "";
        switch (action) {
            case "add":
                sql = "INSERT INTO locations (section, shelf) VALUES ('" + section + "', '" + shelf + "')";
                break;
            case "select":
                sql = "SELECT * FROM locations WHERE section='" + section + "' AND shelf='" + shelf + "'";
        }

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
        } finally {
            try {
                if (statement != null) {
                    statement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException sqlException) {
                sqlException.printStackTrace();
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        }

        return rowsAffected;
    }

    // gets location id
    public int getLocationId(String sql) {
        ResultSet resultSet = null;
        int locationId = 0;
        getConnection();

        try {
            Statement statement = connection.createStatement();

            resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                locationId = resultSet.getInt("id");
            }
        } catch (SQLException SQLe) {
            SQLe.printStackTrace();
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (statement != null) {
                    statement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException sqlException) {
                sqlException.printStackTrace();
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        }
        return locationId;
    }




}
