package rest;

import java.io.IOException;
import java.net.URI;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ws.rs.core.UriBuilder;

import org.glassfish.grizzly.Grizzly;
import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;

import common.ToDoElement;
import common.ToDoList;
import common.jsonIO;

public class Server {
	private static final Logger LOGGER = Grizzly.logger(Server.class);
	
	public static void main(String[] args) {
		LOGGER.setLevel(Level.FINER);
		jsonIO json = new jsonIO();
		ToDoList tdlist = json.loadList();
		
		// Some dummy data
		ToDoElement elemA = new ToDoElement();
		/*
		elemA.setId(1);
		elemA.setTask("task A");
		elemA.setContext("context A");
		elemA.setProject("project A");
		elemA.setPriority(10);
		elemA.setHref(URI.create("http://localhost:8082/todo/elem/1"));
		ToDoElement elemB = new ToDoElement();
		elemB.setId(2);
		elemB.setTask("task B");
		elemB.setContext("context B");
		elemB.setProject("project B");
		elemB.setPriority(20);
		elemB.setHref(URI.create("http://localhost:8082/todo/elem/2"));
		tdlist.getToDoList().add(elemA);
		tdlist.getToDoList().add(elemB);
		*/
		URI uri = UriBuilder.fromUri("http://localhost/").port(8082).build();
		HttpServer server = GrizzlyHttpServerFactory.createHttpServer(uri,
				new ApplicationConfig(tdlist));
		
		 Timer timer = new Timer();
		 timer.schedule(new JsonSaver(json,tdlist), 0, 10000);
		try {
			server.start();
			LOGGER.info("Press 's' to shutdown now the server...");
			while(true){
				int c = System.in.read();
				if (c == 's')
					break;
			}
		} catch (IOException ioe) {
			LOGGER.log(Level.SEVERE, ioe.toString(), ioe);
		} finally {
			server.stop();
			LOGGER.info("Server stopped");
			json.saveList(tdlist);
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


