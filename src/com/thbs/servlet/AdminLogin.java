package com.thbs.servlet;
import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;

import com.thbs.bean_class.UserBean;
import com.thbs.jdbc_connection.DBConnection;
@SuppressWarnings("serial")
public class AdminLogin extends HttpServlet{
	protected void doPost(HttpServletRequest req,HttpServletResponse res) throws IOException,ServletException
	{
		PrintWriter pw = res.getWriter();
		res.setContentType("text/html");
		String uName = req.getParameter("auname");
		String pWord = req.getParameter("apword");
		try {
			Connection con = DBConnection.getCon();
			
			PreparedStatement ps = con.prepareStatement("select * from admins where auname=? and apword=?");
			ps.setString(1, uName);
			ps.setString(2, pWord);
			ResultSet rs = ps.executeQuery();
			if(rs.next())
			{
				UserBean ub = new UserBean();
				ub.setUname(rs.getString("auname"));
				ub.setPword(rs.getString("apword"));
				ub.setFname(rs.getString("afname"));
				ub.setMailid(rs.getString("amailid"));
				ub.setPhno(Long.parseLong(rs.getString("aphno")));
								
				Cookie ck1 = new Cookie("ckname",uName);
				res.addCookie(ck1);
				Cookie ck2 = new Cookie("ckpwd",pWord);
				res.addCookie(ck2);
				
					RequestDispatcher rd = req.getRequestDispatcher("AdminHome.html");				
					rd.include(req, res);
					pw.println("<div class='main'><p1 class='menu'>Hello, "+uName+" ! Welcome </p1></div>");
					pw.println("<div class='tab'>Hi ! Here You can Manage Train Information as per Your Requirement</div>");
					
				}
			else
			{
				RequestDispatcher rd = req.getRequestDispatcher("AdminLogin.html");
				rd.include(req, res);
				pw.println("<div class='tab'><p1 class='menu'>Invalid Username Or Password !</p1></div>");

			}
		}
		catch(Exception e) {}
	}
}
