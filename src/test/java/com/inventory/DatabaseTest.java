package com.inventory;

import com.entity.*;
import junit.framework.TestCase;
import org.junit.Test;

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
        assertFalse(rows < 1);
    }

    public void testSelectLocation() {
        Database database = new Database();
        String sql = database.buildSQL("select", "B", "7");
        Location location = database.getLocationId(sql);
        System.out.println(location);
        assertEquals(1, location.getId());
    }

    public void testSearchItem() {
        Database database = new Database();
        ArrayList<Item> items = database.searchItem("Pen");
        System.out.println(items);

    }
}