<html>
<head>
<title>ToDo List</title>
</head>
<body>
	<h1>ToDo List</h1>
	<form method="get" action="query">
		<table>
			<tr>
				<td>Task: </td>
				<td><input type="text" name="task"></td>
			</tr>
			<tr>
				<td>Context: </td>
				<td><input type="text" name="context"></td>
			</tr>
			<tr>
				<td>Project: </td>
				<td><input type="text" name="project"></td>
			</tr>
			<tr>
				<td>Priority: </td>
				<td><input type="text" name="priority"></td>
			</tr>
			<tr>
				<td><input type="submit" value="Query"></td>
			</tr>
		</table>
	</form>
	
	<form method="get" action="list ToDo">
		<input type="submit" value="Show All">
	</form>
</body>
</html>
