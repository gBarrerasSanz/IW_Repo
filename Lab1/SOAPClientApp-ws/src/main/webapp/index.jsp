<html>
<head>
<title>ToDo WebApp</title>
</head>
<body>
	<h1>ToDo WebApp</h1>
	<hr>
	<h3>Add ToDo</h3>
	<form method="get" action="add">
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
				<td><input type="submit" value="Add ToDo"></td>
			</tr>
		</table>
	</form>
	<hr>
	<h3>Remove ToDo</h3>
	<h5>(Execute List ToDo to see the id's)</h5>
	<form method="get" action="remove">
		<table>
			<tr>
				<td>Id: </td>
				<td><input type="text" name="id"></td>
			</tr>
			<tr>
				<td><input type="submit" value="Delete ToDo"></td>
			</tr>
		</table>
	</form>
	<hr>
	<h3>Delete ToDo</h3>
	<form method="get" action="list">
		<input type="submit" value="List ToDo">
	</form>
</body>
</html>
