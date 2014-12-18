package websocket.server;


import java.io.StringReader;

import javax.json.Json;
import javax.json.JsonObject;
import javax.websocket.Decoder;
import javax.websocket.EndpointConfig;

import com.google.gson.Gson;

import websocket.common.*; 
  
public class MessageDecoder implements Decoder.Text<WSMsg> {
	
	Gson gson = new Gson();
    @Override
    public WSMsg decode(String message) {
//    	WSMsg msg= gson.fromJson(s, WSMsg.class);
    	JsonObject jsonMsg = Json
    	        .createReader(new StringReader(message)).readObject();
    	jsonMsg = jsonMsg.getJsonObject("type");
    	WSMsg msg = new WSMsg();
//    	String[] fields = {"status", "operation", "id", "todoelem"};
//    	for (String field: fields){
//    		try{
//    			
//    		}
//    		catch(ClassCastException e){
//    			
//    		}
//    	}
    	String status = "", operation = "", id = "";
    	JsonObject todoElem = null;
    	ToDoElement elem = null;
    	try{
//    		status = WSMsg.STATUS.valueOf(jsonObject.getJsonString("status").toString());
    		status = getField(jsonMsg, "status");
    		operation = getField(jsonMsg, "operation");
    		id = getField(jsonMsg, "id");
    		try{
    			todoElem = jsonMsg.getJsonObject("status");
    		}
    		catch(Exception e){
    			todoElem = null;
    		}
    		
    		if (todoElem != null){
    			String idElem = "", taskElem = "", contextElem = "", projectElem = "", priorityElem = "";
    			idElem = getField(todoElem, "id");
    			taskElem = getField(todoElem, "task");
    			contextElem = getField(todoElem, "context");
    			projectElem = getField(todoElem, "project");
    			priorityElem = getField(todoElem, "priority");
    			elem = new ToDoElement();
    			elem.setId(Integer.valueOf(idElem));
    			elem.setTask(taskElem);
    			elem.setContext(contextElem);
    			elem.setPriority(Integer.valueOf(priorityElem));
    		}
//    		operation = WSMsg.OPERATION.valueOf(jsonMsg.getJsonString("operation").toString());
//	    	id = Integer.valueOf(jsonMsg.getJsonString("id").toString());
    		msg.setStatus(WSMsg.STATUS.valueOf(status));
    		msg.setOperation(WSMsg.OPERATION.valueOf(operation));
	    	msg.setId(Integer.valueOf(id));
	    	msg.setToDoElem(elem);
//		    ToDoElement elem = gson.fromJson(jsonMsg.getJsonString("todoelem").toString(), ToDoElement.class);
//		    msg.setToDoElem(elem);
    	}
    	catch(Exception e){
    		System.err.println(e.getMessage());
    	}
	    // There is no need to decode a list, since the client will never send a todo list to the server 
	    return msg;
    }
    
    private String getField(JsonObject jo, String key){
    	String value = "";
    	try{
    		value = jo.getJsonString(key).toString();
    	}
    	catch(Exception e){ }
    	return value;
    }
    
    @Override
    public boolean willDecode(String s) {
          
        return (s != null);
    }
 
    @Override
    public void init(EndpointConfig endpointConfig) {
        // do nothing.
    }
 
    @Override
    public void destroy() {
        // do nothing.
    }
}