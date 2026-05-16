package com.servlet;

import com.dao.MarkDAO;
import com.model.StudentMark;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/DeleteMarkServlet")
public class DeleteMarkServlet extends HttpServlet {
    
    // Handle GET request - Show student details for confirmation
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        try {
            int studentId = Integer.parseInt(request.getParameter("studentId"));
            MarkDAO dao = new MarkDAO();
            StudentMark student = dao.getStudentById(studentId);
            
            if (student != null) {
                request.setAttribute("studentToDelete", student);
                request.getRequestDispatcher("markdelete.jsp").forward(request, response);
            } else {
                request.setAttribute("message", "Student with ID " + studentId + " not found!");
                request.setAttribute("messageType", "error");
                request.getRequestDispatcher("markdelete.jsp").forward(request, response);
            }
        } catch (Exception e) {
            request.setAttribute("message", "Error: " + e.getMessage());
            request.setAttribute("messageType", "error");
            request.getRequestDispatcher("markdelete.jsp").forward(request, response);
        }
    }
    
    // Handle POST request - Perform actual deletion
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        try {
            int studentId = Integer.parseInt(request.getParameter("studentId"));
            MarkDAO dao = new MarkDAO();
            
            // Get student details before deletion for confirmation message
            StudentMark student = dao.getStudentById(studentId);
            
            if (dao.deleteStudent(studentId)) {
                request.setAttribute("message", "Student record deleted successfully!\n" +
                        "Deleted Student: " + student.getStudentName() + " (ID: " + studentId + ")");
                request.setAttribute("messageType", "success");
            } else {
                request.setAttribute("message", "Failed to delete student record!");
                request.setAttribute("messageType", "error");
            }
        } catch (Exception e) {
            request.setAttribute("message", "Error: " + e.getMessage());
            request.setAttribute("messageType", "error");
        }
        
        request.getRequestDispatcher("markdelete.jsp").forward(request, response);
    }
}