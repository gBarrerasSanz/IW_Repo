<html>
<head>
<title>Test</title>>
<%@page contentType="text/html" pageEncoding="MacRoman"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<%@ page import="java.io.IOException" %>
<%@ page import="java.net.URI" %>
<%@ page import="java.util.Random" %>
<%@ page import="java.util.Timer" %>
<%@ page import="java.util.TimerTask" %>
<%@ page import="java.util.concurrent.CountDownLatch" %>
<%@ page import="java.util.logging.Logger" %>

<%@ page import="javax.websocket.ClientEndpointConfig" %>
<%@ page import="javax.websocket.Endpoint" %>
<%@ page import="javax.websocket.EndpointConfig" %>
<%@ page import="javax.websocket.MessageHandler" %>
<%@ page import="javax.websocket.Session" %>

<%@ page import="org.glassfish.grizzly.Grizzly" %>
<%@ page import="org.glassfish.tyrus.client.ClientManager" %>
<%@ page import="org.glassfish.tyrus.server.Server" %>
<%!
public static class Client {
	private Session session;
	public static final Logger LOGGER = Grizzly.logger(Server.class);
	static CountDownLatch latch = latch = new CountDownLatch(1);
	public void connect() {
		try {
			final ClientEndpointConfig configuration = ClientEndpointConfig.Builder.create().build();
			ClientManager client = ClientManager.createClient();
			client.connectToServer(
					new Endpoint() {

						@Override
						public void onOpen(Session session,EndpointConfig config) {
								Client.this.session = session;
								Client.this.session.addMessageHandler(new MessageHandler.Whole() {
									@Override
									public void onMessage(Object message) {
										LOGGER.info("C: "+message);
										String msg = message.toString();
										%>
										<script>
										document.getElementById("lb1").value = (<%!msg.toString();%>);
										</script>
										<%!
									}
		                        });
						}
					},
					configuration,new URI("ws://localhost:8025/websockets/game"));

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void sendMessage(String message) throws IOException, InterruptedException{
		session.getBasicRemote().sendText(message);
		LOGGER.info("C: "+message);
	}
	
/* 	public static void main(String[] args) {
		Client client = new Client();
		client.connect();
		Timer timer = new Timer();
		timer.schedule(new RandomMessager(client), 0, 5000);
		try {
			latch.await();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	} */
	
}
public static class RandomMessager extends TimerTask {
	Client client;
	public RandomMessager(Client c){
		client = c;
	}
    public void run() {
    	Random r = new Random();
    	String s = "C->"+r.nextInt(500);
    	try {
			client.sendMessage(s);
		} catch (IOException | InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
 }
%>
<% 
Client client = new Client();
client.connect();
Timer timer = new Timer();
//timer.schedule(new RandomMessager(client), 0, 5000);
client.sendMessage("Hola");
Client.LOGGER.info("Hola enviado.");
%>
</head>
<body>
<label id="lb1" value="None">None</label>
</body>
</html>