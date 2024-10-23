package studentmgt;

import java.io.IOException;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.sql.*;


/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		response.sendRedirect("login.jsp");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	 protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	        String prn = request.getParameter("prn");
	        String password = request.getParameter("password");
	    
	        HttpSession session = request.getSession();

	        try {
	            Class.forName("com.mysql.cj.jdbc.Driver");
	            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/studentdata", "root", "root");

	            String query = "SELECT * FROM users WHERE prn = ? AND password = ?";
	            PreparedStatement stmt = conn.prepareStatement(query);
	            stmt.setString(1, prn);
	            stmt.setString(2, password);

	            ResultSet rs = stmt.executeQuery();

	            if (rs.next()) {
	            	
	                session.setAttribute("prn", prn); // Store the PRN in session
	            
	                session.setAttribute("successMessage", "Login successfully!");
	                response.sendRedirect("dashboard.jsp");
	             
	            } else {
	                session.setAttribute("errorMessage", "Invalid PRN or password!");
	                response.sendRedirect("login.jsp");
	               
	            }
	        } catch (Exception e) {
	            e.printStackTrace();
	            session.setAttribute("errorMessage", "Something went wrong. Please try again later.");
	            response.sendRedirect("login.jsp");
	        } 
//	        
	    }
}
