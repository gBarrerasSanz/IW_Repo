<html>
<head>
<title>Action Done</title>
<%@ page import="common.*" %>
</head>
<body>
	<%	String field = (String)request.getAttribute("action"); %>
	Action = <%=field%>
</body>
</html>