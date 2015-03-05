package websocket.server;

import java.io.IOException;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Logger;

import javax.jws.soap.InitParam;
import javax.websocket.CloseReason;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.CloseReason.CloseCodes;
import javax.websocket.server.ServerEndpoint;
import javax.xml.ws.Action;

import websocket.common.ToDoElement;
import websocket.common.ToDoList;
import websocket.common.WSMsg;
import websocket.common.jsonIO;

import com.google.gson.Gson;

@ServerEndpoint(value = "/todo",
	decoders = {
        MessageDecoder.class,},
    encoders = {
        MessageEncoder.class
    })
public class ToDoServerEndpoint {
	
	private Logger logger = Logger.getLogger(this.getClass().getName());
	public final static String DEFAULT_FILE_NAME = "todo_list.json";
	Gson gson = new Gson();
	
	@OnOpen
	public void onOpen(Session session) {
		logger.info("Connected ... " + session.getId());
	}

	@OnMessage
	public WSMsg onMessage(WSMsg msg, Session session) {
		WSMsg msgResp = new WSMsg();
		WSMsg.OPERATION op = msg.getOperation();
		msgResp.setOperation(op);
		try{
			if (op.equals(WSMsg.OPERATION.QUIT)){
				session.close(new CloseReason(CloseCodes.NORMAL_CLOSURE,"Service ended"));
			}
			else if(op.equals(WSMsg.OPERATION.GET_ELEM)){
				doGetElem(msg, msgResp);
			}
			else if(op.equals(WSMsg.OPERATION.GET_LIST)){
				doGetList(msg, msgResp);
			}
			else if(op.equals(WSMsg.OPERATION.ADD_ELEM)){
				doAddElem(msg, msgResp);
			}
			else if(op.equals(WSMsg.OPERATION.DEL_ELEM)){
				doDelElem(msg, msgResp);
			}
			else if(op.equals(WSMsg.OPERATION.UPDATE_ELEM)){
				doUpdateElem(msg, msgResp);
			}
			else{
				logger.info(String.format("Received unknown msg: "+msg));
			}
	} catch (IOException e) {
		throw new RuntimeException(e);
	}
		logger.info(String.format("Received: "+msg.toString()));
		return msgResp;
	}

	@OnClose
	public void onClose(Session session, CloseReason closeReason) {
		logger.info(String.format("Session %s closed because of %s",
				session.getId(), closeReason));
	}
	
	private void doGetElem (WSMsg msg, WSMsg msgResp) {
		try{
			msgResp.setToDoElem(WebSocketServer.tdlist.getElem(msg.getId()));
			msgResp.setStatus(WSMsg.STATUS.SUCCESS);
		}
		catch (NoSuchElementException e){ 
			msgResp.setStatus(WSMsg.STATUS.NOTFOUND);
		}
	}
	
	private void doGetList (WSMsg msg, WSMsg msgResp) {
		ToDoList list = WebSocketServer.tdlist.getToDoList();
		if (list.size() > 0){
			msgResp.setToDoList(list);
			msgResp.setStatus(WSMsg.STATUS.SUCCESS);
		}
		else{
			msgResp.setStatus(WSMsg.STATUS.NOTFOUND);
		}
	}
	
	private void doAddElem (WSMsg msg, WSMsg msgResp) {
		ToDoElement elem = msg.getToDoElem();
		WebSocketServer.tdlist.addToDoElem(elem);
		msgResp.setStatus(WSMsg.STATUS.SUCCESS);
	}
	
	private void doDelElem (WSMsg msg, WSMsg msgResp) {
		try{
			if (WebSocketServer.tdlist.deleteToDoElem(msg.getId())){
				msgResp.setStatus(WSMsg.STATUS.SUCCESS);
			}
			else{
				msgResp.setStatus(WSMsg.STATUS.NOTFOUND);
			}
		}
		catch (NoSuchElementException e){ 
			msgResp.setStatus(WSMsg.STATUS.NOTFOUND);
		}
	}
	
	private void doUpdateElem (WSMsg msg, WSMsg msgResp){
		ToDoElement elem = null, newElem = null;
		try{
			elem = WebSocketServer.tdlist.getElem(msg.getId());
			newElem = msg.getToDoElem();
			elem.setTask(newElem.getTask());
			elem.setContext(newElem.getContext());
			elem.setProject(newElem.getProject());
			elem.setPriority(newElem.getPriority());
			msgResp.setStatus(WSMsg.STATUS.SUCCESS);
		}
		catch (NoSuchElementException e){ 
			msgResp.setStatus(WSMsg.STATUS.NOTFOUND);
		}
		catch (Exception e){
			msgResp.setStatus(WSMsg.STATUS.NOTFOUND);
		}	
	}
	
}
