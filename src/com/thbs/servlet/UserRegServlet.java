package com.thbs.servlet;
import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;

import com.thbs.jdbc_connection.DBConnection;
public class UserRegServlet extends HttpServlet{
	protected void doPost(HttpServletRequest req,HttpServletResponse res) throws IOException,ServletException
	{
		res.setContentType("text/html");
		PrintWriter pw = res.getWriter();
		try {
			Connection con = DBConnection.getCon();
			PreparedStatement ps = con.prepareStatement("insert into register values(?,?,?,?,?,?,?)");
			
			ps.setString(1, req.getParameter("uname"));
			ps.setString(2, req.getParameter("pword"));
			ps.setString(3, req.getParameter("firstname"));
			ps.setString(4, req.getParameter("lastname"));
			ps.setString(5, req.getParameter("address"));
			ps.setLong(6, Long.parseLong(req.getParameter("phoneno")));
			ps.setString(7, req.getParameter("mailid"));
			int k = ps.executeUpdate();
			if(k==1) {
				RequestDispatcher rd = req.getRequestDispatcher("UserLogin.html");
				rd.include(req, res);
				pw.println("<div class='tab'><p1 class='menu'>User Registered Successfully !</p1></div>");
				
			}
			
		}
		catch(Exception e) {}
	}

}
