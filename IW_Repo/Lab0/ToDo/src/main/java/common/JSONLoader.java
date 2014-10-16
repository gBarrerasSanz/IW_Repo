package common;

import java.io.FileNotFoundException;
import java.io.FileReader;

import com.google.gson.Gson;

public class JSONLoader {

	private String filename;
	
	public JSONLoader(String filename){
		this.filename = filename;
	}
	
	public ToDoList load(){
		ToDoList toDoList = new ToDoList();
		Gson gson = new Gson();

		// Read the existing address book.
		try {
			toDoList = gson.fromJson(new FileReader(filename),
					ToDoList.class);
		} catch (FileNotFoundException e) {
			System.out.println(filename
					+ ": File not found.  Creating a new file.");
		}
		return toDoList;
	}
}
