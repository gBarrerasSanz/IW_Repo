package common;

import java.util.ArrayList;
import java.util.List;

public class ToDoList {
	
	private List<ToDoElement> toDoList = new ArrayList<ToDoElement>();

	public List<ToDoElement> getToDoList() {
		return toDoList;
	}

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
}
