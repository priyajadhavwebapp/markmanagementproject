package com.dao;

import java.sql.*;
import java.util.*;
import com.model.StudentMark;

public class MarkDAO {
    private String jdbcURL = "jdbc:mysql://localhost:3306/MarkDB";
    private String jdbcUsername = "root";
    private String jdbcPassword = "root";

    private static final String INSERT_SQL = "INSERT INTO StudentMarks VALUES (?, ?, ?, ?, ?)";
    private static final String SELECT_ALL = "SELECT * FROM StudentMarks";
    private static final String DELETE_SQL = "DELETE FROM StudentMarks WHERE StudentID=?";
    private static final String UPDATE_SQL = "UPDATE StudentMarks SET StudentName=?, Subject=?, Marks=?, ExamDate=? WHERE StudentID=?";

    protected Connection getConnection() {
        Connection con = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
        } catch(Exception e) { e.printStackTrace(); }
        return con;
    }

    public void insertMark(StudentMark mark) throws SQLException {
        try(Connection con = getConnection();
            PreparedStatement ps = con.prepareStatement(INSERT_SQL)) {
            ps.setInt(1, mark.getStudentID());
            ps.setString(2, mark.getStudentName());
            ps.setString(3, mark.getSubject());
            ps.setInt(4, mark.getMarks());
            ps.setDate(5, new java.sql.Date(mark.getExamDate().getTime()));
            ps.executeUpdate();
        }
    }

    public List<StudentMark> selectAll() {
        List<StudentMark> list = new ArrayList<>();
        try(Connection con = getConnection();
            PreparedStatement ps = con.prepareStatement(SELECT_ALL)) {
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                StudentMark m = new StudentMark();
                m.setStudentID(rs.getInt("StudentID"));
                m.setStudentName(rs.getString("StudentName"));
                m.setSubject(rs.getString("Subject"));
                m.setMarks(rs.getInt("Marks"));
                m.setExamDate(rs.getDate("ExamDate"));
                list.add(m);
            }
        } catch(Exception e) { e.printStackTrace(); }
        return list;
    }

    public void deleteMark(int id) throws SQLException {
        try(Connection con = getConnection();
            PreparedStatement ps = con.prepareStatement(DELETE_SQL)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        }
    }

    public void updateMark(StudentMark mark) throws SQLException {
        try(Connection con = getConnection();
            PreparedStatement ps = con.prepareStatement(UPDATE_SQL)) {
            ps.setString(1, mark.getStudentName());
            ps.setString(2, mark.getSubject());
            ps.setInt(3, mark.getMarks());
            ps.setDate(4, new java.sql.Date(mark.getExamDate().getTime()));
            ps.setInt(5, mark.getStudentID());
            ps.executeUpdate();
        }
    }

	public List<StudentMark> getAllMarks() {
		// TODO Auto-generated method stub
		return null;
	}

	public List<StudentMark> getTopStudents(int n) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<StudentMark> getBySubject(String subject) {
		// TODO Auto-generated method stub
		return null;
	}
}