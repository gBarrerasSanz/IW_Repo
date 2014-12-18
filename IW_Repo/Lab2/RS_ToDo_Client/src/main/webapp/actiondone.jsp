<html>
<head>
<title>Action Done</title>
<%@ page import="javax.ws.rs.core.Response" %>
<%@ page import="java.util.List" %>
<%@ page import="javax.ws.rs.core.MediaType" %>
<%@ page import="javax.ws.rs.client.Client" %>
<%@ page import="javax.ws.rs.client.ClientBuilder" %>
<%@ page import="websocket.common.ToDoElement" %>
<%@ page import="websocket.common.ToDoList" %>
<%@ page import="javax.ws.rs.client.Entity" %>
<%!
private String showList(ToDoList toDolist){
	StringBuilder sb = new StringBuilder();
	List<ToDoElement> list = toDolist.getToDoList();
	ToDoElement elem = null;
	for (int i=0; i<list.size(); i++){
		elem = list.get(i);
		sb.append("<h4>ToDo Element ID = "+elem.getId()+"<h4>"
		+"<ul>"
			+"<li><b>Task:</b> "+elem.getTask()+"</li>"
	 		+"<li><b>Context:</b> "+elem.getContext()+"</li>"
	  		+"<li><b>Project:</b> "+elem.getProject()+"</li>"
	  		+"<li><b>Priority:</b> "+elem.getPriority()+"</li>"
	  		+"<li><b>URI:</b> "+elem.getHref()+"</li>"
	  	+"</ul>");
	}
	return sb.toString();
}

private String dbg(String s){
	return "dbg: "+s;
}
%>
</head>
<body>
<%	String action = (String)request.getAttribute("action"); 
	boolean success = false;
	String result_msg = "";
	Client client = ClientBuilder.newClient();
	Response resp = null;
	ToDoList tdRetrieved = null;
	ToDoElement elem = null;
	int id = -1;
	switch(action){
	case "add":
		elem = new ToDoElement();
		elem.setTask((String)request.getAttribute("task"));
		elem.setContext((String)request.getAttribute("context"));
		elem.setProject((String)request.getAttribute("project"));
		try{
			elem.setPriority(Integer.parseInt((String)request.getAttribute("priority")));
		}catch(Exception ex){}
		resp = client.target("http://localhost:8082/todo")
				.request(MediaType.APPLICATION_JSON)
				.post(Entity.entity(elem, MediaType.APPLICATION_JSON));
		success = resp.getStatus() == 201;
		break;
	case "update":
		elem = new ToDoElement();
		elem.setTask((String)request.getAttribute("task"));
		elem.setContext((String)request.getAttribute("context"));
		elem.setProject((String)request.getAttribute("project"));
		try{
			elem.setPriority(Integer.parseInt((String)request.getAttribute("priority")));
			id = Integer.parseInt((String)request.getAttribute("id"));
		}catch(Exception ex){
		}
		resp = client.target("http://localhost:8082/todo/elem/"+id)
				.request(MediaType.APPLICATION_JSON)
				.put(Entity.entity(elem, MediaType.APPLICATION_JSON));
		success = resp.getStatus() == 200;
		break;
	case "remove":
		try{
			id = Integer.parseInt((String)request.getAttribute("id"));
		}catch(Exception ex){
		}
		resp = client.target("http://localhost:8082/todo/elem/"+id)
			.request(MediaType.APPLICATION_JSON)
			.delete();
		success = resp.getStatus() == 204;
		break;
	case "list":
		resp = client.target("http://localhost:8082/todo")
			.request(MediaType.APPLICATION_JSON)
			.get();
		tdRetrieved = resp.readEntity(ToDoList.class);
		success = resp.getStatus() == 200;
		%>
		<%=showList(tdRetrieved)%>
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