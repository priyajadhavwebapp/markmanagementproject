package com.servlet;

import java.io.IOException;
import javax.servlet.*;
import javax.servlet.http.*;
import com.dao.MarkDAO;
import com.model.StudentMark;
import java.text.SimpleDateFormat;

public class AddMarkServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {

        try {
            StudentMark mark = new StudentMark();

            mark.setStudentID(Integer.parseInt(request.getParameter("id")));
            mark.setStudentName(request.getParameter("name"));
            mark.setSubject(request.getParameter("subject"));
            mark.setMarks(Integer.parseInt(request.getParameter("marks")));

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            mark.setExamDate(sdf.parse(request.getParameter("date")));

            MarkDAO dao = new MarkDAO();
            dao.insertMark(mark);

            response.sendRedirect("markdisplay.jsp");

        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}