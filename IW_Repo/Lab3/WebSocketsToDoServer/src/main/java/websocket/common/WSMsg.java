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
	ToDoList toDoList = null;
	
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
	
	public void setStatus (String status_){
		switch(status_){
		case "INFO":
			this.status = STATUS.INFO;
			break;
		case "SUCCESS":
			this.status = STATUS.SUCCESS;
			break;
		case "NOTFOUND":
			this.status = STATUS.NOTFOUND;
			break;
		case "SERVERROR":
			this.status = STATUS.SERVERROR;
			break;
		default:
			break;
		}
	}
	
	public OPERATION getOperation() throws NoSuchElementException {
		if (operation.equals(null)){
			throw new NoSuchElementException();
		}
		else{
			return operation;
		}
	}
	
	public void setOperation (String operation_){
		switch(operation_.toString()){
		case "QUIT":
			this.operation = OPERATION.QUIT;
			break;
		case "GET_ELEM":
			this.operation = OPERATION.GET_ELEM;
			break;
		case "GET_LIST":
			this.operation = OPERATION.GET_LIST;
			break;
		case "ADD_ELEM":
			this.operation = OPERATION.ADD_ELEM;
			break;
		case "DEL_ELEM":
			this.operation = OPERATION.DEL_ELEM;
			break;
		case "UPDATE_ELEM":
			this.operation = OPERATION.UPDATE_ELEM;
			break;
		default:
			break;
		}
	}
	
	public void setOperation(OPERATION operation) {
		this.operation = operation;
	}

	public int getId() {
		if (operation.equals(-1)){
			return -1;
		}
		else{
			return id;
		}
	}

	public void setId(int id) {
		this.id = id;
	}

	public ToDoElement getToDoElem() {
		if (operation.equals(null)){
			return null;
		}
		else{
			return toDoElem;
		}
	}

	public void setToDoElem(ToDoElement toDoElem) {
		this.toDoElem = toDoElem;
	}

	public ToDoList getToDoList() {
		if (operation.equals(null)){
			return null;
		}
		else{
			return toDoList;
		}
	}

	public void setToDoList(ToDoList toDoList) {
		this.toDoList = toDoList;
	}
	
	@Override
	public String toString(){
		 return "Status: '" + this.status + "', operation: '" + this.operation +"\n"+
				 "', id: '" + this.id + "'" + "', todoelem: '" + this.toDoElem + "'"
				 + "', todoelem: '" + this.toDoList + "'";
	}
}
