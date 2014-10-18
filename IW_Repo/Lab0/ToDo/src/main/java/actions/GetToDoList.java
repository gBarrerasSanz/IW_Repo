package actions;

import common.ToDoElement;
import common.ToDoList;

public class GetToDoList extends Action {

	public GetToDoList() {
		super();
	}
	
	public ToDoList getAll(){
		return list;
	}
	
	public ToDoList getByField(String field, String text){
		ToDoList newlist=new ToDoList();
		switch(field){
		case "task":
			for (int i=0; i<list.size();i++){
				ToDoElement elem = list.getElem(i); 
				if(elem.getTask().toLowerCase().equalsIgnoreCase((text)))
					newlist.addToDoElem(elem);
			}
			break;
		case "context":
			for (int i=0; i<list.size();i++){
				ToDoElement elem = list.getElem(i); 
				if(elem.getContext().toLowerCase().equalsIgnoreCase((text)))
					newlist.addToDoElem(elem);
			}
			break;
		case "project":
			for (int i=0; i<list.size();i++){
				ToDoElement elem = list.getElem(i); 
				if(elem.getProject().toLowerCase().equalsIgnoreCase((text)))
					newlist.addToDoElem(elem);
			}
			break;
		case "priority":
			for (int i=0; i<list.size();i++){
				ToDoElement elem = list.getElem(i); 
				if(Integer.toString(elem.getPriority()).toLowerCase().equalsIgnoreCase((text)))
					newlist.addToDoElem(elem);
			}
			break;
		default:
			break;
		}
		return newlist;
	}
	
	
}
