var ws;

function processResponse() {
	var msg = JSON.parse(resp.data)
	var status = msg.status;
	var operation = msg.operation;
	//    var id = msg.id;
	//    var todoelem = msg.todoelem;
	//    var todolist = msg.todolist;

	switch (operation) {
	case 'GET_ELEM':
		var todoelem = msg.todoelem;
		break;
	case 'GET_LIST':
		var todolist = msg.todolist;
		break;
	case 'ADD_ELEM':
		break;
	case 'DEL_ELEM':
		break;
	case 'UPDATE_ELEM':
		break;
	case 'QUIT':
		break;
	default:
		break;
	}
}

function processOperation(operation_, id_, task_, context_, project_, priority_) {
	//prepare json data
	var msg = {
		status : 'INFO',
		operation : operation_,
		id : id_,
		task : task_,
		context : context_,
		project : project_,
		priority : priority_
	};
	//convert and send data to server
	ws.send(JSON.stringify(msg));
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