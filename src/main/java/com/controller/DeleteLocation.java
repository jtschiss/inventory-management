package com.controller;

import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import com.inventory.Database;
import com.entity.*;

@WebServlet(
        name = "deleteLocation",
        urlPatterns = { "/deleteLocation", "/DeleteLocation" }
)
public class DeleteLocation extends HttpServlet {

    /**
     *  Handles HTTP GET requests.
     *
     *@param  req                   the HttpServletRequest object
     *@param  resp                   the HttpServletResponse object
     *@exception  ServletException  if there is a Servlet failure
     *@exception  IOException       if there is an IO failure
     */
    public void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        Database database = new Database();
        String parameter = req.getParameter("location_id");
        int locationId = Integer.parseInt(parameter);
        parameter = req.getParameter("item_id");
        int itemId = Integer.parseInt(parameter);

        String sql = database.buildSQL("location", locationId);
        Location location = database.getLocation(sql);
        sql = database.buildSQL("item", itemId);
        Item item = database.getItemById(sql);

        database.removeItemFromLocation(item, location);

        RequestDispatcher dispatcher = req.getRequestDispatcher("/itemData?id=" + itemId);
        dispatcher.forward(req, resp);
    }

}
