package cmd;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;

import com.google.gson.Gson;
import common.ToDoElement;
import common.ToDoList;

public class ToDoCMD {

	public final static String DEFAULT_FILE_NAME = "todo_list.json";

	// This function fills in a ToDoElement message based on user input.
	static ToDoElement PromptForElem(BufferedReader stdin, PrintStream stdout)
			throws IOException {
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
			if (priority.length() > 0)	toDoElem.setPriority(0);
		}
		return toDoElem;
	}

	// Main function: Reads the entire address book from a file,
	// adds one toDoElem based on user input, then writes it back out to the same
	// file.
	public static void main(String[] args) throws Exception {
		String filename = DEFAULT_FILE_NAME;
		PrintStream	stdout = System.out;
		BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in));
		
		if (args.length > 0) {
			filename = args[0];
		}

		ToDoList toDoList = new ToDoList();
		Gson gson = new Gson();

		// Read the existing address book.
		try {
			toDoList = gson.fromJson(new FileReader(filename),
					ToDoList.class);
		} catch (FileNotFoundException e) {
			stdout.println(filename
					+ ": File not found.  Creating a new file.");
		}
		
		
		String opt="";
		opt = showOptions(stdin, stdout);
		while (opt.length()> 0){
			try{
				switch(opt){
				case "1":
					addToDoElement(toDoList, stdin, stdout, filename,gson);
					break;
				case "2":
					delToDoElement(toDoList, stdin, stdout, filename,gson);
					break;
				case "3":
					showToDoList(toDoList, stdin, stdout);
					break;
				default:
					break;
				}
				opt = showOptions(stdin, stdout);
			}
			catch(IOException e){ e.printStackTrace();}
			
		}
		
	}
	static void showToDoList(ToDoList toDoList,BufferedReader stdin, PrintStream stdout) {
		int i=0;
		if(toDoList.size() > 0){
			for (ToDoElement toDoElem : toDoList.getToDoList()) {
				stdout.println("ToDo Element ID = "+i);
				stdout.println("\tTask: " + toDoElem.getTask());
				stdout.println("\tContext: " + toDoElem.getContext());
				stdout.println("\tProject: " + toDoElem.getProject());
				stdout.println("\tPriority: " + toDoElem.getPriority());
				i++;
			}
		}
		else{ stdout.println("<Empty list>");}
	}
	
	private static void addToDoElement(ToDoList toDoList, BufferedReader stdin, 
			PrintStream stdout, String filename, Gson gson) throws IOException {
		toDoList.addToDoElem(PromptForElem(stdin, stdout));
		FileWriter output = new FileWriter(filename);
		output.write(gson.toJson(toDoList));
		output.close();
	}
	
	private static void delToDoElement(ToDoList toDoList, BufferedReader stdin,
			PrintStream stdout, String filename, Gson gson) throws IOException{
		showToDoList(toDoList, stdin, stdout);
		stdout.println("Type the ID of the ToDo element you want to delete: ");
		int id=-1; boolean found = false;
		try{ 
			id = Integer.valueOf(stdin.readLine());
			found = toDoList.deleteToDoElem(id);
		}
		catch(Exception e){ 
		}
		finally{
			if(!found){ stdout.println("ToDo Element Not Found"); }
		}
		FileWriter output = new FileWriter(filename);
		output.write(gson.toJson(toDoList));
		output.close();
		
	}


	
	private static String showOptions(BufferedReader stdin, PrintStream stdout) throws IOException {
		stdout.println("\nWhat do you wanna do?\n"
				+ "1. Add a ToDo Element\n"
				+ "2. Delete a ToDoElement\n"
				+ "3. Show all the ToDo list\n"
				+ "Press enter to quit");
		return stdin.readLine();
	}
}
