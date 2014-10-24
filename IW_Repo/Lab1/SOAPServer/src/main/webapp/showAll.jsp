<html>
<head>
<title>ToDo Show All</title>
<%@ page import="common.*" %>
<%@ page import="actions.*" %>
</head>
<body>
	<h1>ToDo Show All</h1>
	<%
	GetToDoList getter = new GetToDoList();
	ToDoList list = getter.getAll();
	ToDoElement elem = null;
	%>
	<%
	for (int i=0; i<list.size(); i++){
		elem = list.getElem(i);
	%>
	<h4>ToDo Element ID =<%=i %><h4>
	<ul>
  		<li><b>Task:</b> <%=elem.getTask()%></li>
 		<li><b>Context:</b> <%=elem.getContext() %></li>
  		<li><b>Project:</b> <%=elem.getProject() %></li>
  		<li><b>Priority:</b> <%=elem.getPriority() %></li>
	</ul>
	<%
	}
	%>
</body>
</html>