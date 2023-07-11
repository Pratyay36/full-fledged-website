import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class connect extends HttpServlet {
	String url=null;
	String user=null;
	String pass=null;
	String driver=null;
	Connection con=null;
	PreparedStatement pst=null;
	ResultSet rs=null;
	ServletConfig sc=null;
	
	String sql="SELECT PASSWORD FROM USERS WHERE USERNAME=? AND PASSWORD=?";
	
	public void init(ServletConfig sc) {
		try {
			this.sc=sc;
			url=sc.getInitParameter("url");
			user=sc.getInitParameter("user");
			pass=sc.getInitParameter("pass");
			driver=sc.getInitParameter("driver");
			Class.forName(driver);
			con= DriverManager.getConnection(url,user,pass);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	public void service(HttpServletRequest req,HttpServletResponse res) {
		try {
			String reggg=req.getParameter("username");
			String passw=req.getParameter("password");
			
			if(reggg.length()!=4 || passw.length()==0) {
				res.sendRedirect("/BPPIMT/invalid.html");
			}
			else {
				pst=con.prepareStatement(sql);
				pst.setString(1, reggg);
				pst.setString(2, passw);
				rs=pst.executeQuery();
				
				if(rs.next()) {
					res.sendRedirect("/BPPIMT/welcome.html");
				}
				else {
					res.sendRedirect("/BPPIMT/incorrect.html");
				}
			}	
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void destroy() {
		try {
			con.close();
			pst.close();
			rs.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}