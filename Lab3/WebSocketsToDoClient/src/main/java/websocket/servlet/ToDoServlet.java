package websocket.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
@WebServlet(urlPatterns = { "/add","/update","/remove","/list"})
public class ToDoServlet extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		String opt = req.getServletPath();
		RequestDispatcher disp = null;
		switch(opt.substring(1)){
		case "add":
			disp = req.getRequestDispatcher("actiondone.jsp");
			req.setAttribute("operation", "ADD_ELEM");
			req.setAttribute("task", req.getParameter("task"));
			req.setAttribute("context", req.getParameter("context"));
			req.setAttribute("project", req.getParameter("project"));
			req.setAttribute("priority", req.getParameter("priority"));
			disp.forward(req, resp);
			break;
		case "update":
			disp = req.getRequestDispatcher("actiondone.jsp");
			req.setAttribute("operation", "UPDATE_ELEM");
			req.setAttribute("id", req.getParameter("id"));
			req.setAttribute("task", req.getParameter("task"));
			req.setAttribute("context", req.getParameter("context"));
			req.setAttribute("project", req.getParameter("project"));
			req.setAttribute("priority", req.getParameter("priority"));
			disp.forward(req, resp);
			break;
		case "remove":
			disp = req.getRequestDispatcher("actiondone.jsp");
			req.setAttribute("operation", "DEL_ELEM");
			req.setAttribute("id", req.getParameter("id"));
			disp.forward(req, resp);
			break;
		case "list":
			disp = req.getRequestDispatcher("actiondone.jsp");
			req.setAttribute("operation", "GET_LIST");
			disp.forward(req, resp);
			
//			resp.setContentType("text/html");
//			PrintWriter out = resp.getWriter();
//			String result = ClientBuilder.newClient()
//			.target("http://localhost:8082/todo")
//			.request("text/plain").get().readEntity(String.class);
//			
//			out.println("<html><head><title>Hello World!</title></head>"
//					+ "<body><h1>"+ result +"</h1></body></html>");
		default:
			break;
//			RequestDispatcher disp = req.getRequestDispatcher("actiondone.jsp");
//			req.setAttribute("action", "None");
//			disp.forward(req, resp);
			
		}	
	}
	
//	private void dbg(HttpServletResponse resp) {
//		try{
//			resp.setContentType("text/html");
//			PrintWriter out = resp.getWriter();
//			out.println("<html><head><title>Hello World!</title></head>"
//					+ "<body><h1>Hello World!</h1></body></html>");
//		}
//		catch(IOException ex){
//			ex.printStackTrace();
//		}
//		
//	}

}
