package websocket.server;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.glassfish.grizzly.Grizzly;
import org.glassfish.tyrus.server.Server;

import websocket.common.ToDoList;
import websocket.common.jsonIO;
 
public class WebSocketServer {
	private static final Logger LOGGER = Grizzly.logger(Server.class);
	public static ToDoList tdlist;
    public static void main(String[] args) {
        runServer();
    } 
    
    public static void runServer() {
    	jsonIO json = new jsonIO();
		tdlist = json.loadList();
        Server server = new Server("localhost", 8025, "/websockets", new HashMap<String,Object>(), ToDoServerEndpoint.class);
        try {
    		Timer timer = new Timer();
    		timer.schedule(new JsonSaver(json,tdlist), 0, 10000);
    		
            server.start();
			LOGGER.info("Press 's' to shutdown now the server...");
			while(true){
				int c = System.in.read();
				if (c == 's')
					break;
			}
        } catch (Exception e) {
			LOGGER.log(Level.SEVERE, e.toString(), e);
        } finally {
        	json.saveList(tdlist);
            server.stop();
			LOGGER.info("Server stopped");
        }
    }
}

class JsonSaver extends TimerTask {
	jsonIO json;
	ToDoList tdlist;
	public JsonSaver(jsonIO json, ToDoList tdlist){
		this.json = json;
		this.tdlist = tdlist;
	}
    public void run() {
    	json.saveList(tdlist);
    }
 }

