package ws;

import javax.xml.ws.Endpoint;

public class SOAPServer {
	
	public static void main(String[] args) {
		Endpoint.publish("http://localhost:8085/SOAPServer", new ToDoWS());
	}
}
