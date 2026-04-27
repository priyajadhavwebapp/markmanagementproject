<link rel="stylesheet" href="css/style.css">

<div class="container">
<h2>Reports</h2>

<form action="ReportServlet" method="post">
<input type="hidden" name="type" value="above">
Marks Above:
<input type="text" name="marks">
<input type="submit" value="Show">
</form>

<form action="ReportServlet" method="post">
<input type="hidden" name="type" value="subject">
Subject:
<input type="text" name="subject">
<input type="submit" value="Show">
</form>

<form action="ReportServlet" method="post">
<input type="hidden" name="type" value="top">
Top N:
<input type="text" name="topn">
<input type="submit" value="Show">
</form>

</div>