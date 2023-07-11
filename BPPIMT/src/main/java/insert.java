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

public class insert extends HttpServlet {
	Connection con=null;
	PreparedStatement pst=null;
	ResultSet rs=null;
	ServletConfig sc=null;
	
	String sql="INSERT INTO USERS VALUES (?,?)";
	
	public void init(ServletConfig sc) {
		try {
			this.sc=sc;
			String url="jdbc:oracle:thin:@localhost:1521:xe";
			String user="SYSTEM";
			String pass="SYSTEM";
			String driver="oracle.jdbc.driver.OracleDriver";
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
			
				pst=con.prepareStatement(sql);
				pst.setString(1, reggg);
				pst.setString(2, passw);
				pst.executeUpdate();
				System.out.println("updated");
				res.sendRedirect("/BPPIMT/welcome2.html");	
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