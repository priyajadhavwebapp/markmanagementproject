<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.model.StudentMark" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Delete Student Record</title>
    <style>
        * {
            margin: 0;
            padding: 0;
            box-sizing: border-box;
        }
        
        body {
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
            background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
            min-height: 100vh;
            padding: 20px;
        }
        
        .container {
            max-width: 700px;
            margin: 50px auto;
            background: white;
            border-radius: 20px;
            padding: 40px;
            box-shadow: 0 20px 60px rgba(0,0,0,0.3);
        }
        
        h2 {
            color: #667eea;
            margin-bottom: 30px;
            text-align: center;
        }
        
        .warning {
            background: #fff3cd;
            color: #856404;
            padding: 15px;
            border-radius: 8px;
            margin-bottom: 20px;
            text-align: center;
            border-left: 4px solid #ffc107;
        }
        
        .form-group {
            margin-bottom: 20px;
        }
        
        label {
            display: block;
            margin-bottom: 8px;
            font-weight: bold;
        }
        
        input {
            width: 100%;
            padding: 12px;
            border: 2px solid #ddd;
            border-radius: 8px;
            font-size: 16px;
        }
        
        button {
            width: 100%;
            padding: 12px;
            background: #dc3545;
            color: white;
            border: none;
            border-radius: 8px;
            font-size: 18px;
            cursor: pointer;
            transition: background 0.3s;
        }
        
        button:hover {
            background: #c82333;
        }
        
        .message {
            padding: 12px;
            border-radius: 8px;
            margin-bottom: 20px;
            text-align: center;
        }
        
        .success { background: #d4edda; color: #155724; }
        .error { background: #f8d7da; color: #721c24; }
        
        .student-details {
            background: #f8f9fa;
            border-radius: 10px;
            padding: 20px;
            margin: 20px 0;
            border: 2px solid #dee2e6;
        }
        
        .student-details h3 {
            color: #764ba2;
            margin-bottom: 15px;
            border-bottom: 2px solid #dee2e6;
            padding-bottom: 10px;
        }
        
        .detail-row {
            display: flex;
            padding: 10px 0;
            border-bottom: 1px solid #e9ecef;
        }
        
        .detail-label {
            font-weight: bold;
            width: 120px;
            color: #495057;
        }
        
        .detail-value {
            color: #212529;
        }
        
        .button-group {
            display: flex;
            gap: 10px;
            margin-top: 20px;
        }
        
        .cancel-btn {
            background: #6c757d;
            text-decoration: none;
            text-align: center;
            line-height: 42px;
        }
        
        .cancel-btn:hover {
            background: #5a6268;
        }
        
        .back-btn {
            display: inline-block;
            margin-top: 20px;
            text-align: center;
            width: 100%;
            text-decoration: none;
            color: #667eea;
        }
    </style>
</head>
<body>
    <div class="container">
        <h2>🗑️ Delete Student Record</h2>
        
        <div class="warning">
            ⚠️ Warning: This action cannot be undone! The record will be permanently deleted.
        </div>
        
        <% if(request.getAttribute("message") != null) { %>
            <div class="message <%= request.getAttribute("messageType") %>">
                <%= request.getAttribute("message") %>
            </div>
        <% } %>
        
        <!-- Search Form -->
        <form action="DeleteMarkServlet" method="get" id="searchForm">
            <div class="form-group">
                <label>Enter Student ID to Delete:</label>
                <input type="number" name="studentId" id="studentId" required>
            </div>
            <button type="submit">Search Student</button>
        </form>
        
        <!-- Display Student Details if found -->
        <%
        StudentMark studentToDelete = (StudentMark) request.getAttribute("studentToDelete");
        if(studentToDelete != null) {
        %>
        <div class="student-details">
            <h3>📋 Student Record to Delete</h3>
            <div class="detail-row">
                <div class="detail-label">Student ID:</div>
                <div class="detail-value"><%= studentToDelete.getStudentId() %></div>
            </div>
            <div class="detail-row">
                <div class="detail-label">Student Name:</div>
                <div class="detail-value"><%= studentToDelete.getStudentName() %></div>
            </div>
            <div class="detail-row">
                <div class="detail-label">Subject:</div>
                <div class="detail-value"><%= studentToDelete.getSubject() %></div>
            </div>
            <div class="detail-row">
                <div class="detail-label">Marks:</div>
                <div class="detail-value"><%= studentToDelete.getMarks() %></div>
            </div>
            <div class="detail-row">
                <div class="detail-label">Exam Date:</div>
                <div class="detail-value"><%= studentToDelete.getExamDate() %></div>
            </div>
        </div>
        
        <div class="button-group">
            <form action="DeleteMarkServlet" method="post" onsubmit="return confirm('Are you absolutely sure you want to delete this record?\n\nStudent: <%= studentToDelete.getStudentName() %>\nID: <%= studentToDelete.getStudentId() %>\nSubject: <%= studentToDelete.getSubject() %>\nMarks: <%= studentToDelete.getMarks() %>');">
                <input type="hidden" name="studentId" value="<%= studentToDelete.getStudentId() %>">
                <button type="submit" style="background: #dc3545;">✓ Confirm Deletion</button>
            </form>
            <button onclick="document.getElementById('searchForm').reset(); window.location.href='markdelete.jsp';" class="cancel-btn" style="background: #6c757d; border: none; padding: 12px;">✗ Clear & Search New</button>
        </div>
        <% } %>
        
        <a href="index.jsp" class="back-btn">← Back to Dashboard</a>
    </div>
</body>
</html>