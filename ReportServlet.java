package com.servlet;

import java.io.IOException;
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

@WebServlet("/ReportServlet")
public class ReportServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            String type = request.getParameter("type");
            MarkDAO dao = new MarkDAO();
            List<StudentMark> reportList = null;

            // 🔹 Marks above given value
            if ("above".equals(type)) {
                int marks = Integer.parseInt(request.getParameter("marks"));
                reportList = dao.getMarksAbove(marks);
            }

            // 🔹 Subject-wise report
            else if ("subject".equals(type)) {
                String subject = request.getParameter("subject");
                reportList = dao.getBySubject(subject);
            }

            // 🔹 Top N students
            else if ("top".equals(type)) {
                int n = Integer.parseInt(request.getParameter("topn"));
                reportList = dao.getTopStudents(n);
            }

            // Send data to JSP
            request.setAttribute("reportList", reportList);

            RequestDispatcher rd = request.getRequestDispatcher("report_result.jsp");
            rd.forward(request, response);

        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().println("Error generating report");
        }
    }
}
