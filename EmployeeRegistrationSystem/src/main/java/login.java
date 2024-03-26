import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import com.mysql.cj.xdevapi.JsonArray;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/login")
public class login extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		PrintWriter printWriter = resp.getWriter();
		String email = req.getParameter("email");
		String passowrd = req.getParameter("password");

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/registerservletjsp",
					"root", "password");

			String queryString = "SELECT * FROM register where email= ? and passoword = ?";
			PreparedStatement preparedStatement = connection.prepareStatement(queryString);
			preparedStatement.setString(1, email);
			preparedStatement.setString(2, passowrd);

			ResultSet resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				if (resultSet.getString("role").equals("admin")) {

					HttpSession httpSession = req.getSession();
					httpSession.setAttribute("name", resultSet.getString("name"));
					httpSession.setAttribute("email", resultSet.getString("email"));
					httpSession.setAttribute("gender", resultSet.getString("gender"));
					httpSession.setAttribute("city", resultSet.getString("city"));
					resp.sendRedirect("admin.jsp");
				} else {
					HttpSession httpSession = req.getSession();
					httpSession.setAttribute("name", resultSet.getString("name"));
					httpSession.setAttribute("email", resultSet.getString("email"));
					httpSession.setAttribute("gender", resultSet.getString("gender"));
					httpSession.setAttribute("city", resultSet.getString("city"));
					resp.sendRedirect("profile.jsp");
				}
			} else {
				resp.setContentType("text/html");
				printWriter.print("<h3 style=color:red;text-algin:center>Email and Password not matched </h3>");
				RequestDispatcher requestDispatcher = req.getRequestDispatcher("index.jsp");
				requestDispatcher.include(req, resp);
			}

		} catch (Exception e) {
			resp.setContentType("text/html");
			printWriter.print("<h3 style=`color:red'>" + e.getMessage() + "</h3>");
			RequestDispatcher requestDispatcher = req.getRequestDispatcher("index.jsp");
			requestDispatcher.include(req, resp);
		}

	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		PrintWriter printWriter = resp.getWriter();
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/registerservletjsp",
					"root", "password");

			String queryString = "SELECT * FROM register where role != 'admin'";
			PreparedStatement preparedStatement = connection.prepareStatement(queryString);
			ResultSet resultSet = preparedStatement.executeQuery();
			JSONArray emArray = new JSONArray();
			while (resultSet.next()) {
				JSONObject empObject = new JSONObject();
				String name = resultSet.getString("name");
				String email = resultSet.getString("email");
				String city = resultSet.getString("city");
				String gender = resultSet.getString("gender");
				empObject.put("name", name);
				empObject.put("email", email);
				empObject.put("city", city);
				empObject.put("gender", gender);
				emArray.put(empObject);
			}
			JSONObject message = new JSONObject();
			/* message.put("message", emArray); */
			PrintWriter out = resp.getWriter();
			out.print(emArray.toString());

		} catch (Exception e) {

		}

	}
}
