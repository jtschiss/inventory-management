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

    // searches for item by name
    public String buildSQL(String search) {
        String sql = "SELECT * FROM items WHERE name LIKE '%" + search + "%'";
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
    public Location getLocationId(String sql) {
        ResultSet resultSet = null;
        Location location = new Location();
        int locationId = 0;
        getConnection();

        try {
            Statement statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                location.setId(resultSet.getInt("id"));
                location.setSection(resultSet.getString("section"));
                location.setShelf(resultSet.getString("shelf"));
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
        return location;
    }

    // item search by name
    public ArrayList<Item> searchItem(String search) {
        ResultSet resultSet = null;
        Item item = new Item();
        ArrayList<Item> items = new ArrayList<>();
        getConnection();

        String sql = buildSQL(search);

        try {
            Statement statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                item.setId(resultSet.getInt("id"));
                item.setName(resultSet.getString("name"));
                item.setPrice(resultSet.getString("price"));
                item.setQuantity(resultSet.getInt("quantity"));
                items.add(item);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return items;
    }





}
