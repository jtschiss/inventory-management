package com.controller;

import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import com.inventory.Database;
import com.entity.*;

@WebServlet(
        name = "addItem",
        urlPatterns = { "/addItem", "/AddItem" }
)
public class AddItem extends HttpServlet {

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
        String name = req.getParameter("name");
        String price = req.getParameter("price");
        String parameter = req.getParameter("quantity");
        int quantity = Integer.parseInt(parameter);

        String sql = database.buildSQL(name, price, quantity);
        int success = database.runSql(sql);

        if(success == 1) {
            req.setAttribute("confirmation", "Item added successfully!");
        } else {
            req.setAttribute("confirmation", "Item unable to be added");
        }

        RequestDispatcher dispatcher = req.getRequestDispatcher("/addItems.jsp");
        dispatcher.forward(req, resp);
    }

}
