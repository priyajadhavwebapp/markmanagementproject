<%@ page import="java.util.*, com.model.StudentMark" %>
<link rel="stylesheet" href="css/style.css">

<h2>Student Marks List</h2>

<table>
<tr>
<th>ID</th>
<th>Name</th>
<th>Subject</th>
<th>Marks</th>
<th>Date</th>
</tr>

<%
List<StudentMark> list = (List<StudentMark>) request.getAttribute("markList");

if(list != null){
for(StudentMark m : list){
%>

<tr>
<td><%=m.getStudentID()%></td>
<td><%=m.getStudentName()%></td>
<td><%=m.getSubject()%></td>
<td><%=m.getMarks()%></td>
<td><%=m.getExamDate()%></td>
</tr>

<%
}
}
%>

</table>