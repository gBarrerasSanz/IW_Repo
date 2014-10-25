package clientapp;

// import javax.xml.ws.BindingProvider;
import ws.*;

public class Client {
 	
	public static void main(String[] args) {
		ToDoWSService wss = new ToDoWSService();
		ToDoWS ws = wss.getToDoWSPort();
		System.out.println(ws.sayHello("I'm ToDo Client"));
		System.out.println(ws.listToDo());
		int rand = (int) Math.random();
		//System.out.println(ws.addToDo("c", "c", "c", 3));
		ws.addToDo("ab", "ab", "ab", 3);
		//System.out.println(ws.removeToDo());
	}
}
