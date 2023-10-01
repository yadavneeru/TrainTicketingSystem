package com.thbs.servlet;
import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;

import com.thbs.bean_class.UserBean;
import com.thbs.jdbc_connection.DBConnection;
@SuppressWarnings("serial")
public class UserLoginServlet extends HttpServlet{
	protected void doPost(HttpServletRequest req,HttpServletResponse res) throws IOException,ServletException
	{
		PrintWriter pw = res.getWriter();
		res.setContentType("text/html");
		String uName = req.getParameter("uname");
		String pWord = req.getParameter("pword");
		try {
			Connection con = DBConnection.getCon();
			
			PreparedStatement ps = con.prepareStatement("select * from register where uname=? and pword=?");
			
			ps.setString(1, uName);
			ps.setString(2, pWord);
			ResultSet rs = ps.executeQuery();
			if(rs.next())
			{
				UserBean ub = new UserBean();
				ub.setUname(rs.getString("uname"));
				ub.setPword(rs.getString("pword"));
				ub.setFname(rs.getString("fname"));
				ub.setAge(rs.getString("age"));
				ub.setGender(rs.getString("gender"));
				ub.setPhno(Long.parseLong(rs.getString("phno")));
				ub.setMailid(rs.getString("mailid"));
			
				Cookie ck1 = new Cookie("ckname",uName);
				res.addCookie(ck1);
				Cookie ck2 = new Cookie("ckpwd",pWord);
				res.addCookie(ck2);
				ServletContext sct = req.getServletContext();
				sct.setAttribute("ubean", ub);
					RequestDispatcher rd = req.getRequestDispatcher("UserHome.html");				
					rd.include(req, res);
					pw.println("<div class='main'><p1 class='menu'>Hello "+uName+" ! Welcome to our new NITRTC Website</p1></div>");
					pw.println("<div class='tab'>Hello "+uName+" ! Good to See You here.<br/> Here you can Check up the train "
							+ "details and train schedule,fare Enquiry and many more information.<br/>Just go to the Side Menu Links and "
							+ "Explore the Advantages.<br/><br/>Thanks For Being Connected with us!"
							+ "</div>");
					
					PreparedStatement ps2 = preparedStatement("select * from userphoto where uname=?");
					ps2.setString(1, uName);
					ResultSet rs2 = ps2.executeQuery();
					if(rs2.next())
					{
						//ub.setPhoto(rs2.getBlob(2));
					}   
			    
				}
			else
			{
				RequestDispatcher rd = req.getRequestDispatcher("UserLogin.html");
				rd.include(req, res);
				
				pw.println("<div class='tab'><p1 class='menu'>Invalid Username Or Password !</p1></div>");

			}
	    
		}
		catch(Exception e) {}
	}

	private PreparedStatement preparedStatement(String string) {
		return null;
	}
}
