package com.controller;

import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import com.inventory.Database;
import com.entity.*;

@WebServlet(
        name = "itemSearch",
        urlPatterns = { "/itemSearch", "/ItemSearch" }
)
public class ItemSearch extends HttpServlet {

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
        System.out.println("We're in!");

        Database database = new Database();
        String search = req.getParameter("search");

        ArrayList<Item> results = database.searchItem(search);

        req.setAttribute("results", results);

        RequestDispatcher dispatcher = req.getRequestDispatcher("/results.jsp");
        dispatcher.forward(req, resp);
    }

}
