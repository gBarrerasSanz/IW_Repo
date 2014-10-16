# Hello thread safe!
This project contains a simple demonstration of __thread safety__ concerns in servlets. 

Deploy your code to a [Jetty](http://www.eclipse.org/jetty/) server with ```gradle jettyEclipseRun```. Then, open the static HTML document served at [http://localhost:8080/](http://localhost:8080/) which sends requests to the servlet at [http://localhost:8080/hi](http://localhost:8080/hi).

Look the code:
```java
@WebServlet(urlPatterns = { "/hi" })
public class HelloThreadSafe extends HttpServlet {
	private AtomicInteger thisIsNotThreadSafe = new AtomicInteger();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String thisIsThreadSafe = req.getParameter("name");
		resp.setContentType("text/html");
		PrintWriter out = resp.getWriter();
		out.println("<html><head><title>Hello " + thisIsThreadSafe + "!</title></head>"
				+ "<body><h1>Hello " + thisIsThreadSafe + " (" + thisIsNotThreadSafe.incrementAndGet()
				+ " times)!</h1></body></html>");
	}
}

```
