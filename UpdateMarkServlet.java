package com.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.MarkDAO;
import com.model.StudentMark;

import java.text.SimpleDateFormat;

public class UpdateMarkServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            // Create object
            StudentMark mark = new StudentMark();

            // Get values from form
            mark.setStudentID(Integer.parseInt(request.getParameter("id")));
            mark.setStudentName(request.getParameter("name"));
            mark.setSubject(request.getParameter("subject"));
            mark.setMarks(Integer.parseInt(request.getParameter("marks")));

            // Convert date
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            mark.setExamDate(sdf.parse(request.getParameter("date")));

            // Call DAO method
            MarkDAO dao = new MarkDAO();
            dao.updateMark(mark);

            // Redirect to display page
            response.sendRedirect("DisplayMarksServlet");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}