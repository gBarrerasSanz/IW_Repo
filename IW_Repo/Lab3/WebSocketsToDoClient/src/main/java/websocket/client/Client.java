package websocket.client;

import java.io.IOException;
import java.net.URI;

import javax.websocket.ClientEndpointConfig;
import javax.websocket.Endpoint;
import javax.websocket.EndpointConfig;
import javax.websocket.MessageHandler;
import javax.websocket.Session;

import org.glassfish.tyrus.client.ClientManager;

public class Client {
	private Session session;

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
										
									}
		                        });
						}
					},
					configuration,new URI("ws://localhost:8080/websocket"));

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void sendMessage(String message) throws IOException, InterruptedException{
		session.getBasicRemote().sendText(message);
	}
}