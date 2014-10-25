package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
@WebServlet(urlPatterns = { "/add","/remove","/list"})
public class ToDoServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
//		String field = req.getParameter("field");
//		String querytext = req.getParameter("querytext");
//		req.setAttribute("field", field);
//		req.setAttribute("querytext", querytext);
		
		String opt = req.getServletPath();
		
		if(opt.substring(1).equals("add")){
			RequestDispatcher disp = req.getRequestDispatcher("actiondone.jsp");
			req.setAttribute("action", "add");
			req.setAttribute("task", req.getParameter("task"));
			req.setAttribute("context", req.getParameter("context"));
			req.setAttribute("project", req.getParameter("project"));
			req.setAttribute("priority", req.getParameter("priority"));
			disp.forward(req, resp);
		}
		else if(opt.substring(1).equals("remove")){
			RequestDispatcher disp = req.getRequestDispatcher("actiondone.jsp");
			req.setAttribute("action", "remove");
			req.setAttribute("id", req.getParameter("id"));
			disp.forward(req, resp);
		}
		else if(opt.substring(1).equals("list")){
			RequestDispatcher disp = req.getRequestDispatcher("actiondone.jsp");
			req.setAttribute("action", "list");
			disp.forward(req, resp);
		}
		else{
			RequestDispatcher disp = req.getRequestDispatcher("actiondone.jsp");
			req.setAttribute("action", "None");
			disp.forward(req, resp);
		}
	}

	private void dbg(HttpServletResponse resp) {
		try{
			resp.setContentType("text/html");
			PrintWriter out = resp.getWriter();
			out.println("<html><head><title>Hello World!</title></head>"
					+ "<body><h1>Hello World!</h1></body></html>");
			System.exit(0);
		}
		catch(IOException ex){
			ex.printStackTrace();
		}
		
	}

}
