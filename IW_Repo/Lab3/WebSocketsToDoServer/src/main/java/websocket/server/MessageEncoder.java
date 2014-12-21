package websocket.server;

import java.io.StringWriter;

import javax.websocket.EncodeException;
import javax.websocket.Encoder;
import javax.websocket.EndpointConfig;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;

import org.json.simple.JSONObject;
//import javax.json.*;


import com.google.gson.Gson;

import websocket.common.*;
  
public class MessageEncoder implements Encoder.Text<WSMsg> {
 
    @Override
    public String encode(WSMsg message) throws EncodeException {
//        JsonObject jsonObject = Json.createObjectBuilder()
//                .add("status", message.getStatus().toString())
//                .add("operation", message.getOperation().toString())
//                .add("id", message.getId())
//                .add("todoelem", message.getToDoElem().toString())
//                .add("todolist", message.getToDoList().toString())
//        .build();
    	JSONObject  jo = new JSONObject();
    	try{ jo.put("status", message.getStatus().toString()); } catch(Exception ex){}
    	try{ jo.put("operation", message.getOperation().toString()); } catch(Exception ex){}
    	try{ jo.put("id", message.getId()); } catch(Exception ex){}
    	try{ jo.put("todoelem", message.getToDoElem().toJson()); } catch(Exception ex){}
    	try{ jo.put("todolist", message.getToDoList().toJson()); } catch(Exception ex){}
        return jo.toString();
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
