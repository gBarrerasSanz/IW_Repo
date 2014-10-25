package cmd;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;

import actions.AddToDoElem;
import actions.DelToDoElem;
import actions.GetToDoList;


public class ToDoCMD {
	private static BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in));
	private static PrintStream	stdout = System.out;

	public static void main(String[] args) throws Exception {
		if (args.length > 0){	//Java action
			switch(args[0]){
			case "addToDoElem":
				AddToDoElem adder = new AddToDoElem();
				adder.add(stdin, stdout);
				break;
			case "delToDoElem":
				DelToDoElem deleter = new DelToDoElem();
				deleter.del(stdin, stdout);
				break;
			case "showAll":
				GetToDoList getter = new GetToDoList();
				stdout.println(getter.getAll().toString());
				break;
			default:
				break;
			}
		}
		else{ 					//Java prompt
			String opt="";
			opt = showOptions();
			while (opt.length()> 0){
				try{
					switch(opt){
					case "1":	//addToDoElem
						AddToDoElem adder = new AddToDoElem();
						adder.add(stdin, stdout);
						break;
					case "2":	//delToDoElem
						DelToDoElem deleter = new DelToDoElem();
						deleter.del(stdin, stdout);
						break;
					case "3":	//showAll
						GetToDoList getter = new GetToDoList();
						stdout.println(getter.getAll().toString());
						break;
					default:
						break;
					}
					opt = showOptions();
				}
				catch(IOException e){ e.printStackTrace();}
			}
		}
	}
	
	private static String showOptions() throws IOException {
		stdout.println("\nWhat do you wanna do?\n"
				+ "1. Add a ToDo Element\n"
				+ "2. Delete a ToDoElement\n"
				+ "3. Show all the ToDo list\n"
				+ "Press enter to quit");
		return stdin.readLine();
	}
}
