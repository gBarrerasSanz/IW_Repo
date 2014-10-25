package actions;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintStream;

import common.ToDoElement;
import common.ToDoList;

public class AddToDoElem extends Action {
	
	public AddToDoElem(){
		super();
	}
	
	public boolean add(BufferedReader stdin, PrintStream stdout){
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
			list.addToDoElem(toDoElem);
			return json.saveList(list);
		}
		catch(IOException ex){
			return false;
		}
		
	}
}
