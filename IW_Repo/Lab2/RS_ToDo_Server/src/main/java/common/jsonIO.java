package common;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import com.google.gson.Gson;

public class jsonIO {

	private String filename = "todo_list.json";
	private Gson gson;
	
	public jsonIO(){
	}
	
	public ToDoList loadList(){
		ToDoList toDoList = new ToDoList();
		gson = new Gson();

		// Read the existing address book.
		try {
			toDoList = gson.fromJson(new FileReader(filename),
					ToDoList.class);
		} catch (FileNotFoundException ex) {
			ex.printStackTrace();
			System.out.println(filename
					+ ": File not found.  Creating a new file.");
		}
		return toDoList;
	}
	
	public boolean saveList(ToDoList list){
		try{
			FileWriter output = new FileWriter(filename);
			output.write(gson.toJson(list));
			output.close();
			return true;
		}
		catch(IOException ex){
			ex.printStackTrace();
			return false;
		}
	}
}
