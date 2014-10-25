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

import common.ToDoElement;
import common.ToDoList;

@SuppressWarnings("serial")
//@WebServlet(urlPatterns = { "/add","/remove", "/list" })
public class ToDoServlet_test extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		String field = req.getParameter("field");
		String querytext = req.getParameter("querytext");
		
		req.setAttribute("field", field);
		req.setAttribute("querytext", querytext);
		
		resp.setContentType("text/html");
		RequestDispatcher disp = req.getRequestDispatcher("actiondone.jsp");
		req.setAttribute("action", "None");
		disp.forward(req, resp);
	}

}
