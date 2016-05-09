import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SimpleLoginServlet extends HttpServlet{

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("In Servlet");
		String userName = request.getParameter("login");
				System.out.println("User"+userName);
		String passWord = request.getParameter("password");
				System.out.println("Pass"+passWord);
		RequestDispatcher rd = null;
		try
		{
		java.sql.DriverManager.registerDriver((java.sql.Driver) Class
				.forName("com.mysql.jdbc.Driver").newInstance());
		Connection conn = java.sql.DriverManager.getConnection("jdbc:mysql://us-cdbr-iron-east-03.cleardb.net/ad_022b84756d28e66", "b3e8def3d92eb9",
				"7b4fff45");
		Statement st=conn.createStatement();
		ResultSet rs = st.executeQuery("select * from employee where name = '"+userName+"'");
		if(rs.next())
		{
			System.out.println("Inside If");
			rd = request.getRequestDispatcher("success.jsp");
			rd.forward(request,response);
		}
		else
		{
			System.out.println("Inside Else");
			rd = request.getRequestDispatcher("failed.jsp");
			rd.forward(request,response);
		}
		}catch(Exception e){}
	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		{
		doPost(request, response);
		}
	}
}
