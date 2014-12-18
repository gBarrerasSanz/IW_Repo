package websocket.common;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;


public class ToDoList {
	
	private List<ToDoElement> toDoList;
	
	public ToDoList(){
		toDoList = new ArrayList<ToDoElement>();
	}
	public List<ToDoElement> getToDoList() {
		return toDoList;
	}
	
	public ToDoElement getElement(int id) throws NoSuchElementException {
		for (int i = 0; i < toDoList.size(); i++) {
			if (toDoList.get(i).getId() == id) {
				return toDoList.get(i);
			}
		}
		throw new NoSuchElementException();
	}
	
	public void setToDoList(List<ToDoElement> elem) {
		this.toDoList = elem;
	}
	public void addToDoElem(ToDoElement elem) {
		elem.setId(getId());
		toDoList.add(elem);
	}
	
	public boolean deleteToDoElem(int id) {
		try{
			return toDoList.remove(toDoList.get(id));
		}
		catch(Exception ex){ return false; }
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
	
	private int getId(){
		int val = -1;
		int size = toDoList.size();
		if(size == 0){ // If list is empty -> Assign id 1
			val = 1;
		}
		else { // If list is NOT empty -> Assign id of last element + 1 
			val = toDoList.get(size - 1).getId() + 1;
		}
		return val;
	}
}
