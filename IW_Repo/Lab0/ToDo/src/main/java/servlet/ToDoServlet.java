package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import common.jsonIO;
import common.ToDoElement;
import common.ToDoList;

@SuppressWarnings("serial")
@WebServlet(urlPatterns = { "/query","/showall" })
public class ToDoServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		String field = req.getParameter("field");
		String querytext = req.getParameter("querytext");
		
		req.setAttribute("field", field);
		req.setAttribute("querytext", querytext);
		
		resp.setContentType("text/html");
		String opt = req.getServletPath();
		if(opt.substring(1).equals("query")){
			RequestDispatcher disp = req.getRequestDispatcher("query.jsp");
			disp.forward(req, resp);
		}
		if(opt.substring(1).equals("showall")){
			RequestDispatcher disp = req.getRequestDispatcher("showAll.jsp");
			disp.forward(req, resp);
		}
	}

}
