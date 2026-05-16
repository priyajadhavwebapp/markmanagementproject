package com.servlet;

import com.dao.MarkDAO;
import com.model.StudentMark;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;

@WebServlet("/AddMarkServlet")
public class AddMarkServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        try {
            String studentName = request.getParameter("studentName");
            String subject = request.getParameter("subject");
            int marks = Integer.parseInt(request.getParameter("marks"));
            Date examDate = Date.valueOf(request.getParameter("examDate"));
            
            StudentMark student = new StudentMark(studentName, subject, marks, examDate);
            MarkDAO dao = new MarkDAO();
            
            if (dao.addStudent(student)) {
                request.setAttribute("message", "Student marks added successfully! Generated ID: " + student.getStudentId());
                request.setAttribute("messageType", "success");
            } else {
                request.setAttribute("message", "Failed to add student marks!");
                request.setAttribute("messageType", "error");
            }
        } catch (Exception e) {
            request.setAttribute("message", "Error: " + e.getMessage());
            request.setAttribute("messageType", "error");
        }
        
        request.getRequestDispatcher("markadd.jsp").forward(request, response);
    }
}