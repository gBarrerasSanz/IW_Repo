var ws;

function processResponse(resp) {
	var msg = JSON.parse(resp.data)
	var status = msg.status;
	var operation = msg.operation;
	//    var id = msg.id;
	//    var todoelem = msg.todoelem;
	//    var todolist = msg.todolist;

	switch (operation) {
	case 'GET_ELEM':
		var status = msg.status;
		var todoelem = msg.todoelem;
		break;
	case 'GET_LIST':
		var status = msg.status;
		var todolist = msg.todolist;
		break;
	case 'ADD_ELEM':
		var status = msg.status;
		break;
	case 'DEL_ELEM':
		var status = msg.status;
		break;
	case 'UPDATE_ELEM':
		var status = msg.status;
		break;
	case 'QUIT':
		break;
	default:
		break;
	}
	document.open();
	if(status == "SUCCESS"){
		document.write("<h2>Success</h2>");
	}
	else{
		document.write("<h2>Error</h2>");
	}
	if (todolist != null){
		for (i = 0; i < todolist.length; i++) { 
			document.write("<h4>ToDo Element ID = "+todolist[i].id+"<h4>"
					+"<ul>"
						+"<li><b>Task:</b> "+todolist[i].task+"</li>"
				 		+"<li><b>Context:</b> "+todolist[i].context+"</li>"
				  		+"<li><b>Project:</b> "+todolist[i].project+"</li>"
				  		+"<li><b>Priority:</b> "+todolist[i].priority+"</li>"
				  	+"</ul>");
		}
	}
	document.close();
}

function createToDoElem(idElem_, task_, context_, project_, priority_){
	var todoelem_ = {
			id: idElem_,
			task: task_,
			context: context_,
			project: project_,
			priority: priority_
	};
	return todoelem;
}

function processOperation(operation_) {
	var todoelem_ = {
			task: taskElem,
			context: contextElem,
			project: projectElem
	}
	//prepare json data
	var msg = {
		status : 'INFO',
		operation : operation_,
		id : id,
		todoelem: todoelem_
	};
	//convert and send data to server
	ws.send('Client', msg);
}

function connect() {
	ws = $.websocket("ws://localhost:8025/websockets/todo");
	ws.onopen = function() {
//		alert("onopen");
		processOperation(operation);
	};
	ws.onclose = function() {
//		alert("onclose");
	};
	ws.onerror = function() {
		alert("onerror");
	};
	ws.onmessage = function(resp) {
//		alert("onmessage");
		processResponse(resp);
	};
}