package com.controller;

import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import com.inventory.Database;
import com.entity.*;

@WebServlet(
        name = "addItemToLocation",
        urlPatterns = { "/addItemToLocation", "/AddItemToLocation" }
)
public class AddItemToLocation extends HttpServlet {

    /**
     *  Handles HTTP GET requests.
     *
     *@param  req                   the HttpServletRequest object
     *@param  resp                   the HttpServletResponse object
     *@exception  ServletException  if there is a Servlet failure
     *@exception  IOException       if there is an IO failure
     */
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        Database database = new Database();
        String parameter = req.getParameter("id");
        int id = Integer.parseInt(parameter);
        String section = req.getParameter("section");
        String shelf = req.getParameter("shelf");

        String sql = database.buildSQL("add", section, shelf);
        if(!sql.equals("")) {
            database.runSql(sql);
        }

        sql = database.buildSQL("select", section, shelf);
        Location location = database.getLocation(sql);

        sql = database.buildSQL("item", id);
        Item item = database.getItemById(sql);

        sql = database.buildSQL("add", item, location);
        if(!sql.equals("")) {
            database.runSql(sql);
        } else {
            sql = database.buildSQL("select", item, location);
            ItemLocation itemLocation = database.getItemLocation(sql);
            database.activateItemLocation(itemLocation);
        }

        req.setAttribute("item", item);


        RequestDispatcher dispatcher = req.getRequestDispatcher("/itemAddConfirmation.jsp");
        dispatcher.forward(req, resp);
    }

}
