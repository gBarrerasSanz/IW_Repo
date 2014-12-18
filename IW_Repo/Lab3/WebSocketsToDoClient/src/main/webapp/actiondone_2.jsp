<html>
<head>
<script src="jquery-2.1.0.min.js"></script>
<title>Action Done</title>

<%@ page import="websocket.common.*" %>
<%@ page import="websocket.client.*" %>
<%@ page import="java.io.IOException" %>
<%@ page import="java.net.URI" %>
<%@ page import="javax.websocket.ClientEndpointConfig" %>
<%@ page import="javax.websocket.Endpoint" %>
<%@ page import="javax.websocket.EndpointConfig" %>
<%@ page import="javax.websocket.MessageHandler" %>
<%@ page import="javax.websocket.Session" %>
<%@ page import="org.glassfish.tyrus.client.ClientManager" %>
<%@ page import="java.util.List" %>
<%@ page import ="com.google.gson.Gson" %>
<%!
Session session;
Gson gson = new Gson();

private void connect() {
	try {
		final ClientEndpointConfig configuration = ClientEndpointConfig.Builder.create().build();
		ClientManager client = ClientManager.createClient();
		client.connectToServer(
				new Endpoint() {

					@Override
					public void onOpen(Session session,EndpointConfig config) {
							session.addMessageHandler(new MessageHandler.Whole() {
								@Override
								public void onMessage(Object message) {
									/*
									WebSocketMessage msgResp = gson.fromJson(message.toString(), 
											WebSocketMessage.class);
									String showMsg="None";
									if(msgResp.getRespVal() != null){
										if(msgResp.getRespVal() == WebSocketMessage.RESP_VAL.OK){
											showMsg = "Successful";
										}
										else{
											showMsg = "Error";
										}
									}
									*/
									String showMsg = "None";
									%>
									<script>
										$("#result").val("<%!showMsg.toString();%>");
									</script>
									<%!
									
								}
	                        });
					}
				},
				configuration,new URI("ws://localhost:8025/websockets/todo"));

	} catch (Exception e) {
		e.printStackTrace();
	}
}

public void sendMessage(String message) throws IOException, InterruptedException{
	session.getBasicRemote().sendText(message);
}
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
	WebSocketMessage msg = new WebSocketMessage(WebSocketMessage.OPERATION.EMPTY);
	//WebSocketMessage msgResp = new WebSocketMessage(WebSocketMessage.OPERATION.EMPTY);
	ToDoList tdRetrieved = null;
	ToDoElement elem = null;
	int id = -1;
	connect();
	switch(action){
	case "add":
		elem = new ToDoElement();
		elem.setTask((String)request.getAttribute("task"));
		elem.setContext((String)request.getAttribute("context"));
		elem.setProject((String)request.getAttribute("project"));
		try{
			elem.setPriority(Integer.parseInt((String)request.getAttribute("priority")));
		}catch(Exception ex){}
		/* resp = client.target("http://localhost:8082/todo")
				.request(MediaType.APPLICATION_JSON)
				.post(Entity.entity(elem, MediaType.APPLICATION_JSON));
		success = resp.getStatus() == 201; */
		msg.setOperation(WebSocketMessage.OPERATION.ADD_ELEM);
		msg.setToDoElem(elem);
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
		/* resp = client.target("http://localhost:8082/todo/elem/"+id)
				.request(MediaType.APPLICATION_JSON)
				.put(Entity.entity(elem, MediaType.APPLICATION_JSON)); */
		msg.setOperation(WebSocketMessage.OPERATION.UPDATE_ELEM);
		msg.setId(id);
		msg.setToDoElem(elem);
		break;
	case "remove":
		try{
			id = Integer.parseInt((String)request.getAttribute("id"));
		}catch(Exception ex){
		}
		/* resp = client.target("http://localhost:8082/todo/elem/"+id)
			.request(MediaType.APPLICATION_JSON)
			.delete(); */
			msg.setOperation(WebSocketMessage.OPERATION.DEL_ELEM);
			msg.setId(id);
		break;
	case "list":
		/* resp = client.target("http://localhost:8082/todo")
			.request(MediaType.APPLICATION_JSON)
			.get();
		tdRetrieved = resp.readEntity(ToDoList.class); */
		msg.setOperation(WebSocketMessage.OPERATION.GET_LIST);
		break;
	default:
		break;
	}
	//sendMessage(gson.toJson(msg));
	//sendMessage("Hola");
	%>
<h2><label id=result></label></h2>
</body>
</html>