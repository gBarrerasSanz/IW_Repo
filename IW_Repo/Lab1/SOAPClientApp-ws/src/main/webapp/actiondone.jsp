<html>
<head>
<title>Action Done</title>
<%@ page import="ws.*" %>
<%@ page import="java.util.List" %>
<%!
private String showList(ToDoList toDolist){
	StringBuilder sb = new StringBuilder();
	List<ToDoElement> list = toDolist.getToDoList();
	ToDoElement elem = null;
	for (int i=0; i<list.size(); i++){
		elem = list.get(i);
		sb.append("<h4>ToDo Element ID = "+i+"<h4>"
		+"<ul>"
			+"<li><b>Task:</b> "+elem.getTask()+"</li>"
	 		+"<li><b>Context:</b> "+elem.getContext()+"</li>"
	  		+"<li><b>Project:</b> "+elem.getProject()+"</li>"
	  		+"<li><b>Priority:</b> "+elem.getPriority()+"</li>"
	  	+"</ul>");
	}
	return sb.toString();
}
%>
</head>
<body>
<%	String action = (String)request.getAttribute("action"); 
	boolean success = false;
	String result_msg = "";
	ToDoWSService wss = new ToDoWSService();
	ToDoWS ws = wss.getToDoWSPort();
	switch(action){
	case "add":
		int priority = 0;
		try{ priority = Integer.parseInt((String)request.getAttribute("priority")); }catch(Exception ex){}
		success = ws.addToDo((String)request.getAttribute("task"), (String)request.getAttribute("context"), 
				(String)request.getAttribute("project"), priority);
		break;
	case "remove":
		success = ws.removeToDo(Integer.parseInt((String)request.getAttribute("id")));
		break;
	case "list":
		ToDoList list= ws.listToDo();
		%>
		<%=showList(list)%>
	<%
		success = true;
		break;
	default:
		break;
	}
	result_msg = "Action "+action+": ";
	if (success) result_msg += "sucessful";
	else		 result_msg += "error";
	%>
<h2><%=result_msg %></h2>
</body>
</html>