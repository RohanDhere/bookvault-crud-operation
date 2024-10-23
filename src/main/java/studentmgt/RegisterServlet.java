package studentmgt;

import java.io.IOException;
import java.sql.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


/**
 * Servlet implementation class RegisterServlet
 */
@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegisterServlet() {
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
	    String confirmPassword = request.getParameter("confirm_password");
	    HttpSession session = request.getSession(); 

	    // Check if passwords match
	    if (!password.equals(confirmPassword)) {
	        request.setAttribute("ErrorMessage", "Password does not match with Confirm Password!");
	        request.getRequestDispatcher("register.jsp").forward(request, response);
	        return;
	    }

	    try {
	        // Load the MySQL driver
	        Class.forName("com.mysql.jdbc.Driver");

	        // Establish connection
	        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/studentdata", "root", "root");

	        // Check if PRN already exists
	        String checkQuery = "SELECT prn FROM users WHERE prn = ?";
	        PreparedStatement checkStmt = conn.prepareStatement(checkQuery);
	        checkStmt.setString(1, prn);
	        ResultSet rs = checkStmt.executeQuery();

	        if (rs.next()) {
	            // PRN already registered
	            session.setAttribute("errorMessage", "PRN number already registered!");
	            response.sendRedirect("register.jsp");
	        } else {
	            // Prepare SQL query for inserting new user
	            String query = "INSERT INTO users (prn, password) VALUES (?, ?)";
	            PreparedStatement stmt = conn.prepareStatement(query);
	            stmt.setString(1, prn);
	            stmt.setString(2, password);

	            // Execute update
	            int row = stmt.executeUpdate();

	            if (row > 0) {
	            	session.setAttribute("successMessage", "Register successfully!");
	                response.sendRedirect("login.jsp");
	            } 

	            // Close statements and connection
	            stmt.close();
	        }

	        checkStmt.close();
	        conn.close();
	    } catch (Exception e) {
	        e.printStackTrace();
	        session.setAttribute("errorMessage", "Something went wrong. Please try again later.");
            response.sendRedirect("register.jsp");
	    }
	}
}
