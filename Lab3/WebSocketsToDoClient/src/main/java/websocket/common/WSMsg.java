package websocket.common;

import java.util.List;
import java.util.NoSuchElementException;

public class WSMsg {
	public enum STATUS {
//		100,	200,	400,		500
		INFO, SUCCESS, NOTFOUND, SERVERROR
	}
	public enum OPERATION {
		QUIT, GET_ELEM, GET_LIST, 
		ADD_ELEM, DEL_ELEM, UPDATE_ELEM
	}
	STATUS status = null;
	OPERATION operation = null;
	int id = -1;
	ToDoElement toDoElem = null;
	List<ToDoElement> toDoList = null;
	
	public WSMsg(){
	}
	
	public STATUS getStatus() throws NoSuchElementException {
		if (status.equals(null)){
			throw new NoSuchElementException();
		}
		else{
			return status;
		}
	}

	public void setStatus(STATUS status) {
		this.status = status;
	}
	
	public OPERATION getOperation() throws NoSuchElementException {
		if (operation.equals(null)){
			throw new NoSuchElementException();
		}
		else{
			return operation;
		}
	}

	public void setOperation(OPERATION operation) {
		this.operation = operation;
	}

	public int getId() throws NoSuchElementException{
		if (operation.equals(-1)){
			throw new NoSuchElementException();
		}
		else{
			return id;
		}
	}

	public void setId(int id) {
		this.id = id;
	}

	public ToDoElement getToDoElem() throws NoSuchElementException{
		if (operation.equals(null)){
			throw new NoSuchElementException();
		}
		else{
			return toDoElem;
		}
	}

	public void setToDoElem(ToDoElement toDoElem) {
		this.toDoElem = toDoElem;
	}

	public List<ToDoElement> getToDoList() throws NoSuchElementException{
		if (operation.equals(null)){
			throw new NoSuchElementException();
		}
		else{
			return toDoList;
		}
	}

	public void setToDoList(List<ToDoElement> toDoList) {
		this.toDoList = toDoList;
	}
}
