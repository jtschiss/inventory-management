package com.controller;

import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import com.inventory.Database;
import com.entity.*;

@WebServlet(
        name = "itemData",
        urlPatterns = { "/itemData", "/ItemData" }
)
public class ItemData extends HttpServlet {

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
        String parameter = req.getParameter("id");
        int id = Integer.parseInt(parameter);

        String sql = database.buildSQL("item", id);
        Item item = database.getItemById(sql);

        req.setAttribute("item", item);

        RequestDispatcher dispatcher = req.getRequestDispatcher("/itemData.jsp");
        dispatcher.forward(req, resp);
    }

}
