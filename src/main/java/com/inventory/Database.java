package com.inventory;

import com.entity.*;

import java.io.*;
import java.sql.*;
import java.util.ArrayList;


public class Database {

    private Connection connection = null;
    private Statement statement = null;
    private ResultSet resultSet = null;

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
                sql = buildSQL("select", section, shelf);
                Location location = getLocation(sql);
                sql = "";
                System.out.println(location);
                if(location.getSection() == null) {
                    sql = "INSERT INTO locations (section, shelf) VALUES ('" + section + "', '" + shelf + "')";
                } else {
                    sql = "";
                }
                break;
            case "select":
                sql = "SELECT * FROM locations WHERE section='" + section + "' AND shelf='" + shelf + "'";
        }
        return sql;
    }

    // Selects item or location
    public String buildSQL(String object, int id) {
        String sql = "";
        switch (object) {
            case "item":
                sql = "SELECT * FROM items WHERE id='" + id + "'";
                break;
            case "location":
                sql = "SELECT * FROM locations WHERE id='" + id + "'";
        }
        return sql;
    }

    // add item to location
    public String buildSQL(String action, Item item, Location location) {
        String sql = "";
        switch (action){
            case "add":
                sql = buildSQL("select", item, location);
                ItemLocation itemLocation = getItemLocation(sql);
                sql = "";
                if(itemLocation.getItemId() == 0) {
                    sql = "INSERT INTO item_locations (item_id, location_id) VALUES (" + item.getId() + ", "
                            + location.getId() + ")";
                }
                break;
            case "select":
                    sql = "SELECT * FROM item_locations WHERE item_id=" + item.getId() + " AND location_id="
                            + location.getId() + "";
                    break;

        }

        return sql;
    }

    // gets all active locations for a given item
    public String buildSQL(Item item) {
        String sql = "SELECT * FROM item_locations WHERE active=1 AND item_id=" + item.getId();
        return sql;
    }

    // runs a given sql statement using executeUpdate()
    public int runSql(String sql) {
        int rowsAffected = 0;
        if(!sql.equals("")) {
            getConnection();

            try {
                Statement statement = connection.createStatement();

                rowsAffected = statement.executeUpdate(sql);
            } catch (SQLException SQLe) {
                SQLe.printStackTrace();
            } finally {
                closeConnection();
            }
        }

        return rowsAffected;
    }

    // gets location
    public Location getLocation(String sql) {
        Location location = new Location();
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
            closeConnection();
        }
        return location;
    }

    // searched for the item by id
    public Item getItemById(String sql) {
        Item item = new Item();
        getConnection();

        try {
            Statement statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                item.setId(resultSet.getInt("id"));
                item.setName(resultSet.getString("name"));
                item.setPrice(resultSet.getString("price"));
                item.setQuantity(resultSet.getInt("quantity"));
            }
        } catch (SQLException SQLe) {
            SQLe.printStackTrace();
        } finally {
            closeConnection();
        }
        return item;
    }

    // item search by name
    public ArrayList<Item> searchItem(String search) {
        Item item = null;
        ArrayList<Item> items = new ArrayList<>();
        getConnection();

        String sql = buildSQL(search);

        try {
            Statement statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                item = new Item();
                item.setId(resultSet.getInt("id"));
                item.setName(resultSet.getString("name"));
                item.setPrice(resultSet.getString("price"));
                item.setQuantity(resultSet.getInt("quantity"));
                items.add(item);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            closeConnection();
        }
        return items;
    }

    // gets itemLocation
    public ItemLocation getItemLocation(String sql) {
        getConnection();
        ItemLocation itemLocation = new ItemLocation();

        try {
            Statement statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                itemLocation.setItemId(resultSet.getInt("item_id"));
                itemLocation.setLocationId(resultSet.getInt("location_id"));
                itemLocation.setActive(resultSet.getInt("active"));
            }
        } catch (SQLException SQLe) {
            SQLe.printStackTrace();
        } finally {
            closeConnection();
        }
        return itemLocation;
    }

    // removes item from location
    public int removeItemFromLocation(Item item, Location location) {
        int rowsAffected = 0;
        String sql = buildSQL("select", item, location);
        ItemLocation itemLocation = getItemLocation(sql);
        if(itemLocation.getItemId() != 0) {
            sql = "UPDATE item_locations SET active=0 WHERE item_id=" + item.getId() + " AND location_id="
                    + location.getId();
            rowsAffected = runSql(sql);
        }
        return rowsAffected;
    }

    // activate itemLocation
    public int activateItemLocation(ItemLocation itemLocation) {
        int rowsAffected = 0;
        if(itemLocation.getItemId() != 0) {
            String sql = "UPDATE item_locations SET active=1 WHERE item_id=" + itemLocation.getItemId() + " AND location_id="
                    + itemLocation.getLocationId();
            rowsAffected = runSql(sql);
        }
        return rowsAffected;
    }

    // gets all active locations that an item is in
    public ArrayList<Location> getActiveItemLocations(Item item) {
        ArrayList<Location> locations = new ArrayList<>();
        ArrayList<Integer> locationIds = new ArrayList<>();
        getConnection();
        String sql = buildSQL(item);
        try {
            Statement statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                locationIds.add(resultSet.getInt("location_id"));
            }

            for(int locationId : locationIds) {
                sql = buildSQL("location", locationId);
                locations.add(getLocation(sql));
            }
        } catch (SQLException SQLe) {
            SQLe.printStackTrace();
        } finally {
            closeConnection();
        }

        return locations;
    }

    // updates the quantity of an item
    public int updateQuantity(Item item, int quantity) {
        int rowsAffected = 0;
        String sql = "UPDATE items SET quantity = " + quantity + " WHERE id = " + item.getId();
        rowsAffected = runSql(sql);
        return rowsAffected;
    }

    // closes the connection to the database
    public void closeConnection() {
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
}
