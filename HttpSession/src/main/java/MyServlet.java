import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/MyServlet")
public class MyServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String name = request.getParameter("name");
		String password = request.getParameter("password");
		PrintWriter outPrintWriter = response.getWriter();
		System.out.println(name);
//		if correct then to profile jsp
		if (name.equals("admin") && password.equals("admin")) {
			HttpSession session = request.getSession();
			session.setAttribute("name", name);
			session.setAttribute("password", password);
			RequestDispatcher rDispatcher = request.getRequestDispatcher("/profile.jsp");
			rDispatcher.forward(request, response);
		} else {
			response.setContentType("text/html"); // yo rakhna vane yesle error dekhauxha with source code
			outPrintWriter.print("<h3 style ='color:red'>Email id and password didnt match</h3>");
			RequestDispatcher rDispatcher = request.getRequestDispatcher("/index.html");
			rDispatcher.include(request, response); // include will attach print and html page
		}
	}
}
