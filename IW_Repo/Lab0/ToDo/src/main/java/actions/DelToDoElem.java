package actions;

import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintStream;

import common.ToDoElement;
import common.ToDoList;

public class DelToDoElem extends Action {
	
	public DelToDoElem(){
		super();
	}
	
	public boolean del(BufferedReader stdin, PrintStream stdout){
		GetToDoList getter = new GetToDoList();
		stdout.println(getter.getAll().toString());
		stdout.println("Type the ID of the ToDo element you want to delete: ");
		int id=-1; boolean found = false;
		try{ 
			id = Integer.valueOf(stdin.readLine());
			found = list.deleteToDoElem(id);
		}
		catch(Exception e){ 
		}
		finally{
			if(found) 	{ stdout.println("Element "+id+" successfully deleted"); }
			else		{ stdout.println("ToDo Element Not Found"); }
		}
		return found && json.saveList(list);
	}
}
