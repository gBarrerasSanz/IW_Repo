package ws;


import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

import common.ToDoElement;
import common.ToDoList;
import actions.AddToDoElem;
import actions.DelToDoElem;
import actions.GetToDoList;


@WebService
public class ToDoWS {	
	@WebMethod
	public ToDoList listToDo() {
		return new GetToDoList().getList();
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
	}
}
