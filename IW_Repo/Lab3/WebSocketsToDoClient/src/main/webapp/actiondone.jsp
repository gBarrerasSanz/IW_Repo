<html>
<head>
<!-- <script src="js/jquery-2.1.0.min.js"></script> -->
<script src="http://www.google.com/jsapi"></script>
<script>google.load("jquery", "1.3")</script>
<script src="http://jquery-json.googlecode.com/files/jquery.json-2.2.min.js"></script>
<script src="http://jquery-websocket.googlecode.com/files/jquery.websocket-0.0.1.js"></script>
<script src="js/functions.js"></script>
</head>
<script>
	var operation 		= "<%=request.getAttribute("operation")%>";
	var id				= "<%=request.getAttribute("id")%>";
	var taskElem 		= "<%=request.getAttribute("task")%>";
	var contextElem 	= "<%=request.getAttribute("context")%>";
	var projectElem 	= "<%=request.getAttribute("project")%>";
	var priorityElem 	= "<%=request.getAttribute("priority")%>";
	$(document).ready(function(){
		connect();
	});
</script>
<body>
	<h3>
		<label id="lb1">None</label>
	</h3>
	<script>
	//$("#lb1").html("operation: "+operation);
	</script>
</body>
</html>