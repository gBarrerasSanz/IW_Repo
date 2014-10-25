<html>
<head>
<title>ToDo List</title>
</head>
<body>
	<h1>ToDo List</h1>
	<form method="get" action="query">
		<table>
			<tr>
				<td>
				<select name=field >
						<option value='task'>task</option>
						<option value='context'>context</option>
						<option value='project'>project</option>
						<option value='priority'>priority</option>
				</select>
				</td>
				<td>
				<input type="text" name="querytext">
				</td>
				<td>
				<input type="submit" value="Query">
				</td>
				</tr>
		</table>
	</form>
	
	<form method="get" action="showall">
		<input type="submit" value="Show All">
	</form>
</body>
</html>
