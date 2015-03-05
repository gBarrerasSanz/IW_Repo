package actions;

import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;

import common.ToDoElement;
import common.ToDoList;

public class DelToDoElem extends Action {
	private static BufferedReader stdin;
	private static PrintStream	stdout;
	
	public DelToDoElem(){
		super();
		stdin = new BufferedReader(new InputStreamReader(System.in));
		stdout = System.out;
	}
	/**
	 * 
	 * @param stdin
	 * @param stdout
	 * @return Interactive deletion
	 */
	public boolean del(){
		ToDoList list = new ToDoList();
		stdout.println(list.toString());
		stdout.println("Type the ID of the ToDo element you want to delete: ");
		int id=-1;
		try{ 
			id = Integer.valueOf(stdin.readLine());
			return del(id);
		}
		catch(Exception e){
			return false;
		}
		
	}
	
	/**
	 * 
	 * @param id
	 * @return NON-Interactive deletion
	 */
	public boolean del(int id){
		boolean found = list.deleteToDoElem(id);
		if(found) 	{ System.out.println("Element "+id+" successfully deleted"); }
		else		{ System.out.println("ToDo Element Not Found"); }
		return found && json.saveList(list);
	}
}
