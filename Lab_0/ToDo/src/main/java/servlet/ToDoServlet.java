package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.JSONLoader;
import common.ToDoElement;
import common.ToDoList;

@SuppressWarnings("serial")
@WebServlet(urlPatterns = { "/query","/showall" })
public class ToDoServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		String filename = "todo_list.json";
		//String thisIsThreadSafe = req.getParameter("name");
		resp.setContentType("text/html");
		PrintWriter out = resp.getWriter();
		
		String opt = req.getServletPath();
		if(opt.substring(1).equals("query")){
			out.println("<html>"
					+ "<head><title></title></head>"
					+ "<body>"
					+ "opt = " + "queryyyyy"
					+ "</body>"
					+"</html");
		}
		if(opt.substring(1).equals("showall")){
			JSONLoader loader = new JSONLoader(filename);
			ToDoList toDoList= loader.load();
			int i=0;
			out.println("<html>"
					+ "<head><title></title></head>"
					+ "<body>");
			for (ToDoElement toDoElem : toDoList.getToDoList()) {
				out.println("ToDo Element ID = "+i);
				out.println("\tTask: " + toDoElem.getTask());
				out.println("\tContext: " + toDoElem.getContext());
				out.println("\tProject: " + toDoElem.getProject());
				out.println("\tPriority: " + toDoElem.getPriority());
				i++;
			}
			out.println("</body></html");
		}
		/*
		out.println("<html><head><title>Hello "
				+ "!</title></head>" + "<body><h1>Hello "
				+ " ("
				+ " times)!</h1></body></html>");
		*/
	}

}
