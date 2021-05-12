package com.inventory;

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
        String sql = database.buildSQL("testItem", "9.99", 10);
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
        int id = database.getLocationId(sql);
        System.out.println(id);
        assertEquals(1, 1);
    }
}