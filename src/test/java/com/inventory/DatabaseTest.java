package com.inventory;

import com.entity.*;
import junit.framework.TestCase;
import org.junit.Test;

import javax.xml.crypto.Data;
import java.sql.SQLException;
import java.util.ArrayList;

public class DatabaseTest extends TestCase {

    @Test
    public void testGetConnection() {
        Database database = new Database();
        database.getConnection();
    }

    public void testAddItem() {
        Database database = new Database();
        String sql = database.buildSQL("Pencil", "0.50", 10);
        int rows = database.runSql(sql);
        System.out.println(rows);
        assertFalse(rows < 1);
    }

    public void testAddLocaiton() {
        Database database = new Database();
        String sql = database.buildSQL("add", "B", "7");
        int rows = database.runSql(sql);
        System.out.println(rows);
        assertTrue(rows == 0 || rows == 1);
    }

    public void testSelectLocation() {
        Database database = new Database();
        String sql = database.buildSQL("select", "B", "7");
        Location location = database.getLocation(sql);
        System.out.println(location);
        assertEquals(1, location.getId());
    }

    public void testSearchItem() {
        Database database = new Database();
        ArrayList<Item> items = database.searchItem("Pen");
        System.out.println(items);

    }

    public void testAddItemToLocation() {
        Database database = new Database();
        ArrayList<Item> items = database.searchItem("ball");
        String sql = database.buildSQL("select", "B", "7");
        Location location = database.getLocation(sql);
        sql = database.buildSQL("add", items.get(0), location);
        int rowsAffected = database.runSql(sql);
        assertTrue(rowsAffected == 0 || rowsAffected == 1);
    }

    public void testGetItemById() {
        Database database = new Database();
        String sql = database.buildSQL("item", 1);
        Item item = database.getItemById(sql);
        System.out.println(item);
        assertEquals("Pen", item.getName());
    }

    public void testGetLocationById() {
        Database database = new Database();
        String sql = database.buildSQL("location", 1);
        Location location = database.getLocation(sql);
        System.out.println(location);
        assertEquals("B", location.getSection());
    }

    public void testGetItemLocation() {
        Database database = new Database();
        String sql = database.buildSQL("location", 1);
        Location location = database.getLocation(sql);
        sql = database.buildSQL("item", 2);
        Item item = database.getItemById(sql);
        sql = database.buildSQL("select", item, location);
        ItemLocation itemLocation = database.getItemLocation(sql);
        assertEquals(2, itemLocation.getItemId());
        assertEquals(1, itemLocation.getLocationId());
    }

    public void testUpdateItemLocation() {
        Database database = new Database();
        String sql = database.buildSQL("location", 1);
        Location location = database.getLocation(sql);
        sql = database.buildSQL("item", 2);
        Item item = database.getItemById(sql);
        int rowsAffected = database.removeItemFromLocation(item, location);
        System.out.println(rowsAffected);
        assertTrue(rowsAffected == 1 || rowsAffected == 0);
    }
}