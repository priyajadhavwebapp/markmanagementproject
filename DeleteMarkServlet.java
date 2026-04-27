package com.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.MarkDAO;

public class DeleteMarkServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            // Get Student ID from form
            int id = Integer.parseInt(request.getParameter("id"));

            // Call DAO to delete record
            MarkDAO dao = new MarkDAO();
            dao.deleteMark(id);

            // Redirect to display page
            response.sendRedirect("DisplayMarksServlet");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}