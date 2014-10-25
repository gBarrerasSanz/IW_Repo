package ws;


<<<<<<< HEAD
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;

=======
>>>>>>> 3b34392188c5634747d8be561097a1092059681a
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

import common.ToDoElement;
import common.ToDoList;
import actions.AddToDoElem;
import actions.DelToDoElem;
import actions.GetToDoList;


@WebService
<<<<<<< HEAD
public class ToDoWS {
	@WebMethod
	public String sayHello(String name) {
		return "Hello "+name +"!";
	}
	
	@WebMethod
	public String listToDo() {
		GetToDoList getter = new GetToDoList();
		ToDoList list = getter.getAll();
		if(list.size() > 0){
			StringBuilder sb = new StringBuilder();
			ToDoElement elem;
			for (int i=0; i<list.size(); i++){
				elem = list.getElem(i);
				sb.append("ToDo Element ID = "+i+"\n");
				sb.append("\tTask: "+elem.getTask()+"\n");
				sb.append("\tContext: "+elem.getTask()+"\n");
				sb.append("\tProject: "+elem.getTask()+"\n");
				sb.append("\tPriority: "+elem.getTask()+"\n");
			}
			return sb.toString();
		}
		else{
			return "Empty List";
		}
	}
	
	@WebMethod
	public String addToDo(
			 @WebParam (name="task")String task,  @WebParam (name="context")String context,
			 @WebParam (name="project")String project,  @WebParam (name="priority")int priority){
		AddToDoElem adder = new AddToDoElem();
		boolean success = adder.add(new ToDoElement(task, context, project, priority));
		if (success) return "Added elem ("+task+", "+context+", "+project+", "+priority+")";
		else		 return "Error while adding";
	}
	
	@WebMethod
	public String removeToDo( @WebParam int id){
		DelToDoElem deleter = new DelToDoElem();
		boolean success =deleter.del(id);
		if (success) return "Removed element "+id;
		else		 return "Error while removing";
=======
public class ToDoWS {	
	@WebMethod
	public ToDoList listToDo() {
		GetToDoList getter = new GetToDoList();
		return getter.getAll();
	}
	
	@WebMethod
	public boolean addToDo(
			 @WebParam (name="task")String task,  @WebParam (name="context")String context,
			 @WebParam (name="project")String project,  @WebParam (name="priority")int priority){
		AddToDoElem adder = new AddToDoElem();
		return adder.add(new ToDoElement(task, context, project, priority));
	}
	
	@WebMethod
	public boolean removeToDo(@WebParam int id){
		DelToDoElem deleter = new DelToDoElem();
		return deleter.del(id);
>>>>>>> 3b34392188c5634747d8be561097a1092059681a
	}
}
