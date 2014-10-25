package common;

import java.util.ArrayList;
import java.util.List;

public class ToDoList {
	
	private List<ToDoElement> toDoList = new ArrayList<ToDoElement>();
	
	public ToDoList(){
		super();
	}
	/*
	public List<ToDoElement> getToDoList() {
		return toDoList;
	}
	*/

	public void setToDoList(List<ToDoElement> elem) {
		this.toDoList = elem;
	}

	public void addToDoElem(ToDoElement elem) {
		toDoList.add(elem);
	}
	
	public boolean deleteToDoElem(int id) {
		toDoList.remove(toDoList.get(id));
		return true;
	}
	
	public int size(){
		return toDoList.size();
	}
	
	public ToDoElement getElem (int i){
		return toDoList.get(i);
	}
	
	public String toString(){
		StringBuilder sb = new StringBuilder();
		int i=0;
		if(toDoList.size() > 0){
			for (ToDoElement toDoElem : toDoList) {
				sb.append("ToDo Element ID = "+i+"\n");
				sb.append("\tTask: " + toDoElem.getTask()+"\n");
				sb.append("\tContext: " + toDoElem.getContext()+"\n");
				sb.append("\tProject: " + toDoElem.getProject()+"\n");
				sb.append("\tPriority: " + toDoElem.getPriority()+"\n\n");
				i++;
			}
		}
		else{	//Empty list
			sb.append("<Empty list>");
		}
		return sb.toString();
	}
}
