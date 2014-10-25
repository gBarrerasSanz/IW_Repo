package actions;

import common.ToDoList;
import common.jsonIO;


public class Action {
	protected jsonIO json;
	protected ToDoList list;
	
	public Action(){
		json = new jsonIO();
		list = json.loadList();
	}
}
