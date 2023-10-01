package com.thbs.servlet;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

import com.thbs.bean_class.UserBean;
@SuppressWarnings("serial")
public class ViewUserProfile extends HttpServlet{
	protected void doGet(HttpServletRequest req,HttpServletResponse res) throws IOException,ServletException
	{
		res.setContentType("text/html");
		PrintWriter pw = res.getWriter();
		Cookie ck[] = req.getCookies();
		if(ck!=null) {
			String uName = ck[0].getValue();
			String pWord = ck[1].getValue();
			if(!uName.equals("")||uName!=null) {
				
					ServletContext sct = req.getServletContext();
					UserBean ub = (UserBean)sct.getAttribute("ubean");
						RequestDispatcher rd = req.getRequestDispatcher("UserHome.html");
						rd.include(req, res);
						pw.println("<div class='tab'>" + 
								"		<p1 class='menu'>" + 
								"	Hello "+uName+" ! Welcome to our new NITRTC Website" + 
								"		</p1>" + 
								"	</div>");
						pw.println("<div class='main'><p1 class='menu'><a href='viewuserprofile'>view Profile</a></p1>"
								+ "<p1 class='menu'><a href='edituserprofile'>Edit Profile</a></p1>"
								+ "<p1 class='menu'><a href='changeuserpassword'>Change Password</a></p1>"
								+ "</div>");
						pw.println("<div class='tab'>Users Profile View</div>");
						pw.println("<div class='tab'>"
								+ "<table>"
								+ "<tr><td>User Name :</td><td>"+ub.getUname()+"</td></tr>"
								+ "<tr><td>Password :</td><td>"+ub.getPword()+"</td></tr>"
								+ "<tr><td>First Name :</td><td>"+ub.getFname()+"</td></tr>"
								+ "<tr><td>Last Name :</td><td>"+ub.getAge()+"</td></tr>"
								+ "<tr><td>Address :</td><td>"+ub.getGender()+"</td></tr>"
								+ "<tr><td>Phone No:</td><td>"+ub.getPhno()+"</td></tr>"
								+ "<tr><td>Mail Id :</td><td>"+ub.getMailid()+"</td></tr>"
								+ "</table>"
								+ "</div>");
				
				
			}
		}
		else {
			RequestDispatcher rd = req.getRequestDispatcher("UserLogin.html");
			rd.include(req, res);
			pw.println("<div class='tab'><p1 class='menu'>Please Login first !</p1></div>");
		}
	}

}
