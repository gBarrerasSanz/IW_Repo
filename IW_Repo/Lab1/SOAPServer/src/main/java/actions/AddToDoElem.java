package actions;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;

import common.ToDoElement;
import common.ToDoList;

public class AddToDoElem extends Action {
	private static BufferedReader stdin;
	private static PrintStream	stdout;
	
	public AddToDoElem(){
		super();
		stdin = new BufferedReader(new InputStreamReader(System.in));
		stdout = System.out;
	}
	/**
	 * 
	 * @param stdin
	 * @param stdout
	 * @return Interactive addition
	 */
	public boolean add(){
		try{
			ToDoElement toDoElem = new ToDoElement();
			stdout.print("Enter Task: ");
			String task = stdin.readLine();
			if (task.length() > 0)	toDoElem.setTask(task);
	
			stdout.print("Enter Context: ");
			String context = stdin.readLine();
			if (context.length() > 0)	toDoElem.setContext(context);
	
			stdout.print("Enter Project: ");
			String project = stdin.readLine();
			if (project.length() > 0)	toDoElem.setProject(project);
			
			stdout.print("Enter Priority: ");
			String priority = stdin.readLine();
			try{
				if (priority.length() > 0)	toDoElem.setPriority(Integer.valueOf(priority));
			}
			catch(Exception e){
				if (priority.length() > 0)	toDoElem.setPriority(1);
			}
			return add(toDoElem);
		}
		catch(IOException ex){
			return false;
		}
	}
	
	/**
	 * 
	 * @param id
	 * @return NON-Interactive addition
	 */
	public boolean add(ToDoElement elem){
		list.addToDoElem(elem);
		return json.saveList(list);
	}
}
