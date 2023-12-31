package com.thbs.servlet;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

import com.thbs.jdbc_connection.DBConnection;

import java.sql.*;
@SuppressWarnings("serial")
public class AdminSearchTrain extends HttpServlet{
	protected void doPost(HttpServletRequest req,HttpServletResponse res) throws IOException,ServletException
	{
		res.setContentType("text/html");
		PrintWriter pw = res.getWriter();
		Cookie ck[] = req.getCookies();
		if(ck!=null) {
			String uName = ck[0].getValue();
			String pWord = ck[1].getValue();
			if(!uName.equals("")||uName!=null) {
				try {
					Connection con = DBConnection.getCon();
					PreparedStatement ps = con.prepareStatement("Select * from trains where tr_no=?");
					ps.setLong(1,Long.parseLong(req.getParameter("trainnumber")));
					ResultSet rs = ps.executeQuery();
					if(rs.next()) {
						RequestDispatcher rd = req.getRequestDispatcher("AdminSearchTrain.html");
						rd.include(req, res);
						pw.println("<div class='main'><p1 class='menu'>Searched Train Detail</p1></div>");
						pw.println("<div class='tab'>"
								+ "<table>"
								+ "<tr><td class='blue'>Train Name :</td><td>"+rs.getString("tr_name")+"</td></tr>"
								+ "<tr><td class='blue'>Train Number :</td><td>"+rs.getLong("tr_no")+"</td></tr>"
								+ "<tr><td class='blue'>From Station :</td><td>"+rs.getString("from_Stn")+"</td></tr>"
								+ "<tr><td class='blue'>To Station :</td><td>"+rs.getString("to_Stn")+"</td></tr>"
								+ "<tr><td class='blue'>Available Seats:</td><td>"+rs.getLong("available")+"</td></tr>"
								+ "<tr><td class='blue'>Fare (INR) :</td><td>"+rs.getLong("fare")+" RS</td></tr>"
								+ "</table>"
								+ "</div>");
					}
					else {
						RequestDispatcher rd = req.getRequestDispatcher("AdminSearchTrain.html");
						rd.include(req, res);
						pw.println("<div class='tab'><p1 class='menu'>Train No."+req.getParameter("trainnumber")+" is Not Available !</p1></div>");
					}
				}
				catch(Exception e) {}
				
			}
		}
		else {
			RequestDispatcher rd = req.getRequestDispatcher("AdminLogin.html");
			rd.include(req, res);
			pw.println("<div class='tab'><p1 class='menu'>Please Login first !</p1></div>");
		}
	}

}
