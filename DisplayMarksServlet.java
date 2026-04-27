package com.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.MarkDAO;
import com.model.StudentMark;
@WebServlet("/DisplayMarksServlet")
public class DisplayMarksServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            // Create DAO object
            MarkDAO dao = new MarkDAO();

            // Get all records
            List<StudentMark> markList = dao.getAllMarks();

            // Set data to request
            request.setAttribute("markList", markList);

            // Forward to JSP page
            RequestDispatcher rd = request.getRequestDispatcher("markdisplay.jsp");
            rd.forward(request, response);

        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().println("Error fetching data");
        }
    }
}