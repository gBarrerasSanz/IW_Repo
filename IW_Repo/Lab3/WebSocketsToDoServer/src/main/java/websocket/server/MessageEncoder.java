package websocket.server;

import java.io.StringWriter;
import javax.json.*;
import javax.websocket.EncodeException;
import javax.websocket.Encoder;
import javax.websocket.EndpointConfig;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;


import websocket.common.*;
  
public class MessageEncoder implements Encoder.Text<WSMsg> {
 
    @Override
    public String encode(WSMsg message) throws EncodeException {
        JsonObject jsonObject = Json.createObjectBuilder()
                .add("status", message.getStatus().toString())
                .add("operation", message.getOperation().toString())
                .add("id", message.getId())
                .add("todoelem", message.getToDoElem().toString())
                .add("todolist", message.getToDoList().toString())
        .build();
        return jsonObject.toString();
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
