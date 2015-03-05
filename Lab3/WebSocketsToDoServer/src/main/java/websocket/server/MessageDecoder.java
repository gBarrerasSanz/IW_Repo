package websocket.server;


import java.io.StringReader;

import javax.json.Json;
import javax.json.JsonObject;
import javax.websocket.Decoder;
import javax.websocket.EndpointConfig;

import com.google.gson.Gson;

import websocket.common.*; 
  
public class MessageDecoder implements Decoder.Text<WSMsg> {
	
//	Gson gson = new Gson();
    @Override
    public WSMsg decode(String message) {
//    	WSMsg msg= gson.fromJson(s, WSMsg.class);
    	JsonObject jsonMsg = Json
    	        .createReader(new StringReader(message)).readObject().getJsonObject("data");
    	WSMsg msg = new WSMsg();
    	String status = "", operation = "", id = "";
    	JsonObject todoElem = null;
    	ToDoElement elem = null;
    	try{
//    		status = WSMsg.STATUS.valueOf(jsonObject.getJsonString("status").toString());
    		status = getField(jsonMsg, "status");
    		operation = getField(jsonMsg, "operation");
    		id = getField(jsonMsg, "id");
    		try{
    			todoElem = jsonMsg.getJsonObject("todoelem");
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
    			try { elem.setTask(taskElem); } catch(Exception ex){}
    			try { elem.setContext(contextElem); } catch(Exception ex){}
    			try { elem.setProject(projectElem); } catch(Exception ex){}
    			try { elem.setPriority(Integer.valueOf(priorityElem)); } catch(Exception ex){}
    		}
//    		operation = WSMsg.OPERATION.valueOf(jsonMsg.getJsonString("operation").toString());
//	    	id = Integer.valueOf(jsonMsg.getJsonString("id").toString());
    		try { msg.setStatus(WSMsg.STATUS.valueOf(status)); } catch(Exception ex){}
    		try { msg.setOperation(WSMsg.OPERATION.valueOf(operation)); } catch(Exception ex){}
    		try { msg.setId(Integer.valueOf(id)); } catch(Exception ex){}
    		try { msg.setToDoElem(elem); } catch(Exception ex){}
//    		msg.setStatus(status);
//    		msg.setOperation(operation);
//	    	msg.setId(Integer.valueOf(id));
//	    	msg.setToDoElem(elem);
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
    		value = jo.getString(key);
    		//value.substring(1, value.length()-1);
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